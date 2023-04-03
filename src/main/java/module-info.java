module ma.fstt.trackingl {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;


    opens ma.fstt.trackingl to javafx.fxml;
    exports ma.fstt.trackingl;

    opens ma.fstt.model.base to javafx.fxml;
    exports ma.fstt.model.base;

    opens ma.fstt.model.livreur to javafx.fxml;
    exports ma.fstt.model.livreur;

    opens ma.fstt.model.product to javafx.fxml;
    exports ma.fstt.model.product;

    opens ma.fstt.model.command to javafx.fxml;
    exports ma.fstt.model.command;

    opens ma.fstt.model to javafx.fxml;
    exports ma.fstt.model;

    opens ma.fstt.model.dashboard to javafx.fxml;
    exports ma.fstt.model.dashboard;

}



