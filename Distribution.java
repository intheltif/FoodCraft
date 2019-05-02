import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

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
    private static int timeToRun;
    
    /**
     * The main entry point to the program
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length != 2) {
            System.out.println("Usage is: java Distribution <time_to_run> <T|F>");
            System.exit(1);
        }

        timeToRun = (Integer.parseInt(args[0]) * 1000);
        System.out.println(args[1]);
        if(args[1].toLowerCase().equals("t")) {
            
            //TODO print output to log.txt
            System.out.println(args[1]); 
            //Time must be positive to write to file.
            if(timeToRun <= 0) {
                System.out.println("Time must be positive to output to a file");
                System.exit(1);
            } else {
                PrintStream out;
                try {
                    out = new PrintStream("./log.txt");
                    System.setOut(out);
                } catch (FileNotFoundException fnfe) {
                    System.out.println("File Could Not Be Found or Created...");
                    System.exit(1);
                }
            }
        } 
        else {

            if(timeToRun <= 0) {
                timeToRun = 0;
            }
        } // end outter-most if-else

        Distribution distributor = new Distribution();
        distributor.distribute();


    } // end main()

    /**
     * What actually runs our program.
     */
    private void distribute() {
        Thread t;
        ArrayList<Miners> miners = new ArrayList<>();
        ArrayList<Messenger> messengers = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        //Create the dock and the foreman.
        Docks dock = new Docks();
        Foreman foreman = new Foreman(dock);

        // Create the three types of miners.
        Miners cheeseMiner = new Miners("cheese", dock);
        Miners breadMiner = new Miners("bread", dock);
        Miners bolognaMiner = new Miners("bologna", dock);

        miners.add(cheeseMiner);
        miners.add(breadMiner);
        miners.add(bolognaMiner);

        // Create the three messenger types.
        Messenger cheeseMessenger = new Messenger("cheese", dock);
        Messenger breadMessenger = new Messenger("bread", dock);
        Messenger bolognaMessenger = new Messenger("bologna", dock);

        messengers.add(cheeseMessenger);
        messengers.add(breadMessenger);
        messengers.add(bolognaMessenger);

        //Starts the foreman thread.
        t = new Thread(foreman);
        threads.add(t);
        t.start();
        //Starts the each type of miner thread.
        for (Miners miner : miners) {
            t = new Thread(miner);
            threads.add(t);
            t.start();
        }

        //Starts the each type of messenger thread.
        for (Messenger messenger : messengers) {
            t = new Thread(messenger);
            threads.add(t);
            t.start();
        }
        System.out.println("Time to run: " + timeToRun);
        try {
            if(timeToRun == 0){
                for(;;){
                    Thread.currentThread().sleep(Long.MAX_VALUE);
                }
            }
            else{
                Thread.currentThread().sleep(timeToRun);
            }
            for(Thread thread: threads){
                    thread.interrupt();
            }
            for(Thread thread : threads) {
                thread.join();
            }
        } catch(InterruptedException ie) {
            return;
        }

        System.exit(0);

    }

}
