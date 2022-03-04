package Model.Data;

public class Adsrequest {
    public boolean show=true;
    public Ads ads;

    public Channel channel;

    public Adsrequest(Ads ads, Channel channel) {
        this.ads = ads;
        this.channel = channel;
    }
}
