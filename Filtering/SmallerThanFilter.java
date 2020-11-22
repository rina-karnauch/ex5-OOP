package filesprocessing.Filtering;

import java.io.File;

/**
 * a smaller filter, which is a size predicate for files, checks if a file size is above a specific size.
 *
 * @author rina.karnauch
 */
public class SmallerThanFilter extends SizePredicate {

    /*
     * value we'd like to no pass this value of size of file
     */
    private double limitSize;

    /**
     * a constructor of the smallerThan filter predicate
     *
     * @param limitSize the limit size to not pass
     */
    public SmallerThanFilter(double limitSize) {
        this.limitSize = limitSize;
    }

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public boolean test(File fileToFilter) {
        return fileToFilter.length() < FilterConstants.BYTE_CONVERTER * this.limitSize;
    }
}
