package dj.tamagotchi;

public class Pet {
    private int energy;
    private int appetite;
    private int happiness;
    private static int MAX_VALUE = 100;

    public Pet() {
        this.energy = MAX_VALUE;
        this.appetite = MAX_VALUE;
        this.happiness = MAX_VALUE;
    }

    public void eat() {
        appetite = Math.min(appetite + 10, MAX_VALUE);
    }

    public void play() {
        happiness = Math.min(happiness + 10, MAX_VALUE);
    }

    public void sleep() {
        appetite = Math.min(appetite + 10, MAX_VALUE);
    }

    public int getEnergy() {
        return energy;
    }

    public int getAppetite() {
        return appetite;
    }

    public int getHappiness() {
        return happiness;
    }

    public static void setMaxValue(int maxValue) {
        MAX_VALUE = maxValue;
    }
}
