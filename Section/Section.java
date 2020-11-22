package filesprocessing.Section;

import filesprocessing.Filtering.*;
import filesprocessing.Order.*;

import java.util.ArrayList;

/**
 * a class which represents a section of file ordering- filter and an order.
 */
public class Section {

    private Filter filter;
    private OrderInterface order;
    private ArrayList<String> warnings = new ArrayList<String>();

    /**
     * a constructor for the section part
     * @param filter given filter of the section
     * @param order given order of the section
     * @param filterWarning warning from the filter construction
     * @param orderWarning warning from the section construction
     */
    public Section(Filter filter, OrderInterface order, String filterWarning, String orderWarning) {
        this.filter = filter;
        this.order = order;
        if(filterWarning != null) {
            warnings.add(filterWarning);
        }
        if(orderWarning != null)
        {
            warnings.add(orderWarning);
        }
    }


    /**
     * getter function for the filter of that section
     * @return the filter of that section in file
     */
    public Filter getFilter() {
        return this.filter;
    }

    /**
     * getter function for the order of that section
     * @return order of a section in file
     */
    public OrderInterface getOrder() {
        return this.order;
    }

    /**
     * a getter function for the warnings of the section
     *
     * @return current warnings for this section
     */
    public ArrayList<String> getWarnings() {
        return this.warnings;
    }

}
