package dj.tamagotchi;

import java.util.HashMap;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class HelloController {
    public static HashMap<States, Image> perrys = new HashMap<>();
    private Pet pet = new Pet();
    @FXML
    public ImageView petImage;
    @FXML
    public Button playButton;
    @FXML
    public Button eatButton;
    @FXML
    public Button sleepButton;
    @FXML
    public Label infoText;
    @FXML
    public AnchorPane mainPane;
    @FXML
    public AnchorPane playPane;
    @FXML
    public ImageView chooseImage1;
    @FXML
    public ImageView chooseImage2;
    @FXML
    public ImageView chooseImage3;
    public Timeline gameLoop;
    private Timeline messageTimeline;

    @FXML
    public void initialize() {
        playPane.setVisible(false);
        playPane.setManaged(false);
        mainPane.setVisible(true);
        mainPane.setManaged(true);
        loadImages();
        petImage.setImage(perrys.get(States.HAPPY));
        gameLoop = new Timeline(new KeyFrame(Duration.seconds(3), e -> updatePet()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void pauseGame() {
        if (gameLoop != null) {
            gameLoop.pause();
        }
    }

    public void continueGame() {
        if (gameLoop != null) {
            gameLoop.play();
        }
    }

    private void updatePet() {
        pet.decreaseEnergie((int) (Math.random() * 5) + 1);
        pet.decreaseAppetite((int) (Math.random() * 5) + 1);
        pet.decreaseHappines((int) (Math.random() * 3) + 1);
        pet.updateStatus(petImage, this);
        System.out.println("Energie: " + pet.getEnergy());
        System.out.println("Appetite:" + pet.getAppetite());
        System.out.println("Happiness: " + pet.getHappiness());
        System.out.println("---------------------------------");
    }

    private void loadImages() {
        perrys.put(States.HAPPY, new Image(getClass().getResourceAsStream("images/happy.png")));
        perrys.put(States.HUNGRY, new Image(getClass().getResourceAsStream("images/hungry.png")));
        perrys.put(States.SAD, new Image(getClass().getResourceAsStream("images/sad.png")));
        perrys.put(States.SLEEPING, new Image(getClass().getResourceAsStream("images/sleeping.png")));
        perrys.put(States.TIRED, new Image(getClass().getResourceAsStream("images/tired.png")));
        perrys.put(States.HUNGRY_TIRED, new Image(getClass().getResourceAsStream("images/hungry_tired.jpeg")));
        perrys.put(States.EATING, new Image(getClass().getResourceAsStream("images/eating.png")));
        perrys.put(States.CUBE, new Image(getClass().getResourceAsStream("images/cube.png")));
        perrys.put(States.SAD_HUNGRY, new Image(getClass().getResourceAsStream("images/sad_hungry.jpeg")));
        perrys.put(States.SAD_TIRED, new Image(getClass().getResourceAsStream("images/sad_tired.jpeg")));
    }

    public void onPlayButtonClick() {
        pauseGame();
        pet.play(this);
    }

    public void onSleepButtonClick() {
        pauseGame();
        petImage.setImage(perrys.get(States.SLEEPING));
        pet.sleep((message) -> showMessage(message, () -> {
            this.continueGame();
        }), playButton, eatButton, sleepButton);
    }

    public void onEatButtonClick() {
        pauseGame();
        petImage.setImage(perrys.get(States.EATING));
        pet.eat(petImage, () -> showMessage("Perry has eaten", () -> {
            this.continueGame();
        }), playButton, eatButton, sleepButton);
    }

    public void showMessage(String message) {
        showMessage(message, null);
    }

    public void showMessage(String message, Runnable onFinished) {
        infoText.setText(message);
        if (messageTimeline != null) {
            messageTimeline.stop();
        }

        messageTimeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            infoText.setText("");
            if (onFinished != null) {
                onFinished.run();
            }
        }));
        messageTimeline.setCycleCount(1);
        messageTimeline.play();
    }

    public static Image getRandomPerry() {
        Random random = new Random();

        Object[] values = perrys.values().toArray();

        // 3. Zufälligen Index generieren und Objekt herausholen
        int randomIndex = random.nextInt(values.length);
        return (Image) values[randomIndex];
    }
}
