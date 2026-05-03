module com.garre.appreservasjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.garre.appreservasjavafx to javafx.fxml;
    exports com.garre.appreservasjavafx;
}