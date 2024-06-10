/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */

import  ListNode  from '../ListNode.mjs';

const recursiveSumList = (head) => {
  if (!head) return 0;

  return head.val + recursiveSumList(head.next);
}

const list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

const result = recursiveSumList(list);
console.log(result);
