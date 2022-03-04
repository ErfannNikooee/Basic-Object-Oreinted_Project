package Model.Data;

public class Entertainment extends Telecast{
    private String presenter;

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getPresenter() {
        return presenter;
    }

    public Entertainment(String name, int starttime, int endtime, String date, int replaystarttime, int replayendtime) {
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
                " \npresenter="+ this.getPresenter()+
                " \nreplystarttime=" + this.getReplaystarttime()+
                " \nreplyendtime=" + this.getReplayendtime() ;
    }
}
