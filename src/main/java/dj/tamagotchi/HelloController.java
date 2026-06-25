package dj.tamagotchi;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.util.Duration;

public class HelloController {
    private HashMap<String, Image> perrys = new HashMap<>();
    private Pet pet;
    @FXML
    private ImageView petImage;

    @FXML
    private Button playButton;

    @FXML
    public void initialize() {
        perrys.put("happy", new Image(getClass().getResourceAsStream("images/happy.png")));
        perrys.put("hungry", new Image(getClass().getResourceAsStream("images/hungry.png")));
        perrys.put("sad", new Image(getClass().getResourceAsStream("images/sad.png")));
        perrys.put("sleeping", new Image(getClass().getResourceAsStream("images/sleeping.png")));
        perrys.put("tired", new Image(getClass().getResourceAsStream("images/tired.png")));
        petImage.setImage(perrys.get("happy"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> doNothing()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void onPlayButtonClick() {
        // TODO: initiate game
        System.out.println("Todo");
    }

    public void onSleepButtonClick() {
        // TODO: initiate sleep
        petImage.setImage(perrys.get("sleeping"));
    }

    public void onEatButtonClick() {
        // TODO: initiate eating
        System.out.println("Todo");
    }

    private void doNothing() {
    }
}

/*
 * How to dissable button button.setDisable(false)
 */
