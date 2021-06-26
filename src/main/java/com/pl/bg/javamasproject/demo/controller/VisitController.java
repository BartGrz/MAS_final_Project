package com.pl.bg.javamasproject.demo.controller;

import com.pl.bg.javamasproject.demo.adapter.*;
import com.pl.bg.javamasproject.demo.model.Client;
import com.pl.bg.javamasproject.demo.model.Doctor;
import com.pl.bg.javamasproject.demo.model.Patient;
import com.pl.bg.javamasproject.demo.model.Visit;
import com.pl.bg.javamasproject.demo.service.DoctorService;
import com.pl.bg.javamasproject.demo.service.VisitService;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.ComboBoxOperations;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewBuild;
import com.pl.bg.javamasproject.demo.tools.FXML_tools.TableViewCreator;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Component
@FxmlView("PatientController.fxml") //from fxWeaver
public class VisitController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(VisitController.class);

    private ClientRepository clientRepository;
    private DoctorRepository doctorRepository;
    private VisitRepository visitRepository;
    private SpecializationRepository specializationRepository;
    private DoctorSpecRepository doctorSpecRepository;

/*
spring boot autowiring repostories (interfaces) automaticaly while starting
 */
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
    private TableView tableView_patient = new TableView();
    @FXML
    private TableView tableView_client = new TableView();
    @FXML
    private TableView tableView_doctor = new TableView();
    @FXML
    private TableView tableView_visit = new TableView();
    @FXML
    private ImageView logo_vet = new ImageView();

    private File file_logo = new File(System.getProperty("user.home") + "\\IdeaProjects\\demoMASSpring\\src\\main\\resources\\static\\" + "lekarz-weterynarii.jpg");
    private Map<String, Doctor> doctorsMap = new HashMap<>();
    private Map<String, Client> clientMap = new HashMap<>();
    private Map<String, Patient> patientMap = new HashMap<>();
    private StringBuilder stb = new StringBuilder();
    private LocalDate date = LocalDate.now();
    private boolean emergency = false;

    /**
     * comboBox_client is filled with data from system, and every object is put in map with String key value 'id_name_last_name' to be easily found
     * even if  there are many clients with the same name or even last name
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_accept.setVisible(false);
        Image image = new Image(file_logo.toURI().toString());
        logo_vet.setImage(image);

        var clients = clientRepository.findAll();
        Looper.forLoop(0, clients.size(), i -> {

            stb.append(clients.get(i).getId_client() + " " + clients.get(i).getName() + " " + clients.get(i).getLast_name());
            comboBox_client.getItems().add(stb.toString());
            clientMap.put(stb.toString(), clients.get(i));
            stb.delete(0, stb.length());
        });

    }

    /**
     * showing clients pets which are clinic patients, every object is put in map with string key value 'id_name' to be easily founded in map
     * even if there are (nearly impossible) patients with the same
     * @param client
     */
    public void showPatients(Client client) {
        ComboBoxOperations.removeAllFrom(comboBox_patient);
        var patients = client.getPatients();
        patients.forEach(patient -> {
            stb.append(patient.getId_patient() + " " + patient.getName());
            comboBox_patient.getItems().add(stb.toString());
            patientMap.put(stb.toString(), patient);
            stb.delete(0, stb.length());
        });

    }

    /**
     * Choosing patient from comboBox, building table and process the use case further by showing possible types of visit
     */
    @FXML
    public void choosePatient() {
        //choosing different patient would add another record to table
        TableViewBuild.removeAllFromView(tableView_patient);
        showVisitTypes();
        buildTableForPatient();
    }

    /**
     * based on what would be chosen in comboBox ,method will choose possible doctors suitable for this type of operation,
     * it is possible to change type of operation which will indicate chain of effect (doctor,dates,hour)
     * that's why all informations must be removed from combobox and have to be reloaded
     *
     * every object of Doctor type will be added to map with String key value 'id_name_lastName_medicalLicenceNo' to be easily found among other doctor objects
     */
    @FXML
    public void chooseVisitType() {

        emergency = false;
        button_accept.setVisible(false);

        ComboBoxOperations.removeAllFrom(comboBox_doctor);
        ComboBoxOperations.removeAllFrom(comboBox_hour);
        ComboBoxOperations.removeAllFrom(comboBox_date);

        comboBox_doctor.setValue(null);
        comboBox_hour.setValue(null);
        comboBox_date.setValue(null);
        TableViewBuild.removeAllFromView(tableView_doctor);

        List<String> listOfSpecsNeeded  = null;
        Set<Doctor> doctorsWithSpec = new HashSet<>();
        if (comboBox_visitType.getValue() != null) {
            String specialistNeeded = null;

            switch (comboBox_visitType.getValue().toString()) {
                case "STOMATOLOGICAL_VISIT":
                    specialistNeeded = "STOMATOLOGIST";
                    break;
                case "CARDIOLOGY" :
                    specialistNeeded = "CARDIOLOGIST";
                    break;
                case "OPERATION":
                    specialistNeeded = "SURGEON";
                    break;
                case "CONTROL":
                    specialistNeeded = "INTERNIST";
                    break;
                case "POSTOPERATION":
                    listOfSpecsNeeded = new ArrayList<>();
                    listOfSpecsNeeded.add("INTERNIST");
                    listOfSpecsNeeded.add("SURGEON");
                    break;
                case "CITO" :
                    listOfSpecsNeeded = new ArrayList<>();
                    listOfSpecsNeeded.add("INTERNIST");
                    listOfSpecsNeeded.add("SURGEON");
                    emergency = true;
                    break;
            }
            /*
            if there is not only one specialization possible, all possibles spec will be added to list ,and then in for each loop every doctor with specialization needed
             will be added to set
             */
            if(listOfSpecsNeeded!=null) {
                Set<Doctor> possibleDoctors = new HashSet<>();

                for (String s : listOfSpecsNeeded) {
                    doctorRepository.findBySpecialization(s).stream().forEach(doctor -> {
                        possibleDoctors.add(doctor);
                    });
                }
                  doctorsWithSpec = possibleDoctors;

                /*
                if there is one spiecialization needen, all is managed by repository returning set of possible doctors
                 */
            }else {
               doctorsWithSpec = doctorRepository.findBySpecialization(specialistNeeded);
            }
            doctorsWithSpec.stream().forEach(doctor -> {
                stb.append(doctor.getId_doctor() + " " + doctor.getName() + " " + doctor.getLast_name() + " " + doctor.getMedical_license_no());
                comboBox_doctor.getItems().add(stb.toString());
                doctorsMap.put(stb.toString(), doctor);
                stb.delete(0, stb.length());

            });
        }
    }

    /**
     * choposing doctor and procceed use case further to choosing date,
     * every time method is called  all data set before on comboBox_date,
     * comboBox_hour will be removed and tableView_doctor will be cleaned
     * @throws ParseException
     */
    @FXML
    public void chooseDoctor() throws ParseException {
        TableViewBuild.removeAllFromView(tableView_doctor);
        ComboBoxOperations.removeAllFrom(comboBox_date);
        ComboBoxOperations.removeAllFrom(comboBox_hour);

        if(comboBox_doctor.getValue()!=null) {
            buildTableForDoctor();
        }
        showPossibleDates();

    }

    /**
     * Showing possible dates - default is two weeks (14)
     * @throws ParseException
     */
    @FXML
    public void showPossibleDates() throws ParseException {
        if (!emergency) {
            Looper.forLoop(0, 14, i -> comboBox_date.getItems().add(date.plusDays(i)));
        }else {
            comboBox_date.getItems().add(date);
        }
    }

    /**
     * showing possible doctor hours based on date choosen in date comboBox and doctor list of possible hours from doctor service method
     * if doctor is not working on choosen date, popup window will appear with suitable information and combobox_date and comboBox_hour will be reloaded,
     * @throws ParseException
     */
    @FXML
    public void showPossibleHours() throws ParseException {
        if (!comboBox_hour.getItems().isEmpty()) {
            comboBox_hour.getItems().removeAll(comboBox_hour.getItems());
        }
        if (comboBox_date.getValue() != null) {
            var doctorService = new DoctorService(doctorRepository, specializationRepository, doctorSpecRepository, visitRepository);

            var doctor = doctorRepository.findById(doctorsMap.get(comboBox_doctor.getValue().toString()).getId_doctor()).get();
            var possibleHours = doctorService.getDoctorsHours(doctor, comboBox_date.getValue());
            possibleHours.stream().forEach(localTime -> comboBox_hour.getItems().add(localTime));

            if(comboBox_hour.getItems().isEmpty()) {
                comboBox_hour.setVisible(false);
                PopUp popUp = new PopUp(400,130);
                popUp.start_error("Doctor is not working on this day, choose another date ");
                PopUp.button.setOnAction(event ->  {
                    comboBox_hour.setVisible(true);
                    comboBox_date.setValue(null);
                    PopUp.stage.close();
                });
            }else {
                comboBox_hour.setVisible(true);
            }
        }
    }

    /**
     * showing add visit button, when all comboBox have value
     */
    @FXML
    public void readyToGo() {
        if (comboBox_hour.getValue() != null) {
            button_accept.setVisible(true);
        }
    }

    @FXML
    public void chooseClient() {
        TableViewBuild.removeAllFromView(tableView_client);
        if (comboBox_client.getValue() != null) {
            String clientName = comboBox_client.getValue().toString();
            String tab[] = clientName.split(" ");
            var client = clientRepository.findById(Integer.parseInt(tab[0])).get();
            showPatients(client);
            buildTableForClient();
        }
    }

    /**
     * when method is call it clears all nodes which possesed any data (comboBox,tableViews)
     */
    @FXML
    public void clear() {

        button_accept.setVisible(false);

        ComboBoxOperations.checkIfEmpty(comboBox_client, () -> comboBox_client.setValue(null));
        ComboBoxOperations.checkIfEmpty(comboBox_patient, () -> comboBox_patient.getItems().removeAll(comboBox_patient.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_visitType, () -> comboBox_visitType.getItems().removeAll(comboBox_visitType.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_doctor, () -> comboBox_doctor.getItems().removeAll(comboBox_doctor.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_date, () -> comboBox_date.getItems().removeAll(comboBox_date.getItems()));
        ComboBoxOperations.checkIfEmpty(comboBox_hour, () -> comboBox_hour.getItems().removeAll(comboBox_hour.getItems()));

        TableViewBuild.removeAllFromView(tableView_client);
        TableViewBuild.removeAllFromView(tableView_patient);
        TableViewBuild.removeAllFromView(tableView_doctor);


    }

    /**
     * showing all possible visit types based on enum inside Visit class
     */
    public void showVisitTypes() {
        ComboBoxOperations.removeAllFrom(comboBox_visitType);
        EnumSet<Visit.VisitType> visitTypes = EnumSet.allOf(Visit.VisitType.class);
        comboBox_visitType.getItems().addAll(visitTypes);
    }


    /**
     * adding visit via visit service based on what user choose in UI , method will call confirmation window from class ConfirmationController
     * visitInformation is static field, it is passed to the ConfirmationController so table can be build based on this object before stage will appear
     * during confirmation all operation will be blocked on main window until
     */
    @FXML
    public void addVisit() throws IOException {


        var client = clientMap.get(comboBox_client.getValue().toString());
        var patient = patientMap.get(comboBox_patient.getValue().toString());
        var doctor = doctorsMap.get(comboBox_doctor.getValue().toString());
        var beginHour =  LocalTime.parse(comboBox_hour.getValue().toString());
        var endHour =LocalTime.parse(comboBox_hour.getValue().toString()).plusHours(1);
        var dateOfVisit = comboBox_date.getValue();
        var visit = new Visit(comboBox_visitType.getValue().toString(),client,doctor,patient,beginHour,endHour,dateOfVisit);
        var visitService = new VisitService(visitRepository,doctorRepository,clientRepository);

        ConfirmationController.visit = visit;
        ConfirmationController.visitService=visitService;
        ConfirmationController controller = new ConfirmationController();
        controller.openConfirmWindow();
        clear();


        comboBox_client.setVisible(false);
        button_clear.setText("NEXT");
        button_clear.setOnAction(event -> {
            if(!ConfirmationController.stage.isShowing()){
                comboBox_client.setVisible(true);
                button_clear.setText("CLEAR");
            }

        });
    }

    /**
     * preparing tablecolumns to be added to tableview, every time the method will run,
     * previous collection on tableview will be removed, because only one record is needed
     */
    public void buildTableForClient() {
        tableView_client.getColumns().removeAll(tableView_client.getColumns());
        TableColumn t_name = TableViewCreator.<String, Client>builder()
                .columnName("Name")
                .classField("name")
                .build()
                .buildColumn();
        TableColumn t_lastName = TableViewCreator.<String, Client>builder()
                .columnName("Last_Name")
                .classField("last_name")
                .build()
                .buildColumn();
        tableView_client.getColumns().addAll(t_name, t_lastName);
        var client = clientMap.get(comboBox_client.getValue().toString());
        TableViewBuild.addSingleObject(tableView_client, client);

    }
    /**
     * preparing tablecolumns to be added to tableview, every time the method will run,
     * previous collection on tableview will be removed, because only one record is needed
     */
    public void buildTableForPatient() {
        tableView_patient.getColumns().removeAll(tableView_patient.getColumns());
        if (comboBox_patient.getValue() != null) {
            TableColumn t_name = TableViewCreator.<String, Patient>builder()
                    .columnName("Name")
                    .classField("name")
                    .build()
                    .buildColumn();
            tableView_patient.getColumns().add(t_name);
            var patient = patientMap.get(comboBox_patient.getValue().toString());
            TableViewBuild.addSingleObject(tableView_patient, patient);
        }
    }

    /**
     * preparing tablecolumns to be added to tableview, every time the method will run,
     * previous collection on tableview will be removed, because only one record is needed
     */
    public void buildTableForDoctor() {
        tableView_doctor.getColumns().removeAll(tableView_doctor.getColumns());
        TableColumn t_name = TableViewCreator.<String, Doctor>builder()
                .columnName("Name")
                .classField("name")
                .build()
                .buildColumn();
        TableColumn t_lastName = TableViewCreator.<String, Doctor>builder()
                .columnName("LastName")
                .classField("last_name")
                .build()
                .buildColumn();
        TableColumn t_specs = TableViewCreator.<String, Doctor>builder()
                .columnName("Specialization")
                .classField("doctorSpecializations")
                .build()
                .buildColumn();

            tableView_doctor.getColumns().addAll(t_name, t_lastName, t_specs);
            var doctor = doctorsMap.get(comboBox_doctor.getValue().toString());
            doctor.fillSpecList();
            TableViewBuild.addSingleObject(tableView_doctor, doctor);

    }

}
