package ui.yinwen;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
       }
   }
    // 107. Binary Tree Level Order Traversal II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root==null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i=0; i<size; i++){
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left!=null) {
                    q.add(node.left);
                }
                if (node.right!=null) {
                    q.add(node.right);
                }
            }
            result.add(0,list);
        }
        return result;
    }

    // 366. Find Leaves of Binary Tree
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(root, res);
        return res;
    }

    private int getHeight(TreeNode node, List<List<Integer>> res){
        if (null==node){
            return -1;
        }
        int level = 1 + Math.max(getHeight(node.left, res), getHeight(node.right, res));
        if(res.size() < level+1){
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        return level;
    }

    // 662. Maximum Width of Binary Tree
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        root.val = 0;
        queue.add(root);

        int size;
        int ans = 0;
        while(!queue.isEmpty()) {
            size = queue.size();
            ans = Math.max(ans, queue.getLast().val - queue.getFirst().val + 1);
            while (size > 0) {
                TreeNode temp = queue.remove();
                if(temp.left != null) {
                    queue.add(temp.left);
                    temp.left.val = temp.val * 2 + 1;
                }
                if(temp.right != null) {
                    queue.add(temp.right);
                    temp.right.val = temp.val * 2 + 2;
                }
                size--;
            }
        }
        return ans;
    }

    // 515. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while(size > 0){
                TreeNode node = queue.poll();
                max= Math.max(max,node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            res.add(max);
        }
        return res;
    }
}
