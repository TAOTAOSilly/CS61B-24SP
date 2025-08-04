import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        public BSTNode(K k,V v) {
            this.key = k;
            this.value = v;
            left = null;
            right = null;
        }
    }

    private BSTNode root;
    private int size;
    public BSTMap() {
        root = null;
        size = 0;
    }

    private BSTNode putHelper(BSTNode node,K key,V value){
        //conner case: Find the final position is nothing here;
        if (node == null) {
            size ++;
            node = new BSTNode(key,value);
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = putHelper(node.left, key, value);
            } else if (cmp > 0) {
                node.right = putHelper(node.right, key, value);
            }
            //the key is equal to k
            node.value = value;
        }
        //finally change the tree
        root = node;
        return root;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */


    @Override
    public void put(K key, V value) {
        putHelper(root,key,value);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        BSTNode position = findHelper(root,key);
        if (position == null)
            return null;
        return position.value;
    }

    private BSTNode findHelper(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return findHelper(node.left, key);
        } else if (cmp > 0) {
            return findHelper(node.right, key);
        } else {
            return node;
        }
    }
    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        BSTNode currentNode = findHelper(root,key);
        if (currentNode == null) {
            return false;
        } else {
            return true;
        }
    }

    ;



    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
