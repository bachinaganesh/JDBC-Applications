package DDLTOPICS;

// TODO: Developed by 'Ganesh Web Tech'

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * TODO: create table by static method
 * TODO: create table by dynamic method
 */
public class CreateTable {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // load and register the oracle driver
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish connection between java application and oracle database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
            // Create a statement
            statement = connection.createStatement();

            String query;
            // creating table query by static method
            query = "create table emp(eno number(3), ename varchar2(10), esal float, eaddress varchar2(10))";

            // executing the query
            statement.executeUpdate(query);

            // success message
            System.out.println("Table emp is created successfully");

            // get the tableName and attributes from the dynamic input
            String tableName;
            String columnName;
            int columnSize;
            String columnDataType;
            int columnCount = 0;
            String status;

            // Taking tableName as input
            System.out.print("Enter the table name - ");
            tableName = scanner.next();
            query = "create table "+ tableName+ "(";
            while (true)
            {
                // Taking column name as input
                System.out.print("Enter the column name - ");
                columnName = scanner.next();
                System.out.print("Enter the column datatype - ");
                columnDataType = scanner.next();
                System.out.print("Enter the column size - ");
                columnSize = scanner.nextInt();
                columnCount += 1;
                if(columnCount == 1) {
                    query = query + columnName + " " + columnDataType + "(" + columnSize + ")";
                }
                else
                {
                    query = query + ", " + columnName + " " + columnDataType + "(" + columnSize + ")";
                }
                System.out.print("Do you want to add one more column press yes else no - ");
                status = scanner.next();
                if(status.equalsIgnoreCase("yes"))
                {
                    continue;
                }
                break;
            }
            query += ")";

            // executing the query
            statement.executeUpdate(query);
            System.out.println("Table "+tableName+" is created successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
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