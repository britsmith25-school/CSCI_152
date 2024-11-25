public class Main {
    public static void main(String[] args) {
        // Initialize world with a spawn rate of 0.05
        World world = new World(0.05);

        // Add initial creatures (10 creatures to start)
        for (int i = 0; i < 10; i++) {
            world.createCreature();
        }

        // Run the simulation
        world.simulate();
    }
}
