package filesprocessing;

/**
 * a subclass of the Errors exception class, a checked exception- represents an I/O problem.
 *
 * @author rina.karnauch
 */
public class IOError extends Errors {

    private static final long serialVersionUID = 1L;
    private static final String IO_EXCEPTION_FORMAT = "I/O error";

    /**
     * constructor for the I/O problems exception of errors of type 2 in file processing
     */
    public IOError() {
        super(IO_EXCEPTION_FORMAT);
    }
}
