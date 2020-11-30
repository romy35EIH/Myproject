package domain;

public class Announce {
    private int id;
    private String title;
    private String brief;
    private String time;

    public String getTime() {
        return time;
    }
    public void settime(String time) {
        this.time = time;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getBrief() {
        return brief;
    }
    public void setbrief(String brief) {
        this.brief = brief;
    }


}
