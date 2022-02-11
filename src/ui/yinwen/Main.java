package ui.yinwen;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(1, true);
//        list.add(2, false);
//        list.add(3, false);
//        list.add(4, false);
//        list.add(5, false);
//        list.add(6, false);
//        list.add(7, false);
//        list.printLinkedList();
//        list.reverse();
//        list.printLinkedList();
//        System.out.println(list.containCycle());

        LinkedList<Integer> list = createListWithCycle();
        System.out.println(list.containCycle());
        Node<Integer> start = list.findStartOfCycle();
        if (start == null){
            System.out.println("No Cycle");
        }else{
            System.out.println(start.data);
        }
        list.breakCycle();
        list.printLinkedList();
        System.out.println(list.findNumberOutNodesInsideCycle());
    }

    public static LinkedList<Integer> createListWithCycle() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1,false);
        list.add(2,false);
        list.add(3,false);
        list.add(4,false);
        list.add(6,false);
        list.add(7,false);
        list.add(8,false);
        list.add(9,false);
        Node<Integer> three = list.head.next.next;
        list.tail.next = three;
        return list;
    }



}
