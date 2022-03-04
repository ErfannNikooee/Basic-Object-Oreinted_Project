package Model.Data;

import java.util.ArrayList;

public class Manager {
    private String username;
    private String password;
    public boolean owning_channel=false;
    public Channel channel;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
    /*public void approveADS(Adsrequest request){
        int index=this.channel.adsrequests.indexOf(request);
        this.channel.adsrequests.get(index).accepted=true;
    }
    public void declineADS(Adsrequest request){
        int index=this.channel.adsrequests.indexOf(request);
        this.channel.adsrequests.get(index).accepted=false;
    }

     */

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
