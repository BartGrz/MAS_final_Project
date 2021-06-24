package com.pl.bg.javamasproject.demo.tools.FXML_tools;


import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.ClassType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Builder
public class TableViewCreator<T, V> {

    private final Logger logger = LoggerFactory.getLogger(TableViewCreator.class);

    private final String columnName;
    private final String classField;
    private TableColumn<T, V> column;


    /**
     * T is type of class field (as Integer or String or Double)
     * V is class from which data will be obtained
     *
     * @return new TableColumn with column name and mapped class field
     */


    public TableColumn buildColumn() {

        column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(classField));
        return column;
    }


}
