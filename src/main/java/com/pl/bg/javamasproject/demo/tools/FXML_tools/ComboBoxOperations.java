package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ComboBoxOperations {

    public static void checkIfEmpty(ComboBox comboBox, ComboBoxChecker comboBoxChecker) {

        if(!comboBox.getItems().isEmpty()) {
            comboBox.setValue(null);
            comboBoxChecker.comboBoxChecker();
        }

    }
    public static void removeAllFrom(ComboBox comboBox) {
        if (!comboBox.getItems().isEmpty()) {
            comboBox.getItems().removeAll(comboBox.getItems());
        }
    }
    public static void removeFromTableViewAndComboBox(TableView tableView , ComboBox comboBox) {

        if(!comboBox.getItems().isEmpty()) {
            comboBox.getItems().removeAll(comboBox.getItems());
            tableView.getColumns().removeAll(tableView.getColumns());
        }

    }

}
