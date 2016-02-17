package pipesrus;

/**
 * Individual class for Type 2 pipes, inherits nearly everything from Pipe.
 * Largely identical to the other Type Pipe classes, but with validity criteria
 * specific to Type 2 pipes.
 * @author UP744526, UP728600
 */
public class Type2Pipe extends Pipe {  
    
    /**
     * Blank constructor.
     * Specific type 2 specs are initialised inside.
     */
    public Type2Pipe() {
        // Integer array of valid pipes
        super.gradesValid = new int[] {2, 3, 4};
        // Integer describing the valid number of colours
        super.coloursValid = 1;
        // Whether or not inner insulation is valid
        super.innerInsulation = false;
        // Whether or not outer reinforcement is valid
        super.outerReinforcement = false;
    }
    
    /**
     * Constructor with the credentials of the pipe.
     * Specific type 2 specs are initialised inside.
     * @param l The length of the pipe in metres.
     * @param d The diameter of the pipe in inches.
     * @param gO The plastic grade ordered (grade 1-5).
     * @param cO The number of colours ordered (0, 1, or 2).
     * @param iI Whether or not inner insulation has been ordered (boolean).
     * @param oR Whether or not outer reinforcement has been ordered (boolean).
     * @param cR Whether or not chemical resistance has been ordered (boolean).
     */
    public Type2Pipe(double l, double d, int gO, int cO, boolean iI, boolean oR, boolean cR) {
        super(l, d, gO, cO, iI, oR, cR);
        // Integer array of valid pipes
        super.gradesValid = new int[] {2, 3, 4};
        // Integer describing the valid number of colours
        super.coloursValid = 1;
        // Whether or not inner insulation is valid
        super.innerInsulation = false;
        // Whether or not outer reinforcement is valid
        super.outerReinforcement = false;
    }
    
    /**
     * Returns a string containing the pipe details as well as the type of the pipe.
     * Uses the returnDetails() method inherited from Pipe.
     * Abstract override puts in the string for this particular pipe type.
     * @return 
     */
    @Override
    String returnDetailsWithType() {
        return "Type: 2 | " + returnDetails();
    }
    
}
