import java.util.concurrent.ThreadLocalRandom;

/**
 * The miner that mines a certain type of food.
 *
 * @author Evert Ball
 * @author Donny Queen
 *
 * @version 05/03/2019
 */
public class Miners implements Runnable {
    
    /** How long a miner can make sandwhich/eat */
    private final static int MAX_TIME = 5000;
    /** The type of miner that this is */
    private String type;

    /** The dock that we are getting the ingredients from */
    private Docks dock;

    /**
     * Creates a new miner for a specified type of ingredient.
     *
     * @param type The type of miner that we are to be.
     * @param dock The docks that we are getting our ingredients from.
     */
    public Miners(String type, Docks dock) {
        this.type = type;
        this.dock = dock;
    }

    /**
     * Starts making sandwiches if we are able to acquire the ingredients
     * needed.
     *
     * Based on the type of miner we are, we ask our messenger if we can
     * have our required ingredients. If we cannot, we wait until we can.
     */
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                switch (this.type) {
                    case "bread":
                        dock.getBrMessengerMutex().acquire();
                        makeSandwiches();
                        break;
                    case "cheese":
                        dock.getChMessengerMutex().acquire();
                        makeSandwiches();
                        break;
                    case "bologna":
                        dock.getBgMessengerMutex().acquire();
                        makeSandwiches();
                        break;
                }
            } catch(InterruptedException ie) {
                return;
            }
        }
        return;
    }

    /**
     * Makes sandwiches for a random amount of milliseconds and prints out
     * that this type of miner is making sandwiches.
     */
    private void makeSandwiches(){

        System.out.println(this.type + " miner is making sandwiches...");
        dock.getForemanMutex().release();

        try{
            Thread.sleep(Math.abs((ThreadLocalRandom.current().nextInt(MAX_TIME))));
           // Thread.sleep(500);
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

        eatSandwiches();

    } // end makeSandwiches

    /**
     * Eats sandwiches for a random amount of milliseconds and prints out
     * that this type of miner is eating sandwiches.
     */
    private void eatSandwiches(){

        System.out.println(this.type + " miner is eating sandwiches...");
        try{
            Thread.sleep(Math.abs((ThreadLocalRandom.current().nextInt(MAX_TIME))));
            //Thread.sleep(500);
        } catch (InterruptedException ie) {
           System.out.println(ie.getMessage());
        }

    } // end eatSandwiches

} // end Miners
