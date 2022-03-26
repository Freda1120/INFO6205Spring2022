class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution1:
    def getInterval(self, nums, lower, upper):
        nums = [lower-1] + nums + [upper+1]
        res = []
        for i in range(len(nums)-1):
            if nums[i+1] - nums[i] == 2:
                res.append(str(nums[i+1] - nums[i]))
            elif nums[i+1] - nums[i] > 2:
                res.append(str(nums[i] + 1) + '->' + str(nums[i+1] - 1))
        return res

class Solution2:
    def getSum(self, l1, l2):
        if not l1 or not l2:
            return l1 or l2
        dummy = ListNode(0)
        cur = dummy
        carry = 0
        while l1 or l2 or carry:
            if l1:
                carry += l1.val
                l1 = l1.next
            if l2:
                carry += l2.val
                l2 = l2.next
            cur.next = ListNode(carry % 10)
            carry = carry // 10
            cur = cur.next
        return dummy.next

class Solution3:
    def constructTree(self, preorder, inorder):
        if not preorder and not inorder:
            return None
        node_val = preorder[0]
        node = TreeNode(node_val)
        idx = inorder.index(node_val)
        node.left = self.constructTree(preorder[1: idx+1], inorder[:idx])
        node.right = self.constructTree(preorder[idx+1:], inorder[idx+1:])    
        return node

    
class Solution4:
    def mergeIntervals(self, intervals):
        res = []
        for interval in intervals:
            if len(res) == 0 or interval[0] > res[-1][1]:
                res.append(interval)
            else:
                res[-1][1] = max(res[-1][1], interval[1])
        return res


def constructTree(preorder, inorder):
        if not preorder and not inorder:
            return None
        node_val = preorder[0]
        node = TreeNode(node_val)
        idx = inorder.index(node_val)
        node.left = constructTree(preorder[1: idx+1], inorder[:idx])
        node.right = constructTree(preorder[idx+1:], inorder[idx+1:])    
        return node
    