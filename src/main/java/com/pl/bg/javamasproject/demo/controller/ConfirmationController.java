package com.pl.bg.javamasproject.demo.controller;

import com.pl.bg.javamasproject.demo.holder.VisitInformation;
import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.model.Visit;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewBuild;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ConfirmationController implements Initializable  {

    @Getter
    @Setter
    static Visit visit;
    @FXML
     TableView tableView_visit = new TableView();


    public void openConfirmWindow() throws IOException {
       // buildTable();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/confirmationController.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(root,738,220));
        stage.setTitle("ConfirmationWindow");
        stage.show();


    }
    //creating abstract from visit just created failed - columns are not loaded
    public void buildTable() {

        VisitInformation visitInfo = new VisitInformation(visit);
        System.out.println(" client name  " + visitInfo.getClient_name()+ " cost  " + visitInfo.getOverall_cost());

        TableColumn t_clientName = TableViewCreator.<String, VisitInformation>builder()
                .columnName("Client")
                .classField("client_name")
                .build()
                .buildColumn();
        TableColumn t_patientNname = TableViewCreator.<String, VisitInformation>builder()
                .columnName("Patient")
                .classField("patient_name")
                .build()
                .buildColumn();
        TableColumn t_officeNumber = TableViewCreator.<Integer, VisitInformation>builder()
                .columnName("Office")
                .classField("office_number")
                .build()
                .buildColumn();
        TableColumn t_cost = TableViewCreator.<Double, VisitInformation>builder()
                .columnName("Cost")
                .classField("overall_cost")
                .build()
                .buildColumn();
        TableColumn t_date = TableViewCreator.<LocalDate, VisitInformation>builder()
                .columnName("Date")
                .classField("dateOfVisit")
                .build()
                .buildColumn();
        TableColumn t_beginHour = TableViewCreator.<LocalTime, VisitInformation>builder()
                .columnName("approximate beginnig")
                .classField("beginingOfVisit")
                .build()
                .buildColumn();
        TableColumn t_endHour = TableViewCreator.<LocalTime, VisitInformation>builder()
                .columnName("approximate ending")
                .classField("endOfVisit")
                .build()
                .buildColumn();

        tableView_visit.getColumns().addAll(t_clientName,t_patientNname,t_officeNumber,t_date,t_beginHour,t_endHour,t_cost);
        TableViewBuild.addSingleObject(tableView_visit,visitInfo);
    }
    @FXML
    public void confirm () {

    }
    @FXML
    public void decline () {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildTable();
    }
}
