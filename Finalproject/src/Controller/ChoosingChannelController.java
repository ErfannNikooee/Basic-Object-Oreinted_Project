package Controller;

import Model.Data.Channel;
import Model.ProgramData;
import Model.pageloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;


public class ChoosingChannelController {

    ObservableList<String> channelnames= FXCollections.observableArrayList();

    @FXML
    ChoiceBox<String> channellist;

    public void initialize(){
        for (Channel channel : ProgramData.channels) {
            channelnames.add(channel.getName());
        }
        channellist.setItems(channelnames);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/GeneralManagerPanel.fxml");
    }

    public void go(ActionEvent actionEvent) throws IOException {
        ProgramData.ChoosenChannel=channellist.getValue();
        new pageloader().load("../View/ManagingPrograms.fxml");

    }
}
