package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import ma.fstt.model.SwitchScene;
import ma.fstt.model.dashboard.Dashboard;
import ma.fstt.model.dashboard.DashboardDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private VBox commandVBox;

    private DashboardDAO dashboardDAO;

    @FXML
    public void switchCommand() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("command.fxml"));
        new SwitchScene(commandVBox , fxmlLoader);
    }



    public DashboardController() {
        try {
            dashboardDAO = new DashboardDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chart() {
        try {
            barChart.setTitle("Tracking des livreurs");
            xAxis.setLabel("Livreur");
            yAxis.setLabel("Taux de command");
            List<Dashboard> dashboardList = dashboardDAO.getAll();
            ObservableList<XYChart.Data<String, Integer>> chartData = FXCollections.observableArrayList();
            for (Dashboard dashboard : dashboardList) {
                chartData.add(new XYChart.Data<>(dashboard.getNom(), dashboard.getId_command().intValue()));
            }
            XYChart.Series<String, Integer> series = new XYChart.Series<>(chartData);
            barChart.getData().add(series);
            series.setName("Livraison");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chart();
    }
}



