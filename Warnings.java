package filesprocessing;

/**
 * a class of an unchecked error(type 1 errors) in file processing
 * extends of RunTimeException which are unchecked exceptions, handled in our code and not throwable.
 *
 * @author rina.karnauch
 */
public class Warnings extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String WARNING_EXCEPTION_PRINT_FORMAT = "Warning in line ";

    /**
     * a constructor of the exception
     *
     * @param line line number of the warning
     */
    public Warnings(int line) {
        super(WARNING_EXCEPTION_PRINT_FORMAT + line);
    }
}
