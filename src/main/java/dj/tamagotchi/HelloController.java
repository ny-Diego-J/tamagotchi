package dj.tamagotchi;

import java.util.HashMap;

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

    @FXML
    public void initialize() {
        playPane.setVisible(false);
        playPane.setManaged(false);
        mainPane.setVisible(true);
        mainPane.setManaged(true);
        loadImages();
        petImage.setImage(perrys.get(States.HAPPY));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> updatePet()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updatePet() {
        pet.decreaseEnergie(5);
        pet.decreaseAppetite(5);
        pet.decreaseHappines(5);
        pet.updateStatus(petImage);
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
        perrys.put(States.HUNGRY_TIRED, new Image(getClass().getResourceAsStream("images/hungry_tired.png")));
        perrys.put(States.DEAD, new Image(getClass().getResourceAsStream("images/dead.jpg")));
        perrys.put(States.EATING, new Image(getClass().getResourceAsStream("images/eating.png")));
        perrys.put(States.FAKE, new Image(getClass().getResourceAsStream("images/fake_perry.png")));
        perrys.put(States.OTHER_FAKE, new Image(getClass().getResourceAsStream("images/fake_perry_2.png")));
        perrys.put(States.CUBE, new Image(getClass().getResourceAsStream("images/cube.png")));
    }

    public void onPlayButtonClick() {
        pet.play(this);
    }

    public void onSleepButtonClick() {
        petImage.setImage(perrys.get(States.SLEEPING));
        pet.sleep((message) -> showMessage(message), playButton, eatButton, sleepButton);
    }

    public void onEatButtonClick() {
        petImage.setImage(perrys.get(States.EATING));
        pet.eat(petImage, () -> showMessage("Perry has eaten"), playButton, eatButton, sleepButton);
    }

    private void showMessage(String message) {
        infoText.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            infoText.setText("");
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
