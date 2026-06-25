package dj.tamagotchi;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.util.Duration;

public class HelloController {
    public static HashMap<States, Image> perrys = new HashMap<>();
    private Pet pet = new Pet();
    @FXML
    public ImageView petImage;

    @FXML
    private Button playButton;

    @FXML
    public void initialize() {
        perrys.put(States.HAPPY, new Image(getClass().getResourceAsStream("images/happy.png")));
        perrys.put(States.HUNGRY, new Image(getClass().getResourceAsStream("images/hungry.png")));
        perrys.put(States.SAD, new Image(getClass().getResourceAsStream("images/sad.png")));
        perrys.put(States.SLEEPING, new Image(getClass().getResourceAsStream("images/sleeping.png")));
        perrys.put(States.TIRED, new Image(getClass().getResourceAsStream("images/tired.png")));
        petImage.setImage(perrys.get(States.HAPPY));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> updatePet()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void onPlayButtonClick() {
        pet.play();
    }

    public void onSleepButtonClick() {
        pet.sleep();
    }

    public void onEatButtonClick() {
        pet.eat();
    }

    private void updatePet() {
        pet.decreaseEnergie(5);
        pet.updateStatus(petImage);
    }
}

/*
 * How to dissable button button.setDisable(false)
 */
