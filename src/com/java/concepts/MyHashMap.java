package com.java.concepts;

/**
 * MyHashMap - a simple generic HashMap implementation using separate chaining.
 *
 * Not thread-safe.
 *
 * Features:
 * - Generic keys and values
 * - Supports one null key (treated as hash 0) and null values
 * - Uses separate chaining with singly linked nodes for collision handling
 * - Resizes (rehashes) when size > capacity * loadFactor
 *
 * Complexity:
 * - Average O(1) for put/get/remove. Worst-case O(n) when many keys collide into the same bucket.
 */
public class MyHashMap<K, V> {

    // Default settings
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // The table of buckets; each bucket is a linked list of Node<K,V>
    private Node<K, V>[] table;
    private int size;
    private int capacity;
    private final float loadFactor;

    /** Node used in chaining */
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        final int hash; // stored hash to speed comparisons

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /** Default constructor */
    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /** Construct with custom initial capacity and default load factor */
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /** Construct with full control */
    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity must be > 0");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("loadFactor must be > 0");
        }
        this.capacity = nextPowerOfTwo(initialCapacity);
        this.loadFactor = loadFactor;
        this.table = (Node<K, V>[]) new Node[this.capacity];
        this.size = 0;
    }

    // -------------------------
    // Public API (HashMap-like)
    // -------------------------

    /**
     * Associates the specified value with the specified key in this map.
     * Returns the previous value associated with key, or null if there was no mapping for key.
     */
    public V put(K key, V value) {
        ensureCapacityForInsert();
        int hash = hashOf(key);
        int idx = indexFor(hash, capacity);

        Node<K, V> current = table[idx];
        for (Node<K, V> n = current; n != null; n = n.next) {
            if (n.hash == hash && equalsKey(n.key, key)) {
                V old = n.value;
                n.value = value;
                return old;
            }
        }

        // Insert new node at head for simplicity
        Node<K, V> newNode = new Node<>(hash, key, value, current);
        table[idx] = newNode;
        size++;
        return null;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     */
    public V get(Object key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * Returns the previous value associated with key, or null if there was no mapping for key.
     */
    public V remove(Object key) {
        int hash = hashOf((K) key);
        int idx = indexFor(hash, capacity);
        Node<K, V> prev = null;
        Node<K, V> curr = table[idx];

        while (curr != null) {
            if (curr.hash == hash && equalsKey(curr.key, (K) key)) {
                // remove curr
                if (prev == null) {
                    table[idx] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                V old = curr.value;
                // help GC
                curr.next = null;
                return old;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** Returns true if this map contains no key-value mappings. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Removes all of the mappings from this map. The map will be empty after this call. */
    @SuppressWarnings("unchecked")
    public void clear() {
        this.table = (Node<K, V>[]) new Node[this.capacity];
        this.size = 0;
    }

    // -------------------------
    // Helper / internal methods
    // -------------------------

    /** Compute hash (matches spec: key == null ? 0 : (key.hashCode() & 0x7fffffff)). */
    private int hashOf(K key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7fffffff);
    }

    /** Compute bucket index from hash and table length */
    private int indexFor(int hash, int length) {
        return hash % length;
    }

    /** Get node for key (or null) */
    private Node<K, V> getNode(Object key) {
        int hash = hashOf((K) key);
        int idx = indexFor(hash, capacity);
        for (Node<K, V> n = table[idx]; n != null; n = n.next) {
            if (n.hash == hash && equalsKey(n.key, (K) key)) {
                return n;
            }
        }
        return null;
    }

    /** equality check for keys (handle nulls) */
    private boolean equalsKey(K a, K b) {
        return (a == b) || (a != null && a.equals(b));
    }

    /** Ensure capacity before inserting a new element (resize if necessary) */
    private void ensureCapacityForInsert() {
        if (size + 1 > (int) (capacity * loadFactor)) {
            resize(capacity * 2);
        }
    }

    /** Resize to newCapacity (must be >= current) and rehash all nodes */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Node<K, V>[] old = table;
        int oldCap = capacity;

        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];
        // rehash all nodes
        for (int i = 0; i < oldCap; i++) {
            Node<K, V> n = old[i];
            while (n != null) {
                Node<K, V> next = n.next;
                int idx = indexFor(n.hash, newCapacity);
                // insert at head in new bucket
                n.next = newTable[idx];
                newTable[idx] = n;

                n = next;
            }
        }
        this.table = newTable;
        this.capacity = newCapacity;
    }

    /** Utility: next power of two >= x (to keep capacities nice) */
    private int nextPowerOfTwo(int x) {
        int power = 1;
        while (power < x) {
            power <<= 1;
        }
        return power;
    }

    // For debugging / testing convenience: pretty print buckets (not part of java.util API)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (int i = 0; i < capacity; i++) {
            for (Node<K, V> n = table[i]; n != null; n = n.next) {
                if (!first) sb.append(", ");
                sb.append(String.valueOf(n.key)).append("=").append(String.valueOf(n.value));
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // -------------------------
    // Demo / Tests in main()
    // -------------------------
    public static void main(String[] args) {
        System.out.println("=== MyHashMap Demo ===");

        // Use small initial capacity to force resizing quickly
        MyHashMap<String, Integer> map = new MyHashMap<>(4, 0.75f);

        // Basic puts and gets
        System.out.println("Put (a,1): " + map.put("a", 1)); // expect null
        System.out.println("Put (b,2): " + map.put("b", 2));
        System.out.println("Get a => expected 1, actual: " + map.get("a"));
        System.out.println("Get b => expected 2, actual: " + map.get("b"));

        // Null key and null value
        System.out.println("Put (null,100): " + map.put(null, 100));
        System.out.println("Get null => expected 100, actual: " + map.get(null));
        System.out.println("Put (c,null): " + map.put("c", null));
        System.out.println("Get c => expected null, actual: " + map.get("c"));

        // Update existing
        System.out.println("Update a -> 10, previous: " + map.put("a", 10));
        System.out.println("Get a => expected 10, actual: " + map.get("a"));

        // Contains key & size
        System.out.println("containsKey(b) => expected true: " + map.containsKey("b"));
        System.out.println("size => expected 4: " + map.size());

        // Force resizing by inserting more entries (> capacity * loadFactor)
        System.out.println("\n-- Forcing Resizing --");
        for (int i = 0; i < 10; i++) {
            map.put("k" + i, i);
        }
        System.out.println("After inserts size: " + map.size());
        System.out.println("Map content (concise): " + map.toString());

        // Demonstrate collision handling by using keys that deliberately collide
        System.out.println("\n-- Collision Demonstration --");
        // Custom keys with identical hashCode
        MyHashMap<CollidingKey, String> collideMap = new MyHashMap<>(4, 0.75f);
        CollidingKey ck1 = new CollidingKey("one", 42);
        CollidingKey ck2 = new CollidingKey("two", 42);
        CollidingKey ck3 = new CollidingKey("three", 42);
        collideMap.put(ck1, "value1");
        collideMap.put(ck2, "value2");
        collideMap.put(ck3, "value3");
        System.out.println("Inserted 3 colliding keys (same hash). size: " + collideMap.size());
        System.out.println("Get ck2 => expected value2: " + collideMap.get(ck2));
        System.out.println("Remove ck1 => expected value1: " + collideMap.remove(ck1));
        System.out.println("Get ck1 => expected null: " + collideMap.get(ck1));
        System.out.println("Remaining map: " + collideMap.toString());

        // Remove head/middle/tail cases
        System.out.println("\n-- Remove edge cases --");
        MyHashMap<Integer, String> rmMap = new MyHashMap<>(4, 0.75f);
        // craft integers that may land in same bucket for demonstration - use CollidingKey style is easier,
        // but we'll demonstrate general remove correctness using string keys already in map above
        System.out.println("Before removal: " + map.toString());
        System.out.println("Remove b => expected 2: " + map.remove("b"));
        System.out.println("ContainsKey b => expected false: " + map.containsKey("b"));
        System.out.println("After removal size: " + map.size());
        System.out.println("Final map content: " + map.toString());

        System.out.println("\nAll demo operations completed.");
    }

    /**
     * Small helper class used in demo to force collisions: hashCode() returns provided value.
     * equals() is based on id string equality.
     */
    private static class CollidingKey {
        private final String id;
        private final int forcedHash;

        CollidingKey(String id, int forcedHash) {
            this.id = id;
            this.forcedHash = forcedHash;
        }

        @Override
        public int hashCode() {
            return forcedHash; // intentionally force collisions
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CollidingKey)) return false;
            CollidingKey other = (CollidingKey) o;
            return (id == null ? other.id == null : id.equals(other.id));
        }

        @Override
        public String toString() {
            return "CK(" + id + ")";
        }
    }
}

