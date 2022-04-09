class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

class Solution:
    def __init__(self):
        self.visit = {None: None}
    def cloneGraph(self, node: 'Node') -> 'Node':
        if not node:
            return node
        if node in self.visit:
            return self.visit[node]
        res = Node(node.val, [])
        self.visit[node] = res
        if node.neighbors:
            res.neighbors = [self.cloneGraph(n) for n in node.neighbors]
        return res

import collections
class Solution:
    def maximalPathQuality(self, values: List[int], edges: List[List[int]], maxTime: int) -> int:
        n = len(values)
        graph = collections.defaultdict(list)
        for x, y, time in edges:
            graph[x].append((y, time))
            graph[y].append((x, time))
        self.res = 0
        visit = {0}
        
        def dfs(node, quality, time):
            if node == 0:
                self.res = max(self.res, quality)
            for neighbor, neighbor_time in graph[node]:
                new_time = time + neighbor_time
                if new_time > maxTime:
                    continue
                visited = neighbor in visit
                visit.add(neighbor)
                dfs(neighbor, quality + (0 if visited else values[neighbor]), new_time)
                if not visited:
                    visit.remove(neighbor)
        dfs(0, values[0], 0)
        return self.res
    
    

class Solution:
    def maximalPathQuality(self, values: List[int], edges: List[List[int]], maxTime: int) -> int:
        n = len(values)
        graph = collections.defaultdict(list)
        for x, y, time in edges:
            graph[x].append((y, time))
            graph[y].append((x, time))
        self.res = 0
        visit = {0}
        
        def dfs(node, quality, time):
            if node == 0:
                self.res = max(self.res, quality)
            for neighbor, neighbor_time in graph[node]:
                new_time = time + neighbor_time
                if new_time > maxTime:
                    continue
                visited = neighbor in visit
                visit.add(neighbor)
                dfs(neighbor, quality + (0 if visited else values[neighbor]), new_time)
                if not visited:
                    visit.remove(neighbor)
        dfs(0, values[0], 0)
        return self.res
    
    