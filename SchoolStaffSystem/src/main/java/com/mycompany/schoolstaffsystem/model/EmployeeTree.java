package com.mycompany.schoolstaffsystem.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary tree for the school hierarchy.
 * Insert is level-order: go through the nodes top to bottom, left to right,
 * and put the new person in the first empty spot (left before right).
 * A queue gives the nodes back in the same order they went in, which is level order.
 *
 * @author shahz
 */
public class EmployeeTree {

    private TreeNode root;

    public void insert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.getLeft() == null) {
                current.setLeft(newNode);
                return;
            }
            queue.add(current.getLeft());

            if (current.getRight() == null) {
                current.setRight(newNode);
                return;
            }
            queue.add(current.getRight());
        }
    }

    // level order traversal - gives back a list for each level (level 0 = root)
    public List<List<TreeNode>> levelOrder() {
        List<List<TreeNode>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // how many nodes are on this level
            List<TreeNode> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                level.add(current);
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            levels.add(level);
        }
        return levels;
    }

    // height counted as number of levels (just a root = 1)
    public int getHeight() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public int getNodeCount() {
        return count(root);
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.getLeft()) + count(node.getRight());
    }

    public TreeNode getRoot() {
        return root;
    }
}
