package Controller;

import Model.Data.Manager;
import Model.ProgramData;
import Model.pageloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ManagingManagersController {

    @FXML
    TextField managername;

    @FXML
    TextField managerpassword;

    @FXML
    ListView<String> managerslist;

    @FXML
    Label existed;

    @FXML
    Label error;

    public void initialize(){
        for (Manager manager: ProgramData.managers) {
            managerslist.getItems().add(manager.getUsername());
        }
    }


    public void save(ActionEvent actionEvent) {
        if (managername.getText().isEmpty() || managerpassword.getText().isEmpty())
            error.setVisible(true);
        else {
            error.setVisible(false);
            existed.setVisible(false);
            Manager m = new Manager(managername.getText(), managerpassword.getText());
            for (Manager manager : ProgramData.managers) {
                if (m.getUsername().equals(manager.getUsername())) {
                    existed.setVisible(true);
                    return;
                }
            }
            ProgramData.managers.add(m);
            managerslist.getItems().add(m.getUsername());
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        new pageloader().load("../View/GeneralManagerPanel.fxml");
    }
}
