package DMLTOPICS;

// Developed by ganesh web tech

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Update data from a table
 */
public class UpdateData {

    static Scanner scanner = null;
    static Connection connection = null;
    static Statement statement = null;
    static String tableName;

    // Update data into table by call this method
    public static void updateData()
    {
        int id;
        String name;
        float salary;
        int status;
        int rowCount;
        String query = null;
        System.out.print("Enter the id to update - ");
        id = scanner.nextInt();
        while(true) {
            System.out.println("1. Update name\n2. Update salary\n 3. Update both name and salary");
            System.out.print("Choose the above option - ");
            status = scanner.nextInt();
            if (status == 1) {
                System.out.print("Enter the name - ");
                name = scanner.next();
                query = "update " + tableName + " set fname = '" + name + "' where fno = " + id;
            } else if (status == 2) {
                System.out.print("Enter the salary - ");
                salary = scanner.nextFloat();
                query = "update " + tableName + " set fsalary = " + salary + " where fno = " + id;
            } else if(status == 3){
                System.out.print("Enter the name - ");
                name = scanner.next();
                System.out.print("Enter the salary - ");
                salary = scanner.nextFloat();
                query = "update " + tableName + " set fname = '" + name + "', fsalary = " + salary + " where fno = " + id;
            }
            else
            {
                System.out.println("-------------Invalid Option Chosen----------------");
                continue;
            }
            try {
                rowCount = statement.executeUpdate(query);
                System.out.println("-----------------" + rowCount + " rows are updated successfully! ------------------");
                break;
            }
            catch (Exception e)
            {
                System.out.println("Updation Error Occurred! " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
            statement = connection.createStatement();

            // Get the table name from the input
            System.out.print("Enter the table name - ");
            int status;
            tableName = scanner.next();
            do {
                updateData();
                System.out.print("Do you want to update one more press 1 else 0 - ");
                status = scanner.nextInt();
            }while (status == 1);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                // Closing the resources
                scanner.close();
                connection.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
