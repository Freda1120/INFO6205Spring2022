package ui.yinwen;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    // 2 Add Two Numbers
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p, dummy = new ListNode(0);
        p = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(carry % 10);
            carry /= 10;
            p = p.next;
        }
        return dummy.next;
    }


    // 138 Copy List with Random Pointer
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<Node, Node>();
        return copyRandomList(head, map);
    }
    private Node copyRandomList(Node head, Map<Node, Node> map) {
        if (head == null) {
            return null;
        }
        if (map.containsKey(head)) {
            return map.get(head);
        }
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        newHead.next = copyRandomList(head.next, map);
        newHead.random = copyRandomList(head.random, map);

        return newHead;

    }

    // 23 Merge k Sorted Lists
    private static ListNode mergeKLists(List<ListNode> lists) {
        Queue<ListNode> heap = new PriorityQueue(new Comparator<ListNode>(){
            @Override public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        ListNode head = new ListNode(0), tail = head;
        for (ListNode node : lists) if (node != null) heap.offer(node);
        while (!heap.isEmpty()) {
            tail.next = heap.poll();
            tail = tail.next;
            if (tail.next != null) heap.offer(tail.next);
        }
        return head.next;
    }


    // 143 Reorder List
    private void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode prev = null, slow = head, fast = head, l1 = head;
        // find middle
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        // reverse
        ListNode l2 = reverse(slow);

        // merge
        merge(l1, l2);
    }

    ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head, next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    void merge(ListNode l1, ListNode l2) {
        while (l1 != null) {
            ListNode n1 = l1.next, n2 = l2.next;
            l1.next = l2;
            if (n1 == null)
                break;
            l2.next = n1;
            l1 = n1;
            l2 = n2;
        }
    }

    // 234 Palindrome Linked List
    private boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        slow = reverseList(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // 19 Remove Nth Node From End of List
    private static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        for(int i=0; i<=n; i++)   {
            fast = fast.next;
        }

        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return start.next;
    }

    // 328 Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenHead = head.next;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    // 708 Insert into a Sorted Circular Linked List
    class LinkedNode {
        public int val;
        public LinkedNode next;

        public LinkedNode() {}

        public LinkedNode(int _val) {
            val = _val;
        }

        public LinkedNode(int _val, LinkedNode _next) {
            val = _val;
            next = _next;
        }
    }

    private LinkedNode insert(LinkedNode head, int insertVal) {
        LinkedNode res = new LinkedNode(insertVal);
        if (head == null) {
            res.next = res;
            return res;
        }
        LinkedNode pre = head;
        LinkedNode cur = head.next;
        while (cur != head) {
            if (pre.val <= insertVal && insertVal <= cur.val) break;
            if (pre.val > cur.val) {
                if (insertVal <= cur.val || insertVal >= pre.val) break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = res;
        res.next = cur;
        return head;
    }

    // 1019 Next Greater Node In Linked List
    private static int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next)
            arr.add(node.val);
        int[] res = new int[arr.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.size(); ++i) {
            while (!stack.isEmpty() && arr.get(stack.peek()) < arr.get(i))
                res[stack.pop()] = arr.get(i);
            stack.push(i);
        }
        return res;
    }

    // 82 Remove Duplicates from Sorted List II
    private static ListNode deleteDuplicates(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode dummy =new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            while(cur.next !=null && cur.val == cur.next.val){
                cur = cur.next;
            }
            if(pre.next == cur){
                pre = pre.next;
            }
            else{
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

}
