/**
 * The Driver of the program. Confirms that all command line input exists 
 * and is correct, then determines which direction to take based on above
 * mentioned command line input.
 *
 * @author Evert Ball
 * @author Donny Queen
 *
 * @version 05/03/2019
 */
public class Distribution {
    
    /** The time to run the program */
    private int timeToRun;
    
    /**
     * The main entry point to the program
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("\u001b[43m"); //bg yellow
        System.out.println("\u001b[30m"); //text black
        System.out.println("Class Under Construction");
        System.out.println("\u001b[0m"); //reset colors.

        if(args.length < 3) {
            System.out.println("Usage is: java Distribution <time_to_run> <T|F>");
            System.exit(1);
        }

        this.timeToRun = Integer.parseInt(args[1]);

        if(args[2].toLowerCase() == "t") {
            
            //TODO print output to log.txt
            
            //Time must be positive to write to file.
            if(this.timeToRun < 0) {
                System.out.println("Time must be positive to output to a file");
                System.exit(1);
            }
        } 
        else {
            //TODO print output to stdout

            if(timeToRun <= 0) {
                //TODO run forever (until ctrl + c)
            }
        } // end outter-most if-else


    } // end main()

}
