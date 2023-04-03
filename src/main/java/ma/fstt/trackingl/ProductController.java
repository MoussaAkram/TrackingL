package ma.fstt.trackingl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;
import ma.fstt.model.product.Product;
import ma.fstt.model.product.ProductDAO;
import ma.fstt.model.SwitchScene;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;



public class ProductController implements Initializable {

    @FXML
    private TextField nom ;


    @FXML
    private TextField prix ;
    @FXML
    private TextField description ;


    @FXML
    private TableView<Product> mytable1 ;


    @FXML
    private TableColumn<Product ,Long> col_id ;

    @FXML
    private TableColumn <Product ,String> col_nom ;

    @FXML
    private TableColumn <Product ,Float> col_prix ;
    @FXML
    private TableColumn <Product ,String> col_description ;



    @FXML
    private VBox livreurtVBox ;
    @FXML
    private VBox commandVBox;

    @FXML
    public void switchLivreur() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("livreur.fxml"));
        new SwitchScene(livreurtVBox , fxmlLoader);
    }
    @FXML
    public void switchCommand() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("command.fxml"));
        new SwitchScene(commandVBox , fxmlLoader);
    }





    @FXML
    protected void onSaveButtonClick() {

        String nomText = nom.getText().trim();
        String prixText = prix.getText().trim();
        String descriptionText = description.getText().trim();

        // check if name , prix and description are not empty
        if (nomText.isEmpty() || prixText.isEmpty() || descriptionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }
        // accees a la bdd

        try {
            ProductDAO productDAO = new ProductDAO();

            // check if a product with same name already exists
            List<Product> existingProduct = productDAO.getAll();
            for (Product products : existingProduct) {
                if (products.getNom().equalsIgnoreCase(nomText) ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Ce produit est exists.");
                    alert.showAndWait();
                    return;
                }
            }
            Product product = new Product(0l , nom.getText() , Float.parseFloat(prix.getText()) , description.getText());

            productDAO.save(product);


            UpdateTable();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onUpdateButtonClick() {

        String nomText = nom.getText().trim();
        String prixText = prix.getText().trim();
        String descriptionText = description.getText().trim();

        // check if name , prix and description are not empty
        if (nomText.isEmpty() || prixText.isEmpty()|| descriptionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }
        // accees a la bdd

        try {
            ProductDAO productDAO = new ProductDAO();

            Product product = new Product(0l , nom.getText() , Float.parseFloat(prix.getText()), description.getText());

            productDAO.update(product);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onDeleteButtonClick() {

        String nomText = nom.getText().trim();
        String prixText = prix.getText().trim();
        String descriptionText = description.getText().trim();

        // check if name , prix and description are not empty
        if (nomText.isEmpty() || prixText.isEmpty() || descriptionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Entrer les information.");
            alert.showAndWait();
            return;
        }

        // accees a la bdd

        try {
            ProductDAO productDAO = new ProductDAO();

            // check if a product with the name exist
            List<Product> existingProduct =  productDAO.getAll();
            boolean productExists = false;
            for (Product product : existingProduct) {
                if (product.getNom().equals(nom.getText())) {
                    productExists = true;
                    break;
                }
            }
                if (!productExists) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Product n'existe pas.");
                    alert.showAndWait();
                    return;
                }


            Product product = new Product(0l , nom.getText() , Float.parseFloat(prix.getText()), description.getText());

            productDAO.delete(product);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Product,Long>("id_product"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Product,String>("nom"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Product,Float>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Product,String>("description"));



        mytable1.setItems(this.getDataProducts());
    }
    public void onShowAll(){
        mytable1.setItems(this.getDataProducts());
    }

    public static ObservableList<Product> getDataProducts(){

        ProductDAO productDAO = null;

        ObservableList<Product> listfx = FXCollections.observableArrayList();

        try {
            productDAO = new ProductDAO();
            for (Product ettemp : productDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    public static ObservableList<Product> getOneProduct(String nom) {
        ProductDAO productDAO = null;
        ObservableList<Product> listfx = FXCollections.observableArrayList();
        try {
            productDAO = new ProductDAO();
            Product product = productDAO.getOne(nom);
            if (product != null) {
                listfx.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx;
    }
    public void onGetOneProduct(){
        mytable1.setItems(this.getOneProduct(nom.getText()));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
}

