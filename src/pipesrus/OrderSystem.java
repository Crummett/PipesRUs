package pipesrus;

import java.util.ArrayList;

/**
 * Contains the basket and methods for manipulating it.
 * Used within the OrderForm to store the ongoing order.
 * @author UP744526, UP728600
 */
public class OrderSystem {

    ArrayList<Pipe> basket;

    /**
     * Constructor with no parameters. Initialises the basket.
     */
    public OrderSystem() {
        this.basket = new ArrayList<>();
    }

    /**
     * Attempts to add a pipe with the specified credentials to the basket.
     * Returns false if the pipe is an invalid order and doesn't add it to the
     * basket. Works by making a pipe of each type in turn and using the valid()
     * function. If the pipe is valid, it is added to the basket and the
     * function returns true.
     *
     * @param l The length of the pipe in metres.
     * @param d The diameter of the pipe in inches.
     * @param gO The plastic grade ordered (grade 1-5).
     * @param cO The number of colours ordered (0, 1, or 2).
     * @param iI Whether or not inner insulation has been ordered (boolean).
     * @param oR Whether or not outer reinforcement has been ordered (boolean).
     * @param cR Whether or not chemical resistance has been ordered (boolean).
     * @return True if pipe successfully added, otherwise false.
     */
    public boolean addToBasket(double l, double d, int gO, int cO, boolean iI, boolean oR, boolean cR) {
        boolean pipeAdded = false;
        while (!pipeAdded) {
            Type1Pipe Type1 = new Type1Pipe(l, d, gO, cO, iI, oR, cR);
            if (Type1.valid()) {
                basket.add(Type1);
                pipeAdded = true;
            }
            Type2Pipe Type2 = new Type2Pipe(l, d, gO, cO, iI, oR, cR);
            if (Type2.valid()) {
                basket.add(Type1);
                pipeAdded = true;
            }
            Type3Pipe Type3 = new Type3Pipe(l, d, gO, cO, iI, oR, cR);
            if (Type3.valid()) {
                basket.add(Type1);
                pipeAdded = true;
            }
            Type4Pipe Type4 = new Type4Pipe(l, d, gO, cO, iI, oR, cR);
            if (Type4.valid()) {
                basket.add(Type1);
                pipeAdded = true;
            }
            Type5Pipe Type5 = new Type5Pipe(l, d, gO, cO, iI, oR, cR);
            if (Type5.valid()) {
                basket.add(Type1);
                pipeAdded = true;
            }
            return pipeAdded;
        }
        return pipeAdded;
    }

    /**
     * Returns a long string composed of all of the details strings of the pipes in the basket.
     * Adds a number to the start of each line to identify each item in the order.
     * Uses format '(Number of order) | (Details string) \r\n'
     * @return A long string of the details of the entire order.
     */
    public String returnOrders() {
        String order = "";
        int item = 1;
        for (Pipe i : basket) { // Cycles through the pipes in the basket.
            // Adds the details string to the overall string.
            order = order + item + " | " + i.returnDetails() + "\r\n";
            item++;
        }
        return order;
    }

    /**
     * Same as the returnOrders() method, only the pipes type is included.
     * Used to print the order for Pipes'R'Us.
     * Uses format '(Number of order) | Type: | (Details string) \r\n'
     * @return A long string of the details of the entire order with the pipes type.
     */
    public String returnOrdersWithType() {
        String order = "";
        int item = 1;
        for (Pipe i : basket) { // Cycles through the pipes in the basket.
            // Adds the details string to the overall string.
            order = order + item + " | " + i.returnDetailsWithType() + "\r\n";
            item++;
        }
        return order;
    }

    /**
     * Returns the total order cost rounded to 2dp.
     * Rounded to make pounds/pence.
     * @return The total cost of the order so far.
     */
    public double orderCost() {
        double totalCost = 0;
        for (Pipe i : basket) {
            totalCost = totalCost + i.calcCost();
        }
        return Math.round(totalCost * 100.0) / 100.0;
    }

}