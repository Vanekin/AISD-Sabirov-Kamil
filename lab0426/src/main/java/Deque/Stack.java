package Deque;

public class Stack<G> {
    class Node<G> {
        public G value;
        public Node next;
        public Node previous;

        public Node(G value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;

    public void pull(G value) { //добавление в stack
        Node newNode = new Node(value);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
    }

    public G pop() { //удаление последнего добавленного
        G x;
        if (last == null) {
            throw new IllegalStateException("Список пуст");//сли список пустой, удаление приведёт к ошибке
        } else {
            x = (G) last.value;
            last = last.previous;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
        return x;
    }

    public G peek() {
        if (last == null) {
            throw new IllegalStateException("Список пуст");//сли список пустой, удаление приведёт к ошибке
        }else return (G) last.value;

    }

    public boolean isEmpty() {
        if (first == null) return true;
        else return false;
    }

}
