/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */

import  ListNode  from '../ListNode.mjs';

const maxNumberInList = (head) => {
  if (!head) return Number.MIN_VALUE;

  const res = maxNumberInList(head.next);

  return head.val > res ? head.val : res
}

const list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
const list2 = new ListNode(1, new ListNode(1, new ListNode(8, new ListNode(-1, new ListNode(5)))));
const result1 = maxNumberInList(list1);
const result2 = maxNumberInList(list2);

console.log(`result1 = ${result1}`);
console.log(`result2 = ${result2}`);
