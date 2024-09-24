package mongodb;

public class DepartmentList {
    String EMP_LNAME;
    String EMP_FNAME;
    String SALARY;

    public DepartmentList(String EMP_LNAME, String EMP_FNAME, String SALARY) {
        this.EMP_LNAME = EMP_LNAME;
        this.EMP_FNAME = EMP_FNAME;
        this.SALARY = SALARY;
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

    public String getSALARY() {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY = SALARY;
    }

}
