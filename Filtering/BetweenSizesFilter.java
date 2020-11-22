package filesprocessing.Filtering;

import java.io.File;

/**
 * a between filter, which is a size predicate for files, checks if a file size is in between two values.
 *
 * @author rina.karnauch
 */
public class BetweenSizesFilter extends SizePredicate {


    /*
     * value we'd like to not pass this value of size of file
     */
    private double upperSizeLimit;

    /*
     * value we'd like to pass this value of size of file
     */
    private double lowerSizeLimit;

    /**
     * constructor for the between filter
     *
     * @param lowerSizeLimit lower size limit
     * @param upperSizeLimit upper size limit
     */
    public BetweenSizesFilter(double lowerSizeLimit, double upperSizeLimit) {
        this.upperSizeLimit = upperSizeLimit;
        this.lowerSizeLimit = lowerSizeLimit;
    }

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public boolean test(File fileToFilter) {
        double lowerInBytes = this.lowerSizeLimit * FilterConstants.BYTE_CONVERTER;
        double upperInBytes = this.upperSizeLimit * FilterConstants.BYTE_CONVERTER;
        double fileSize = fileToFilter.length();
        return (fileSize >= lowerInBytes && fileSize <= upperInBytes);
    }
}
