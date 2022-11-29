package executemethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExecuteSelectQuery {

    public static void main(String[] args) {

        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
                Statement statement = connection.createStatement();
                )
        {
            String query = "select * from employee";
            boolean flag = statement.execute(query);
            System.out.println(flag);
            ResultSet resultSet = statement.getResultSet();
            System.out.println("ENO\tENAME");
            while (resultSet.next())
            {
                System.out.print(resultSet.getInt("ENO") + "\t");
                System.out.print(resultSet.getString("ENAME") + "\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
