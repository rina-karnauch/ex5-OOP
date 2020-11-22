package filesprocessing.Order;

import java.io.File;

/**
 * a reverse order of some kind of order to sort by
 *
 * @author rina.karnauch
 */
public class ReverseOrder implements OrderInterface {

    /*
    current order type
     */
    private OrderInterface orderKind;

    /**
     * constructor for a reversed sorting of type order that is given
     * @param orderKind order kind to be reversed
     */
    public ReverseOrder(OrderInterface orderKind) {
        this.orderKind = orderKind;
    }

    /**
     * order files methods by wanted sorting method
     *
     * @param filesToOrder array to order
     * @return the ordered array
     */
    @Override
    public File[] orderFiles(File[] filesToOrder) {
        File[] sortedFiles = orderKind.orderFiles(filesToOrder);
        File[] sortedReversedFiles = new File[sortedFiles.length];

        int j = sortedFiles.length - 1;
        for (int i = 0; i < sortedFiles.length; i++) {
            sortedReversedFiles[i] = sortedFiles[j];
            j--;
        }

        return sortedReversedFiles;
    }
}
