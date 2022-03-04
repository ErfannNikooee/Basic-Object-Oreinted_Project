package Model.Data;

public class News extends Telecast{
    private String presenter;

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getPresenter() {
        return presenter;
    }

    public News(String name, int starttime, int endtime, String date) {
        super(name, starttime, endtime,date);

    }

    public String toString() {
        return "name= " + this.getName() +
                " \nstarttime=" + this.getStarttime() +
                " \nendtime=" + this.getEndtime() +
                " \ndate=" + this.getDate()+
                " \npresenter=" + this.getPresenter();
    }
}
