package ui.yinwen;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    // 1669. Merge In Between Linked Lists
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode end = list1, start = null;
        for (int i = 0; i < b; i++, end = end.next) {
            if (i == a - 1) {
                start = end;
            }
        }
        start.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = end.next;
        end.next = null;
        return list1;
    }

    // 86. Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode p1 = dummy1;
        ListNode p2 = dummy2;
        while (head!=null){
            if (head.val<x) {
                p1.next = head;
                p1 = head;
            }else {
                p2.next = head;
                p2 = head;
            }
            head = head.next;
        }
        p1.next = dummy2.next;
        p2.next = null;
        return dummy1.next;
    }

    // 2074. Reverse Nodes in Even Length Groups
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int groupRequired = 1;
        ListNode temp = head;
        while(temp != null){
            int count = 0;
            ListNode start = temp;
            Stack<Integer> stack = new Stack<>();
            while(count != groupRequired && temp != null){
                stack.push(temp.val);
                temp = temp.next;
                count++;
            }
            if(count % 2 == 0) {
                while(start != temp){
                    start.val = stack.pop();
                    start = start.next;
                }
            }
            groupRequired++;
        }
        return head;
    }


    // 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points



    // 148. Sort List
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null)
            p.next = l1;
        if (l2 != null)
            p.next = l2;
        return l.next;
    }


    // 382. Linked List Random Node
    public class Solution {
        ListNode head;
        Random random;

        public Solution(ListNode h) {
            head = h;
            random = new Random();
        }
        public int getRandom() {
            ListNode node = head;
            int p = node.val;
            for(int i=1;node.next != null;i++){

                node = node.next;
                if(random.nextInt(i + 1) == i) {
                    p = node.val;
                }
            }
            return p;
        }
    }

    // 92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 0; i<left-1; i++) pre = pre.next;

        ListNode leftNode = pre.next;
        ListNode rightNode = leftNode.next;

        for(int i=0; i<right-left; i++){
            leftNode.next = rightNode.next;
            rightNode.next = pre.next;
            pre.next = rightNode;
            rightNode = leftNode.next;
        }
        return dummy.next;

    }

    // 725. Split Linked List in Parts
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] parts = new ListNode[k];
        int len = 0;
        for (ListNode node = head; node != null; node = node.next)
            len++;
        int n = len / k, r = len % k;
        ListNode node = head, prev = null;
        for (int i = 0; node != null && i < k; i++, r--) {
            parts[i] = node;
            for (int j = 0; j < n + (r > 0 ? 1 : 0); j++) {
                prev = node;
                node = node.next;
            }
            prev.next = null;
        }
        return parts;
    }

    // 817. Linked List Components
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> setNums = new HashSet<>();
        for (int i: nums){
            setNums.add(i);
        }
        int res = 0;
        while (head != null) {
            if (setNums.contains(head.val) && (head.next == null || !setNums.contains(head.next.val))) res++;
            head = head.next;
        }
        return res;
    }


    // 2130. Maximum Twin Sum of a Linked List
    public int pairSum(ListNode head) {
        if (head == null) {
            return 0;
        }
        if (head.next == null) {
            return head.val;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow);
        fast = head;
        int sum = Integer.MIN_VALUE;
        while (slow != null) {
            sum = Math.max(slow.val + fast.val, sum);
            slow = slow.next;
            fast = fast.next;
        }
        return sum;
    }


    public ListNode reverse(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode cur = node;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
