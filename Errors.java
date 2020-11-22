package filesprocessing;

/**
 * a class of a checked error(type 2 errors) in file processing
 * extends of Exception which are checked exceptions, which throws us out of code.
 *
 * @author rina.karnauch
 */
public class Errors extends Exception {

    private static final long serialVersionUID = 1L;
    private static final String ERROR_EXCEPTION_PRINT_FORMAT = "ERROR: ";

    /**
     * constructor for the checked exception of errors of type 2 in file processing
     *
     * @param errorMessage the message of error to print
     */
    public Errors(String errorMessage) {
        super(ERROR_EXCEPTION_PRINT_FORMAT + errorMessage + "\n");
    }
}
