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
    private TextField textFieldDescription;

    @FXML
    private TextArea textFieldGetArray;

    @FXML
    private TextField textFieldGetMaxPriority;

    @FXML
    private TextField textFieldPriority1;

    @FXML
    private TextField textFieldExpirationDate;

    @FXML
    void OnActionDeleteButton(ActionEvent event) {
        if (heapInstance != null && !heapInstance.isEmpty()) {
            task maxTask = heapInstance.extractMax(); // Extrae la tarea con mayor prioridad
            AlertBox alertBox = new AlertBox();
            alertBox.showAlert("Tarea Eliminada", "Tarea con ID: " + maxTask.getId() + " eliminada.", "Descripción: " + maxTask.getDescription(), Alert.AlertType.INFORMATION);
        } else {
            AlertBox alertBox = new AlertBox();
            alertBox.showAlert("Error", "No hay tareas en el heap.", "No se puede eliminar una tarea de un heap vacío.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void OnActionGetArray(ActionEvent event) {
        textFieldGetArray.setText("");
        if (heapInstance != null && !heapInstance.isEmpty()) {
            StringBuilder ids = new StringBuilder();
            for (task t : heapInstance.getHeap()) { // Usamos getHeap para obtener los objetos task
                ids.append(t.getPriority()).append(",");
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
    void OnActionGetMaxPriorityButton(ActionEvent event) {
        if (heapInstance != null && !heapInstance.isEmpty()) {
            task maxTask = heapInstance.getMax(); // Obtiene la tarea con mayor prioridad
            textFieldGetMaxPriority.setText("ID: " + maxTask.getId() + ", Descripción: " + maxTask.getDescription() + ", Prioridad: " + maxTask.getPriority());
        } else {
            textFieldGetMaxPriority.setText("No hay tareas en el heap.");
        }
    }

    @FXML
    void OnActionInsertButton(ActionEvent event) {
        String description = textFieldDescription.getText();
        String priorityText = textFieldPriority1.getText();
        String expirationDate = textFieldExpirationDate.getText();

        try {
            int priority = Integer.parseInt(priorityText);
            if (description == null || description.isEmpty() || priorityText == null || priorityText.isEmpty() || priority < 1 || priority > 10 || expirationDate == null || expirationDate.isEmpty()) {
                AlertBox alertBox = new AlertBox();
                alertBox.showAlert("Error", "Condiciones no cumplidas", "La descripción o fecha de vencimiento no puede estar vacía y la prioridad debe ser un número entero entre 1 y 10", Alert.AlertType.ERROR);
            }
            else {
                AlertBox alertBox = new AlertBox();
                alertBox.showAlert("Notificación", "Tarea añadida con exito", "", Alert.AlertType.INFORMATION);
                task newTask = new task(priority, description, expirationDate);
                heapInstance.insert(newTask);
                textFieldDescription.setText("");
                textFieldPriority1.setText("");
                textFieldExpirationDate.setText("");
            }

        } catch (NumberFormatException e) {
            System.out.println("No se creó la tarea. Asegúrese de las condiciones son correctas");
        }
    }

    @FXML
    void OnActionRestartButton(ActionEvent event) {
        // Reiniciar el heap
        heapInstance = new heap();
        textFieldDescription.setText("");
        textFieldPriority1.setText("");
        textFieldGetArray.setText("");
        textFieldGetMaxPriority.setText("");
        textFieldExpirationDate.setText("");
        AlertBox alertBox = new AlertBox();
        alertBox.showAlert("Reinicio", "El heap ha sido reiniciado.", "Todas las tareas han sido eliminadas.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void initialize() {
        heapInstance = new heap();

    }
}
