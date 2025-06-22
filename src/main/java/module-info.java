module com.example.midtermproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.midtermproject to javafx.fxml;
    exports com.example.midtermproject;
}