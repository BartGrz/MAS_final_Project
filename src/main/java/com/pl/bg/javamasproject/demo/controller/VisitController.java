package com.pl.bg.javamasproject.demo.controller;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.controllers.PopUp;
import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.model.Doctor;
import com.pl.bg.javamasproject.demo.model.Patient;
import com.pl.bg.javamasproject.demo.model.Visit;
import com.pl.bg.javamasproject.demo.service.DoctorService;
import com.pl.bg.javamasproject.demo.service.VisitService;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.ComboBoxOperations;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Component
@FxmlView("PatientController.fxml")
public class VisitController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(VisitController.class);

    private ClientRepository clientRepository;
    private DoctorRepository doctorRepository;
    private SpecializationRepository specializationRepository;
    private DoctorSpecRepository doctorSpecRepository;
    private VisitRepository visitRepository;

    public VisitController(ClientRepository clientRepository, DoctorRepository doctorRepository, SpecializationRepository specializationRepository,
                           DoctorSpecRepository doctorSpecRepository, VisitRepository visitRepository
    ) {
        this.clientRepository = clientRepository;
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
        this.doctorSpecRepository = doctorSpecRepository;
        this.visitRepository = visitRepository;
    }

    @FXML
    private Button button_clear = new Button();
    @FXML
    private Button button_accept = new Button();
    @FXML
    private ComboBox comboBox_client = new ComboBox();
    @FXML
    private ComboBox comboBox_patient = new ComboBox();
    @FXML
    private ComboBox comboBox_visitType = new ComboBox();
    @FXML
    private ComboBox comboBox_doctor = new ComboBox();
    @FXML
    private ComboBox<LocalDate> comboBox_date = new ComboBox();
    @FXML
    private ComboBox comboBox_hour = new ComboBox();
    @FXML
    private TableView tableView_patients = new TableView();
    @FXML
    private TableView tableView_client = new TableView();
    @FXML
    private TableView tableView_doctor = new TableView();
    @FXML
    private TableView tableView_hour = new TableView();

    @FXML
    private ImageView logo_vet = new ImageView();
    private Image image;
    private File file_logo = new File(System.getProperty("user.home") + "\\IdeaProjects\\demoMASSpring\\src\\main\\resources\\static\\" + "lekarz-weterynarii.jpg");
    private Map<String, Doctor> doctorsMap = new HashMap<>();
    private Map<String, Client> clientMap = new HashMap<>();
    private Map<String, Patient> patientMap = new HashMap<>();
    private StringBuilder stb = new StringBuilder();
    private LocalDate date = LocalDate.now();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_accept.setVisible(false);
        Image image = new Image(file_logo.toURI().toString());
        logo_vet.setImage(image);

        var clients = clientRepository.findAll();
        Looper.forLoop(0, clients.size(), i -> {

            stb.append(clients.get(i).getId_client() + " "+ clients.get(i).getName() + " " + clients.get(i).getLast_name());
            comboBox_client.getItems().add(stb.toString());
            clientMap.put(stb.toString(),clients.get(i));
            stb.delete(0, stb.length());
        });

    }


    public void refreshView() {

    }

    public void showPatients(Client client) {

        var patients = client.getPatients();
        patients.forEach(patient -> {
            stb.append(patient.getId_patient() + " " + patient.getName());
            comboBox_patient.getItems().add(stb.toString());
            patientMap.put(stb.toString(),patient);
            stb.delete(0,stb.length());
        });

    }

    @FXML
    public void choosePatient() {
        showVisitTypes();
    }

    @FXML
    public void chooseVisitType() {
        if(comboBox_visitType.getValue()!=null) {
        String specialistNeeded = null;
        switch (comboBox_visitType.getValue().toString()) {
            case "STOMATOLOGICAL_VISIT":
                specialistNeeded = "STOMATOLOGIST";
                break;
            case "OPERATION":
                specialistNeeded = "SURGEON";
                break;
            default:
                break;
        }

            var doctorsWithSpec = doctorRepository.findBySpecialization(specialistNeeded);
            doctorsWithSpec.stream().forEach(doctor -> {
                stb.append(doctor.getId_doctor() + " " + doctor.getName() + " " + doctor.getLast_name() + " " + doctor.getMedical_license_no());
                comboBox_doctor.getItems().add(stb.toString());
                doctorsMap.put(stb.toString(), doctor);
                stb.delete(0, stb.length());

            });
        }
    }

    @FXML
    public void chooseDoctor() throws ParseException {
        showPossibleDates();

    }

    @FXML
    public void showPossibleDates() throws ParseException {

        Looper.forLoop(0, 14, i -> comboBox_date.getItems().add(date.plusDays(i)));
    }

    @FXML
    public void showPossibleHours() throws ParseException {
        if (!comboBox_hour.getItems().isEmpty()) {
            comboBox_hour.getItems().removeAll(comboBox_hour.getItems());
        }
        if (comboBox_date.getValue() != null) {
            var doctorService = new DoctorService(doctorRepository, specializationRepository, doctorSpecRepository);

            var doctor = doctorRepository.findById(doctorsMap.get(comboBox_doctor.getValue().toString()).getId_doctor()).get();
            var possibleHours = doctorService.getDoctorsHours(doctor, comboBox_date.getValue());
            possibleHours.stream().forEach(localTime -> comboBox_hour.getItems().add(localTime));
            button_accept.setVisible(true);
        }
    }

    @FXML
    public void chooseClient() {

        if (comboBox_client.getValue() != null) {
            String clientName = comboBox_client.getValue().toString();
            String tab[] = clientName.split(" ");
            var client = clientRepository.findById(Integer.parseInt(tab[0])).get();
            showPatients(client);
        }
    }

    @FXML
    public void clear() {

        ComboBoxOperations.checkIfEmpty(comboBox_client,()->comboBox_client.setValue(null));
        ComboBoxOperations.checkIfEmpty(comboBox_patient,()->comboBox_patient.getItems().removeAll(comboBox_patient.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_visitType,()->comboBox_visitType.getItems().removeAll(comboBox_visitType.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_doctor,()->comboBox_doctor.getItems().removeAll(comboBox_doctor.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_date,()->comboBox_date.getItems().removeAll(comboBox_date.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_hour,()->comboBox_hour.getItems().removeAll(comboBox_hour.getItems()));



    }

    public void showVisitTypes() {
        EnumSet<Visit.VisitType> visitTypes = EnumSet.allOf(Visit.VisitType.class);
        comboBox_visitType.getItems().addAll(visitTypes);
    }


    @FXML
    private void confirmOperation () {
        PopUp popUp = new PopUp();
        popUp.start_ok("OPERATION COMPLETED");
        addVisit();

    }

    @FXML
    public void addVisit() {

        VisitService visitService = new VisitService(visitRepository,doctorRepository,clientRepository);
        var client =  clientMap.get(comboBox_client.getValue().toString());
        var patient = patientMap.get(comboBox_patient.getValue().toString());
        var doctor = doctorsMap.get(comboBox_doctor.getValue().toString());

        visitService.createVisit(client.getId_client(),patient.getId_patient()
                ,comboBox_visitType.getValue().toString(),doctor.getId_doctor(),
                LocalTime.parse(comboBox_hour.getValue().toString()), LocalTime.parse(comboBox_hour.getValue().toString()).plusHours(1),comboBox_date.getValue());

      //  clear();

    }

    //TODO: 2021-24-06 create methpds to fill tableviews node
    public void buildTableForClient() {

    }
    public void buildTableForPatient() {

    }
    public void buildTableForDoctor() {

    }
    public void buildTableForDateAndHours() {

    }
}
