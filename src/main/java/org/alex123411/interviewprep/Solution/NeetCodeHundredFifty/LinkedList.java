package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;

import org.alex123411.interviewprep.Solution.Structures.ListNode;

public class LinkedList {
    // 206. Reverse Linked List
    // https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode reversed = new ListNode(head.val);
        while (head.next != null) {

            ListNode temp = new ListNode(head.next.val);
            temp.next = reversed;
            reversed = temp;

            head = head.next;
        }
        return reversed;
    }

    // 21. Merge Two Sorted Lists
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode temp = new ListNode();
        ListNode res = temp;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                temp.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1.next == null && list2.next != null) temp.next = list2;
        if (list2.next == null && list1.next != null) temp.next = list1;

        return res.next;
    }

    // 143. Reorder List
    // https://leetcode.com/problems/reorder-list/
    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode reversed = reverseList(head);
        ListNode copy = new ListNode(head.val);

        int len = 1;

        ListNode temp1 = copy;
        ListNode temp2 = head.next;

        while (temp2 != null) {
            len++;
            temp1.next = new ListNode(temp2.val);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        int i = 2;

        ListNode temp3 = head;
        copy = copy.next;
        while (len != -1) {
            if (i % 2 == 0) {
                temp3.next = new ListNode(reversed.val);
                reversed = reversed.next;
                temp3 = temp3.next;
            } else if (i % 2 == 1) {
                temp3.next = new ListNode(copy.val);
                copy = copy.next;
                temp3 = temp3.next;
            }
            i++;
            len--;
        }
    }

    // 19. Remove Nth Node From End of List
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        if (n == 1 && head.next == null) return null;

        int i = 0;
        int len = 0;

        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        if (len - n == 0) return head.next;
        temp = head;
        while (temp != null) {
            i++;
            if (i == len - n) {
                if (temp.next != null) {
                    temp.next = temp.next.next;
                }
                break;
            }
            temp = temp.next;
        }

        return head;
    }
}