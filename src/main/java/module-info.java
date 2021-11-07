module com.example.practiceasofour {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.practiceasofour.ui.panels;
    opens com.example.practiceasofour.ui;
    opens com.genesiscode.practiceasofour.views;
    opens com.genesiscode.practiceasofour.views.panels;
}