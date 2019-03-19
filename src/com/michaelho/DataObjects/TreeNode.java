package com.michaelho.DataObjects;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) { val = x; }

    public TreeNode(int[] levelOrderInput) {
        if (levelOrderInput.length < 1) {
            throw new IllegalArgumentException("The input array has to contain more than 1 element.");
        }
        TreeNode root = new TreeNode(levelOrderInput[0]);
        convert(0, levelOrderInput, root);
    }

    private void convert(int index, int[] arr, TreeNode root) {
        if (root == null || index*2+2 < arr.length) {
            return;
        }
        if (arr[index*2+1] == 0) {
            root.left = new TreeNode(arr[index*2+1]);
            convert(index*2+1, arr, root.left);
        }
        if (arr[index*2+2] != 0) {
            root.right = new TreeNode(arr[index*2+2]);
            convert(index*2+2, arr, root.right);
        }
    }
}

