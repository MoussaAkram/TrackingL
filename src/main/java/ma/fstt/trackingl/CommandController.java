package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import ma.fstt.model.SwitchScene;
import ma.fstt.model.command.Command;
import ma.fstt.model.command.CommandDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommandController implements Initializable {


    @FXML
    private TextField date_debut ;


    @FXML
    private TextField date_fin ;

    @FXML
    private TextField km;

    @FXML
    private TextField client ;

    @FXML
    private TextField id_livreur ;

    @FXML
    private TextField id_product ;


    @FXML
    private TableView<Command> mytable2 ;


    @FXML
    private TableColumn<Command ,Long> col_id ;

    @FXML
    private TableColumn <Command ,String> col_date_debut ;

    @FXML
    private TableColumn <Command ,String> col_date_fin ;
    @FXML
    private TableColumn <Command ,Float> col_km ;
    @FXML
    private TableColumn <Command ,String> col_client ;
    @FXML
    private TableColumn<Command ,Long> col_id_livreur ;
    @FXML
    private TableColumn<Command ,Long> col_id_product ;


    @FXML
    private VBox productVBox ;
    @FXML
    private VBox livreurVBox;
    @FXML
    private VBox chartVBox ;

    @FXML
    public void switchLivreur() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("livreur.fxml"));
        new SwitchScene(productVBox , fxmlLoader);
    }

    @FXML
    public void switchProduct() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));
        new SwitchScene(livreurVBox , fxmlLoader);
    }
    @FXML
    public void switchDashboard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        new SwitchScene(chartVBox , fxmlLoader);
    }


    @FXML
    protected void onSaveButtonClick() {

        String date_debutText = date_debut.getText().trim();
        String date_finText = date_fin.getText().trim();
        String kmText = km.getText().trim();
        String clientText = client.getText().trim();
        String id_livreurText = id_livreur.getText().trim();
        String id_productText = id_product.getText().trim();

        // check if information not empty
        if ( date_debutText.isEmpty() || date_finText.isEmpty() || kmText.isEmpty() || clientText.isEmpty() || id_livreurText.isEmpty() || id_productText.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }
        // accees a la bdd

        try {
            CommandDAO commandDAO = new CommandDAO();

            Command command = new Command(0l , date_debut.getText() , date_fin.getText() ,
                    Float.parseFloat(km.getText()) ,client.getText(), Long.parseLong(id_livreur.getText()) ,
                    Long.parseLong(id_product.getText()));

            commandDAO.save(command);


            UpdateTable();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onUpdateButtonClick() {

        String date_debutText = date_debut.getText().trim();
        String date_finText = date_fin.getText().trim();
        String kmText =  km.getText().trim();
        String clientText = client.getText().trim();
        String id_livreurText = id_livreur.getText().trim();
        String id_productText =  id_product.getText().trim();

        // check if information not empty
        if ( date_debutText.isEmpty() || date_finText.isEmpty() || kmText.isEmpty()|| clientText.isEmpty() || id_livreurText.isEmpty() || id_productText.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information..");
            alert.showAndWait();
            return;
        }
        // accees a la bdd

        try {
            CommandDAO commandDAO = new CommandDAO();

            Command command = new Command(0l , date_debut.getText() , date_fin.getText() ,
                    Float.parseFloat(km.getText()) ,client.getText(), Long.parseLong(id_livreur.getText()) ,
                    Long.parseLong(id_product.getText()));
            commandDAO.update(command);
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onDeleteButtonClick() {

        String date_debutText = date_debut.getText().trim();
        String date_finText = date_fin.getText().trim();
        String kmText =  km.getText().trim();
        String clientText = client.getText().trim();
        String id_livreurText = id_livreur.getText().trim();
        String id_productText =  id_product.getText().trim();

        // check if information not empty
        if ( date_debutText.isEmpty() || date_finText.isEmpty() || kmText.isEmpty()|| clientText.isEmpty() || id_livreurText.isEmpty() || id_productText.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }

        // accees a la bdd

        try {
            CommandDAO commandDAO = new CommandDAO();

            Command command = new Command(0l , date_debut.getText() , date_fin.getText() ,
                    Float.parseFloat(km.getText()) ,client.getText(), Long.parseLong(id_livreur.getText()) ,
                    Long.parseLong(id_product.getText()));
            commandDAO.delete(command);
            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Command,Long>("id_command"));
        col_date_debut.setCellValueFactory(new PropertyValueFactory<Command,String>("date_debut"));
        col_date_fin.setCellValueFactory(new PropertyValueFactory<Command,String>("date_fin"));
        col_km.setCellValueFactory(new PropertyValueFactory<Command,Float>("km"));
        col_client.setCellValueFactory(new PropertyValueFactory<Command,String>("client"));
        col_id_livreur.setCellValueFactory(new PropertyValueFactory<Command,Long>("id_livreur"));
        col_id_product.setCellValueFactory(new PropertyValueFactory<Command,Long>("id_product"));


        mytable2.setItems(this.getDataCommands());
    }


    public static ObservableList<Command> getDataCommands(){

        CommandDAO commandDAO = null;

        ObservableList<Command> listfx = FXCollections.observableArrayList();

        try {
            commandDAO = new CommandDAO();
            for (Command ettemp : commandDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    public static ObservableList<Command> getOne(String nom) {
        return null;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
}

