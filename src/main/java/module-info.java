module com.celsius.notas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.celsius.notas to javafx.fxml;
    exports com.celsius.notas;
}
