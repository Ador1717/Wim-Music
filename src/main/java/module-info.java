module com.inholland.nl.wimmusic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.inholland.nl.wimmusic to javafx.fxml;
    exports com.inholland.nl.wimmusic;
    exports com.inholland.nl.wimmusic.Model;
    opens com.inholland.nl.wimmusic.Model to javafx.fxml;
    exports com.inholland.nl.wimmusic.Controller;
    opens com.inholland.nl.wimmusic.Controller to javafx.fxml;
}