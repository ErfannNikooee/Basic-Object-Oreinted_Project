package Controller;

import Model.Data.Adscompany;
import Model.Data.Adsrequest;
import Model.Data.Manager;
import Model.ProgramData;
import Model.pageloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class ManagingAdsrequestsController {

    @FXML
    ListView<String> requestslist;

    @FXML
    Label inteference;

    ObservableList<String> Allrequests= FXCollections.observableArrayList();

    public void setRequestslist(){
        if (ProgramData.Currentusername.equals(ProgramData.gm.getUsername())) {
            for (Adsrequest adsrequest : ProgramData.adsrequests) {
                if (adsrequest.show)
                    Allrequests.add(adsrequest.ads.getName()+ " "+adsrequest.ads.getAdscompany().getUsername());
            }
        }
        else {
            Manager m;
            for (Manager manager : ProgramData.managers) {
                if (manager.getUsername().equals(ProgramData.Currentusername)) {
                    m = manager;
                    for (Adsrequest adsrequest:ProgramData.adsrequests) {
                        if (m.getChannel().getName().equals(adsrequest.channel.getName()))
                            if (adsrequest.show)
                                Allrequests.add(adsrequest.ads.getName()
                                        + " "+adsrequest.ads.getAdscompany().getUsername());
                    }
                    break;
                }
            }
        }
        requestslist.setItems(Allrequests);
    }


    public void initialize(){
        setRequestslist();
    }

    int requesti;

    public void decline(ActionEvent actionEvent) {
        inteference.setVisible(false);
        ProgramData.adsrequests.get(requesti).show=false;
        Allrequests.clear();
        setRequestslist();
    }

    public void Accept(ActionEvent actionEvent) {
        int i=ProgramData.channels.indexOf(ProgramData.adsrequests.get(requesti).channel);
        if (ProgramData.channels.get(i).check(ProgramData.adsrequests.get(requesti).ads)) {
            inteference.setVisible(false);
            ProgramData.channels.get(i).addtelecast(ProgramData.adsrequests.get(requesti).ads);
            ProgramData.adsrequests.get(requesti).show = false;
            Allrequests.clear();
            setRequestslist();
        }
        else
        {
            inteference.setVisible(true);
        }
    }

    public void select(MouseEvent mouseEvent) {
        int i=0;
        for (i=0;i<ProgramData.adsrequests.size();i++){
            String[] str=requestslist.getSelectionModel().getSelectedItems().toString().split("");
            if (ProgramData.adsrequests.get(i).ads.getName().equals(str[0]))
                break;
        }
        requesti=i-1;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        if (ProgramData.Currentusername.equals(ProgramData.gm.getUsername()))
            new pageloader().load("../View/GeneralManagerPanel.fxml");
        else
            new pageloader().load("../View/ManagerPanel.fxml");
    }
}
