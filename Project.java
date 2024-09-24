package mongodb;


import java.util.List;

public class Project {
    String Pname;
    String Pnumber;
    String Dname;
    List<EmployeeList> EmployeeList;

    public Project(String pname, String pnumber, String dname, List<EmployeeList> EmployeeList) {
        this.Pname = pname;
        this.Pnumber = pnumber;
        this.Dname = dname;
        this.EmployeeList=EmployeeList;
    }

    public Project() {

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

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public List<EmployeeList> getEmployee() {
        return EmployeeList;
    }

    public void setEmployee(List<EmployeeList> employeeList) {
        EmployeeList = employeeList;
    }
}
