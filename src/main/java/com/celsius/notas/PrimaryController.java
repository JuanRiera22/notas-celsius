package com.celsius.notas;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;

public class PrimaryController {
  @FXML
  private ListView<Nota> listaNotas;

  @FXML
  private TextField textoTitulo;

  @FXML
  private TextArea textoContenido;

  @FXML
  private Button botonGuardar;
   
  private Nota notaSeleccionada;

  @FXML
  void salir(ActionEvent event) {
    Alert confirmacion = new Alert(
        AlertType.CONFIRMATION,
        "¿Está seguro de que desea salir?",
        new ButtonType("Cancelar"),
        new ButtonType("Salir"));
    // Modificamos el título y texto de cabecera del Alert
    confirmacion.setTitle("Confirmación");
    confirmacion.setHeaderText("Confirmación");

    // respuesta -> {} es una función lambda, que es una
    // función anónima. Es un concepto complejo que no vamos
    // a ver en el curso.
    confirmacion.showAndWait().ifPresent(respuesta -> {
      // Si el ButtonType tiene el texto "Salir"
      // cerramos la aplicación
      if (respuesta.getText().equals("Salir")) {
        // Método para salir de una aplicación de JavaFX
        Platform.exit();

        // Método para terminar un proceso de Java
        // El parámetro es el código de error. Si no hay
        // error, va 0.
        System.exit(0);
      }
    });
  }

  @FXML
  void acercaDe(ActionEvent event) {
    // Mostrar un Alert con información acerca del
    // programa.

    // Ej: Celsius Notas v1.0
    // Desarrollado por Mariano Córdoba
  }

  @FXML
  void eliminarNota(ActionEvent event) {

    BaseDeDatosSingleton.getInstancia().getBd().eliminar(notaSeleccionada);
    sincronizar();
    limpiarSeleccion();

  }

  @FXML
  void crearNota(ActionEvent event) {
    Nota nota = new Nota("Nota sin título", "");
    //BaseDeDatosSingleton.getInstancia().getBd().guardar(nota);
    BaseDeDatosSingleton instancia = BaseDeDatosSingleton.getInstancia();
    IBaseDeDatos bd = instancia.getBd();
    bd.guardar(nota);

    sincronizar();
  }

  @FXML
  void seleccionarNota(MouseEvent event) {
    notaSeleccionada = listaNotas.getSelectionModel().getSelectedItem();
    botonGuardar.setDisable(false);
    textoTitulo.setText(notaSeleccionada.getTitulo());
    textoTitulo.setDisable(false);
    textoContenido.setText(notaSeleccionada.getContenido());
    textoContenido.setDisable(false);

  }

  private void sincronizar() {
    listaNotas.getItems().clear();
    listaNotas.getItems().addAll(
      BaseDeDatosSingleton.getInstancia().getBd().listar()
    );
  }

  private void limpiarSeleccion() {
    notaSeleccionada = null;
    botonGuardar.setDisable(true);
    textoTitulo.setText("");
    textoTitulo.setDisable(true);
    textoContenido.setText("");
    textoContenido.setDisable(true);
  }

  @FXML
  void guardarNota(ActionEvent event) {
    notaSeleccionada.setTitulo(textoTitulo.getText());
    notaSeleccionada.setContenido(textoContenido.getText());

    BaseDeDatosSingleton.getInstancia().getBd().guardar(notaSeleccionada);
    sincronizar();
  }

  
}
