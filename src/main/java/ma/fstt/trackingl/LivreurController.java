package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import ma.fstt.model.livreur.Livreur;
import ma.fstt.model.livreur.LivreurDAO;
import ma.fstt.model.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LivreurController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField tele ;


    @FXML
    private TableView<Livreur> mytable ;


    @FXML
    private TableColumn<Livreur ,Long> col_id ;

    @FXML
    private TableColumn <Livreur ,String> col_nom ;

    @FXML
    private TableColumn <Livreur ,String> col_tele ;


    @FXML
    private VBox livreurVBox ;
    @FXML
    private VBox commandVBox;


    @FXML
    public void switchProduct() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));
        new SwitchScene(livreurVBox , fxmlLoader);
    }
    @FXML
    public void switchCommand() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("command.fxml"));
        new SwitchScene(commandVBox , fxmlLoader);
    }


    @FXML
    protected void onSaveButtonClick() {

        String nomText = nom.getText().trim();
        String teleText = tele.getText().trim();

        // check if name and telephone are not empty
        if (nomText.isEmpty() || teleText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }
        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            // check if a Livreur with the same name and telephone number already exists
            List<Livreur> existingLivreurs = livreurDAO.getAll();
            for (Livreur livreur : existingLivreurs) {
                if (livreur.getNom().equalsIgnoreCase(nomText) && livreur.getTelephone().equalsIgnoreCase(teleText)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Ce livreur est exists.");
                    alert.showAndWait();
                    return;
                }
            }

            Livreur liv = new Livreur(0l , nom.getText() , tele.getText());

            livreurDAO.save(liv);


            UpdateTable();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onUpdateButtonClick() {

        String nomText = nom.getText().trim();
        String teleText = tele.getText().trim();

        // check if name and telephone are not empty
        if (nomText.isEmpty() || teleText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }
        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            Livreur liv = new Livreur(0l , nom.getText() , tele.getText());
            livreurDAO.update(liv);
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onDeleteButtonClick() {

        String nomText = nom.getText().trim();
        String teleText = tele.getText().trim();

        // check if name and telephone are not empty
        if (nomText.isEmpty() || teleText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }

        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            // check if a Livreur with the name and telephone not exist
            List<Livreur> existingLivreur =  livreurDAO.getAll();
            boolean livreurExists = false;
            for (Livreur livreur : existingLivreur) {
                if (livreur.getNom().equals(nom.getText()) && livreur.getTelephone().equals(tele.getText())) {
                    livreurExists = true;
                    break;
                }
            }
            if (!livreurExists){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Livreur n'existe pas.");
                    alert.showAndWait();
                    return;
                }


            Livreur liv = new Livreur(0l , nom.getText() , tele.getText());
            livreurDAO.delete(liv);
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur,String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur,String>("telephone"));

        mytable.setItems(this.getDataLivreurs());
    }
    public void onShowAll(){
        mytable.setItems(this.getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs(){

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    public static ObservableList<Livreur> getOneLivreur(String nom) {
        LivreurDAO livreurDAO = null;
        ObservableList<Livreur> listfx = FXCollections.observableArrayList();
        try {
            livreurDAO = new LivreurDAO();
            Livreur livreur = livreurDAO.getOne(nom);
            if (livreur != null) {
                listfx.add(livreur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx;
    }
    public void onGetOneLivreur(){
        mytable.setItems(this.getOneLivreur(nom.getText()));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
}