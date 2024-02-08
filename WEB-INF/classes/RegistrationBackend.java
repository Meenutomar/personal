import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationBackend {
    public int registerUser(Person person) {
           // JDBC URL, username, and password of MySQL server

           String url = "jdbc:oracle:thin:@localhost:1521:xe";
           String user = "bpm";
           String password = "bpm";
           int numberOfRecordsUpdated = 0;
           try {
               // Register JDBC driver
               Class.forName("oracle.jdbc.driver.OracleDriver"); // Type-4 Driver
   
               // Open a connection
               Connection connection = DriverManager.getConnection(url, user, password);
   
               // Insert record in table
               String sql = "insert into Person( Name,  Password,Email) VALUES(?,?,?)";
               PreparedStatement statement = connection.prepareStatement(sql);
               statement.setString(1, person.getName());
               statement.setString(2, person.getPassword());
               statement.setString(3, person.getEmail());
               numberOfRecordsUpdated = statement.executeUpdate();

               // Close resources
               statement.close();
               connection.close();
               
           } catch (ClassNotFoundException | SQLException e) {
               throw new PersonException(e);
           }
           return numberOfRecordsUpdated;

    }
    
}
