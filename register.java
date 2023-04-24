import java.io.*;
import java.util.*;

class password { // checking password requirements

	boolean checkPass(String pass) {
		boolean numCheck = false;
		boolean upperCheck = false;
		for (int i = 0; i < pass.length(); i++) {
			if (pass.charAt(i) >= '0' && pass.charAt(i) <= '9') { // checking for numeric value
				numCheck = true;
				break;
			}
		}

		for (int i = 0; i < pass.length(); i++) { // checking for upper case
			if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z') {
				upperCheck = true;
				break;
			}
		}

		if (pass.length() < 8) {
			System.out.println("     >>Password length should be greater than or equal to 8!!");
			return false;

		} else if (!upperCheck) {
			System.out.println("     >>Password must consists of atleast 1 uppercase alphabet!!");
			return false;
		}

		else if (!numCheck) {
			System.out.println("     >>No numeric character present");
			return false;
		}

		else {
			System.out.println("     >>Password is good");
		}
		return true;

	}

}

public class register extends password { // inheriting class pasword
	register() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n-----Enter registration option-----");
		System.out.println("     1. Admin Register\n     2. User Register");
		System.out.print("     >>");
		int op = sc.nextInt(); // enter register option

		switch (op) {
			case 1: {
				FileWriter fw = new FileWriter("loginAdmin.txt", true); // open loginUser file to write

				System.out.print("\n     Enter UserID: ");
				String userid = sc.next(); // entering userid

				if (checkAdmin(userid)) { // checking if the user is already exists

					System.out.print("     Enter Password: ");
					String pass = sc.next(); // entering password

					password p1 = new password();
					if (p1.checkPass(pass)) { // checking the password requirements
						System.out.print("\n     Enter Name: ");
						String name = sc.next(); // entering name
						fw.write("\n" + userid + ":" + pass + ":" + name + "\n"); // concatenating and writing on to the
																					// file
						fw.close();
						System.out.println("     >>Registration Successful");
						new login();
					} else {
						System.out.println(
								"Password length must be 8 and consists a numeric characte and an uppercase alphabet!!");
						new register();
					}
				} else {
					System.out.println("UserID already exists!!");
					new register();
				}
				break;
			}
			case 2: {
				FileWriter fw = new FileWriter("loginUser.txt", true); // open loginUser file to write

				System.out.print("\n     Enter UserID: ");
				String userid = sc.next(); // entering userid

				if (checkUser(userid)) { // checking if the user is already exists

					System.out.print("     Enter Password: ");
					String pass = sc.next(); // entering password
					password p1 = new password();
					if (p1.checkPass(pass)) { // checking the password requirements
						System.out.print("\n     Enter Name: ");
						String name = sc.next(); // entering name
						fw.write("\n" + userid + ":" + pass + ":" + name + "\n"); // concatenating and writing on to the
																					// file
						fw.close();
						System.out.println("     >>Registration Successful");
						new login();
					} else {
						System.out.println(
								"     >>Password length must be 8 and consists of a numeric value and an uppercase alphabet!!");
						new register();
					}
				} else {
					System.out.println("     >>UserID already exists!!");
					new register();
				}
				break;
			}

			default: {
				System.out.print("     >>Enter a valid option!!\n");
				new register(); // after entering wrong option again call the register class constructor
			}
		}

		File f = new File("register.txt");
		if (!f.exists()) {
			f.createNewFile();

		}

	}

	boolean checkUser(String uid) throws IOException { // checking in the loginUser file for userid

		FileReader f = new FileReader("loginUser.txt");
		BufferedReader br = new BufferedReader(f); // buffered reader for reading from the login file
		String s = "";
		while ((s = br.readLine()) != null) {
			String check[] = s.split(":"); // splitting the line in the file using .split

			if (check[0].equals(uid)) {
				return false;
			}
		}
		return true;
	}

	boolean checkAdmin(String uid) throws IOException { // checking in the loginAdmin file for userid
		FileReader f = new FileReader("loginAdmin.txt");
		BufferedReader br = new BufferedReader(f); // buffered reader for reading from the login file
		String s = "";
		while ((s = br.readLine()) != null) {
			String check[] = s.split(":"); // splitting the line in the file using .split

			if (check[0].equals(uid)) {
				return false;
			}
		}
		return true;
	}

}
