package Model.Data;

import java.util.ArrayList;

public class Adscompany{
    private String username;
    private String password;
    public int money=0;

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

    public void setMoney(int money) {
        this.money = money;
    }

    public Adscompany(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public void Chargingaccount(int money){
        this.money+=money;
    }

}
