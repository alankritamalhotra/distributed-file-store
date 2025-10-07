import java.util.*;

public class DistributedFileStore {
    private final TreeMap<Integer, Node> ring;
    private final Map<String, Node> nodes;
    private static final int VIRTUAL_NODES = 3;

    public DistributedFileStore() {
        this.ring = new TreeMap<>();
        this.nodes = new HashMap<>();
    }

    /**
     * Adds a node to the consistent hashing ring.
     * Creates multiple virtual nodes for better distribution.
     */
    public void addNode(Node node) {
        if (nodes.containsKey(node.getId())) {
            throw new IllegalArgumentException("Node already exists: " + node.getId());
        }
        
        nodes.put(node.getId(), node);
        
        // Add virtual nodes to the ring
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            int hash = HashUtils.hashVirtualNode(node.getId(), i);
            ring.put(hash, node);
        }
    }

    /**
     * Removes a node from the consistent hashing ring.
     */
    public void removeNode(String nodeId) {
        Node node = nodes.remove(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("Node not found: " + nodeId);
        }
        
        // Remove all virtual nodes from the ring
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            int hash = HashUtils.hashVirtualNode(nodeId, i);
            ring.remove(hash);
        }
    }

    /**
     * Finds the node responsible for a given key using consistent hashing.
     */
    private Node getNodeForKey(String key) {
        if (ring.isEmpty()) {
            throw new IllegalStateException("No nodes available in the ring");
        }
        
        int hash = HashUtils.hash(key);
        
        // Find the first node with hash >= key's hash
        Map.Entry<Integer, Node> entry = ring.ceilingEntry(hash);
        
        // If no node found, wrap around to the first node
        if (entry == null) {
            entry = ring.firstEntry();
        }
        
        return entry.getValue();
    }

    /**
     * Stores a file in the distributed system.
     */
    public void storeFile(String fileName, String content) {
        Node node = getNodeForKey(fileName);
        node.store(fileName, content);
    }

    /**
     * Retrieves a file from the distributed system.
     */
    public String retrieveFile(String fileName) {
        Node node = getNodeForKey(fileName);
        return node.retrieve(fileName);
    }

    /**
     * Checks if a file exists in the distributed system.
     */
    public boolean containsFile(String fileName) {
        Node node = getNodeForKey(fileName);
        return node.containsKey(fileName);
    }

    /**
     * Removes a file from the distributed system.
     */
    public void removeFile(String fileName) {
        Node node = getNodeForKey(fileName);
        node.remove(fileName);
    }

    /**
     * Gets all nodes in the system.
     */
    public Collection<Node> getNodes() {
        return nodes.values();
    }

    /**
     * Gets the ring structure for debugging.
     */
    public TreeMap<Integer, Node> getRing() {
        return new TreeMap<>(ring);
    }

    /**
     * Gets the number of nodes in the system.
     */
    public int getNodeCount() {
        return nodes.size();
    }
}
