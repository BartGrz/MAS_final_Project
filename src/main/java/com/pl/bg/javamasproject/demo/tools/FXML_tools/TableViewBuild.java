package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.scene.control.TableView;
import java.util.List;

public class TableViewBuild {

    public static void addFromList(TableView tableView, List<?> list) {

        Looper.forLoop(0,list.size(),i -> tableView.getItems().add(list.get(i)));

    }
    public static void removeAllFromView(TableView tableView) {

        tableView.getItems().removeAll(tableView.getItems());
    }
    public static void addSingleObject(TableView tableView , Object obj) {
        tableView.getItems().add(obj);
    }


}
