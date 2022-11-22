package org.mijnenveger.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.List;

public class MijnenvegerController {

    @FXML
    private GridPane rooster;
    private List<Integer> bommen = new ArrayList<>();

    @FXML
    public void initialize(){
        int btnNr = 0;
        rooster.setHgap(1);
        rooster.setVgap(1);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(30);
        for (int i = 0; i < 10; i++) {
            rooster.getRowConstraints().add(rowConstraints);
        }


        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setMinWidth(30);
        for (int i = 0; i < 10; i++) {
            rooster.getColumnConstraints().add(columnConstraints1);
            float s = (float) (Math.random() * 99);
            bommen.add(Math.round(s));
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button(" ");


                if (bommen.contains(btnNr)){
                    /*Image flag = new Image(getClass().getResourceAsStream("/media/image/icons8-explosive-100.png"));
                    ImageView view = new ImageView(flag);
                    view.setFitHeight(10);
                    view.setFitWidth(10);
                    button.setGraphic(view);*/
                    button.getStyleClass().add("bom");
                }else{
                    button.getStyleClass().add("empty");
                }

                //button.setId(Integer.toString(i) + "-" + Integer.toString(j));
                button.setId(Integer.toString(btnNr++));
                button.setOnAction((n) -> click(n));
                button.setOnMouseClicked((n) -> mark(n));
                button.setMinSize(30, 30);
                rooster.add(button, i, j);
            }
        }

        System.out.println(bommen);
    }

    public void mark(MouseEvent n) {

        Button button = (Button) n.getSource();

        if (button.getText().equals("?")) {
            button.setGraphic(null);
            button.setText(" ");
        }
        else if (button.getText().isEmpty()){
            button.getStyleClass().remove("flag");
            button.setGraphic(null);
            button.setText("?");
        }
        else if (button.getText().isBlank()) {
            Image flag = new Image(getClass().getResourceAsStream("/media/image/red-flag.png"));
            ImageView view = new ImageView(flag);
            button.setText("");
            button.getStyleClass().add("flag");
            view.setFitHeight(15);
            view.setFitWidth(15);
            button.setGraphic(view);
        }
        winCondition();
    }

    public void winCondition(){
        List<String> winConditionLst = new ArrayList<>();
        winConditionLst.add("bom");
        winConditionLst.add("flag");

        rooster.getChildren().forEach((node) -> {
            if (node.getClass().getSimpleName().equals("Button") && node.getStyleClass().containsAll(winConditionLst)) {
                System.out.println("you win!");
            }
        });
    }
    public void click(Event e){
        winCondition();

        Button button = (Button) e.getSource();

        System.out.println(bommen.contains(Integer.parseInt(button.getId())));
        System.out.println(Integer.parseInt(button.getId()));



        if (bommen.contains(Integer.parseInt(button.getId()))){
            Text textGameOver = new Text("Game Over :P");
            rooster.getChildren().clear();
            rooster.getChildren().add(textGameOver);
        }else{
            rooster.getChildren().remove(e.getSource());
            int aantalBommenProximity = 0;
            if (Integer.parseInt(button.getId()) + 1 >= 0 && Integer.parseInt(button.getId()) + 1 < 100 && bommen.contains(Integer.parseInt(button.getId()) + 1)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) + 9 >= 0 && Integer.parseInt(button.getId()) + 9 < 100 && bommen.contains(Integer.parseInt(button.getId()) + 9)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) + 10 >= 0 && Integer.parseInt(button.getId()) + 10 < 100 && bommen.contains(Integer.parseInt(button.getId()) + 10)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) + 11 >= 0 && Integer.parseInt(button.getId()) + 11 < 100 && bommen.contains(Integer.parseInt(button.getId()) + 11)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) - 1 >= 0 && Integer.parseInt(button.getId()) - 1 < 100 && bommen.contains(Integer.parseInt(button.getId()) - 1)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) - 9 >= 0 && Integer.parseInt(button.getId()) - 9 < 100 && bommen.contains(Integer.parseInt(button.getId()) - 9)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) - 10 >= 0 && Integer.parseInt(button.getId()) - 10 < 100 && bommen.contains(Integer.parseInt(button.getId()) - 10)){
                aantalBommenProximity++;
            }
            if (Integer.parseInt(button.getId()) - 11 >= 0 && Integer.parseInt(button.getId()) - 11 < 100 && bommen.contains(Integer.parseInt(button.getId()) - 11)){
                aantalBommenProximity++;
            }
            int x = (int) Math.floor(Integer.parseInt(button.getId()))/10;
            int y = (int) Integer.parseInt(button.getId()) - (x*10);
            System.out.println("x: " + x);
            System.out.println("y: " + y);


            if (aantalBommenProximity != 0){
                Text text = new Text(String.valueOf(aantalBommenProximity));

                switch (aantalBommenProximity){
                    case 1:
                        text.getStyleClass().add("one");
                        break;
                    case 2:
                        text.getStyleClass().add("two");
                        break;
                    case 3:
                        text.getStyleClass().add("three");
                        break;
                    case 4:
                        text.getStyleClass().add("four");
                        break;
                }
                HBox hBox = new HBox(text);
                hBox.setAlignment(Pos.CENTER);
                rooster.add(hBox, x, y);
            }
        }
    }
}
