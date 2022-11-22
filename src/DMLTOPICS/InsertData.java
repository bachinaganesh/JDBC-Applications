package DMLTOPICS;

// Developed by 'Ganesh Web Tech'

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * TODO: Insert the data into the table
 */

// insert data into a table by call this method
public class InsertData {
    static Scanner scanner = null;
    static Connection connection = null;
    static Statement statement = null;
    static String tableName;
    public static void insertData()
    {
        String name;
        int no;
        float salary;
        System.out.print("Enter the id - ");
        no = scanner.nextInt();
        System.out.print("Enter the name - ");
        name = scanner.next();
        System.out.print("Enter the salary - ");
        salary = scanner.nextFloat();

        // insertion query
        String query = "insert into " + tableName + " values(" + no + ", '" + name + "', " + salary + ")";
        try {
            // executing the insertion query
            int rowCount = statement.executeUpdate(query);
            System.out.println(rowCount + " row inserted successfully!");
        }
        catch (Exception e)
        {
            System.out.println("Insertion error occurred " + e.getMessage());
        }
    }
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
            statement = connection.createStatement();

            // Get the table name from the input
            System.out.print("Enter the table name - ");
            tableName = scanner.next();
            int status;
            do {
                insertData();
                System.out.print("Do you want insert one more record press 1 else 0 - ");
                status = scanner.nextInt();
            }while (status == 1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
