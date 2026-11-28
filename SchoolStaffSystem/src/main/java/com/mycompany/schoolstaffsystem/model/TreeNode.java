package com.mycompany.schoolstaffsystem.model;

/**
 * Node for the hierarchy tree - one employee and up to two children.
 *
 * @author shahz
 */
public class TreeNode {

    private Employee employee;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
