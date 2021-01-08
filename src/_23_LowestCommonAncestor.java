/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 */
public class _23_LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;
        t2.right = t5;

        t3.left = t6;
        t3.right = t7;

        t4.left = t8;
        t4.right = t9;

        System.out.println(lowestCommonAncestor(t1, t5, t8));
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        boolean findPLeft = findNode(root.left, p);
        boolean findQRight = findNode(root.right, q);
        if (findPLeft && findQRight) {
            return root;
        } else if (findPLeft) {
            //都在左子叶
            return lowestCommonAncestor(root.left, p, q);
        } else if (findQRight) {
            //都在右子叶
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    public static boolean findNode(TreeNode r, TreeNode target) {
        if (r == null) {
            return false;
        }
        if (r == target) {
            return true;
        }
        if (r.left != null && r.right != null) {
            return findNode(r.left, target) || findNode(r.right, target);
        } else if (r.right != null) {
            return findNode(r.right, target);
        } else if (r.left != null) {
            return findNode(r.left, target);
        } else {
            return false;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
