package filesprocessing;

public class BadCommandFilesError extends Errors {
    private static final long serialVersionUID = 1L;
    private static final String BAD_EXCEPTION_FORMAT = "Bad format of Commands File";

    /**
     * constructor for the checked exception of errors of type 2 in file processing
     */
    public BadCommandFilesError() {
        super(BAD_EXCEPTION_FORMAT);
    }
}
