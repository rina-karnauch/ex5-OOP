package filesprocessing;

/**
 * a filter warning in the warning exceptions.
 *
 * @author rina.karnauch
 */
public class FilterWarning extends Warnings {

    private static final long serialVersionUID = 1L;

    /**
     * a constructor of the exception
     * @param line line number of the warning
     */
    public FilterWarning(int line) {
        super(line);
    }
}
