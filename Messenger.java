/**
 * The messenger class that sends and receives signals from processes.
 *
 * @author Evert Ball
 * @author Donny Queen
 *
 * @version 05/03/2019
 */
public class Messenger implements Runnable {

    /** The type of messenger that this messenger is */
    private String type;

    /** The dock that has all the ingredients */
    private Docks dock;

    /**
     * Constructs a messenger object that sends and receives signals.
     */
    public Messenger(String type, Docks dock) {

        this.type = type;
        this.dock = dock;


    } // end constructor

    /**
     * Initializes the messenger and attempts gather and release ingredients
     * to its type of miner.
     *
     * For example, if this is a cheese messenger it will attempt to get
     * bologna then bread and send those ingredients to the cheese miner.
     */
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            switch(this.type) {
                case "bread":
                    doBread();
                    break;
                case "cheese":
                    doCheese();
                    break;
                case "bologna":
                    doBologna();
                    break;
            } // end switch
        } // end while

        return;
    } // end initMessenger

    /**
     * If this is a bread messenger, it will attempt to get the other two
     * ingredients (bologna and cheese respectively) and send it to the bread miner.
     *
     * If it can get bologna but it cannot get cheese, it will release the bologna
     * so that another type of messenger may attempt to send its miners their
     * ingredients if they are ready.
     */
    private void doBread() {
        try {
            dock.getBolognaMutex().acquire();

            if (dock.getCheeseMutex().tryAcquire()) {
                System.out.println(this.type + " messenger is waking up " + this.type + " miner");
                dock.getBrMessengerMutex().release();
            } else {
                dock.getBolognaMutex().release();
            }
        } catch (InterruptedException ie) {
//            Thread.currentThread().interrupt();
           System.out.println(ie.getMessage());
        } // end try-catch
    } // end doBread

    /**
     * If this is a cheese messenger, it will attempt to get the other two
     * ingredients (bologna and bread respectively) and send it to the bread miner.
     *
     * If it can get bologna but it cannot get bread, it will release the bologna
     * so that another type of messenger may attempt to send its miners their
     * ingredients if they are ready.
     *
     * If it can get both, it will go ahead and send those ingredients to the miner.
     */
    private void doCheese() {
        try{
            dock.getBolognaMutex().acquire();

            if(dock.getBreadMutex().tryAcquire()) {
                System.out.println(this.type + " messenger is waking up " + this.type + " miner");
                dock.getChMessengerMutex().release();
            } else {
                dock.getBolognaMutex().release();
            }

        } catch (InterruptedException ie) {
  //          Thread.currentThread().interrupt();
            System.out.println(ie.getMessage());
        } // end try-catch
    } // end doCheese

    /**
     * If this is a bologna messenger, it will attempt to get the other two
     * ingredients (bread and cheese respectively) and send it to the bread miner.
     *
     * If it can get bread but it cannot get cheese, it will release the bread
     * so that another type of messenger may attempt to send its miners their
     * ingredients if they are ready.
     *
     * If it can get both, it will go ahead and send those ingredients to the miner.
     */
    private void doBologna() {
        try {
            dock.getBreadMutex().acquire();

            if (dock.getCheeseMutex().tryAcquire()) {
                System.out.println(this.type + " messenger is waking up " + this.type + " miner");
                dock.getBgMessengerMutex().release();
            } else {
                dock.getBreadMutex().release();
            }
        } catch (InterruptedException ie) {
    //        Thread.currentThread().interrupt();
            System.out.println(ie.getMessage());
        } // end try-catch
    } // end doBologna

} // end Messenger
