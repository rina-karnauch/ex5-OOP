package filesprocessing.Order;

import java.io.File;

/**
 * interface of order, which orders the files array according to the needed order kind
 * and holds 3 kinds of sorting possibilities
 */
public interface OrderInterface {

    /**
     * order files methods by wanted sorting method
     * @param filesToOrder array to order
     * @return the ordered array
     */
    File[] orderFiles(File[] filesToOrder);

}
