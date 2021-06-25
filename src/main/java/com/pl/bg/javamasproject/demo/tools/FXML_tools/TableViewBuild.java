package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.scene.control.TableView;
import java.util.List;

public class TableViewBuild {

    /**
     * Adding items to table view from List with wildcard generic type
     * @param tableView
     * @param list
     */
    public static void addFromList(TableView tableView, List<?> list) {

        Looper.forLoop(0,list.size(),i -> tableView.getItems().add(list.get(i)));

    }

    /**
     * Removing all objects from TableView
     * @param tableView
     */
    public static void removeAllFromView(TableView tableView) {

        tableView.getItems().removeAll(tableView.getItems());
    }

    /**
     * Adding a single object to TableView
     * @param tableView
     * @param obj
     */
    public static void addSingleObject(TableView tableView , Object obj) {
        tableView.getItems().add(obj);
    }


}
