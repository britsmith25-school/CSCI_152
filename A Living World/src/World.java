import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class World implements Spawner {
    private List<Creature> creatures;
    private double spawnRate;
    private final int MAX_CREATURES = 100;  // Stop the simulation if the world exceeds this many creatures.
    private final Scanner scanner = new Scanner(System.in); // To read user input

    public World(double spawnRate) {
        this.creatures = new ArrayList<>();
        this.spawnRate = spawnRate;
    }

    // Create a new creature and add to the world
    @Override
    public void createCreature() {
        String name = "Creature_" + (creatures.size() + 1);
        creatures.add(new Creature(name, 0.3, 0.1));
    }

    // Determines if a new creature should spawn
    public boolean rollSpawn() {
        Random rand = new Random();
        return rand.nextDouble() < spawnRate;
    }

    // Method to handle a "World-Ending Event" (Resets all population to 0)
    private void worldEndingEvent() {
        System.out.println("World-Ending Event triggered! All creatures have been reset to 0.");
        creatures.clear();
    }

    // Method to handle "Reduction of Creature" (Reduces the population to 2)
    private void reductionOfCreature() {
        System.out.println("Reduction of Creature triggered! Population reduced to 2.");
        while (creatures.size() > 2) {
            creatures.remove(creatures.size() - 1);
        }
    }

    // Main loop to update creatures and their behaviors
    public void simulate() {
        int iteration = 0;

        // Run for up to 100 iterations or until creature population stabilizes
        while (iteration < 100 && creatures.size() > 0) {  // Avoid running indefinitely if population reaches 0
            System.out.println("--- Iteration " + iteration + " ---");

            // Loop through each creature
            for (Creature creature : new ArrayList<>(creatures)) {  // Use copy to avoid modification during iteration
                creature.ageOneYear();

                // Check if the creature dies
                if (creature.die()) {
                    System.out.println(creature.getName() + " has died.");
                    creatures.remove(creature);
                } else {
                    // Reproduce if successful with a mate
                    if (creatures.size() > 1) {
                        Creature mate = findMate(creature);  // Find a mate for reproduction
                        if (mate != null) {
                            Creature newCreature = creature.reproduce(mate);  // Reproduce with the mate
                            if (newCreature != null) {
                                System.out.println(creature.getName() + " reproduced with " + mate.getName() + " to create " + newCreature.getName() + ".");
                                creatures.add(newCreature);
                            }
                        }
                    }
                }

                // If population hits 100, prompt user for action
                if (creatures.size() == 100) {
                    System.out.println("Creature population has reached 100!");

                    // Ask user to choose an action
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("Would you like to trigger a World-Ending Event (reset population to 0) or a Reduction of Creature (reduce population to 2)?");
                        System.out.println("Enter '1' for World-Ending Event or '2' for Reduction of Creature:");
                        String userInput = scanner.nextLine();

                        if (userInput.equals("1")) {
                            worldEndingEvent();  // Reset population to 0
                            validInput = true;
                        } else if (userInput.equals("2")) {
                            reductionOfCreature();  // Reduce population to 2
                            validInput = true;
                        } else {
                            System.out.println("Invalid input! Please enter '1' or '2'.");
                        }
                    }
                }

                // If population is too high, stop adding more creatures
                if (creatures.size() > MAX_CREATURES) {
                    System.out.println("Maximum creature population reached. Stopping reproduction.");
                    break;
                }
            }

            // Spawn new creature naturally (but ensure max limit isn't exceeded)
            if (rollSpawn() && creatures.size() < MAX_CREATURES) {
                createCreature();
                System.out.println("A new creature spawned naturally!");
            }

            iteration++;
        }

        if (creatures.size() == 0) {
            System.out.println("All creatures have died. Simulation stopped.");
        } else {
            System.out.println("Simulation complete. Final creature count: " + creatures.size());
        }
    }

    // Find a mate for reproduction (simple random selection from the list)
    private Creature findMate(Creature creature) {
        Random rand = new Random();
        Creature mate = null;

        // Try finding a mate from the remaining creatures
        while (mate == null && creatures.size() > 1) {
            int index = rand.nextInt(creatures.size());
            mate = creatures.get(index);

            // Ensure it's not the same creature and check the mate's status
            if (mate != creature && !mate.die()) {
                break;
            } else {
                mate = null;
            }
        }

        return mate;
    }
}
