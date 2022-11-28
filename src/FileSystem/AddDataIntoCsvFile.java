package FileSystem;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddDataIntoCsvFile {

    public static void main(String[] args) {
        try(
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
                Statement statement = connection.createStatement();
                FileOutputStream fileOutputStream = new FileOutputStream("emp1.csv");
                ResultSet resultSet = statement.executeQuery("select * from employee")
        )
        {
            String data = "ENO,ENAME,ESAL\n";
            while (resultSet.next())
            {
                data = data + resultSet.getInt("ENO") + ",";
                data = data + resultSet.getString("ENAME") + ",";
                data = data + resultSet.getFloat("ESAL") + "\n";
            }

            byte [] byteArray = data.getBytes();
            fileOutputStream.write(byteArray);
            System.out.println("Data successfully added into emp1.csv file");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
