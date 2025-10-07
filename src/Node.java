import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String id;
    private final Map<String, String> storage;

    public Node(String id) {
        this.id = id;
        this.storage = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void store(String key, String value) {
        storage.put(key, value);
    }

    public String retrieve(String key) {
        return storage.get(key);
    }

    public boolean containsKey(String key) {
        return storage.containsKey(key);
    }

    public void remove(String key) {
        storage.remove(key);
    }

    public Map<String, String> getStorage() {
        return new HashMap<>(storage);
    }

    @Override
    public String toString() {
        return "Node{id='" + id + "', storageSize=" + storage.size() + "}";
    }
}
