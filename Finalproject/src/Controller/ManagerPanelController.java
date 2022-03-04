package Controller;

import Model.pageloader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ManagerPanelController {

    public void manageprograms(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/ManagingPrograms.fxml");
    }

    public void manageAdsrequests(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/ManagingAdsrequests.fxml");

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/loginpage.fxml");
    }
}
