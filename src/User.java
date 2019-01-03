import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String user = "root";
    private String pass = "";
    private final String URL = "jdbc:mysql://localhost/pizzamenu?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private List<String> userList = new ArrayList<>();
    private String username;
    private String password;
    private String role;

    public User() {

    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Connection getConnection() {
        try {
           connection = DriverManager.getConnection(URL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void userLogin(String username, String password) throws SQLException {
        statement = getConnection().createStatement();
        String query = "SELECT * FROM users WHERE username='" + username + "' and password='" + password + "'";
        resultSet = statement.executeQuery(query);
        resultSet.last();
        if (resultSet.getRow() == 0) {
            System.out.println("Forkert login");
            statement.close();
            resultSet.close();
            System.exit(0);
        }
        resultSet.beforeFirst();
        while (resultSet.next()) {
            userList.add(resultSet.getString("username"));
            userList.add(resultSet.getString("password"));
            userList.add(resultSet.getString("role"));

            User user = new User(userList.get(0), userList.get(1), userList.get(2));

            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println("Velkommen " + username + ". Din rolle er " + user.getRole());
                switch (user.getRole()) {
                    case "pleb":
                        Menu pleb = new Menu(user.getRole());
                        pleb.getMenu();
                        break;
                    case "Admin":
                        Menu admin = new Menu(user.getRole());
                        admin.getMenu();
                        break;
                    default:

                        break;
                }
            }
        }
    }

}
