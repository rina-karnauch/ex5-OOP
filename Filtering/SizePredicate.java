package filesprocessing.Filtering;

import java.io.File;

/**
 * an abstract class of size predicates for files
 *
 * @author rina.karnauch
 */
public abstract class SizePredicate implements Filter {

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public abstract boolean test(File fileToFilter);
}
