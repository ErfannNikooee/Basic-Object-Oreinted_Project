package Model.Data;
import javax.sql.XADataSource;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Comparator;

class Sort implements Comparator<Telecast>{
  public int compare(Telecast a,Telecast b){
    return a.getStarttime()-b.getStarttime();
  }
}

public class Channel{
  private String name;
  private int x;
  private Manager manager;
  public ArrayList<Telecast> telecasts=new ArrayList<Telecast>();
  public ArrayList<Adsrequest> adsrequests=new ArrayList<Adsrequest>();

  public void addrequest(Adsrequest adsrequest){
    this.adsrequests.add(adsrequest);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }

  public Manager getManager() {
        return manager;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getprice(Ads ads,int starttiime,int endtime){
    ArrayList<Telecast> temp=telecasts;
    temp.add(ads);
    temp.sort(new Sort());
    int index=temp.indexOf(ads);
    if (telecasts.size()<=3) {
      temp.remove(ads);
      return (endtime - starttiime) * 100;
    }
    if (!(temp.get(index-1) instanceof Ads) && !(temp.get(index+1) instanceof Ads)) {
      temp.remove(ads);
      return (endtime - starttiime) * 100 * this.x;
    }
    else {
      temp.remove(ads);
      return (endtime - starttiime) * 100;
    }
  }


  public int findtelecast(String name){
    int i=0;
    for (Telecast telecast:telecasts) {
      if (telecast.getName().equals(name))
        i=telecasts.indexOf(telecast);
    }
    return i;
  }

  public Channel (String name,int x,Manager manager){
    this.name=name;
    this.x=x;
    this.manager=manager;
  }

  public boolean check(Telecast telecast){
    for (Telecast telecast1 : telecasts) {
      if (telecast1.getName().equals(telecast.getName()))
        continue;
      if (telecast1.getDate().equals(telecast.getDate())) {
        if ((telecast1.getStarttime()<telecast.getStarttime()
                &&telecast1.getEndtime()>telecast.getStarttime())
                ||(telecast1.getEndtime()>telecast.getEndtime()
                &&telecast1.getStarttime()<telecast.getEndtime())) {
          return false;
        }
        if ((telecast1.getReplaystarttime() < telecast.getReplaystarttime()
                && telecast1.getReplayendtime() > telecast.getReplaystarttime())
                ||(telecast1.getReplayendtime()>telecast.getReplayendtime()
                &&telecast1.getReplaystarttime()<telecast.getReplayendtime())) {
          return false;
        }
      }
    }
    return true;
  }

  public void addtelecast(Telecast telecast){
    this.telecasts.add(telecast);
    this.telecasts.sort(new Sort());
  }

  public void removetelecast(Telecast telecast){
    this.telecasts.remove(telecast);
    this.telecasts.sort(new Sort());
  }
  public void edittelecast(Telecast telecast){
    int index=0;
    this.telecasts.set(index,telecast);
    this.telecasts.sort(new Sort());
  }

}