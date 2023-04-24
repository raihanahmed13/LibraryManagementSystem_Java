import java.io.IOException;
import java.util.*;

public class libraryMain {

	public static void options() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n     1. Register (New User) \n     2. Login");
		System.out.print("     >>");
		int op = sc.nextInt();

		switch (op) {
			case 1: {
				new register(); // calling the register class constructor for new registration
				break;
			}
			case 2: {
				new login(); // calling the login class constructor for login
				break;
			}
			default: {
				System.out.println("     Please Enter valid key!!");

				options(); // if wrong option is chosen then using recursion again invoking the options()
							// method
			}
		}
	}

	public static void main(String args[]) throws Exception {

		try {
			new createfile(); // calling the constructor of createfile for creating new file if not available

		} catch (IOException e) {
			System.out.println("Error Occured while accessing the file");
		}
		System.out.println("\n-----Welcome To Library-----");

		options(); // calling the options method for choosing login or register

	}
}
