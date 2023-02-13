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

public class AllPatients implements Initializable {
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

    public void SearchForPatient() throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    public void KEyPressed() throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }


    public void MyPatients() throws IOException {
        Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Home.fxml")));
        Scene homescene = new Scene (home);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(homescene);
    }
    public void MyExaminations() throws IOException {
        Parent myexams = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MyExaminations.fxml")));
        Scene myexamsscene = new Scene (myexams);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(myexamsscene);
    }

    public void NameEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setName(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    public void PlaceEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setPlace(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    public void AddressEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setAddress(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    public void PhoneEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setPhone_num(patientStringCellEditEvent.getNewValue());
        patientManager.update(patient);
    }

    public void BirthEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException, ParseException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setBirth_date(new SimpleDateFormat("dd/MM/yyyy").parse(patientStringCellEditEvent.getNewValue()));
        patientManager.update(patient);
    }

    public void HealthEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        PatientManager patientManager = new PatientManager();
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setHealth_insurance(patientStringCellEditEvent.getNewValue().equals("true"));
        patientManager.update(patient);
    }
}
