package filesprocessing.Order;

import filesprocessing.OrderWarning;

public class OrderFactory {

    private OrderFactory() {
        // wouldn't want such a instance
    }

    /**
     * a order maker factory method to create an order value
     * @param inputLine the input line of the order info
     * @param line line of the order was given from
     * @return the order object
     * @throws OrderWarning warning problem at the info input line
     */
    public static OrderInterface OrderMaker(String inputLine, int line) throws OrderWarning {
        if (inputLine == null) {
            return null;
        }
        return getOrderFactoryHelper(inputLine, line);
    }

    /*
    helper method for the factory of ordered
     */
    private static OrderInterface getOrderFactoryHelper(String inputLine, int line) throws OrderWarning {
        if (inputLine == null) {
            return null;
        }
        boolean reverseFlag = false;
        String[] informationLine = inputLine.split(OrderConstants.HASH_MARK);
        if (informationLine.length < 1 || informationLine.length > 2) {
            throw new OrderWarning(line);
        } else if (informationLine.length == 2) {
            if (!informationLine[1].equals(OrderConstants.REVERSE)) {
                throw new OrderWarning(line);
            } else {
                reverseFlag = true;
            }
        }
        OrderInterface order;
        switch (informationLine[0]) {
            case (OrderConstants.ABS): {
                order = new SortByAbs();
                break;
            }
            case (OrderConstants.TYPE): {
                order = new SortByType();
                break;
            }
            case (OrderConstants.SIZE): {
                order = new SortBySize();
                break;
            }
            default: {
                throw new OrderWarning(line);
            }
        }
        if (reverseFlag) {
            order = new ReverseOrder(order);
        }
        return order;
    }
}
