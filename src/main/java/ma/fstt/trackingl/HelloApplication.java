package ma.fstt.trackingl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("livreur.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/livraison.png")));
        stage.setTitle("Tracking des livreurs!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}