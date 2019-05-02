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

    /** The semaphore for each ingredient type */
    private final Semaphore breadMutex;
    private final Semaphore cheeseMutex;
    private final Semaphore bolognaMutex;

    /** The semaphore for the separate ingredient type messengers */
    private final Semaphore brMessengerMutex;
    private final Semaphore bgMessengerMutex;
    private final Semaphore chMessengerMutex;

    /** The semaphore for signaling the foreman */
    private final Semaphore foremanMutex;

    public Docks() {

        this.foremanMutex = new Semaphore(1);

        this.breadMutex   = new Semaphore(1);
        this.cheeseMutex  = new Semaphore(1);
        this.bolognaMutex = new Semaphore(1);

        this.brMessengerMutex = new Semaphore(1);
        this.bgMessengerMutex = new Semaphore(1);
        this.chMessengerMutex = new Semaphore(1);

    } // end constructor

    public Semaphore getBreadMutex() {
        return breadMutex;
    }

    public Semaphore getCheeseMutex() {
        return cheeseMutex;
    }

    public Semaphore getBolognaMutex() {
        return bolognaMutex;
    }

    public Semaphore getBrMessengerMutex() {
        return brMessengerMutex;
    }

    public Semaphore getBgMessengerMutex() {
        return bgMessengerMutex;
    }

    public Semaphore getChMessengerMutex() {
        return chMessengerMutex;
    }

    public Semaphore getForemanMutex() {
        return foremanMutex;
    }
} // end Docks
