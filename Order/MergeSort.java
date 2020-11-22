package filesprocessing.Order;

import java.io.File;
import java.util.Comparator;

/**
 * class to help us merge sort our file array according to our comparator
 *
 * @author rina.karnauch
 */
public class MergeSort {

    /*
    the array to sort
     */
    private File[] sortedArray;

    /**
     * constructor to create a merging object
     *
     * @param file       the array of files to sort
     * @param comparator the comparator type to sort by
     */
    public MergeSort(File[] file, Comparator<File> comparator) {
        this.sortedArray = file;
        sort(file, comparator);
    }

    /**
     * a method to get the sorted array
     * @return a file array
     */
    protected File[] getSorted() {
        return this.sortedArray;
    }

    /*
    merge helper function
     */
    private void merge(File[] leftArray, File[] rightArray, File[] sorted, Comparator<File> comparator) {

        int leftIndex = 0;
        int rightIndex = 0;
        int sortedIndex = 0;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            int comparingValue = comparator.compare(leftArray[leftIndex], rightArray[rightIndex]);
            if (comparingValue <= OrderConstants.EQUAL) {
                sorted[sortedIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                sorted[sortedIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            sortedIndex++;
        }

        System.arraycopy(leftArray, leftIndex, sorted, sortedIndex, leftArray.length - leftIndex);
        System.arraycopy(rightArray, rightIndex, sorted, sortedIndex, rightArray.length - rightIndex);

        this.sortedArray = sorted.clone();
    }

    /*
    sort merged arrays
     */
    private void sort(File[] filesToOrder, Comparator<File> comparator) {
        if (filesToOrder.length <= 1) {
            return;
        }

        int length = filesToOrder.length;
        File[] leftArray = new File[length / 2];
        File[] rightArray = new File[length - leftArray.length];

        System.arraycopy(filesToOrder, 0, leftArray, 0, leftArray.length);
        System.arraycopy(filesToOrder, leftArray.length, rightArray, 0, rightArray.length);

        sort(leftArray, comparator);
        sort(rightArray, comparator);

        merge(leftArray, rightArray, filesToOrder, comparator);
    }
}
