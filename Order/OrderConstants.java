package filesprocessing.Order;

/**
 * class of constants for the order package
 *
 * @author rina.karnauch
 */
public class OrderConstants {

    /**
     * for sorting, an equal return value
     */
    public static final int EQUAL = 0;

    /**
     * for sorting, return value for first file is smaller in the order method
     */
    public static final int FIRST_IS_SMALLER = -1;

    /**
     * for sorting, return value for first file is greater in the order method
     */
    public static final int FIRST_IS_GREATER = 1;

    /**
    HASH mark for dividing a line
     */
    public static final String HASH_MARK = "#";

    /**
     * reverse string
     */
    public static final String REVERSE = "REVERSE";

    /**
     * order type, for file types
     */
    public static final String TYPE = "type";

    /**
     * order type, for file absolute path
     */
    public static final String ABS = "abs";

    /**
     * order type, for file sizes
     */
    public static final String SIZE = "size";
}
