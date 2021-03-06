public class LinearProbingHashMap<K,V>
{
    private Node<K,V>[] HashTable;
    private int length;

    LinearProbingHashMap(int maxSize)
    {
        this.length = 2 * maxSize;
        this.HashTable = new Node[length];
    }

    public void add (Node<K,V> node)
    {
        int i = node.key.hashCode() % (length-1); //use the item's hash as key and convert this to address
        while (HashTable[i] != null)
            i = (i + 1) % length;
        HashTable[i] = node;
    }

    public void remove(K key)
    {
        int i = key.hashCode() % (length-1);
        while (HashTable[i] != null)
        {
            if (key.equals(HashTable[i].key))
                break;
            else
                i = (i + 1) % length;
        }
        HashTable[i] = null;
        int j = (i+1) % length;
        while (HashTable[j] != null)
        {
            Node<K,V> temp = HashTable[j];
            HashTable[j] = null;
            add(temp);
            j = (j + 1) % length;
        }
    }

    public Node<K,V> search(K key)
    {
        int i = key.hashCode() % (length-1);
        while (HashTable[i] != null)
        {
            if (key.equals(HashTable[i].key))
                return HashTable[i];
            else
                i = (i + 1) % length;
        }
        return null;
    }
}