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

    @Override
    public V lookUp(K key)
    {
        Node<K,V> node = hm.search(key);
        ++lookups;
        if (node != null)
        {
            if (dll.head != node)
                dll.rotate(node);
            ++hits;
            return node.value;
        }
        return null;
    }

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