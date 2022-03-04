package Controller;

import Model.pageloader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class GMPanelController {
    public void manageprograms(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/ChoosingChannel.fxml");
    }

    public void manageAdsrequests(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/ManagingAdsrequests.fxml");

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/loginpage.fxml");
    }

    public void managingmanagers(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/ManagingManagers.fxml");
    }

    public void managingchannels(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/ManagingChannels.fxml");
    }
}
