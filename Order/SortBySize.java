package filesprocessing.Order;

import java.io.File;
import java.util.Comparator;

/**
 * a sort object to implement the order interface
 * to sort by file size
 *
 * @author rina.karnauch
 */
public class SortBySize implements OrderInterface{

    /**
     * order files methods by wanted sorting method
     * @param filesToOrder array to order
     * @return the ordered array
     */
    @Override
    public File[] orderFiles(File[] filesToOrder) {
        MergeSort mergeObject = new MergeSort(filesToOrder, getSizeComparator());
        return mergeObject.getSorted();
    }

    /*
    comparator for current sorting by size of files
     */
    private Comparator<File> getSizeComparator() {
        return new Comparator<File>() {
            @Override
            public int compare(File first, File second) {
                double sizeFirst = first.length();
                double sizeSecond = second.length();

                if (sizeFirst < sizeSecond) {
                    return OrderConstants.FIRST_IS_SMALLER;
                } else if (sizeFirst == sizeSecond) {
                    return first.getAbsolutePath().compareTo(second.getAbsolutePath());
                } else {
                    return OrderConstants.FIRST_IS_GREATER;
                }
            }
        };
    }
}
