package mongodb;

import java.util.Date;
import java.util.List;

public class Department {
    String DNAME;
    String MANAGER_LNAME;
    Date MGR_START_DATE;

    List<DepartmentList> departmentList;

    public Department(String DNAME, String MANAGER_LNAME, Date MGR_START_DATE, List<DepartmentList> departmentList) {
        this.DNAME = DNAME;
        this.MANAGER_LNAME = MANAGER_LNAME;
        this.MGR_START_DATE = MGR_START_DATE;
        this.departmentList = departmentList;
    }

    public Department() {

    }

    public String getDNAME() {
        return DNAME;
    }

    public void setDNAME(String DNAME) {
        this.DNAME = DNAME;
    }

    public String getMANAGER_LNAME() {
        return MANAGER_LNAME;
    }

    public void setMANAGER_LNAME(String MANAGER_LNAME) {
        this.MANAGER_LNAME = MANAGER_LNAME;
    }

    public Date getMGR_START_DATE() {
        return MGR_START_DATE;
    }

    public void setMGR_START_DATE(Date MGR_START_DATE) {
        this.MGR_START_DATE = MGR_START_DATE;
    }

    public List<DepartmentList> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentList> departmentList) {
        this.departmentList = departmentList;
    }


}
