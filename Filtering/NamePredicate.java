package filesprocessing.Filtering;

import java.io.File;

/**
 * abstract class of name predicates for files
 *
 * @author rina.karnauch
 */
public abstract class NamePredicate implements Filter {

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public abstract boolean test(File fileToFilter);
}
