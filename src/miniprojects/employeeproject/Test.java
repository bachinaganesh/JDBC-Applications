package miniprojects.employeeproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test {

    static Connection connection = null;
    static Statement statement = null;

    static Scanner scanner = null;
    static Employee employee;

    static ResultSet resultSet;
    static String query;
    static boolean result;

    public static void displayOptions()
    {
        System.out.println("1. Add Employee");
        System.out.println("2. Display Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Display All Employees");
        System.out.println("6. Exit");
    }
    public static String addEmployee(Employee employee)
    {
        String query;
        query = "insert into employee values(" + employee.getEmployeeId() + ", '" + employee.getEmployeeName() + "', " + employee.getEmployeeSalary() +
                ", '" + employee.getEmployeeEmail() + "', '" + employee.getEmployeeMobileNumber() + "', '" + employee.getEmployeeAddress() + "')";
        try
        {
            int rowCount = statement.executeUpdate(query);
            if(rowCount == 1)
            {
                return "success";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "failure";
    }
    public static ResultSet displayEmployee(int employeeId)
    {
        String query;
        query = "select * from employee where eno = " + employeeId;
        try {
            resultSet = statement.executeQuery(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static String updateEmployee(int employeeId)
    {
        int choice;
        int rowCount;
        String status = "";

        query = "select * from employee where eno = " + employeeId;

        try {
            resultSet = statement.executeQuery(query);
            result = resultSet.next();

            if (!result) {
                status = "not existed";
            } else {

                while (true) {
                    System.out.println();
                    System.out.println("1. Update employee name");
                    System.out.println("2. Update employee salary");
                    System.out.println("3. Update employee email");
                    System.out.println("4. Update employee mobile number");
                    System.out.println("5. Update employee address");
                    System.out.println("6. Update all fields");

                    System.out.println();
                    System.out.print("Choose the above option - ");
                    choice = scanner.nextInt();

                    String name;
                    String email;
                    float salary;
                    String address;
                    String mobileNumber;

                    switch (choice) {
                        case 1:
                            System.out.print("Enter the new employee name - ");
                            name = scanner.next();
                            query = "update employee set ename = '" + name + "'";
                            break;
                        case 2:
                            System.out.print("Enter the new employee salary - ");
                            salary = scanner.nextFloat();
                            query = "update employee set esal = " + salary;
                            break;
                        case 3:
                            System.out.print("Enter the new employee email - ");
                            email = scanner.next();
                            query = "update employee set eemail = '" + email + "'";
                            break;
                        case 4:
                            System.out.print("Enter the new employee mobile number - ");
                            mobileNumber = scanner.next();
                            query = "update employee set emobile = '" + mobileNumber + "'";
                            break;
                        case 5:
                            System.out.print("Enter the new employee address - ");
                            address = scanner.next();
                            query = "update employee set eaddress = '" + address + "'";
                            break;
                        case 6:
                            System.out.print("Enter the new employee name - ");
                            name = scanner.next();
                            System.out.print("Enter the new employee salary - ");
                            salary = scanner.nextFloat();
                            System.out.print("Enter the new employee email - ");
                            email = scanner.next();
                            System.out.print("Enter the new employee mobile number - ");
                            mobileNumber = scanner.next();
                            System.out.print("Enter the new employee address - ");
                            address = scanner.next();
                            query = "update employee set ename = '" + name + "', esal = " + salary + ", eemail = '" + email + "', emobile = '" + mobileNumber
                                    + "', eaddress = '" + address + "'";
                            break;
                        default:
                            query = "";
                            System.out.println("------------------ Invalid Option Chosen --------------------");
                    }
                    if (query.length() == 0) {
                        continue;
                    } else {
                        rowCount = statement.executeUpdate(query);
                        if(rowCount == 1)
                            status = "success";
                        else
                            status = "failure";
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            status = "failure";
            e.printStackTrace();
        }
        return status;
    }

    public static int deleteEmployee(int employeeId)
    {
        int rowCount = 0;
        try
        {
            query = "delete from employee where eno = " + employeeId;
            rowCount = statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rowCount;
    }
    public static ResultSet displayAllEmployees()
    {
        try
        {
            query = "select * from employee";

            resultSet = statement.executeQuery("select * from employee");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void main(String[] args) throws Exception{

        scanner = new Scanner(System.in);

        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
        statement = connection.createStatement();

        int choice;
        int employeeId;
        String status;

        while (true)
        {
            displayOptions();
            System.out.print("Choose the above option  - ");
            choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    employee = new Employee();

                    System.out.print("Enter the employee id - ");
                    employee.setEmployeeId(scanner.nextInt());

                    System.out.print("Enter the employee name - ");
                    employee.setEmployeeName(scanner.next());

                    System.out.print("Enter the employee salary - ");
                    employee.setEmployeeSalary(scanner.nextFloat());

                    System.out.print("Enter the employee email - ");
                    employee.setEmployeeEmail(scanner.next());

                    System.out.print("Enter the employee mobile number - ");
                    employee.setEmployeeMobileNumber(scanner.next());

                    System.out.print("Enter the employee address - ");
                    employee.setEmployeeAddress(scanner.next());
                    status = addEmployee(employee);
                    if(status.equalsIgnoreCase("success"))
                    {
                        System.out.println("-------------- Employee record inserted successfully ---------------");
                    }
                    else
                    {
                        System.out.println("-------------- Employee record insertion failure -------------------");
                    }
                    break;
                case 2:
                    System.out.print("Enter the employee id - ");
                    employeeId = scanner.nextInt();

                    resultSet = displayEmployee(employeeId);

                    result = resultSet.next();

                    if (!result) {
                        System.out.println("---------------------- Employee Doesn't Existed ---------------------");
                    } else {
                        System.out.println();
                        System.out.println("-------------------- Employee Details ---------------------------");
                        System.out.println();
                        System.out.println("Employee Id - " + resultSet.getInt("ENO"));
                        System.out.println("Employee Name - " + resultSet.getString("ENAME"));
                        System.out.println("Employee Salary - " + resultSet.getString("ESAL"));
                        System.out.println("Employee Email - " + resultSet.getString("EEMAIL"));
                        System.out.println("Employee MobileNumber - " + resultSet.getString("EMOBILE"));
                        System.out.println("Employee Address - " + resultSet.getString("EADDRESS"));
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.print("Enter the employee id - ");
                    employeeId = scanner.nextInt();

                    status = updateEmployee(employeeId);

                    if(status.equalsIgnoreCase("not existed"))
                    {
                        System.out.println("------------------- Employee Doesn't Existed -------------------");
                    }
                    else if(status.equalsIgnoreCase("success"))
                    {
                        System.out.println("---------------- Employee Updated Successfully ---------------------");
                    }
                    else
                    {
                        System.out.println("---------------- Employee Updated Failure --------------------------");
                    }
                    break;
                case 4:
                    System.out.print("Enter the employee id - ");
                    employeeId = scanner.nextInt();

                    if(deleteEmployee(employeeId) != 0)
                    {
                        System.out.println("----------------- Employee Deleted Successfully ------------------");
                    }
                    else
                    {
                        System.out.println("----------------- Employee Deleted Failure -----------------------");
                    }
                    break;
                case 5:
                    resultSet = displayAllEmployees();
                    result = resultSet.next();
                    if(!result)
                    {
                        System.out.println("----------------- No Employees Data Found -------------------");
                    }
                    else
                    {
                        resultSet = displayAllEmployees();
                        System.out.println("====================== Employee Details =====================");
                        System.out.println("ENO\t\t\tENAME\t\t\tESAL\t\t\tEEMAIL\t\t\tEMOBILE\t\t\tEADDRESS");
                        while (resultSet.next())
                        {
                            System.out.print(resultSet.getInt("ENO") + "\t\t" + resultSet.getString("Ename") + "\t\t" +
                                    resultSet.getFloat("ESAL") + "\t\t" + resultSet.getString("EEMAIL") + "\t\t" +
                                    resultSet.getString("EMOBILE") + "\t\t" + resultSet.getString("EADDRESS") +  "\n");
                        }
                        System.out.println("===================================================");
                    }
                    break;
                case 6:
                    scanner.close();
                    connection.close();
                    System.out.println("------------------------ Thank You For Using Application ------------------------");
                    System.exit(0);
                    break;
            }
        }

    }
}
