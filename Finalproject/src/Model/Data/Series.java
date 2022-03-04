package Model.Data;

public class Series extends Telecast{



    public Series(String name, int starttime, int endtime, String date, int replaystarttime, int replayendtime) {
        super(name, starttime, endtime,date);
        this.setReplaystarttime(replaystarttime);
        this.setReplayendtime(replayendtime);
    }

    @Override
    public String toString() {
        return "name= " + this.getName() +
                " \nstarttime=" + this.getStarttime() +
                " \nendtime=" + this.getEndtime() +
                " \ndate=" + this.getDate()+
                " \nreplystarttime=" + this.getReplaystarttime()+
                " \nreplyendtime=" + this.getReplayendtime() ;
    }
}
