module com.inholland.nl.wimmusic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.inholland.nl.wimmusic to javafx.fxml;
    exports com.inholland.nl.wimmusic;
    exports com.inholland.nl.wimmusic.model;
    opens com.inholland.nl.wimmusic.model to javafx.fxml;
    exports com.inholland.nl.wimmusic.controller;
    opens com.inholland.nl.wimmusic.controller to javafx.fxml;
}