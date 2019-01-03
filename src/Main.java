import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        User user = new User();

        System.out.println("Indtast username og password");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        String password = sc.nextLine();
        byte temp = 0;
        try {
            temp = user.userLogin(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch (temp) {
            case 0:
                System.out.println("Forkert login!");
                break;
            case 1:
                System.out.println("pleb");
                break;
            case 2:
                System.out.println("Admin");
                break;
            case 3:
                System.out.println("Fejl i systemet.");
                break;
        }

    }

}
