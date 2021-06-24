package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import javafx.scene.control.ComboBox;

public class ComboBoxOperations {

    public static void checkIfEmpty(ComboBox comboBox, ComboBoxChecker comboBoxChecker) {

        if(!comboBox.getItems().isEmpty()) {
            comboBox.setValue(null);
            comboBoxChecker.comboBoxChecker();
        }

    }

}
