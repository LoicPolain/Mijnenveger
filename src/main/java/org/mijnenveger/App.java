package org.mijnenveger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mijnenveger");
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL fxmlUrl = getClass().getResource("/fxml/mijnenveger.fxml");
        fxmlLoader.setLocation(fxmlUrl);

        Parent root = fxmlLoader.load();

        root.getStylesheets().add(getClass().getResource("/css/mijnenveger.css").toString());



        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.resizableProperty().set(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}