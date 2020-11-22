package filesprocessing.Order;

import java.io.File;
import java.util.Comparator;

/**
 * a sort object to implement the order interface
 * to sort by file absolute path
 *
 * @author rina.karnauch
 */
public class SortByAbs implements OrderInterface {

    /**
     * order files methods by wanted sorting method
     * @param filesToOrder array to order
     * @return the ordered array
     */
    @Override
    public File[] orderFiles(File[] filesToOrder) {
        MergeSort mergeObject = new MergeSort(filesToOrder, getAbsComparator());
        return mergeObject.getSorted();
    }

    /*
    comparator for current sorting by file absolute path
     */
    private Comparator<File> getAbsComparator() {
        return new Comparator<File>() {
            public int compare(File first, File second) {
                String absPathFirst = first.getAbsolutePath();
                String absPathSecond = second.getAbsolutePath();

                int comparePaths = absPathFirst.compareTo(absPathSecond);
                if (comparePaths < OrderConstants.EQUAL) {
                    return OrderConstants.FIRST_IS_SMALLER;
                } else if (comparePaths == OrderConstants.EQUAL) {
                    return OrderConstants.EQUAL;
                } else {
                    return OrderConstants.FIRST_IS_GREATER;
                }
            }
        };
    }
}
