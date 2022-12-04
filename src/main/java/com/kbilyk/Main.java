package com.kbilyk;


import com.kbilyk.graphicalInterface.SceneMainMenu;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Квіти. Визначити ієрархію квітів. Створити кілька об’єктів-квітів.
 * Зібрати букет (використовуючи аксесуари) з визначенням його вартості.
 * Здійснити сортування квітів у букеті на основі рівня свіжості.
 * Знайти квітку в букеті, що відповідає заданому діапазону довжин стебел.
 */

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        SceneMainMenu mainMenu = new SceneMainMenu();
        mainMenu.startMainMenu(stage);
    }
}