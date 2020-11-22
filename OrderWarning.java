package filesprocessing;

/**
 * an order warning in the warning exceptions.
 *
 * @author rina.karnauch
 */
public class OrderWarning extends Warnings{

    private static final long serialVersionUID = 1L;

    /**
     * a constructor of the exception
     * @param line line number of the warning
     */
    public OrderWarning(int line) {
        super(line);
    }
}
