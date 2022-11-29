package executemethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ExecuteDynamicQuery {

    public static void main(String[] args) {

        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
                Statement statement = connection.createStatement();
                )
        {
            Scanner scanner = new Scanner(System.in);

            String query;
            System.out.print("Enter the employee related query - ");
            query = scanner.nextLine();
            boolean flag = statement.execute(query);
            if(flag)
            {
                System.out.println(flag);
                ResultSet resultSet = statement.getResultSet();
                System.out.println("ENO\tENAME");
                while (resultSet.next())
                {
                    System.out.print(resultSet.getInt("ENO") + "\t");
                    System.out.print(resultSet.getString("ENAME") + "\n");
                }
            }
            else
            {
                System.out.println(flag);
                int rowCount = statement.getUpdateCount();
                System.out.println("Rowcount - " + rowCount);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
