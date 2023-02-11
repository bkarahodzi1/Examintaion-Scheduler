package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
        PasswordId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(PasswordId.getText().length()<=5 || PasswordId.getText().matches("[a-zA-Z]+")){
                    PasswordId.setStyle("-fx-border-color: #E92929");
                    label4.setText("Password has to be at least 5 characters long and must contain at least one letter and one number");
                }
            }
        });
    }

    public void CreateClicked(ActionEvent actionEvent) throws HospitalException, IOException, InterruptedException {
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
        Stage closing = (Stage) label1.getScene().getWindow();
        closing.close();
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/PopUp.fxml")));
        Stage temporaryStage = new Stage();
        temporaryStage.setTitle("Great success");
        temporaryStage.setScene(new Scene(root1, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        temporaryStage.setResizable(false);
        temporaryStage.getIcons().add(new Image("C:\\Users\\Svage\\IdeaProjects\\projekatB\\src\\main\\resources\\Images\\v987-18a.jpg"));
        temporaryStage.show();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Home.fxml")));
        primaryStage.setTitle("Hospital main page");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("C:\\Users\\Svage\\IdeaProjects\\projekatB\\src\\main\\resources\\Images\\v987-18a.jpg"));
        PauseTransition delay2 = new PauseTransition(Duration.seconds(3));
        delay2.setOnFinished( event -> primaryStage.show() );
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished( event -> temporaryStage.close() );
        delay.play();
        delay2.play();
    }
}
