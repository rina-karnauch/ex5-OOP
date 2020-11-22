package filesprocessing;

/**
 * a subclass of the errors exception class, a checked exception of an invalid usage error.
 *
 * @author rina.karnauch
 */
public class InvalidUsageError extends Errors {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_EXCEPTION_FORMAT = "Invalid usage Exception.";

    /**
     * constructor for the invalidUsage exception of errors of type 2 in file processing
     */
    public InvalidUsageError() {
        super(INVALID_EXCEPTION_FORMAT);
    }
}
