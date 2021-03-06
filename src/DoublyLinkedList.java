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

    public void addFirst(Node<K,V> temp)
    {
        //System.out.println("add \n");
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

    public void rotate(Node<K,V> temp)
    {
        if (head.next == tail)
        {
            Node<K,V> t = head;
            head = tail;
            tail = t;
        }
        else if (tail == temp)
        {
            temp.next = head;
            head.previous = temp;
            head = temp;
        }
        else
        {
            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
            temp.next = head;
            head.previous = temp;
            head = temp;
        }
    }

    public void removeLast()
    {
        tail = tail.previous;
        tail.next = null;
        --size;
    }

    public void print()
    {
        try
        {
            if (size == 0)
                System.out.println("Empty cache!");
            else
            {
                System.out.println("Cache items:  ");
                Node<K,V> current = head;
                do
                {
                    System.out.println("{" + current.key + ", " + current.value + "}");
                    current = current.next;
                }
                while (current!=null);
            }
            System.out.println("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}