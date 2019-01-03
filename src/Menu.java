import java.util.Scanner;

public class Menu {

    private String role;

    public Menu(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void getMenu() {
        if(role.equals("pleb")) {
            System.out.println("1. Bestil pizza   2. Historik   3. Log ud");
            Scanner scanner = new Scanner(System.in);
        } else if(role.equals("Admin")) {
            System.out.println("1. Tilføj pizza   2. Se menu   3. Log ud");
            Scanner scanner = new Scanner(System.in);
        }
    }

}
