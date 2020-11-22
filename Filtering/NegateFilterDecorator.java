package filesprocessing.Filtering;


import java.io.File;

/**
 * abstract class of a negate decorator for a filter predicates for files
 *
 * @author rina.karnauch
 */
public class NegateFilterDecorator implements Filter {

    /*
    filter to negate
     */
    private Filter filter;

    /**
     * a constructor for the negate decorator of the filter.
     *
     * @param filter the filter to negate.
     */
    public NegateFilterDecorator(Filter filter) {
        this.filter = filter;
    }

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public boolean test(File fileToFilter) {
        return !this.filter.test(fileToFilter);
    }

}
