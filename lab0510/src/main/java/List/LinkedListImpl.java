package List;

public class LinkedListImpl<T>{

    public class Node<T> {
        public T value;
        public Node<T> next;
        public Node(T value) {
            this.value = value;
        }
    }

    private int size;
    private Node<T> first;

    public LinkedListImpl(T value) {
        this.first = new Node<T>(value);
        this.size = 1;
    }

    public LinkedListImpl() {
        this.first = null;
        this.size = 0;
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> findLast() {
        if (first == null) {
            return null;
        }
        Node<T> current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public void add(T value) {
        Node<T> newElement = new Node(value);
        findLast().next = newElement;
        size++;
    }

    public void add(T value, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Некорректная позиция: " + position);
        }
        if (position == 0) {
            Node<T> newElement = new Node(value);
            newElement.next = first;
            first = newElement;
        } else {
            Node<T> current = first;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            Node<T> newElement = new Node(value);
            newElement.next = current.next;
            current.next = newElement;
        }
        size++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<T> current = first;
        s.append(first.value).append(" ");
        while (current.next != null) {
            s.append(" | ").append(current.next.value);
            current = current.next;
        }
        return s.toString();
    }


    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Некорректная позиция: " + position);
        }
        if (position == 0) {
            first = first.next;
        } else {
            Node<T> currentPrev = first;
            for (int i = 0; i < position - 1; i++) {
                currentPrev = currentPrev.next;
            }
            Node<T> current = currentPrev.next;
            currentPrev.next = current.next;
        }
        size--;
    }

    public T get(int num) {
        if (num < 0 || num >= size) {
            throw new IndexOutOfBoundsException("Некорректная позиция: " + num);
        }
        Node<T> current = first;
        for (int i = 0; i < num; i++) {
            current = current.next;
        }
        return current.value;
    }

    public int size() {
        return size;
    }
}
