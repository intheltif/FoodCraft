import java.util.ArrayList;
import java.util.Random;
/**
 * Foreman class for FoodCraft project; This class is woken up by a miner, picks
 * two random ingredients, and signals the Docks that they are available
 * to be used. 
 *
 * @author - Donald Queen
 * @author - Evert Ball
 * @version - May 3, 2019
 */
public class Foreman implements Runnable {

    /** Named constant for the number of ingredients we have */ 
    private static final int NUM_OF_INGREDIENTS = 3;	
	
    /** ArrayList that holds the food */
    ArrayList<String> food;

    /** Docks class that is the shared memory between all other classes */
    Docks dock;

    /**
     * Constructor that takes the dock that represents the shared memory
     * @param dock - shared memory
     */
    public Foreman(Docks dock){
        this.dock = dock;
        this.food = new ArrayList<>();
    }

    /**
     * Run method that has the Foreman trying to constantly acquire the foreman
     * mutex from the dock; Once it can acquire that mutex, it picks two random
     * ingredients and signals the dock that those ingredients are able to be
     * used.
     */
    @Override
    public void run() {
        /* TODO: 
         * Pick two supplies randomly, the two supplies will always be different from each other.
         * Signal the 'docks' indicating supplies are ready for EACH type of supply chosen.
         * Foreman will NOT send any more supplies of any kind until the two picked have been
         * consumed.
         */
        while(!Thread.currentThread().isInterrupted()){

            dock.getForemanMutex().acquire();
            System.out.println("Foreman is awake!");
            this.food = new ArrayList<>();
            this.food.add("cheese");
            this.food.add("bread");
            this.food.add("bologna");
            String foodPicked;
            Random rand;
            int choice;
            for(int i = 0; i < NUM_OF_INGREDIENTS; i++){
                choice = rand.nextInt(food.length());
                foodPicked = food.remove(choice); //removes the ingredient from the Arraylist
                                                  // so it cannot be used again
                switch(foodPicked){
                case "bread":
                    System.out.println("Foreman is sending bread!");
                    dock.getBreadMutex().release();
                    break;

                case "bologna":
                    System.out.println("Foreman is sending bologna!");
                    dock.getBolognaMutex().release();
                    break;
        
                case "cheese":
                    System.out.println("Foreman is sending cheese!");
                    dock.getCheeseMutex().release();
                    break;

                }      
            }
            System.out.println("Foreman is done! Time for a break");
        }
    }
}

