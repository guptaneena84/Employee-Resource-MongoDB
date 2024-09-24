package mongodb;

public class EmployeeList {
    String EMP_LNAME;
    String EMP_FNAME;
    String HOURS;

    public EmployeeList(String EMP_LNAME, String EMP_FNAME, String HOURS) {
        this.EMP_LNAME = EMP_LNAME;
        this.EMP_FNAME = EMP_FNAME;
        this.HOURS = HOURS;
    }


    public String getEMP_LNAME() {
        return EMP_LNAME;
    }

    public void setEMP_LNAME(String EMP_LNAME) {
        this.EMP_LNAME = EMP_LNAME;
    }

    public String getEMP_FNAME() {
        return EMP_FNAME;
    }

    public void setEMP_FNAME(String EMP_FNAME) {
        this.EMP_FNAME = EMP_FNAME;
    }

    public String getHOURS() {
        return HOURS;
    }

    public void setHOURS(String HOURS) {
        this.HOURS = HOURS;
    }
}
