
from ast import List
import collections
from typing import Optional

class TreeNode:
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None

         
# 437. Path Sum III
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        def dfs(root, cur):
            if not root:
                return
            cur += root.val
            pre = cur - targetSum
            if pre in self.dic:
                self.res += self.dic[pre]
            self.dic[cur] = self.dic.get(cur, 0) + 1
            dfs(root.left, cur)
            dfs(root.right, cur)
            self.dic[cur] -= 1
        self.dic = {0: 1}
        self.res = 0
        dfs(root, 0)
        return self.res

# 236. Lowest Common Ancestor of a Binary Tree
class Solution:
    def lowestCommonAncestor(self, root, p, q):
        if not root:
            return None
        if root.val == p.val or root.val == q.val:
            return root
        left, right = self.lowestCommonAncestor(root.left, p, q), self.lowestCommonAncestor(root.right, p, q)
        if left and right:
            return root
        if left:
            return left
        if right:
            return right

# 687. Longest Univalue Path
class Solution:
    def longestUnivaluePath(self, root: Optional[TreeNode]) -> int:
        self.res = 0
        def dfs(root):
            if not root:
                return 0
            left = dfs(root.left)
            right = dfs(root.right)
            left = left + 1 if root.left and root.val == root.left.val else 0
            right = right + 1 if root.right and root.val == root.right.val else 0
            self.res = max(self.res, left + right)
            return max(left, right)
        dfs(root)
        return self.res


# 297. Serialize and Deserialize Binary Tree
class Codec:

    def serialize(self, root):
        if not root:
            return ''
        res = []
        queue = collections.deque([root])
        while queue:
            cur = queue.popleft()
            if cur:
                res.append(str(cur.val))
                queue.append(cur.left)
                queue.append(cur.right)
            else:
                res.append("")
        for i in range(len(res)-1, -1, -1):
            if res[i] == '':
                res.pop()
            else:
                break
        return ','.join(res)
        

    def deserialize(self, data):
        if data == '':
            return None
        data = collections.deque([TreeNode(int(i)) if i != '' else None for i in data.split(',')])
        root = data.popleft()
        queue = collections.deque([root])
        while data:
            node = queue.popleft()
            node.left = data.popleft() if data else None
            node.right = data.popleft() if data else None
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        return root

# 987. Vertical Order Traversal of a Binary Tree
class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []
        dic = collections.defaultdict(list)
        q = [(root, 0, 0)]
        while q:
            cur, pos, level = q.pop(0)
            dic[pos].append((level, cur.val))
            if cur.left:
                q.append((cur.left, pos-1, level+1))
            if cur.right:
                q.append((cur.right, pos+1, level+1))
        res = []
        for key, val in sorted(dic.items()):
            res.append(v for k, v in sorted(val))
        return res

# 222. Count Complete Tree Nodes
class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        level = 0
        node = root.left
        while node:
            level += 1
            node = node.left
        left = 2 ** level
        right = 2 ** (level+1)-1
        def check(mid, node):
            path = bin(mid)[3:]
            for i in path:
                if i == '1':
                    node = node.right
                else:
                    node = node.left
            if node:
                return True
            else:
                return False
        while left < right:
            mid = (left + right + 1) // 2
            node = root
            if check(mid, node):
                left = mid
            else:
                right = mid - 1
        return left

# 129. Sum Root to Leaf Numbers
class Solution:
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        def dfs(root, num):
            if not root:
                return 0
            num = 10 * num + root.val
            if not root.left and not root.right:
                return num
            else:
                return dfs(root.left, num) + dfs(root.right, num)
        return dfs(root, 0)

# 1325. Delete Leaves With a Given Value
class Solution:
    def removeLeafNodes(self, root: Optional[TreeNode], target: int) -> Optional[TreeNode]:
        if root:
            root.left = self.removeLeafNodes(root.left, target)
            root.right = self.removeLeafNodes(root.right, target)
            if root.val != target or root.left or root.right:
                return root
            
            