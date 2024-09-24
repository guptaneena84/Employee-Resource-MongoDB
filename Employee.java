package mongodb;

import java.util.List;

public class Employee {
    String EMP_LNAME;
    String EMP_FNAME;
    String DNAME;

    List<ProjectList> projectList;

    public Employee(String EMP_LNAME, String EMP_FNAME, String DNAME,List<ProjectList> projectList) {
        this.EMP_LNAME = EMP_LNAME;
        this.EMP_FNAME = EMP_FNAME;
        this.DNAME = DNAME;
        this.projectList = projectList;
    }

    public Employee() {

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
        return DNAME;
    }

    public void setHOURS(String HOURS) {
        this.DNAME = HOURS;
    }

    public List<ProjectList> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectList> projectList) {
        this.projectList = projectList;
    }

}
