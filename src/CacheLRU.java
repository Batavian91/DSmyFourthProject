/**
 * Uses a doubly linked list, that stores the objects from
 * left (most recently used) to right (least recently used).
 * When we have a cache hit, the node that contains the object
 * is transferred to the front of the list. To reach it at O(1),
 * a hash map/table is used. The hash table uses the object's
 * hashed key as index and stores the node as value. In case
 * of a cache miss, the node is stored at the front of the list
 * and in the table. To store an object in a full list, the last
 * element is initially removed and then the object is inserted.
 * */
public class CacheLRU<K,V> implements Cache<K,V>
{
    private int cacheSize;
    private long hits, lookups;
    private DoublyLinkedList<K,V> dll;
    private LinearProbingHashMap<K,V> hm;

    public CacheLRU(int max)
    {
        this.cacheSize = max;
        this.hits = this.lookups = 0;
        dll = new DoublyLinkedList<>();
        hm = new LinearProbingHashMap<>(cacheSize);
    }

    /**
     * Looks in a hash table to find if an object with the
     * specified key exists. The hash table stores a node
     * (address/reference) that contains the object.
     * --> index = key.hash % length
     * --> content = node (address/reference)
     * If the object exists, it checks whether it is at
     * the front of the cache (list). If not, it has to
     * remove it from the table and re-insert it, while
     * the list removes it from it's position and places
     * it at front. The removal and re-insertion are made,
     * because we must create a new node with the contents
     * of the old one, otherwise errors will arise.
     * */
    @Override
    public V lookUp(K key)
    {
        Node<K,V> node = hm.search(key);
        ++lookups;
        if (node != null)
        {
            if (dll.head != node)
            {
                hm.remove(key);
                node = dll.removeFromMiddle(node);
                hm.add(node);
            }
            ++hits;
            return node.value;
        }
        return null;
    }

    /**
     * Stores a node (reference) in the hash table.
     * When cache size == list size, it removes the
     * last element from the list and inserts the new
     * one at the front.
     * */
    @Override
    public void store(K key, V value)
    {
        if (cacheSize == dll.size)
        {
            hm.remove(dll.tail.key);
            dll.removeLast();
        }
        Node<K,V> node = new Node<>(key, value);
        dll.addFirst(node);
        hm.add(node);
    }

    @Override
    public double getHitRatio() { return (double) hits/lookups; }

    @Override
    public long getHits() { return hits; }

    @Override
    public long getMisses() { return lookups - hits; }

    @Override
    public long getNumberOfLookUps() { return lookups; }
}