package com.example.ada1fp.controllers;
import com.example.ada1fp.models.task;
import com.example.ada1fp.alert.AlertBox;
import com.example.ada1fp.models.heap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class heapController {

    private heap heapInstance;
    @FXML
    private TextField textFieldDeleteIndex;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextArea textFieldGetArray;

    @FXML
    private TextField textFieldPriority1;

    @FXML
    private TextField textFieldPriority2;

    @FXML
    private TextField textFieldReplaceIndex;

    @FXML
    void OnActionDeleteButton(ActionEvent event) {
        String indexText = textFieldDeleteIndex.getText();

        try {
            int index = Integer.parseInt(indexText);

            if (index >= 0 && index < heapInstance.size()) {
                // Eliminar el elemento en el índice especificado
                heapInstance.getHeap().remove(index);
                // Mantener las reglas del heap
                heapInstance.maintainHeap();
                textFieldDeleteIndex.setText("");
            } else {
                // Mostrar alerta si el índice no existe
                AlertBox alertBox = new AlertBox();
                alertBox.showAlert("Error", "Índice no válido", "El índice especificado no existe en el heap.", AlertBox.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            // Mostrar alerta si el texto ingresado no es un número válido
            AlertBox alertBox = new AlertBox();
            alertBox.showAlert("Error", "Entrada no válida", "Por favor, ingrese un indice válido.", AlertBox.AlertType.ERROR);
        }
    }

    @FXML
    void OnActionGetArray(ActionEvent event) {
        textFieldGetArray.setText("");
        if (heapInstance != null && !heapInstance.isEmpty()) {
            StringBuilder ids = new StringBuilder();
            for (task t : heapInstance.getHeap()) { // Usamos getHeap para obtener los objetos task
                ids.append(t.getId()).append(",");
            }
            // Eliminar la última coma
            if (ids.length() > 0) {
                ids.setLength(ids.length() - 1);
            }
            textFieldGetArray.setText(ids.toString());
        } else {
            textFieldGetArray.setText("No hay tareas en el heap.");
        }
    }

    @FXML
    void OnActionInsertButton(ActionEvent event) {
        String description = textFieldDescription.getText();
        String priorityText = textFieldPriority1.getText();

        try {
            int priority = Integer.parseInt(priorityText);
            if (description == null || description.isEmpty() || priorityText == null || priorityText.isEmpty() || priority < 1 || priority > 10) {
                AlertBox alertBox = new AlertBox();
                alertBox.showAlert("Error", "Asegure las condiciones", "La descripción no puede estar vacía y la prioridad debe ser un número entre 1 y 10", Alert.AlertType.ERROR);
            }
            else {
                task newTask = new task(priority, description);
                // Add the task to the heap
                heapInstance.insert(newTask);
                // Ensure the heap maintains its rules
                heapInstance.maintainHeap();
                textFieldDescription.setText("");
                textFieldPriority1.setText("");
            }

        } catch (NumberFormatException e) {
            System.out.println("No se creó la tarea. Asegúrese de las condiciones son correctas");
        }

    }

    @FXML
    void OnActionReplaceButton(ActionEvent event) {

    }

    @FXML
    void OnActionRestartButton(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        heapInstance = new heap();

    }
}
