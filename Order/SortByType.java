package filesprocessing.Order;

import java.io.File;
import java.util.Comparator;


/**
 * a sort object to implement the order interface
 * to sort by file type
 *
 * @author rina.karnauch
 */
public class SortByType implements OrderInterface {

    /*
    period string to get the ending of the file type
     */
    private static final String PERIOD = ".";

    /**
     * order files methods by wanted sorting method
     *
     * @param filesToOrder array to order
     * @return the ordered array
     */
    @Override
    public File[] orderFiles(File[] filesToOrder) {
        MergeSort mergeObject = new MergeSort(filesToOrder, getTypeComparator());
        return mergeObject.getSorted();
    }

    /*
    comparator for current sorting by file type
     */
    private Comparator<File> getTypeComparator() {
        return new Comparator<File>() {
            @Override
            public int compare(File first, File second) {

                String fileTypeFirst = getExtension(first);
                String fileTypeSecond = getExtension(second);

                int compareValue = fileTypeFirst.compareTo(fileTypeSecond);
                if (compareValue < OrderConstants.EQUAL) {
                    return OrderConstants.FIRST_IS_SMALLER;
                } else if (compareValue == OrderConstants.EQUAL) {
                    return first.getAbsolutePath().compareTo(second.getAbsolutePath());
                } else {
                    return OrderConstants.FIRST_IS_GREATER;
                }
            }
        };
    }


    /*
    a helper function to get the file type
     */
    private String getExtension(File file) {
        String fileType = file.getName();
        int extensionBeginning = fileType.lastIndexOf(PERIOD);
        if (extensionBeginning == -1 || extensionBeginning == 0) {
            return "";
        }
        fileType = fileType.substring(extensionBeginning + 1);
        return fileType;
    }
}
