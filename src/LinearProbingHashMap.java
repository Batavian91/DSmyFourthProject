public class LinearProbingHashMap<K,V>
{
    private Node<K,V>[] HashTable;
    private int length;

    LinearProbingHashMap(int maxSize)
    {
        this.length = 2 * maxSize;
        this.HashTable = new Node[length];
    }

    /**
     * Turns a key to an index and stores a node as value to
     * keep the address of the objects's node.
     * */
    public void add (Node<K,V> node)
    {
        int i = node.key.hashCode() % (length); //use the item's hash as key and convert this to address
        while (HashTable[i] != null)
            i = (i + 1) % length;
        HashTable[i] = node;
    }

    /**
     * Removes a node from the table using linear probing.
     * */
    public void remove(K key)
    {
        int i = key.hashCode() % (length);
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

    /**
     * Returns the node that contains the specified key or null
     * if the key is not found. Uses linear probing.
     * */
    public Node<K,V> search(K key)
    {
        int i = key.hashCode() % (length);
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