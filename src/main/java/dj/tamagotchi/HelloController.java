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
        perrys.put(States.HUNGRY_TIRED, new Image(getClass().getResourceAsStream("images/hungry_tired.png")));
        perrys.put(States.DEAD, new Image(getClass().getResourceAsStream("images/dead.jpg")));
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
        pet.decreaseAppetite(5);
        pet.decreaseHappines(5);
        pet.updateStatus(petImage);
        System.out.println("Energie: " + pet.getEnergy());
        System.out.println("Appetite:" + pet.getAppetite());
        System.out.println("Happiness: " + pet.getHappiness());
        System.out.println("---------------------------------");
    }
}

/*
 * How to dissable button button.setDisable(false)
 */
