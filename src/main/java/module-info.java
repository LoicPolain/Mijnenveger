module Mijnenveger {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.fxml;

    exports org.mijnenveger.controller;
    exports org.mijnenveger;

    opens org.mijnenveger to javafx.controls, javafx.graphics, javafx.media, javafx.fxml;
    opens org.mijnenveger.controller to javafx.controls, javafx.graphics, javafx.media, javafx.fxml;
}