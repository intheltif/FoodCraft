import java.util.concurrent.Semaphore;

/**
 * Represents the shared memory of the program.
 *
 * @author Evert Ball
 * @author Donny Queen
 *
 * @version 05/03/2019
 */
public class Docks {

    public static Semaphore breadMutex;

    public static Semaphore cheeseMutex;

    public static Semaphore bolognaMutex;

    public static Semaphore brMessengerMutex;

    public static Semaphore bgMessengerMutex;
    
    public static Semaphore chMessengerMutex;

    public Docks() {

        this.breadMutex   = new Semaphore(1);
        this.cheeseMutex  = new Semaphore(1);
        this.bolognaMutex = new Semaphore(1);

        this.brMessengerMutex = new Semaphore(1);
        this.bgMessengerMutex = new Semaphore(1);
        this.chMessengerMutex = new Semaphore(1);

    } // end constructor


} // end Docks
