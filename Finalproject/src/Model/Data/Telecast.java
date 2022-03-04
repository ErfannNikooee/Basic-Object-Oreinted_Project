package Model.Data;

import java.util.ArrayList;
import java.util.Objects;

public class Telecast {
    private String name;
    private int starttime;
    private int endtime;
    private String date;
    private double rate;
    private int replaystarttime;
    private int replayendtime;

    private ArrayList<Integer> rates=new ArrayList<Integer>();

    public void setRate(int rate){
        rates.add(rate);
        calculateRate();
    }

    public void calculateRate() {
        double S=0;
        for (Integer integer : rates) {
            S += integer;
        }
        this.rate=(S/rates.size());
    }
    public double getRate(){
        return rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setReplaystarttime(int replaystarttime) {
        this.replaystarttime = replaystarttime;
    }

    public int getReplaystarttime() {
        return replaystarttime;
    }

    public void setReplayendtime(int replayendtime) {
        this.replayendtime = replayendtime;
    }

    public int getReplayendtime() {
        return replayendtime;
    }

    public Telecast(String name, int starttime, int endtime, String date) {
        this.name = name;
        this.starttime = starttime;
        this.endtime = endtime;
        this.date=date;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telecast telecast = (Telecast) o;
        return starttime == telecast.starttime &&
                endtime == telecast.endtime &&
                replaystarttime == telecast.replaystarttime &&
                replayendtime == telecast.replayendtime &&
                name.equals(telecast.name) &&
                date.equals(telecast.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, starttime, endtime, date, replaystarttime, replayendtime);
    }
}
