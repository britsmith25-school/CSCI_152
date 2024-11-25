import java.util.Random;

public class Creature {
    private String name;
    private int age;
    private double chanceToReproduce;
    private double chanceToDie;

    public Creature(String name, double chanceToReproduce, double chanceToDie) {
        this.name = name;
        this.age = 0;
        this.chanceToReproduce = chanceToReproduce;
        this.chanceToDie = chanceToDie;
    }

    // Returns True if the creature dies based on chance
    public boolean die() {
        Random rand = new Random();
        return rand.nextDouble() < chanceToDie;
    }

    // Reproduce only if another mate is available and the chance of reproduction succeeds
    public Creature reproduce(Creature mate) {
        Random rand = new Random();
        if (rand.nextDouble() < chanceToReproduce && mate != null && mate != this) {
            return new Creature(name + "_child", this.chanceToReproduce, this.chanceToDie);
        }
        return null;
    }

    // Increment creature's age
    public void ageOneYear() {
        this.age++;
    }

    public String getName() {
        return this.name;
    }
}
