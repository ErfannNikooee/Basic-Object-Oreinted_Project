package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class pageloader {

    public static Stage stage;

    public static void initStage(Stage primaryStage){
        stage=primaryStage;
        stage.setTitle("Seda Sima");
        stage.getIcons().add(new Image(Paths.get("src/Images/logo.png").toUri().toString()));
        stage.setHeight(675);
        stage.setWidth(400);

    }

    public void load(String url) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        stage.setScene(new Scene(root, 400, 675));
        stage.show();
    }
}
