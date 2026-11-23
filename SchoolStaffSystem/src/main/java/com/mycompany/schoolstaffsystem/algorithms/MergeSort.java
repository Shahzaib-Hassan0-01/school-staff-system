package com.mycompany.schoolstaffsystem.algorithms;

import com.mycompany.schoolstaffsystem.model.Employee;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Recursive merge sort.
 *
 * Why merge sort: it's always O(n log n), even when the list is already
 * nearly sorted (which happens here every time a new record is added and we
 * re-sort). Quick sort can drop to O(n^2) in that case. It's also stable, which
 * I need for the tree (sort by name, then by rank, and names stay in order).
 * Bubble/insertion/selection are O(n^2) so they don't scale.
 * Downside: needs extra memory for the halves, but the staff list is small.
 *
 * @author shahz
 */
public class MergeSort {

    public static List<Employee> sort(List<Employee> list, Comparator<Employee> rule) {
        // base case - 0 or 1 item is already sorted
        if (list.size() <= 1) {
            return new ArrayList<>(list);
        }

        int mid = list.size() / 2;
        List<Employee> left = sort(list.subList(0, mid), rule);
        List<Employee> right = sort(list.subList(mid, list.size()), rule);

        return merge(left, right, rule);
    }

    private static List<Employee> merge(List<Employee> left, List<Employee> right, Comparator<Employee> rule) {
        List<Employee> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            // <= takes from the left side when equal, that's what keeps it stable
            if (rule.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        // add whatever is left
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }
        return result;
    }
}
