package filesprocessing.Filtering;

import java.io.File;

/**
 * a functional interface to filter out files, by given wanted filter to apply to them.
 */
@FunctionalInterface
public interface Filter{

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    boolean test(File fileToFilter);
}
