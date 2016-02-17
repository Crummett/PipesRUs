package pipesrus;

/**
 * Abstract class for the pipes ordered by the system.
 * @author UP744526, UP728600
 */
public abstract class Pipe {

    private double length;
    private double diameter;
    final double[] gradeCosts = {0.3, 0.32, 0.35, 0.40, 0.46};
    
    /**
     * The number of colours valid. Set in the inherited classes.
     */
    public int coloursValid;
    private int coloursOrdered;
    /**
     * Array detailing which plastic grades are valid. Set in the inherited classes.
     */
    public int[] gradesValid;
    private int gradeOrdered;
    /**
     * Whether or not inner insulation is allowed. Set in the inherited classes.
     */
    public boolean innerInsulation;
    private boolean inInsOrdered;
    /**
     * Whether or not outer reinforcement is allowed. Set in the inherited classes.
     */
    public boolean outerReinforcement;
    private boolean outReinOrdered;
    private boolean chemResOrdered;
    
    /**
     * Blank constructor.
     */
    public Pipe() {
    }
    
    /**
     * Main constructor for a pipe.
     * All of the requirements for it are specified in the parameters below.
     * @param l The length of the pipe in metres.
     * @param d The diameter of the pipe in inches.
     * @param gO The plastic grade ordered (grade 1-5).
     * @param cO The number of colours ordered (0, 1, or 2).
     * @param iI Whether or not inner insulation has been ordered (boolean).
     * @param oR Whether or not outer reinforcement has been ordered (boolean).
     * @param cR Whether or not chemical resistance has been ordered (boolean).
     */
    public Pipe(double l, double d, int gO, int cO, boolean iI, boolean oR, boolean cR) {
        length = l;
        diameter = d;
        gradeOrdered = gO;
        coloursOrdered = cO;
        inInsOrdered = iI;
        outReinOrdered = oR;
        chemResOrdered = cR;
    }

    /**
     * Sets the integer array of valid plastic grades for use in the valid() method.
     * @param pG Array of valid plastic grades.
     */
    public void setPlasticGrades(int[] pG) {
        gradesValid = pG;
    }

    /**
     * Sets the length of the pipe.
     * @param l Length in metres.
     */
    public void setLength(double l) {
        length = l;
    }

    /**
     * Sets the diameter of the pipe.
     * @param d Diameter in inches.
     */
    public void setDiameter(double d) {
        diameter = d;
    }
    
    /**
     * Checks whether the current pipe is a valid order.
     * Checks the credentials of the ordered pipe set in the parameters of the constructor,
     * against the valid settings defined in each of the type classes.
     * @return True/false as to whether the pipe is valid.
     */
    public boolean valid() {
        boolean validGrade = false;
        for (int i = 0; i < gradesValid.length; i++) {
            if (gradeOrdered == gradesValid[i]) {
                validGrade = true;
            }
        }
        boolean validColours = false;
        if (coloursOrdered == coloursValid) {
            validColours = true;
        }
        boolean inInsValid = false;
        if (inInsOrdered == innerInsulation) {
            inInsValid = true;
        }
        boolean outReinValid = false;
        if (outReinOrdered == outerReinforcement) {
            outReinValid = true;
        }
        /*System.out.println("lets go");
        System.out.println(validGrade);
        System.out.println(validColours);
        System.out.println(inInsValid);
        System.out.println(outReinValid);
        System.out.println(validGrade && validColours && inInsValid && outReinValid); */
        return validGrade && validColours && inInsValid && outReinValid;
    }

    /**
     * Works out the percentage increase on the base cost from the extra requirements.
     * @return The factor for the extra costs.
     */
    private double extraCosts() { 
        double extraCosts = 1;
        if (coloursOrdered == 1) { // Adds 12% if one colour ordered
            extraCosts = extraCosts + 0.12;
        } else if (coloursOrdered == 2) { // Adds 17% if two colours ordered
            extraCosts = extraCosts + 0.17;
        }
        if (inInsOrdered) { // Adds 14% if inner insulation ordered
            extraCosts = extraCosts + 0.14;
        }
        if (outReinOrdered) { // Adds 15% if outer reinforcement ordered
            extraCosts = extraCosts + 0.15;
        }
        if (chemResOrdered) { // Adds 12% if chemical resistance ordered
            extraCosts = extraCosts + 0.12;
        }
        return extraCosts;
    }
    
    /**
     * Calculates the cost of the pipe.
     * @return The cost of the pipe as a double.
     */
    public double calcCost() {
        // Gets the price of the plastic from the gradeCosts array
        double plasticCost = gradeCosts[gradeOrdered - 1];
        // Converts the length from metres to inches
        double lengthInches = length * 39.37;
        // Halves the diameter to get the radius
        double radius = diameter / 2;
        // Gets the inner radius which is 90% of the total radius
        double innerRadius = radius * 0.9;
        
        // Calculates the volume of the whole pipe as a whole cylinder
        double totalVolume = Math.PI * (radius*radius) * lengthInches;
        // Calculates the volume of the empty inner space
        double innerVolume = Math.PI * (innerRadius*innerRadius) * lengthInches;
        // Takes the empty inner space from total cylineder volume to get the 
        // total volume of plastic in the pipe in inches cubed.
        double plasticVolume = totalVolume - innerVolume;
        
        // Gets the final cost
        double cost = plasticCost * extraCosts() * plasticVolume;
        
        return cost;
    }
    
    /**
     * Generates a string containing all of the details of the order.
     * Used in the output text documents and in the order list in the GUI
     * @return Pipe details in the form 'Length: | Diameter: | Plastic Grade: | Colours: | Any extra orders | Cost:
     */
    public String returnDetails() {
        String details = "";
        // Length and diameter rounded to three decimal places in case of converted units.
        details = details + "Length: " + Math.round(length*1000.0)/1000.0 + 
                            "metres | Diameter: " + Math.round(diameter*1000.0)/1000.0 +
                            "inches | Plastic Grade: " + gradeOrdered + 
                            " | Colours: " + coloursOrdered + " | ";
        boolean anythingElse = false;
        // Adds any other elemnts ordered
        if (inInsOrdered) {
            details = details + "Insulation | ";
            anythingElse = true;
        }
        if (outReinOrdered) {
            details = details + "Reinforcement | ";
            anythingElse = true;
        }
        if (chemResOrdered) {
            details = details + "Chemical Resistance | ";
            anythingElse = true;
        }
        if (!anythingElse) { // If no other elements ordered it says so
            details = details + "No other features | ";
        }
        // Returns the cost rounded to 2 decimal places (ie pounds & pence)
        details = details + "Cost: £" + (Math.round(this.calcCost()*100.0)/100.0);
        return details;
    }
    
    // Set in the inherited classes, returns the details string with the pipe type
    // included at the start for use in the order for Pipes'R'Us
    abstract String returnDetailsWithType();

}
