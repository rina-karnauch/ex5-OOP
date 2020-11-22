package filesprocessing.Filtering;

import java.io.File;

/**
 * a prefix filter, which is a name predicate for files, checks if a file name is the start of another file name.
 *
 * @author rina.karnauch
 */
public class FileNamePrefixedFilter extends NamePredicate{

    /*
    final name to value with
     */
    private String fileValue;

    /**
     * a constructor of the prefix filter predicate
     *
     * @param fileName the name of the file holds the value to test to
     */
    public FileNamePrefixedFilter(String fileName) {
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
        return fileToFilter.getName().startsWith(this.fileValue);
    }
}
