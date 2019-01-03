import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String user = "thomas";
    private String pass = "123456";
    private final String URL = "jdbc:mysql://212.237.138.123:3306/thomas?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private List<String> userList = new ArrayList<>();
    private byte isConnected;

    public Connection getConnection() {
        try {
           connection = DriverManager.getConnection(URL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public byte userLogin(String username, String password) throws SQLException {
        statement = getConnection().createStatement();
        String query = "SELECT * FROM userDatabase WHERE username='" + username + "' and password='" + password + "'";
        resultSet = statement.executeQuery(query);
        resultSet.last();
        if (resultSet.getRow() == 0) {
            isConnected = 0;
            statement.close();
            resultSet.close();
        }
        resultSet.beforeFirst();
        while (resultSet.next()) {
            userList.add(resultSet.getString("username"));
            userList.add(resultSet.getString("password"));
            userList.add(resultSet.getString("role"));

            if (username.equals(userList.get(0)) && password.equals(userList.get(1))) {
                System.out.println("Velkommen " + username + ". Din rolle er " + userList.get(2));
                switch (userList.get(2)) {
                    case "pleb":
                        isConnected = 1;
                        break;
                    case "Admin":
                        isConnected = 2;
                        break;
                    default:
                        isConnected = 3;
                        break;
                }
            }
        }
        return isConnected;
    }

}
