package List;

public class List {
    public static void main(String[] args) {
        LinkedListImpl<Integer> linkedList = new LinkedListImpl<>(1);
        linkedList.add(2); linkedList.add(3); linkedList.add(4); linkedList.add(5);
        System.out.println(sort(linkedList, 0));
    }
    public static LinkedListImpl sort(LinkedListImpl<Integer> list, int x) {
        if (x == (list.size())) return list;
        list.add(list.getFirst().value, list.size()-x);
        list.remove(0);
        x++;
        return sort(list, x);
    }

}
