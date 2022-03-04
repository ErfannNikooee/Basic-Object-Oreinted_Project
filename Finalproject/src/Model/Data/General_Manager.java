package Model.Data;

import java.util.ArrayList;

public class General_Manager extends Manager{
    private ArrayList<Manager> managers=new ArrayList<Manager>();
    private ArrayList<Channel> channels=new ArrayList<Channel>();

    public General_Manager(String username, String password) {
        super(username, password);
    }


    public void addmanager(Manager manager){
        this.managers.add(manager);
    }
    public void createchannel(String name,int x){
        this.channels.add(new Channel(name,x,this));
    }

    public void setManager(Manager manager,Channel channel){
        int index=this.channels.indexOf(channel);
        this.channels.get(index).setManager(manager);
    }
}
