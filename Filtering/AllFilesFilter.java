package filesprocessing.Filtering;

import java.io.File;

/**
 * a all filter, which is a sub filter of filter for files, all files match according to this filter.
 *
 * @author rina.karnauch
 */
public class AllFilesFilter implements Filter {

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public boolean test(File fileToFilter) {
        return true;
    }
}
