package DDLTOPICS;

// TODO: Developed by 'Ganesh Web Tech'

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * TODO: In alter there are three sub queries are there
 *         1. add
 *         2. modify
 *         3. drop
 *  In these we can see all the three topics
 */
public class AlterTable {

    static Connection connection = null;
    static Statement statement = null;
    static String tableName = null;
    static Scanner scanner = null;

    // options for choose
    public static void options()
    {
        System.out.println("1. Add");
        System.out.println("2. Modify");
        System.out.println("3. Drop");
        System.out.println("4. Exit");
    }

    // add column into the existing table
    public static void addColumn()
    {
        if(tableName != null)
        {
            String columnName;
            String columnDataType;
            int columnSize;
            String query;

            System.out.print("Enter the column name - ");
            columnName = scanner.next();
            System.out.print("Enter the column data type - ");
            columnDataType = scanner.next();
            System.out.print("Enter the column size - ");
            columnSize = scanner.nextInt();

            query = "alter table " + tableName + " add " + columnName + " " + columnDataType + "(" + columnSize + ")";
            try {
                // executing the query
                statement.executeUpdate(query);
                // execution success message
                System.out.println("Column " + columnName + " added successfully");
            }catch(Exception e)
            {
                System.out.println("Column added exception occurred " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Table is not existed");
        }
    }

    // modify column into the existing table
    public static void modifyColumn()
    {
        if(tableName != null)
        {
            String columnName;
            String columnDataType;
            int columnSize;
            String query;

            System.out.print("Enter the modify column name - ");
            columnName = scanner.next();
            System.out.print("Enter the new modify column data type - ");
            columnDataType = scanner.next();
            System.out.print("Enter the new modify column size - ");
            columnSize = scanner.nextInt();

            // modify column query
            query = "alter table " + tableName + " modify " + columnName + " " + columnDataType + "(" + columnSize + ")";

            try {
                // executing the query
                statement.executeUpdate(query);
                // execution success message
                System.out.println("Column " + columnName + " modified successfully");
            }catch(Exception e)
            {
                System.out.println("Modifying exception occurred " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Table is not existed");
        }
    }

    // drop column into the existing table
    public static void dropColumn()
    {
        if(tableName != null)
        {
            String columnName;
            String query;

            System.out.print("Enter the column name - ");
            columnName = scanner.next();

            // dropping column name into existing table
            query = "alter table " + tableName + " drop column " + columnName;

            try {
                // executing the query
                statement.executeUpdate(query);
                // execution success message
                System.out.println("Column " + columnName + "dropped successfully");
            }catch(Exception e)
            {
                System.out.println("Dropping exception occurred " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Table is not existed");
        }
    }
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        int choice;

        // Loading and Register the oracle driver class
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            // Establish the connection between java application and database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");

            //Create a statement
            statement = connection.createStatement();

            // Taking tableName as input
            System.out.print("Enter the table name - ");
            tableName = scanner.next();

            while (true)
            {
                // chose the options add or modify or drop and perform the operations based on it.
                options();
                System.out.print("Choose the above option - ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> addColumn();
                    case 2 -> modifyColumn();
                    case 3 -> dropColumn();
                    case 4 -> System.exit(0);
                    default -> System.out.println("Invalid Option Chosen!");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                // closing the resources
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
