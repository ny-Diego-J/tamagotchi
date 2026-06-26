package dj.tamagotchi;

public enum States {
    HAPPY(1), SAD(2), HUNGRY(3), SLEEPING(4), TIRED(5), HUNGRY_TIRED(6), DEAD(7), EATING(8), FAKE(9), OTHER_FAKE(10), CUBE(11), SAD_HUNGRY(12), SAD_TIRED(13);

    private final int number;

    States(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
