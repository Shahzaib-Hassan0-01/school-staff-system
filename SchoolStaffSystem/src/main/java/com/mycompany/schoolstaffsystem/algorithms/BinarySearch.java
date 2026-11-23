package com.mycompany.schoolstaffsystem.algorithms;

import com.mycompany.schoolstaffsystem.model.Employee;
import java.util.List;

/**
 * Recursive binary search on the sorted list.
 *
 * Why binary search: the list is already sorted by merge sort so it makes
 * sense to use that. O(log n) - with 46 people it's max 6 checks.
 * Linear search would check everyone one by one (O(n)) and ignore the sorting.
 * Interpolation search is meant for evenly spread numbers, not names.
 * A HashMap would be fast but it isn't recursive and I'd have to keep a
 * second structure in sync with the list.
 *
 * @author shahz
 */
public class BinarySearch {

    // returns the index of the name, or -1 if it's not there
    public static int search(List<Employee> list, String name, int low, int high) {
        if (low > high) {
            return -1; 
        }

        int mid = low + (high - low) / 2;
        int cmp = list.get(mid).getFullName().compareToIgnoreCase(name);

        if (cmp == 0) {
            return mid;
        } else if (cmp > 0) {
            return search(list, name, low, mid - 1);   
        } else {
            return search(list, name, mid + 1, high);  
        }
    }
}
