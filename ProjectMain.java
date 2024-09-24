package mongodb;

import java.sql.*;
import java.sql.ResultSet;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ProjectMain {

    public static void main(String[] args) throws SQLException {
        // JDBC Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMPANY", "root", "Zohra@123");
        Statement statement = connection.createStatement();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter value 1 to create PROJECTS document");
        System.out.println("Enter value 2 to create EMPLOYEES document");
        System.out.println("Enter value 3 to create DEPARTMENTS document");
        System.out.println("Enter value 4 to exit");

        //Menu driven logic
        System.out.println("Enter your choice:: ");

        //accept user input
        int choice = scan.nextInt();

        switch(choice){
            case 1: System.out.println("I am in case 1");
                //method call for case 1
                createProject(statement);
                break;
            case 2: System.out.println("I am in case 2");
                //method call for case 2
                createEmployees(statement);
                break;
            case 3: System.out.println("I am in case 3");
                //method call for case 3
                createDepartment(statement);
                break;
            case 4: System.out.println("Exiting the application");
                System.exit(0);
            default: System.out.println("Incorrect input!!! Please re-enter choice from the menu");
        }

    }

    public static void createProject(Statement statement) throws SQLException {

        //SELECT query to create PROJECTS document in MongoDB
        ResultSet resultSet = statement.executeQuery("\n" +
                "SELECT p.Pname as PNAME, p.Pnumber as PNUMBER, d.Dname as DNAME, " +
                "e.Lname as EMP_LNAME, e.Fname as EMP_FNAME, w.Hours as HOURS FROM COMPANY.PROJECT as p \n" +
                "LEFT JOIN COMPANY.DEPARTMENT as d ON p.Dnum = d.Dnumber\n" +
                "LEFT JOIN COMPANY.EMPLOYEE as e ON d.Dnumber= e.Dno \n" +
                "LEFT JOIN COMPANY.WORKS_ON as w ON e.Ssn = w.Essn ORDER BY p.Pnumber;");


        //Storing ResultSet into a Hashmap
        HashMap<String, Project> makeJson
                = new HashMap<String, Project>();

        while(resultSet.next()) {
            String pname = resultSet.getString("PNAME");

            if (makeJson.containsKey(pname)){

                Project projectName = makeJson.get(resultSet.getString("PNAME"));
                EmployeeList employeeList2 = new EmployeeList(resultSet.getString("EMP_FNAME"), resultSet.getString("EMP_LNAME"), resultSet.getString("HOURS"));
                projectName.getEmployee().add(employeeList2);
           }

            else {

                Project project = new Project();
                List<EmployeeList> employeeList = new ArrayList<>();
                EmployeeList employeeList1 = new EmployeeList(resultSet.getString("EMP_FNAME"), resultSet.getString("EMP_LNAME"), resultSet.getString("HOURS"));

                project.setPname(resultSet.getString("PNAME"));
                project.setPnumber(resultSet.getString("PNUMBER"));
                project.setDname(resultSet.getString("DNAME"));
                employeeList.add(employeeList1);
                project.setEmployee(employeeList);
                makeJson.put(pname,project);

            }


        }

        //Converting hashmap into JSON using GSON
        List<String> jsonList = new ArrayList<String>();
        for (Map.Entry entry : makeJson.entrySet()) {

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            String JSONObject = gson.toJson(entry.getValue());
            jsonList.add(JSONObject);
            log(JSONObject);
        }
        System.out.println(jsonList);

        //Connecting to MongoDB
        String uri = "mongodb://localhost:27017/local";
        try  {
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("local");

            //Creating a PROJECTS document
            MongoCollection<Document> collection = database.getCollection("PROJECTS");
            List<Document> jsonDocument = new ArrayList<Document>();

            for (String object : jsonList) {
               Document jsnObject = Document.parse(object);
                jsonDocument.add(jsnObject);
            }

            //Inserting the PORJECTS document into MongoDB
            collection.insertMany(jsonDocument);

            //Closing MongoDB connection
            mongoClient.close();
        }catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
           }

        //Closing MySQL connection
        resultSet.close();
        statement.close();
    }

    public static void createEmployees(Statement statement) throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT COMPANY.EMPLOYEE.Lname AS EMP_LNAME, " +
                "COMPANY.EMPLOYEE.Fname AS EMP_FNAME, COMPANY.DEPARTMENT.Dname AS DNAME, \n" +
                "COMPANY.PROJECT.Pname as PNAME, COMPANY.PROJECT.Pnumber as PNUMBER, COMPANY.WORKS_ON.Hours AS HOURS \n" +
                "FROM COMPANY.EMPLOYEE " +
                "LEFT JOIN COMPANY.DEPARTMENT " +
                "ON COMPANY.EMPLOYEE.Dno = COMPANY.DEPARTMENT.Dnumber \n" +
                "LEFT JOIN COMPANY.WORKS_ON " +
                "ON COMPANY.EMPLOYEE.Ssn = COMPANY.WORKS_ON.Essn \n" +
                "LEFT JOIN COMPANY.PROJECT " +
                "ON COMPANY.WORKS_ON.Pno = COMPANY.PROJECT.Pnumber \n" +
                "ORDER BY COMPANY.EMPLOYEE.Ssn;");


        HashMap<String, Employee> makeJson
                = new HashMap<String, Employee>();

        while(resultSet.next()) {
            String emp_fname = resultSet.getString("EMP_FNAME");

            if (makeJson.containsKey(emp_fname)){

                Employee employeeName = makeJson.get(resultSet.getString("EMP_FNAME"));
                ProjectList projectList2 = new ProjectList(resultSet.getString("PNAME"), resultSet.getString("PNUMBER"), resultSet.getString("HOURS"));
                employeeName.getProjectList().add(projectList2);
            }

            else {

                Employee employee;
                employee = new Employee();
                List<ProjectList> projectList = new ArrayList<>();
                ProjectList projectList1 = new ProjectList(resultSet.getString("PNAME"), resultSet.getString("PNUMBER"), resultSet.getString("HOURS"));

                employee.setEMP_FNAME(resultSet.getString("EMP_FNAME"));
                employee.setEMP_LNAME(resultSet.getString("EMP_LNAME"));
                employee.setHOURS(resultSet.getString("HOURS"));
                projectList.add(projectList1);
                employee.setProjectList(projectList);
                makeJson.put(emp_fname,employee);

            }


        }
        List<String> jsonList = new ArrayList<>();
        for (Map.Entry entry : makeJson.entrySet()) {

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            String JSONObject = gson.toJson(entry.getValue());
            jsonList.add(JSONObject);
            log(JSONObject);
        }
        System.out.println(jsonList);

        String uri = "mongodb://localhost:27017/local";
        try  {
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("local");
            MongoCollection<Document> collection = database.getCollection("EMPLOYEES");
            List<Document> jsonDocument = new ArrayList<>();

            for (String object : jsonList) {
                Document jsnObject = Document.parse(object);
                jsonDocument.add(jsnObject);
            }
            collection.insertMany(jsonDocument);
            mongoClient.close();
        }catch (MongoException me) {
            System.err.println("An error occurred while attempting to run a command: " + me);
        }

        resultSet.close();
        statement.close();
    }

    public static void createDepartment(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT d.Dname AS DNAME, e1.Lname AS MGR_LNAME, d.Mgr_start_date AS MGR_START_DATE, \n" +
                "e2.Lname AS EMP_LNAME, e2.Fname AS EMP_FNAME, e2.Salary AS SALARY \n" +
                "FROM COMPANY.DEPARTMENT AS d\n" +
                "LEFT JOIN COMPANY.EMPLOYEE as e1 \n" +
                "ON d.Mgr_ssn = e1.Ssn \n" +
                "LEFT JOIN COMPANY.EMPLOYEE as e2 \n" +
                "ON d.Dnumber = e2.Dno;");


        HashMap<String, Department> makeJson
                = new HashMap<String, Department>();

        while(resultSet.next()) {
            String Dname = resultSet.getString("DNAME");

            if (makeJson.containsKey(Dname)){

                Department departmentName = makeJson.get(resultSet.getString("DNAME"));
                DepartmentList departmentList2 = new DepartmentList(resultSet.getString("EMP_LNAME"), resultSet.getString("EMP_FNAME"), resultSet.getString("SALARY"));
                departmentName.getDepartmentList().add(departmentList2);
            }

            else {

                Department department;
                department = new Department();
                List<DepartmentList> departmentList = new ArrayList<>();
                DepartmentList departmentList1 = new DepartmentList(resultSet.getString("EMP_LNAME"), resultSet.getString("EMP_FNAME"), resultSet.getString("SALARY"));

                department.setDNAME(resultSet.getString("DNAME"));
                department.setMANAGER_LNAME(resultSet.getString("MGR_LNAME"));
                department.setMGR_START_DATE(resultSet.getDate("MGR_START_DATE"));
                departmentList.add(departmentList1);
                department.setDepartmentList(departmentList);
                makeJson.put(Dname,department);

            }


        }
        List<String> jsonList = new ArrayList<>();
        for (Map.Entry entry : makeJson.entrySet()) {

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            String JSONObject = gson.toJson(entry.getValue());
            jsonList.add(JSONObject);
            log(JSONObject);
        }
        System.out.println(jsonList);

        String uri = "mongodb://localhost:27017/local";
        try  {
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("local");
            MongoCollection<Document> collection = database.getCollection("DEPARTMENTS");
            List<Document> jsonDocument = new ArrayList<>();

            for (String object : jsonList) {
                Document jsnObject = Document.parse(object);
                jsonDocument.add(jsnObject);
            }
            collection.insertMany(jsonDocument);
            mongoClient.close();
        }catch (MongoException me) {
            System.err.println("An error occurred while attempting to run a command: " + me);
        }

        resultSet.close();
        statement.close();
    }
    private static void log(Object print) {
        System.out.println(print);

    }

}