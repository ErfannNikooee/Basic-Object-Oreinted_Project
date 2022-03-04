package Controller;

import Model.ProgramData;
import Model.Data.*;
import Model.pageloader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class AudiencePanelController {

    @FXML
    ChoiceBox<String> ChannelsList;

    @FXML
    ChoiceBox<String> TelecastsList;

    @FXML
    ChoiceBox<Integer> rate;

    @FXML
    Button saverate;

    @FXML
    Label avaragerates;

    @FXML
    Label description;


    ObservableList<String> channelnames=FXCollections.observableArrayList();

    ObservableList<String> telecastnames=FXCollections.observableArrayList();

    ObservableList<Integer> rates=FXCollections.observableArrayList(1,2,3,4,5);


    public int findchannel(String name){
        int i=0;
        for (Channel channel :ProgramData.channels) {
            if (channel.getName().equals(name))
                i=ProgramData.channels.indexOf(channel);
        }
        return i;
    }

    int channeli;
    int telecasti;

    public void AddChannelNames(){
        for (Channel channel:ProgramData.channels) {
            channelnames.add(channel.getName());
        }
    }

    public void AddTelecastNames(Channel channel){
        for (Telecast telecast:channel.telecasts) {
            telecastnames.add(telecast.getName());
        }
    }

    public void initialize(){
        AddChannelNames();
        ChannelsList.setItems(channelnames);
        ChannelsList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            channeli=findchannel(t1);
            AddTelecastNames(ProgramData.channels.get(channeli));
            TelecastsList.setItems(telecastnames);
            TelecastsList.setVisible(true);
        });
        TelecastsList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            telecasti=ProgramData.channels.get(channeli).findtelecast(t1);
            description.setText(ProgramData.channels.get(channeli).telecasts.get(telecasti).toString());
            if(ProgramData.channels.get(channeli).telecasts.get(telecasti).getName().equals(t1)) {
                if(!(ProgramData.channels.get(channeli).telecasts.get(telecasti)instanceof Ads)) {
                    rate.setItems(rates);
                    rate.setVisible(true);
                    saverate.setVisible(true);
                }
                else {
                    rate.setVisible(false);
                    saverate.setVisible(false);
                }
            }
        });
    }



    public void saverate(ActionEvent actionEvent) {
        ProgramData.channels.get(channeli).telecasts.get(telecasti).setRate(rate.getValue());
        double avrates=ProgramData.channels.get(channeli).telecasts.get(telecasti).getRate();
        String avr=String.format("%.02f",avrates);
        avaragerates.setText("avarage rates :\n"+ avr);
        avaragerates.setVisible(true);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/loginpage.fxml");
    }
}
