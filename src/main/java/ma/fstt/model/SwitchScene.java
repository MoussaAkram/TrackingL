package ma.fstt.model;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;


public class SwitchScene  {

    public SwitchScene(VBox currentVBox , FXMLLoader fxmlLoader) throws IOException {
        VBox  nextVBox  = fxmlLoader.load();
        currentVBox.getChildren().removeAll();
        currentVBox.getChildren().setAll(nextVBox);

    }
}
