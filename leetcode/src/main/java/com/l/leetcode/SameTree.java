package com.l.leetcode;

/**
 * @author : sin97
 * @date : 2019/11/18 22:48
 */
public class SameTree {
    class TreeNode {
        private TreeNode left;

        private TreeNode right;

        private int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public Boolean sameTree(TreeNode p, TreeNode q) {
        if (null == p || null == q) {
            return p == q;
        }
        if (p.val != q.val) {
            return false;
        }
        return sameTree(p.left, q.left) && sameTree(p.right, q.right);
    }

}
