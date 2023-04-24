import java.io.*;
import java.util.*;

class login {
	login() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n-----Login Options----- \n\n     1. Admin\n     2. User");
		System.out.print("     >>");
		int op = sc.nextInt();
		switch (op) {
			case 1: {
				admin();
				break;
			}
			case 2: {
				users();
				break;
			}
			default: {
				System.out.println("     >>Invalid Option!!");
				new login();
			}
		}
		sc.close();
	}

	void admin() throws Exception, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n-----Enter Login Credentials-----");
		System.out.print("     UserID: ");
		String userid = sc.next();
		System.out.print("     Password: ");
		String password = sc.next();

		FileReader f = new FileReader("loginAdmin.txt");
		BufferedReader br = new BufferedReader(f); // buffered reader for reading from the login file
		String s = "";
		while ((s = br.readLine()) != null) {
			String check[] = s.split(":"); // splitting the line in the file using .split

			if (check[0].equals(userid) && check[1].equals(password)) {
				System.out.println("     Welcome: " + check[2]);
				new LibraryData(); // ADMIN functionality
				return;
			}

		}
		System.out.println("     >>Invalid Credentials!!");
		admin();

	}

	void users() throws Exception, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n-----Enter Login Credentials-----");
		System.out.print("     UserID: ");
		String userid = sc.next();
		System.out.print("     Password: ");
		String password = sc.next();

		FileReader f = new FileReader("loginUser.txt");
		BufferedReader br = new BufferedReader(f); // buffered reader for reading from the login file
		String s = "";
		while ((s = br.readLine()) != null) {
			String check[] = s.split(":"); // splitting the line in the file using .split

			if (check[0].equals(userid) && check[1].equals(password)) {
				System.out.println("     Welcome: " + check[2]); // before return call functions
				new LibraryDataUser(userid); // User functionalities
				return;
			}

		}
		System.out.println("Invalid Credentials!!");
		users();

	}

}
