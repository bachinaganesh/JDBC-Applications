package executemethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * TODO: In this we can execute not select sql query through executeQuery() method
 *  and select sql query through executeUpdate() method and let's check what will be happen.
 */
public class Test {

    public static void main(String[] args) {

        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
                Statement statement = connection.createStatement();
                )
        {
            String selectQuery = "select * from employee";
            String nonSelectQuery = "update employee set esal=esal + 500";
            ResultSet resultSet = statement.executeQuery(nonSelectQuery);
            int rowCount = statement.executeUpdate(selectQuery);
            System.out.println(rowCount);
            while (resultSet.next())
            {
                System.out.println("Record found");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
