package dj.tamagotchi;

import javafx.scene.image.ImageView;

public class Pet {
    private int energy;
    private int appetite;
    private int happiness;
    private static int MAX_VALUE = 100;
    private static int CRITICAL_VALUE = 25;

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

    public void decreaseHappines(int amount) {
        happiness = Math.max(happiness - amount, 0);
    }

    public void decreaseAppetite(int amount) {
        appetite = Math.max(appetite - amount, 0);
    }

    public void updateStatus(ImageView img) {
        boolean isHungry = appetite <= CRITICAL_VALUE ? true : false;
        boolean isSad = happiness <= CRITICAL_VALUE ? true : false;
        boolean isTired = energy <= CRITICAL_VALUE ? true : false;
        if (isHungry && isSad && isTired) {
            img.setImage(HelloController.perrys.get(States.DEAD));
        } else if (isHungry && isSad) {
            // TODO: add images
        } else if (isHungry && isTired) {
            img.setImage(HelloController.perrys.get(States.HUNGRY_TIRED));
        } else if (isSad && isTired) {
            // TODO: add images
        } else if (isHungry) {
            img.setImage(HelloController.perrys.get(States.HUNGRY));
        } else if (isSad) {
            img.setImage(HelloController.perrys.get(States.SAD));
        } else if (isTired) {
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
