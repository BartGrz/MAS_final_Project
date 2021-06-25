package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ComboBoxOperations {


    /**
     * Method check if comboBox collection is empty, when true it set its value to null and give possibility for run operation by lambda expression
     * @param comboBox
     * @param comboBoxChecker
     */
    public static void checkIfEmpty(ComboBox comboBox, ComboBoxChecker comboBoxChecker) {

        if(!comboBox.getItems().isEmpty()) {
            comboBox.setValue(null);
            comboBoxChecker.comboBoxChecker();
        }
    }

    /**
     * removing all items from comboBox
     * @param comboBox
     */
    public static void removeAllFrom(ComboBox comboBox) {
        if (!comboBox.getItems().isEmpty()) {
            comboBox.getItems().removeAll(comboBox.getItems());
        }
    }

}
