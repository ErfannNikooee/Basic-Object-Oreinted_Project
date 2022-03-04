package Model.Data;

public class Ads extends Telecast{
    private Adscompany adscompany;

    public void setAdscompany(Adscompany adscompany) {
        this.adscompany = adscompany;
    }

    public Adscompany getAdscompany() {
        return adscompany;
    }


    public Ads(String name, int starttime, int endtime, String date, int replaystarttime, int replayendtime) {
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
                " \nreplyendtime=" + this.getReplayendtime()+
                " \nadscompany=" + this.adscompany.getUsername();
    }
}
