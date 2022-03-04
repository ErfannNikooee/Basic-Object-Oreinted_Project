package Controller;

import Model.Data.Adscompany;
import Model.Data.General_Manager;
import Model.Data.Manager;
import Model.ProgramData;
import Model.pageloader;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;

public class LoginPageController {

    ObservableList<String> Types= FXCollections.observableArrayList("Audience","Manager","General Manager","Adscompany");

    @FXML
    ChoiceBox<String> UserType;

    @FXML
    Label typeerror;

    @FXML
    TextField usernamefield;

    @FXML
    PasswordField passwordfield;

    @FXML
    Label wrongpasslabel;

    @FXML
    Button loginbutton;

    @FXML
    Button register;

    @FXML
    TextField visiblepassfield;

    @FXML
    ImageView showhide;

    @FXML
    Label addGM;


    public void initialize(){
        if (ProgramData.start){
            addGM.setVisible(true);
            UserType.setVisible(false);
            loginbutton.setVisible(false);
            FadeTransition fadein = new FadeTransition(Duration.millis(3400), register);
            fadein.setFromValue(0.0);
            fadein.setToValue(1.0);
            fadein.play();
        }
        else {
            addGM.setVisible(false);
            UserType.setVisible(true);
            loginbutton.setVisible(true);
            register.setVisible(false);
            UserType.setItems(Types);
            UserType.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
                if (t1.equals("Audience")) {
                    usernamefield.setVisible(false);
                    passwordfield.setVisible(false);
                    showhide.setVisible(false);
                } else {
                    register.setVisible(false);
                    if (t1.equals("Adscompany"))
                        register.setVisible(true);
                    usernamefield.setVisible(true);
                    passwordfield.setVisible(true);
                    showhide.setVisible(true);
                }
            });
            TranslateTransition tt = new TranslateTransition(Duration.millis(1400), loginbutton);
            tt.setToY(-100);
            tt.playFromStart();
            FadeTransition fadein = new FadeTransition(Duration.millis(1400), loginbutton);
            fadein.setFromValue(0.0);
            fadein.setToValue(1.0);
            fadein.play();
        }
    }


    public boolean check(String type,String username,String password) throws IOException {
        switch (type){
            case "General Manager":
                if (ProgramData.gm.getUsername().equals(username) && ProgramData.gm.getPassword().equals(password)) {
                    ProgramData.Currentusername=username;
                    new pageloader().load("../View/GeneralManagerPanel.fxml");
                }
                else
                    return true;
                break;
            case "Manager":
                for (Manager manager:ProgramData.managers) {
                    if (manager.getUsername().equals(username)&&manager.getPassword().equals(password)) {
                        ProgramData.Currentusername=username;
                        new pageloader().load("../View/ManagerPanel.fxml");
                    }
                }
                break;
            case "Adscompany":
                for (Adscompany adscompany:ProgramData.adscompanies) {
                    if (adscompany.getUsername().equals(username)&&adscompany.getPassword().equals(password)) {
                        ProgramData.Currentusername=username;
                        new pageloader().load("../View/AdscompanyPanel.fxml");
                    }
                }
                break;
        }
        return true;
    }

    public void login(ActionEvent actionEvent) throws IOException {
        if (UserType.getValue()!=null) {
            if (UserType.getValue().equals("Audience")) {
                new pageloader().load("../View/AudiencePanel.fxml");
            } else {
                if (passwordfield.isVisible()) {
                    if (check(UserType.getValue(), usernamefield.getText(), passwordfield.getText())) {
                        wrongpasslabel.setVisible(true);
                    }
                }
                else {
                    if (check(UserType.getValue(), usernamefield.getText(), visiblepassfield.getText())) {
                        wrongpasslabel.setVisible(true);
                    }
                }
            }
        }
        else {
            typeerror.setVisible(true);
        }
    }

    public void showpass(MouseEvent mouseEvent) {
        if (!visiblepassfield.isVisible()){
            visiblepassfield.setText(passwordfield.getText());
            passwordfield.setVisible(false);
            visiblepassfield.setVisible(true);
        }
        else {
            passwordfield.setText(visiblepassfield.getText());
            visiblepassfield.setVisible(false);
            passwordfield.setVisible(true);
        }
    }

    public void register(ActionEvent actionEvent) {
        if (ProgramData.start){
            if (passwordfield.isVisible()){
            ProgramData.gm=new General_Manager(usernamefield.getText(),passwordfield.getText());
            ProgramData.gm.setPassword(passwordfield.getText());}
            else {
                ProgramData.gm=new General_Manager(usernamefield.getText(),visiblepassfield.getText());
                ProgramData.gm.setPassword(visiblepassfield.getText());
            }
            ProgramData.start=false;
            initialize();
        }
        else {
            Adscompany adscompany = new Adscompany(usernamefield.getText(), passwordfield.getText());
            ProgramData.Currentusername = usernamefield.getText();
            ProgramData.adscompanies.add(adscompany);
        }

    }
}

