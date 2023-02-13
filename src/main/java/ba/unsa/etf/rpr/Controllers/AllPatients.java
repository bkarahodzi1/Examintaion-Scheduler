package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Business.PatientManager;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * JavaFX controller for showing all the patients in the database
 *
 * @author Berin Karahodžić
 */
public class AllPatients implements Initializable {
    //window fields
    public TableView<Patient> tableViewId;
    public Button searchId;
    public TextField nameSearch;
    public Button allPatientsId;
    public Button myPatientsId;
    public TableColumn<Patient,String> NameId;
    public TableColumn<Patient,String> PlaceId;
    public TableColumn<Patient,String> AddressID;
    public TableColumn<Patient,String> PhoneId;
    public TableColumn<Patient,String> BirthId;
    public TableColumn<Patient,String> HealthId;
    public Button myExaminationsId;
    public TableColumn <Patient,Integer> PPINId;

    /**
     * Initialize method for setting up the tableview
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            PPINId.setCellValueFactory(new PropertyValueFactory<>("id"));
            NameId.setCellValueFactory(new PropertyValueFactory<>("name"));
            PlaceId.setCellValueFactory(new PropertyValueFactory<>("place"));
            AddressID.setCellValueFactory(new PropertyValueFactory<>("address"));
            PhoneId.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
            BirthId.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
            HealthId.setCellValueFactory(new PropertyValueFactory<>("health_insurance"));
            tableViewId.setItems(getPatientInfo(""));
            tableViewId.setEditable(true);
            NameId.setCellFactory(TextFieldTableCell.forTableColumn());
            PlaceId.setCellFactory(TextFieldTableCell.forTableColumn());
            AddressID.setCellFactory(TextFieldTableCell.forTableColumn());
            PhoneId.setCellFactory(TextFieldTableCell.forTableColumn());
        } catch (IOException | HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * function for getting the patient info of all patients
     * @param name
     * @return Observable list to put into tableview
     * @throws IOException
     * @throws HospitalException
     */
    public ObservableList<Patient> getPatientInfo(String name) throws IOException, HospitalException {
        PatientManager patientManager = new PatientManager();
        ObservableList<Patient> patientInfo = FXCollections.observableArrayList();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LogIn.fxml"));
        List<Patient> patients = patientManager.getAll();
        for(Patient p : patients) {
            if(name.equals("") || p.getName().equals(name))
                patientInfo.add(new Patient(p.getId(),p.getName(), p.getPlace(),p.getAddress(),p.getPhone_num(),p.getBirth_date(),p.isHealth_insurance()));
        }
        return patientInfo;
    }

    /**
     * button for beginning the search of patients
     * @throws HospitalException
     * @throws IOException
     */
    public void SearchForPatient() throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    /**
     * does the same as pressing search when enter is pressed
     * @throws HospitalException
     * @throws IOException
     */

    public void KEyPressed() throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    /**
     * function for setting the home-screen on stage
     * @throws IOException
     */
    public void MyPatients() throws IOException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Home.fxml")));
        Scene homescene = new Scene (home);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(homescene);
    }

    /**
     * function for setting the MyExaminations scene in stage
     * shows all the doctor's patients
     * @throws IOException
     */
    public void MyExaminations() throws IOException {
        Parent myexams = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MyExaminations.fxml")));
        Scene myexamsscene = new Scene (myexams);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(myexamsscene);
    }

    /**
     * function that enables double-click altering of patient info (name)
     * @param patientStringCellEditEvent
     * @throws HospitalException
     */
    public void NameEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setName(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    /**
     * function that enables double-click altering of patient info (place)
     * @param patientStringCellEditEvent
     * @throws HospitalException
     */
    public void PlaceEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setPlace(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    /**
     * function that enables double-click altering of patient info (address)
     * @param patientStringCellEditEvent
     * @throws HospitalException
     */
    public void AddressEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setAddress(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    /**
     * function that enables double-click altering of patient info (phone number)
     * @param patientStringCellEditEvent
     * @throws HospitalException
     */
    public void PhoneEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setPhone_num(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    /**
     * function that enables double-click altering of patient info (birth date)
     * @param patientStringCellEditEvent
     * @throws HospitalException
     * @throws ParseException
     */
    public void BirthEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException, ParseException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setBirth_date(new SimpleDateFormat("dd/MM/yyyy").parse(patientStringCellEditEvent.getNewValue()));
        patientManager.update(patient);
    }

    /**
     * function that enables double-click altering of patient info (health insurance)
     * @param patientStringCellEditEvent
     * @throws HospitalException
     */
    public void HealthEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setHealth_insurance(patientStringCellEditEvent.getNewValue().equals("true"));
        patientManager.update(patient);
    }
}
