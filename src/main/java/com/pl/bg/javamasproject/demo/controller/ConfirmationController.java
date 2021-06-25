package com.pl.bg.javamasproject.demo.controller;

import com.pl.bg.javamasproject.demo.controllers.PopUp;
import com.pl.bg.javamasproject.demo.holder.VisitInformation;
import com.pl.bg.javamasproject.demo.model.Visit;
import com.pl.bg.javamasproject.demo.service.VisitService;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewBuild;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ConfirmationController  implements Initializable {


    /**
     * visit and visitService fields need to be static cause of need of initialize them before instance of class is created
     */
    @Getter
    @Setter
    static VisitService visitService;
    @Getter
    @Setter
    static Visit visit;
    @FXML
    private TableView tableView_visit = new TableView();
    static Stage stage = new Stage();


    public void openConfirmWindow() throws IOException {



        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/confirmationController.fxml"));

        stage.setResizable(false);
        stage.setScene(new Scene(root, 738, 220));
        stage.setTitle("ConfirmationWindow");
        stage.show();

    }

    /**
     * preparing columns for table view based on VisitInformation object which have all information about just created visit
     */
    public void buildTable() {

        VisitInformation visitInfo = new VisitInformation(visit);
        System.out.println(visitInfo.getBeginingOfVisit());

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
                .columnName("Approximate_Cost")
                .classField("overall_cost")
                .build()
                .buildColumn();
        TableColumn t_date = TableViewCreator.<LocalDate, VisitInformation>builder()
                .columnName("Date")
                .classField("dateOfVisit")
                .build()
                .buildColumn();
        TableColumn t_beginHour = TableViewCreator.<LocalTime, VisitInformation>builder()
                .columnName("Approximate_beginnig")
                .classField("beginingOfVisit")
                .build()
                .buildColumn();
        TableColumn t_endHour = TableViewCreator.<LocalTime, VisitInformation>builder()
                .columnName("Approximate_ending")
                .classField("endOfVisit")
                .build()
                .buildColumn();

        tableView_visit.getColumns().addAll(t_clientName, t_patientNname, t_officeNumber, t_date, t_beginHour, t_endHour, t_cost);
        TableViewBuild.addSingleObject(tableView_visit, visitInfo);
    }


    /**
     * if operation is confirmed, popup window will appear, visit wiil be added to database via visitService, stage will be closed.
     */
    @FXML
    public void confirm() {
        PopUp popUp = new PopUp();
        popUp.start_ok("Operation Confirmed");
        visitService.createVisit(visit);
        ConfirmationController.stage.close();

    }

    @FXML
    /**
     * if operation is confirmed, popup window will appear, visit wiil not be added to database , stage will be closed.
     */
    public void decline() {
        PopUp popUp = new PopUp();
        popUp.start_error("Operation Declined");
        ConfirmationController.stage.close();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildTable();
    }
}


