package filesprocessing.Filtering;

public class FilterConstants {

    /**
     *
     */
    public static final int BYTE_CONVERTER = 1024;

    /**
     *
     */
    public static final String GREATER_THAN = "greater_than";

    /**
     *
     */
    public static final String BETWEEN = "between";

    /**
     *
     */
    public static final String SMALLER_THAN = "smaller_than";

    /**
     *
     */
    public static final String FILE = "file";

    /**
     *
     */
    public static final String CONTAINS = "contains";

    /**
     *
     */
    public static final String PREFIX = "prefix";

    /**
     *
     */
    public static final String SUFFIX = "suffix";

    /**
     *
     */
    public static final String WRITABLE = "writable";

    /**
     *
     */
    public static final String EXECUTABLE = "executable";

    /**
     *
     */
    public static final String HIDDEN = "hidden";

    /**
     *
     */
    public static final String ALL = "all";

    /**
     *
     */
    public static final String YES = "YES";

    /**
     *
     */
    public static final String NOT = "NOT";

    /**
     *
     */
    public static final String NO = "NO";

    /**
     *
     */
    public static final String HASH_MARK = "#";

    /**
     *
     */
    public static final String EMPTY_STRING = "";

    /**
     *
     */
    public static final String SIZE = "SIZE";

    /**
     *
     */
    public static final String ABILITY = "ABILITY";

    /**
     *
     */
    public static final String NAME = "NAME";

    /**
     * magic number valuee for the split statement of the input line
     *
     */
    public static final int SPLIT_LIMIT = -1;

    /**
     * index of the section name in the split input line
     */
    public static final int INDEX_OF_SECTION_NAME = 0;

    /**
     * size filtration input minimal length
     */
    public static final int SIZE_FILTRATION_LENGTH = 2;

    /**
     * size filtration input maximal length
     */
    public static final int NEGATE_SIZE_FILTRATION_LENGTH = 3;

    /**
     * negation value index in size filter
     */
    public static final int NEGATION_SIZE_INDEX = 2;

    /**
     * minimal size value for size filters
     */
    public static final double MINIMAL_SIZE_VALUE = 0.0;

    /**
     * index of value to the filter in input line
     */
    public static final int VALUE_INDEX = 1;

    /**
     * range filtration minimal length
     */
    public static final int RANGE_FILTRATION_LENGTH = 3;

    /**
     * range filtration maximal length
     */
    public static final int NEGATE_RANGE_FILTRATION_LENGTH = 4;

    /**
     * index of negative logical value in range filter
     */
    public static final int NEGATION_RANGE_INDEX = 3;

    /**
     * index of first value in range filter
     */
    public static final int FIRST_RANGE_VALUE_INDEX = 1;

    /**
     * index of second value in range filter
     */
    public static final int SECOND_RANGE_VALUE_INDEX = 2;

    /**
     * minimal length of input line in name and ability filters
     */
    public static final int MINIMAL_SUB_FILTER_LENGTH = 2;

    /**
     * maximal length of input line in name and ability filters
     */
    public static final int MAXIMAL_SUB_FILTER_LENGTH = 3;

    /**
     * index of negation value in other filters than size
     */
    public static final int NEGATION_INDEX = 2;

    /**
     * index of logical value in other filters than size
     */
    public static final int LOGICAL_INDEX = 1;

    /**
     * minimal length for default filter
     */
    public static final int DEFAULT_MINIMAL_LENGTH = 1;

    /**
     * maximal length for default filter
     */
    public static final int DEFAULT_MAXIMAL_LENGTH = 2;

    /**
     * logical value of YES or NO or NONE for indicating of an error,
     * using it with our ability predicates.
     */
    enum logicalEnum {
        YES,
        NO,
        NONE;

        /**
         * an enum method to test out if a boolean value is true(equals yes), or false(equals no)
         *
         * @param value boolean value
         * @return true for both hold the same logical value, false otherwise.
         */
        boolean logicalEquals(boolean value) {
            if (value && this.equals(YES)) {
                return true;
            } else if (!value && this.equals(NO)) {
                return true;
            }
            return false;
        }

        /**
         * an enum method to test out if the possibleAnswer given
         *
         * @return true for indeed logical value, false otherwise
         */
        boolean isLogical() {
            if (this.equals(NONE)) {
                return false;
            }
            return (this.equals(YES) || this.equals(NO));
        }
    }
}
