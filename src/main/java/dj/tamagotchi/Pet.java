package dj.tamagotchi;

import java.util.function.Consumer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    public void updateStatus(ImageView img) {
        // TODO: find a way to add status effects like sleepy, overfeed etc
        boolean isHungry = appetite <= CRITICAL_VALUE ? true : false;
        boolean isSad = happiness <= CRITICAL_VALUE ? true : false;
        boolean isTired = energy <= CRITICAL_VALUE ? true : false;
        if (isHungry && isSad && isTired) {
            img.setImage(HelloController.perrys.get(States.DEAD));
        } else if (isHungry && isSad) {
            // TODO: add images that are matching
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

    /**
     * 
     * @param img image to change
     * @param onFinishedAction run this action once the timeline is done
     * @param buttons buttons to dissable
     */
    public void eat(ImageView img, Runnable onFinishedAction, Button... buttons) {
        int amount = (int) (Math.random() * 8) + 3;
        for (Button button : buttons) {
            button.setDisable(true);
        }
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(amount), event -> {
            for (Button button : buttons) {
                button.setDisable(false);
            }
            appetite = Math.min(appetite + amount, MAX_VALUE);
            energy = Math.min(energy + (int) (amount / 2), MAX_VALUE);

            updateStatus(img);

            if (onFinishedAction != null) {
                onFinishedAction.run();
            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void play(HelloController controller) {
        controller.mainPane.setVisible(false);
        controller.mainPane.setManaged(false);
        controller.playPane.setVisible(true);
        controller.playPane.setManaged(true);

        controller.infoText.setText("Select the right Perry");
        int spot = (int) (Math.random() * 3) + 1;
        int switchUp = (int) (Math.random() * 2) + 1;
        States fake1 = (switchUp == 1) ? States.FAKE : States.OTHER_FAKE;
        States fake2 = (switchUp == 1) ? States.OTHER_FAKE : States.FAKE;

        switch (spot) {
        case 1:
            controller.chooseImage1.setImage(HelloController.perrys.get(States.CUBE));
            controller.chooseImage2.setImage(HelloController.perrys.get(fake1));
            controller.chooseImage3.setImage(HelloController.perrys.get(fake2));

            controller.chooseImage1.setOnMouseClicked(e -> handlePlayResult(true, controller));
            controller.chooseImage2.setOnMouseClicked(e -> handlePlayResult(false, controller));
            controller.chooseImage3.setOnMouseClicked(e -> handlePlayResult(false, controller));
            break;

        case 2:
            controller.chooseImage1.setImage(HelloController.perrys.get(fake1));
            controller.chooseImage2.setImage(HelloController.perrys.get(States.CUBE));
            controller.chooseImage3.setImage(HelloController.perrys.get(fake2));

            controller.chooseImage1.setOnMouseClicked(e -> handlePlayResult(false, controller));
            controller.chooseImage2.setOnMouseClicked(e -> handlePlayResult(true, controller));
            controller.chooseImage3.setOnMouseClicked(e -> handlePlayResult(false, controller));
            break;

        case 3:
            controller.chooseImage1.setImage(HelloController.perrys.get(fake1));
            controller.chooseImage2.setImage(HelloController.perrys.get(fake2));
            controller.chooseImage3.setImage(HelloController.perrys.get(States.CUBE));

            controller.chooseImage1.setOnMouseClicked(e -> handlePlayResult(false, controller));
            controller.chooseImage2.setOnMouseClicked(e -> handlePlayResult(false, controller));
            controller.chooseImage3.setOnMouseClicked(e -> handlePlayResult(true, controller));
            break;
        }
    }

    private void handlePlayResult(boolean won, HelloController controller) {
        controller.chooseImage1.setOnMouseClicked(null);
        controller.chooseImage2.setOnMouseClicked(null);
        controller.chooseImage3.setOnMouseClicked(null);

        if (won) {
            happiness = Math.min(happiness + 15, MAX_VALUE);
        } else {
            happiness = Math.min(happiness + 2, MAX_VALUE);
        }

        decreaseEnergie(5);

        controller.playPane.setVisible(false);
        controller.playPane.setManaged(false);
        controller.mainPane.setVisible(true);
        controller.mainPane.setManaged(true);
    }

    public void sleep(Consumer<String> onFinishedAction, Button... buttons) {
        int amount = (int) (Math.random() * 10) + 1;
        for (Button button : buttons) {
            button.setDisable(true);
        }
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(amount), event -> {
            for (Button button : buttons) {
                button.setDisable(false);
            }
            energy = Math.min(energy + amount * 5, MAX_VALUE);
            String message = "";
            if (amount < 4) {
                message = "Perry has not slept well";
            } else if (amount < 7) {
                message = "Perry has slept okay";
            } else {
                message = "Perry has slept wonderfull";
            }

            if (onFinishedAction != null) {
                onFinishedAction.accept(message);
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();

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
