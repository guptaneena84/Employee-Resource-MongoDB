package mongodb;

public class ProjectList {
    String Pname;
    String Pnumber;
    String HOURS;

    public ProjectList(String pname, String pnumber, String HOURS) {
        Pname = pname;
        Pnumber = pnumber;
        this.HOURS = HOURS;
    }


    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPnumber() {
        return Pnumber;
    }

    public void setPnumber(String pnumber) {
        Pnumber = pnumber;
    }

    public String getHOURS() {
        return HOURS;
    }

    public void setHOURS(String HOURS) {
        this.HOURS = HOURS;
    }



}
