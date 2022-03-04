package Controller;

import Model.Data.Ads;
import Model.Data.Adscompany;
import Model.Data.Adsrequest;
import Model.Data.Channel;
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


public class AdsCompanyPanelController {

    @FXML
    Label balance;

    @FXML
    Label cost;

    @FXML
    TextField adsname;

    @FXML
    TextField starttime;

    @FXML
    TextField endtime;

    @FXML
    TextField replaystarttime;

    @FXML
    TextField replayendtime;

    @FXML
    TextField date;

    @FXML
    TextField charge;

    @FXML
    ChoiceBox<String> channelslist;

    @FXML
    ListView<String> adsrequestslist;

    ObservableList<String> channels= FXCollections.observableArrayList();

    public void initialize(){
        for (Adscompany adscompany:ProgramData.adscompanies) {
            if (ProgramData.Currentusername.equals(adscompany.getUsername()))
                balance.setText("Balance : "+adscompany.money);
        }
        for (Channel channel:ProgramData.channels) {
            channels.add(channel.getName());
        }
        channelslist.setItems(channels);
        for (Adsrequest adsrequest:ProgramData.adsrequests) {
            if (adsrequest.show)
                adsrequestslist.getItems().add(adsrequest.ads.toString());
        }
    }
    public void logout(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/loginpage.fxml");
    }
    public void save(ActionEvent actionEvent) {
        if (adsname==null||starttime==null||endtime==null||date==null
                ||replaystarttime==null||replayendtime==null) {
            cost.setText("fill all fields!!");
            cost.setVisible(true);
        }
        else {
            Ads ads = new Ads(adsname.getText(),
                    Integer.parseInt(starttime.getText()),
                    Integer.parseInt(endtime.getText()),
                    date.getText(),
                    Integer.parseInt(replaystarttime.getText()),
                    Integer.parseInt(replayendtime.getText()));
            for (Adscompany adscompany:ProgramData.adscompanies) {
                if (adscompany.getUsername().equals(ProgramData.Currentusername))
                    ads.setAdscompany(adscompany);
            }
            Adsrequest adsrequest;
            for (Channel channel : ProgramData.channels) {
                if (channel.getName().equals(channelslist.getValue())) {
                    adsrequest = new Adsrequest(ads, channel);
                    ProgramData.adsrequests.add(adsrequest);
                    adsrequestslist.getItems().add(ads.toString());
                    break;
                }
            }
            temp=ads;
            purchase();
        }
    }

    Ads temp;
    int price=0;

    public void charge(ActionEvent actionEvent) {
        for (Adscompany adscompany:ProgramData.adscompanies) {
            if (adscompany.getUsername().equals(ProgramData.Currentusername)){
                adscompany.Chargingaccount(Integer.parseInt(charge.getText()));
                balance.setText("Balance : "+adscompany.money);
            }
        }
    }

    public void purchase(){
        for (Adscompany adscompany:ProgramData.adscompanies) {
            if (adscompany.getUsername().equals(ProgramData.Currentusername)){
                for (Channel channel:ProgramData.channels){
                    if (channel.getName().equals(channelslist.getValue())){
                        adscompany.money-=price=channel.getprice(
                                temp,Integer.parseInt(starttime.getText()),Integer.parseInt(endtime.getText()));
                        cost.setText("Cost : "+price);
                        cost.setVisible(true);
                        balance.setText("Balance : "+adscompany.money);
                    }
                }
            }
        }
    }
}
