package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUp implements Initializable {

    public ChoiceBox<String> SpecId;
    public Spinner<Integer> SeniorityId;
    public TextField NameId;
    public TextField UsernameId;
    public PasswordField PasswordId;
    public PasswordField RepeatId;
    public Button CreateId;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public GridPane GridId;
    public RowConstraints Row2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpecId.setValue("--Select specialization--");
        SpecId.getItems().setAll("Anatomical Pathology", "Anesthesiology", "Cardiology", "Cardiovascular/Thoracic Surgery", "Clinical Immunology/Allergy", "Critical Care Medicine", "Dermatology", "Diagnostic Radiology", "Emergency Medicine", "Endocrinology and Metabolism", "Family Medicine", "Gastroenterology", "General Internal Medicine", "General Surgery", "General/Clinical Pathology", "Geriatric Medicine", "Hematology", "Medical Biochemistry", "Medical Genetics", "Medical Microbiology and Infectious Diseases", "Medical Oncology", "Nephrology", "Neurology", "Neurosurgery", "Nuclear Medicine", "Obstetrics/Gynecology", "Occupational Medicine", "Ophthalmology", "Orthopedic Surgery", "Otolaryngology", "Pediatrics", "Physical Medicine and Rehabilitation (PM & R)", "Plastic Surgery", "Psychiatry", "Public Health and Preventive Medicine (PhPm)", "Radiation Oncology", "Respirology", "Rheumatology", "Urology");
        SpinnerValueFactory<Integer> spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,45,0);
        this.SeniorityId.setValueFactory(spin);
        SeniorityId.setEditable(true);
    }

    public void CreateClicked(ActionEvent actionEvent) throws HospitalException, IOException {
        if(NameId.getText().trim().isEmpty()){
            label5.setPrefHeight(0);
            label4.setPrefHeight(0);
            label3.setPrefHeight(0);
            label2.setPrefHeight(0);
            label1.setPrefHeight(USE_COMPUTED_SIZE);
            return;
        }
        if(SpecId.getSelectionModel().getSelectedItem().equals("--Select specialization--")) {
            label1.setPrefHeight(0);
            label2.setPrefHeight(USE_COMPUTED_SIZE);
            label3.setPrefHeight(0);
            label4.setPrefHeight(0);
            label5.setPrefHeight(0);
            return;
        }
        if(UsernameId.getText().isEmpty()) {
            label4.setPrefHeight(0);
            label5.setPrefHeight(0);
            label3.setPrefHeight(USE_COMPUTED_SIZE);
            label1.setPrefHeight(0);
            label2.setPrefHeight(0);
            return;
        }
        if(PasswordId.getText().isEmpty()) {
            label4.setPrefHeight(USE_COMPUTED_SIZE);
            label5.setPrefHeight(0);
            label1.setPrefHeight(0);
            label2.setPrefHeight(0);
            label3.setPrefHeight(0);
            return;
        }
        if(!RepeatId.getText().equals(PasswordId.getText())) {
            label1.setPrefHeight(0);
            label2.setPrefHeight(0);
            label3.setPrefHeight(0);
            label4.setPrefHeight(0);
            label5.setPrefHeight(USE_COMPUTED_SIZE);
            return;
        }
        Doctor d1 = new Doctor();
        d1.setName(NameId.getText());
        d1.setSpecialization(SpecId.getSelectionModel().getSelectedItem());
        d1.setSeniority(SeniorityId.getValue());
        d1.setUsername(UsernameId.getText());
        d1.setPassword(PasswordId.getText());
        DaoFactory.DoctorDao().add(d1);
        Popup popup = new Popup();
        Label label = new Label("Account successfully made");
        popup.getContent().add(label);
        Stage closing = (Stage) label1.getScene().getWindow();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        primaryStage.setTitle("Hospital");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new Image("C:\\Users\\Svage\\IdeaProjects\\projekatB\\src\\main\\resources\\Images\\v987-18a.jpg"));
        primaryStage.show();
        closing.close();
    }
}
