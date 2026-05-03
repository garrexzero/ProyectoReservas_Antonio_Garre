package com.garre.appreservasjavafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.LocalTime;

public class HelloController {

    @FXML private TableView<Reserva> tablaReservas;
    @FXML private TableColumn<Reserva, Integer> colIdLocal;
    @FXML private TableColumn<Reserva, LocalDate> colFecha;
    @FXML private TableColumn<Reserva, String> colMotivo;
    @FXML private TableColumn<Reserva, Integer> colPlazas;

    @FXML private DatePicker dpFecha;
    @FXML private TextField txtMotivo;
    @FXML private TextField txtPlazas;

    private ReservaDAO dao = new ReservaDAO();

    @FXML
    public void initialize() {
        // Conexión de columnas con el modelo Reserva
        colIdLocal.setCellValueFactory(new PropertyValueFactory<>("idReservaLocal"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colPlazas.setCellValueFactory(new PropertyValueFactory<>("numeroPlazas"));

        actualizarTabla();
    }

    private void actualizarTabla() {
        // Carga inicial y refresco de datos
        tablaReservas.setItems(FXCollections.observableArrayList(dao.obtenerTodas()));
    }

    @FXML
    protected void onGuardarClick() {
        try {
            // Validamos que los campos no estén vacíos
            if (dpFecha.getValue() == null || txtMotivo.getText().isEmpty() || txtPlazas.getText().isEmpty()) {
                System.out.println("Error: Rellena todos los campos");
                return;
            }

            Reserva r = new Reserva();
            r.setIdRecurso(1); // Valor por defecto para la prueba
            r.setIdUsuario(1);  // Valor por defecto para la prueba
            r.setFecha(dpFecha.getValue());
            r.setMotivo(txtMotivo.getText());
            r.setNumeroPlazas(Integer.parseInt(txtPlazas.getText()));

            // Datos necesarios para que el DAO no de error
            r.setHoraInicio(LocalTime.of(9, 0));
            r.setHoraFin(LocalTime.of(10, 0));
            r.setCoste(0.0);

            // Inserción en la base de datos
            if (dao.crearReserva(r)) {
                actualizarTabla();
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: En 'Plazas' debes poner un número");
        }
    }

    @FXML
    protected void onBorrarClick() {
        // Obtenemos la fila seleccionada para la cancelación
        Reserva seleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            dao.borrarReserva(seleccionada.getIdReservaLocal());
            actualizarTabla();
        } else {
            System.out.println("Selecciona una reserva de la tabla para borrarla");
        }
    }

    private void limpiarCampos() {
        dpFecha.setValue(null);
        txtMotivo.clear();
        txtPlazas.clear();
    }
}