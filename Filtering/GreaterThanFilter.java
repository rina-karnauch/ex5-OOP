package filesprocessing.Filtering;

import java.io.File;

/**
 * a greater filter, which is a size predicate for files, checks if a file size is below a specific size.
 *
 * @author rina.karnauch
 */
public class GreaterThanFilter extends SizePredicate {

    /*
     * value we'd like to pass this value of size of file
     */
    private double limitSize;

    /**
     * a constructor of the greaterThan filter predicate
     *
     * @param limitSize the limit size to pass
     */
    public GreaterThanFilter(double limitSize) {
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
        return fileToFilter.length() > FilterConstants.BYTE_CONVERTER * this.limitSize;
    }

}
