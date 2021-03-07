public class DoublyLinkedList<K,V>
{
    protected int size;
    protected Node<K,V> head;
    protected Node<K,V> tail;

    DoublyLinkedList()
    {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Places a node at the front of the list.
     * */
    public void addFirst(Node<K,V> temp)
    {
        if (size == 0)
        {
            head = tail = temp;
        }
        else
        {
            temp.next = head;
            head.previous = temp;
            head = temp;
        }
        ++size;
    }

    /**
     * In this method a new node is initialized, since the old one
     * must be placed at the front of the list and ties with prev
     * and next have to be cut. AddFirst is called to put the node
     * at the front. Check for temp == head is done in Cache, so
     * this method checks for the rest of the potential cases.
     * */
    public Node<K,V> removeFromMiddle(Node<K,V> temp)
    {
        Node<K,V> node = new Node<>(temp.key, temp.value);
        if (size == 2)
        {
            tail = head;
            head.next = null;
        }
        else if (temp == tail)
        {
            tail = tail.previous;
            tail.next = null;
        }
        else
        {
            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
        }
        --size;
        addFirst(node);
        return node;
    }

    /**
     * Removes the last node from the list. It doesn't
     * check for null values, since we suppose a cache
     * memory has a positive number of blocks, hence
     * there will always be at least a node in the list.
     * */
    public void removeLast()
    {
        tail = tail.previous;
        tail.next = null;
        --size;
    }
}