package filesprocessing.Filtering;

import java.io.File;

/**
 * writable filter, which is an ability predicate for a file, checks if file has a writing permission
 *
 * @author rina.karnauch
 */
public class WriteableFilter extends AbilityPredicate {

    /**
     * a constructor of the writable filter predicate
     *
     * @param logicalValue logical value yes or no of enum, to determine if we want it to be with the ability or not
     */
    public WriteableFilter(FilterConstants.logicalEnum logicalValue) {
        this.logicalValue = logicalValue;
    }

    /**
     * a function to test out the wanted ability filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for file contains the ability false otherwise
     */
    public boolean testAbility(File fileToFilter) {
        return fileToFilter.canWrite();
    }
}
