package com.kbilyk.dialog;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InfoDialog {
    /**
     * The method creates info window
     * @param title Title of info window
     * @param message The message in info window
     */
    public static void display(String title, String message){

        Stage window = new Stage();

        // parameters of window
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMaxWidth(100);
        window.setResizable(false);

        // creating text in window
        Label label = new Label();
        label.setText(message);

        // creating ok button that close the window
        Button button = new Button("Ok");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(5);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
}
