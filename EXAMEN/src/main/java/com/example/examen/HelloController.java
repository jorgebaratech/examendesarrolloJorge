package com.example.examen;

import com.example.examen.clases.Cliente;
import com.example.examen.clases.EntradaTabla;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField metermatricula;
    @FXML
    private SplitMenuButton eleccionmodelo;
    @FXML
    private RadioButton checkstandar;
    @FXML
    private RadioButton chequeoferta;
    @FXML
    private RadioButton cheklargaduracion;
    @FXML
    private DatePicker entradafecha;
    @FXML
    private DatePicker salidafecha;
    @FXML
    private Label Resultadopago;
    @FXML
    private Button botonañadir;
    @FXML
    private Button botonsalir;
    @FXML
    private TableColumn Matriculatabla;
    @FXML
    private TableColumn Modelotabla;
    @FXML
    private TableColumn fechaentregatabla;
    @FXML
    private TableColumn fechasalidatabla;
    @FXML
    private TableColumn cleintetabka;
    @FXML
    private TableColumn tarifatabla;
    @FXML
    private TableColumn costetabla;
    @FXML
    private SplitMenuButton eleccionmodelo2;
    @FXML
    private TableView tabla;


    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void initialize() {

        initializeModeloComboBox();
        initializeClienteChoiceBox();
        initializeTipoTarifaComboBox();


        Matriculatabla.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        Modelotabla.setCellValueFactory(new PropertyValueFactory<>("modeloCoche"));
        cleintetabka.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tarifatabla.setCellValueFactory(new PropertyValueFactory<>("tipoTarifa"));
        fechaentregatabla.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        fechasalidatabla.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        costetabla.setCellValueFactory(new PropertyValueFactory<>("costeTotal"));


        cargarDatosDePrueba();
    }

    private void initializeModeloComboBox() {
        List<String> modelos = Arrays.asList("Modelo1", "Modelo2", "Modelo3");
        eleccionmodelo.getItems().addAll((MenuItem) modelos);
    }

    private void initializeClienteChoiceBox() {
        List<Cliente> clientes = Arrays.asList(
                new Cliente(1, "Cliente1", "cliente1@example.com"),
                new Cliente(2, "Cliente2", "cliente2@example.com")
        );
        eleccionmodelo2.getItems().addAll((MenuItem) clientes);
    }

    private void initializeTipoTarifaComboBox() {
        List<String> tarifas = Arrays.asList("Standard", "Oferta", "Larga duración");
        eleccionmodelo.getItems().addAll((MenuItem) tarifas);
        eleccionmodelo.setValue("Standard");
    }

    private void cargarDatosDePrueba() {

        EntradaTabla entrada1 = new EntradaTabla("lore ipsum");
        EntradaTabla entrada2 = new EntradaTabla("");
        tabla.getItems().addAll(entrada1, entrada2);
    }

    @FXML
    private void handleAnadirButton() {

        String matricula = metermatricula.getText();
        String modelo = eleccionmodelo.getValue();
        Cliente cliente = eleccionmodelo2.getValue();
        String tipoTarifa = eleccionmodelo.getValue();
        LocalDate fechaEntrada = entradafecha.getValue();
        LocalDate fechaSalida = salidafecha.getValue();


        double costeTotal = calcularCosteTotal(tipoTarifa, fechaEntrada, fechaSalida);

        EntradaTabla nuevaEntrada = new EntradaTabla(matricula, modelo, cliente, tipoTarifa, fechaEntrada, fechaSalida, costeTotal);

        tabla.getItems().add(nuevaEntrada);

        limpiarCampos();
    }

    private double calcularCosteTotal(String tipoTarifa, LocalDate fechaEntrada, LocalDate fechaSalida) {


        double precioDiario;

        switch (tipoTarifa) {
            case "Oferta":
                precioDiario = 6.0;
                break;
            case "Larga duración":
                precioDiario = 2.0;
                break;
            default:
                precioDiario = 8.0;
        }

        Period period = Period.between(fechaEntrada, fechaSalida);
        int dias = period.getDays();

        return precioDiario * dias;
    }

    private void limpiarCampos() {
        metermatricula.clear();
        eleccionmodelo.getSelectionModel().clearSelection();
        eleccionmodelo2.getSelectionModel().clearSelection();
        eleccionmodelo.getSelectionModel().clearSelection();
        entradafecha.setValue(null);
        salidafecha.setValue(null);
    }



}