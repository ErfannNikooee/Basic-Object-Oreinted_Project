package Controller;

import Model.Data.Channel;
import Model.Data.General_Manager;
import Model.Data.Manager;
import Model.ProgramData;
import Model.pageloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ManagingChannelsController {
    @FXML
    TextField channelname;

    @FXML
    TextField pricecofficient;

    @FXML
    Label exists;

    @FXML
    Label error;
    @FXML
    ChoiceBox<String> managerslist;

    @FXML
    ListView<String> channelslist;

    ObservableList<String> managers= FXCollections.observableArrayList();



    public void initialize(){
        for (Manager manager:ProgramData.managers) {
            if(!manager.owning_channel) {
                managers.add(manager.getUsername());
            }
        }
        managerslist.setItems(managers);
        for (Channel channel:ProgramData.channels) {
            channelslist.getItems().add(channel.getName());
        }
    }


    public void save(ActionEvent actionEvent) {
        if (channelname.getText().isEmpty()||pricecofficient.getText().isEmpty())
            error.setVisible(true);
        else {
            error.setVisible(false);
            Manager m = null;
            Channel channel;
            exists.setVisible(false);
            for (Channel channel1 : ProgramData.channels) {
                if (channel1.getName().equals(channelname.getText())) {
                    exists.setVisible(true);
                    return;
                }
            }
            int i = 0;
            if (managerslist.getValue() == null) {
                m = ProgramData.gm;
                channel = new Channel(channelname.getText(), Integer.parseInt(pricecofficient.getText()), m);
                ProgramData.channels.add(channel);
                channelslist.getItems().add("channel_name:" + channel.getName() + "    manager :" + m.getUsername());
            } else {
                for (Manager manager : ProgramData.managers) {
                    if (managerslist.getValue().equals(manager.getUsername())) {
                        m = manager;
                        channel = new Channel(channelname.getText(), Integer.parseInt(pricecofficient.getText()), m);
                        ProgramData.channels.add(channel);
                        i = ProgramData.managers.indexOf(m);
                        ProgramData.managers.get(i).setChannel(channel);
                        channelslist.getItems().add("channel_name:" + channel.getName() + "    manager :" + m.getUsername());
                    }
                }
            }
            if (!(m instanceof General_Manager))
                ProgramData.managers.get(i).owning_channel = true;
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/GeneralManagerPanel.fxml");
    }
}
