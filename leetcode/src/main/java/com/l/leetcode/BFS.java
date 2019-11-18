package com.l.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : sin97
 * @date : 2019/11/18 22:25
 * <p>
 * 二叉树的层次遍历
 */
public class BFS {
    class TreeNode {
        private TreeNode left;

        private TreeNode right;

        private int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> bfs(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)
            return res;
        qu.add(root);
        while(!qu.isEmpty()){
            int size = qu.size();
            List<Integer> temp = new ArrayList<>();
            while(size>0){
                TreeNode tn = qu.remove();
                if(tn!=null) {
                    if(tn.left!=null)
                        qu.add(tn.left);
                    if(tn.right!=null)
                        qu.add(tn.right);
                }
                temp.add(tn.val);
                size--;
            }
            res.add(temp);
        }
        return res;
    }

}

