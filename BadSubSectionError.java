package filesprocessing;

/**
 * bad sub section of filter or order exception
 *
 * @author rina.karnauch
 */
public class BadSubSectionError extends Errors {

    private static final long serialVersionUID = 1L;
    private static final String BAD_ORDER_EXCEPTION = "Bad subsection name";

    /**
     * constructor for the checked exception of errors of type 2 in file processing
     */
    public BadSubSectionError() {
        super(BAD_ORDER_EXCEPTION);
    }
}
