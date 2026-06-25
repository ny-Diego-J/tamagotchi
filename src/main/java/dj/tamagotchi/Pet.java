package dj.tamagotchi;

import javafx.scene.image.ImageView;

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
        energy = Math.min(energy + 10, MAX_VALUE);
    }

    public void decreaseEnergie(int amount) {
        energy = Math.max(energy - amount, 0);
    }

    public void updateStatus(ImageView img) {
        if (energy < 20) {
            img.setImage(HelloController.perrys.get(States.TIRED));
        } else {
            img.setImage(HelloController.perrys.get(States.HAPPY));
        }
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
