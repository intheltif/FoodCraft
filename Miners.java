import java.util.concurrent.ThreadLocalRandom;

public class Miners implements Runnable {

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
     *
     */
    @Override
    public void run() {
        try {
            switch (this.type) {
                case "bread":
                    dock.getBrMessengerMutex().acquire();
                    break;
                case "cheese":
                    dock.getChMessengerMutex().acquire();
                    break;
                case "bologna":
                    dock.getBgMessengerMutex().acquire();
                    break;
            }
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }

        makeSandwiches();
        eatSandwiches();
    }

    /**
     * Makes sandwiches for a random amount of milliseconds and prints out
     * that this type of miner is making sandwiches.
     */
    private void makeSandwiches(){

        dock.getForemanMutex().release();

        try{
            Thread.sleep((ThreadLocalRandom.current().nextInt()*1000));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println(this.type + " miner is making sandwiches...");

    } // end makeSandwiches

    /**
     * Eats sandwiches for a random amount of milliseconds and prints out
     * that this type of miner is eating sandwiches.
     */
    private void eatSandwiches(){

        try{
            Thread.sleep((ThreadLocalRandom.current().nextInt()*1000));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println(this.type + " miner is eating sandwiches...");

    } // end eatSandwiches

} // end Miners
