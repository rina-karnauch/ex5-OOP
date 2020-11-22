package filesprocessing.Filtering;

import java.io.File;

/**
 * a file filter, which is a name predicate for files, checks if a file name is equal to another file name.
 *
 * @author rina.karnauch
 */
public class FileNameEqualFilter extends NamePredicate {

    /*
    final name to value with
     */
    private String fileValue;

    /**
     * a constructor of the file filter predicate
     *
     * @param fileName the name of the file holds the value to test to
     */
    public FileNameEqualFilter(String fileName) {
        this.fileValue = fileName;
    }

    /**
     * a function to test out the wanted predicate filter on the current file
     *
     * @param fileToFilter file to test
     * @return true for test passed false otherwise
     */
    @Override
    public boolean test(File fileToFilter) {
        return fileToFilter.getName().equals(this.fileValue);
    }
}
