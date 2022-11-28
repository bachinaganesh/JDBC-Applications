package FileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddDataInHtmlFile {
    public static void main(String[] args) {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            FileOutputStream fileOutputStream = new FileOutputStream("emp1.html");
            String data = "";
            data = data + "<html><head><title>Employee Data</title></head><body><table border=1>";
            data = data + "<tr><th>ENO</th><th>ENAME</th><th>ESAL</th></tr>";

            while (resultSet.next())
            {
                data = data + "<tr>";
                data = data + "<td>" + resultSet.getInt("ENO") + "</td>";
                data = data + "<td>" + resultSet.getString("ENAME") + "</td>";
                data = data + "<td>" + resultSet.getFloat("ESAL") + "</td>";
                data = data + "</tr>";
            }

            data = data + "</table></body></html>";

            byte [] byteArray = data.getBytes();
            fileOutputStream.write(byteArray);
            System.out.println("Data is successfully added into emp1.html file");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
