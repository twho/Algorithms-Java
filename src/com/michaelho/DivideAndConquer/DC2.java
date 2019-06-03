package com.michaelho.DivideAndConquer;

/**
 * The DC2 class explores a set of divide and conquer algorithms such as quick select kth smallest, quick select
 * kth largest and quick sort.
 *
 * @author Michael Ho
 * @since 2015-01-11
 * */
public class DC2 {

    /**
     * Quick select algorithm helps find the kth smallest element in the unordered array.
     * Runtime: O(N)
     * Reference: https://www.geeksforgeeks.org/quickselect-algorithm/
     *
     * @param array The array to be queried.
     * @param left The starting index.
     * @param right The ending index.
     * @param k The k for finding the kth smallest element.
     *
     * @return The kth smallest element in the array.
     * */
    int kthSmallest(int[] array, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            // Partition the array around last element and get position of
            // pivot element in sorted array
            int pivot = partition(array, left, right);

            // If position is same as k
            if (pivot - left == k - 1)
                return array[pivot];

            // If position is more, recur for left subarray
            if (pivot - left > k - 1)
                return kthSmallest(array, left, pivot - 1, k);

            // Else recur for right subarray
            return kthSmallest(array, pivot + 1, right, k - pivot + left - 1);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Quick select algorithm helps find the kth largest element in the unsorted array.
     * Runtime: O(N)
     *
     * @param array The array to be queried.
     * @param left The starting index.
     * @param right The ending index.
     * @param k The k for finding the kth largest element.
     *
     * @return The kth largest element in the array.
     * */
    int kthLargest(int[] array, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            int pivot = partition(array, left, right);

            if (right - pivot == k - 1)
                return array[pivot];

            if (right - pivot > k - 1)
                return kthLargest(array, pivot + 1, right, k);

            return kthLargest(array, left, pivot - 1, k - right + pivot - 1);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * This function takes last element as pivot, places the pivot element
     * at its correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot.
     *
     * @param arr The array to be partitioned.
     * @param low The starting index.
     * @param high The ending index.
     *
     * @return The index of the pivot, which is also the final index of the element in sorted array.
     * */
    private int partition(int[] arr, int low, int high) {
        int right = arr[high];
        int i = (low-1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= right) {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    /**
     * Quick sort algorithm.
     * Runtime: O(N logN)
     *
     * @param array The array to be sorted.
     * @param low The starting index.
     * @param high The ending index.
     * */
    void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot-1);
            quickSort(array, pivot+1, high);
        }
    }
}
