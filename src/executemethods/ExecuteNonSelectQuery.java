package executemethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ExecuteNonSelectQuery {
    public static void main(String[] args) {

        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
                Statement statement = connection.createStatement();
                )
        {
            String query = "update employee set esal = esal + 500";
            boolean flag = statement.execute(query);
            System.out.println(flag);
            System.out.println(statement.getUpdateCount());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
