module dj.tamagotchi {
    requires javafx.controls;
    requires javafx.fxml;


    opens dj.tamagotchi to javafx.fxml;
    exports dj.tamagotchi;
}