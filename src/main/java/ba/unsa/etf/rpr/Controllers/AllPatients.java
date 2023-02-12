package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Domain.PatientExam;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
            PPINId.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
            NameId.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
            PlaceId.setCellValueFactory(new PropertyValueFactory<Patient, String>("place"));
            AddressID.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
            PhoneId.setCellValueFactory(new PropertyValueFactory<Patient, String>("phone_num"));
            BirthId.setCellValueFactory(new PropertyValueFactory<Patient, String>("birth_date"));
            HealthId.setCellValueFactory(new PropertyValueFactory<Patient, String>("health_insurance"));
            tableViewId.setItems(getPatientInfo(""));
            tableViewId.setEditable(true);
            NameId.setCellFactory(TextFieldTableCell.forTableColumn());
            PlaceId.setCellFactory(TextFieldTableCell.forTableColumn());
        } catch (IOException | HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Patient> getPatientInfo(String name) throws IOException, HospitalException {
        ObservableList<Patient> patientInfo = FXCollections.observableArrayList();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LogIn.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        LogIn controller = loader.getController();
        String user = controller.getDoctor().getUsername();
        List<Patient> patients = DaoFactory.PatientDao().getAll();
        for(Patient p : patients) {
            if(name.equals("") || p.getName().equals(name))
                patientInfo.add(new Patient(p.getName(), p.getPlace(),p.getAddress(),p.getPhone_num(),p.getBirth_date(),p.isHealth_insurance()));
        }
        return patientInfo;
    }

    public void SearchForPatient(ActionEvent actionEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    public void KEyPressed(KeyEvent keyEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }


    public void MyPatients(ActionEvent actionEvent) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene homescene = new Scene (home);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(homescene);
    }
    public void MyExaminations(ActionEvent actionEvent) throws IOException {
        Parent myexams = FXMLLoader.load(getClass().getResource("/fxml/MyExaminations.fxml"));
        Scene myexamsscene = new Scene (myexams);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(myexamsscene);
    }

    public void NameEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setName(patientStringCellEditEvent.getNewValue());
        DaoFactory.PatientDao().update(patient);
    }

    public void PlaceEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setPlace(patientStringCellEditEvent.getNewValue());
        DaoFactory.PatientDao().update(patient);
    }

    public void AddressEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setAddress(patientStringCellEditEvent.getNewValue());
        DaoFactory.PatientDao().update(patient);
    }

    public void PhoneEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setPhone_num(patientStringCellEditEvent.getNewValue());
        DaoFactory.PatientDao().update(patient);
    }

    public void BirthEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException, ParseException {
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setBirth_date(new SimpleDateFormat("dd/MM/yyyy").parse(patientStringCellEditEvent.getNewValue()));
        DaoFactory.PatientDao().update(patient);
    }

    public void HealthEdit(TableColumn.CellEditEvent<Patient, String> patientStringCellEditEvent) throws HospitalException {
        Patient patient = tableViewId.getSelectionModel().getSelectedItem();
        patient.setHealth_insurance(patientStringCellEditEvent.getNewValue().equals("true"));
        DaoFactory.PatientDao().update(patient);
    }
}
