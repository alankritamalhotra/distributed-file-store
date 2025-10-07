import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class HashUtils {
    
    /**
     * Computes a consistent hash value for the given input string using MD5.
     * Returns a positive integer hash value.
     * 
     * @param input The string to hash
     * @return A positive integer hash value
     */
    public static int hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            
            // Convert first 4 bytes to integer
            int hash = 0;
            for (int i = 0; i < 4; i++) {
                hash = (hash << 8) | (hashBytes[i] & 0xFF);
            }
            
            // Return absolute value to ensure positive hash
            return Math.abs(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
    
    /**
     * Computes a consistent hash value for the given input string within a specified range.
     * Useful for mapping keys to a specific number of nodes or slots.
     * 
     * @param input The string to hash
     * @param range The maximum value (exclusive) for the hash range
     * @return A hash value between 0 (inclusive) and range (exclusive)
     */
    public static int hashInRange(String input, int range) {
        if (range <= 0) {
            throw new IllegalArgumentException("Range must be positive");
        }
        return hash(input) % range;
    }
    
    /**
     * Computes a hash for a node with a virtual node index.
     * This is useful for creating virtual nodes in consistent hashing.
     * 
     * @param nodeId The node identifier
     * @param virtualIndex The virtual node index
     * @return A positive integer hash value
     */
    public static int hashVirtualNode(String nodeId, int virtualIndex) {
        return hash(nodeId + ":" + virtualIndex);
    }
}
