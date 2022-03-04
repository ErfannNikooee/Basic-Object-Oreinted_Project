package Controller;

import Model.Data.*;
import Model.ProgramData;
import Model.pageloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class ManagingProgramsController {

    @FXML
    Label channelname;

    @FXML
    Label error;

    @FXML
    Label interference;

    @FXML
    TextField name;

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
    TextField presenter;

    @FXML
    ChoiceBox<String> telecasttype;

    @FXML
    ListView<String> description;

    ObservableList<String> telecaststypes=FXCollections.observableArrayList("Movie","News","Series","Entertainment");

    ObservableList<String> Alltelcasts=FXCollections.observableArrayList();

    public int findchannel(String name){
        int i=0;
        for (Channel channel:ProgramData.channels) {
            if (ProgramData.Currentusername.equals(channel.getManager().getUsername()))
                i= ProgramData.channels.indexOf(channel);
        }
        return i;
    }
    int channeli=0;
    int telecasti=0;

    public void editTelecast(int i, Telecast telecast){
        for (Telecast telecast1:ProgramData.channels.get(i).telecasts) {
            if (telecast.getName().equals(telecast1.getName())){
                if (ProgramData.channels.get(i).check(telecast)){
                    ProgramData.channels.get(i).edittelecast(telecast);
                    interference.setVisible(false);
                    error.setVisible(false);
                }
                else {
                    error.setVisible(false);
                    interference.setVisible(true);
                }
                return;
            }
        }
        if (ProgramData.channels.get(i).check(telecast)){
        ProgramData.channels.get(i).addtelecast(telecast);
        error.setVisible(false);
        interference.setVisible(false);}
        else {
            error.setVisible(false);
            interference.setVisible(true);
        }
    }

    public void initialize(){
        if (ProgramData.Currentusername.equals(ProgramData.gm.getUsername())) {
            for (Channel channel:ProgramData.channels) {
                if (channel.getName().equals(ProgramData.ChoosenChannel)){
                    channelname.setText("Channel : "+ channel.getName());
                    channeli=ProgramData.channels.indexOf(channel);
                }
            }
        }
        else {
            Manager m;
            for (Manager manager:ProgramData.managers) {
                if (manager.getUsername().equals(ProgramData.Currentusername)) {
                    m=manager;
                    for (Channel channel:ProgramData.channels) {
                        if (m.getUsername().equals(channel.getManager().getUsername())){
                            channelname.setText("Channel : "+ channel.getName());
                            channeli=ProgramData.channels.indexOf(channel);
                        }
                    }
                    break;
                }
            }
        }
        for (Telecast telecast:ProgramData.channels.get(channeli).telecasts) {
            Alltelcasts.add(telecast.toString());
        }
        description.setItems(Alltelcasts);
        telecasttype.setItems(telecaststypes);
        telecasttype.getSelectionModel().selectedItemProperty().addListener((observable,s,t1) -> {
            if(t1.equals("News")||t1.equals("Entertainment")) {
                presenter.setVisible(true);
                if (t1.equals("News")) {
                    replaystarttime.setVisible(false);
                    replayendtime.setVisible(false);
                } else {
                    replaystarttime.setVisible(true);
                    replayendtime.setVisible(true);
                }
            }
            else
                presenter.setVisible(false);
        });
    }

    public void save(ActionEvent actionEvent) {
        if (name.getText()==null||starttime.getText()==null||endtime.getText()==null||
                telecasttype.getValue()==null ||
                date.getText()==null||replaystarttime.getText()==null||replayendtime==null)
            error.setVisible(true);
        else {
            switch (telecasttype.getValue()) {
                case "Movie": {
                    Movie m = new Movie(name.getText(),
                            Integer.parseInt(starttime.getText()),
                            Integer.parseInt(endtime.getText()),
                            date.getText(),
                            Integer.parseInt(replaystarttime.getText()),
                            Integer.parseInt(replayendtime.getText())
                    );
                    editTelecast(channeli, m);
                    break;
                }
                case "News": {
                    News n = new News(name.getText(),
                            Integer.parseInt(starttime.getText()),
                            Integer.parseInt(endtime.getText()),
                            date.getText()
                    );
                    n.setPresenter(presenter.getText());
                    editTelecast(channeli, n);
                    break;
                }
                case "Series": {
                    Series s = new Series(name.getText(),
                            Integer.parseInt(starttime.getText()),
                            Integer.parseInt(endtime.getText()),
                            date.getText(),
                            Integer.parseInt(replaystarttime.getText()),
                            Integer.parseInt(replayendtime.getText())
                    );
                    editTelecast(channeli, s);
                    break;
                }
                case "Entertainment": {
                    Entertainment m = new Entertainment(name.getText(),
                            Integer.parseInt(starttime.getText()),
                            Integer.parseInt(endtime.getText()),
                            date.getText(),
                            Integer.parseInt(replaystarttime.getText()),
                            Integer.parseInt(replayendtime.getText())
                    );
                    m.setPresenter(presenter.getText());
                    editTelecast(channeli, m);
                    break;
                }
            }
        }
        Alltelcasts.clear();
        for (Telecast telecast2:ProgramData.channels.get(channeli).telecasts) {
            Alltelcasts.add(telecast2.toString());
        }
        description.setItems(Alltelcasts);
    }

    public void remove(ActionEvent actionEvent) {
        ProgramData.channels.get(channeli).telecasts.remove(telecasti);
        Alltelcasts.clear();
        for (Telecast telecast:ProgramData.channels.get(channeli).telecasts) {
            Alltelcasts.add(telecast.toString());
        }
        description.setItems(Alltelcasts);
    }

    public void select(MouseEvent mouseEvent) {
        if (description.getSelectionModel().getSelectedItems().isEmpty())
            return;
        int i;
        String[] str=description.getSelectionModel().getSelectedItems().toString().split(" ");
        for (i=0;i<ProgramData.channels.get(channeli).telecasts.size();i++){
            if(ProgramData.channels.get(channeli).telecasts.get(i).getName().equals(str[1])) {
                break;
            }
        }
        telecasti=i;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        if (ProgramData.gm.getUsername().equals(ProgramData.Currentusername))
            new pageloader().load("../View/ChoosingChannel.fxml");
        else
            new pageloader().load("../View/ManagerPanel.fxml");
    }
}
