public class Node<K,V>
{
    K key;
    V value;
    Node<K,V> previous, next;
    Node (K key, V value)
    {
        this.key = key;
        this.value = value;
        this.previous = null;
        this.next = null;
    }
}