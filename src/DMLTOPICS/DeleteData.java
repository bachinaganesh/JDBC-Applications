package DMLTOPICS;

//Developed by 'Ganesh Web Tech'

/**
 * TODO: Delete data from the table
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteData {
    static Scanner scanner = null;
    static Connection connection = null;
    static Statement statement = null;
    static String tableName;

    // delete record from the table by call this method
    public static void deleteData()
    {
        int id;
        String query;
        System.out.print("Enter the id - ");
        id = scanner.nextInt();
        query = "delete from " + tableName + " where fno = " + id;
        try
        {
            // executing the deletion query
            int rowCount = statement.executeUpdate(query);
            System.out.println("------------" + rowCount + " rows are deleted -------------");
        }
        catch (Exception e)
        {
            System.out.println("Deletion Error Occurred " + e.getMessage());
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
                deleteData();
                System.out.print("Do you want to delete one more record press 1 else 0 - ");
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
