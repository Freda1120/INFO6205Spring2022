# Q1

class Solution1:
    def compress(s): 
        chars = list(s)
        n = len(chars)
        i = 0
        count = 1
        for j in range(1, n+1):
            if j < n and chars[j] == chars[j-1]:
                count += 1
            else:
                chars[i] = chars[j-1]
                i += 1
                if count > 1:
                    for m in str(count):
                        chars[i] = m
                        i += 1
                count = 1
        res = chars[:i]
        for i in range(1, len(res)):
            if i == len(res)-1 and res[i].isalpha():
                res.append('1')
            elif res[i].isalpha() and res[i-1].isalpha():
                res.insert(i, '1')
        res = "".join(res)
            
        if len(res) >= len(s):
            return s
        
        else:
            return res
    print(compress("aabcccccaaa"))
    print(compress("ab"))
    print(compress("aaab")) # "aaab" has same length as "a3b1"
   

# Q2

class Solution2:
    def island(grid):
        row, col = len(grid), len(grid[0])
        res = 0

        def dfs(i, j):
            if i < 0 or i >= row or j < 0 or j >= col or grid[i][j] == '0':
                return
            grid[i][j] = '0'
            dfs(i+1, j)
            dfs(i-1, j)
            dfs(i, j+1)
            dfs(i, j-1)

        for i in range(row):
            for j in range(col):
                if grid[i][j] == '1':
                    res += 1
                    dfs(i, j)
        return res

    print(island([
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]))

    print(island([
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]))


# Q3

class Solution3:
    def anagram(strs):
        from collections import defaultdict
        dic = defaultdict(list)
        for s in strs:
            dic["".join(sorted(s))].append(s)
        return dic.values()

    print(anagram(["eat","tea","tan","ate","nat","bat"]))
    print(anagram([""]))
# Q4

class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

class Solution4:
    def lowestCommonAncestor(self, root, p, q):
        if not root:
            return None
        if root == p or root == q:
            return root
            
        left, right = self.lowestCommonAncestor(root.left, p, q), self.lowestCommonAncestor(root.right, p, q)

        if left and right:
            return root
        if left:
            return left
        if right:
            return right


 



