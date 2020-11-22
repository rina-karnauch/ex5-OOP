package filesprocessing.Filtering;

import java.io.File;

/**
 * abstract class of ability predicates for files
 *
 * @author rina.karnauch
 */
public abstract class AbilityPredicate implements Filter {

    /**
     * value we'd like to pass this value of size of file
     */
    protected FilterConstants.logicalEnum logicalValue;

    /**
     * a function to test out the wanted ability filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for file contains the ability false otherwise
     */
    public abstract boolean testAbility(File fileToFilter);

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public boolean test(File fileToFilter) {
        boolean abilityBooleanValue = testAbility(fileToFilter);
        return this.logicalValue.logicalEquals(abilityBooleanValue);
    }

}
