import java.util.*;
import java.io.*;

class Library implements Serializable {
    int BookID;
    String BookName;
    String genre;

    Library(int BookID, String BookName, String genre) {
        this.BookID = BookID;
        this.BookName = BookName;
        this.genre = genre;
    }

    public String toString() {
    	int len = 35-BookName.length();
    	if(!(len==0)) {
    		for(int i=0;i<len;i++) {
    			BookName=BookName+" ";  //for making uniformity while displaying results
    		}
    	}
        return BookID + "        " + BookName + "       " + genre;
    }
}

class LibraryData {
	LibraryData() throws Exception {
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        File file = new File("Library.txt");   
        File f = new File("IssueData.txt");
        ArrayList<Library> books = new ArrayList<>();  //creating an ArrayList of type Library
        ArrayList<IssueData> idata = new ArrayList<>();
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        ListIterator li = null;

        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            books = (ArrayList<Library>) ois.readObject();
            ois.close();
        }

        do {
        	System.out.println("\n-----Please select from the options below-----");
            System.out.println("     1.Add new book");
            System.out.println("     2.Display all books");
            System.out.println("     3.Search by BookID");
            System.out.println("     4.Search by Genre");
            System.out.println("     5.Remove a book");
            System.out.println("     6.Display Issued Books Data");
            System.out.println("     7.Logout");
            System.out.print("     >>");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:       //adding new book 
                    System.out.println("     No. of Books to Insert: ");
                    int n = sc.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.print("     >>Enter BookID: ");
                        int BookID = sc.nextInt();
                        sc.nextLine();
                        System.out.print("     >>Enter BookName: ");
                        String BookName = sc.nextLine();
                        System.out.print("     >>Enter genre: ");
                        String genre = sc.nextLine();
                        Library book = new Library(BookID, BookName, genre);
                        books.add(book);
                       
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(books);
                    oos.close();
                    break;
                case 2:    //displaying the books
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        books = (ArrayList<Library>) ois.readObject();
                        ois.close();
                        System.out.println("     >>Available Books:");
                        System.out.println("--------------------------------------");
                        li = books.listIterator();  
                        while (li.hasNext())            //iterating for every entry
                            System.out.println("     "+ li.next());
                        	System.out.println("--------------------------------------");
                    } else {
                        	System.out.println("     File not Exist!!");
                    }
                    break;

                case 3:   //searching by book id
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        books = (ArrayList<Library>) ois.readObject();
                        ois.close();
                        boolean found = false;
                        System.out.println("\n     >>Enter Book ID");
                        int BookID = sc.nextInt();
                        System.out.println("--------------------------------------");
                        li = books.listIterator();
                        while (li.hasNext()) {
                            Library e = (Library) li.next();
                            if (e.BookID == BookID) {  //checking for a match
                                System.out.println("     "+e);
                                found = true;
                            }
                        }
                        if (!found)
                            System.out.println("     >>Book is not Available!!");
                        	System.out.println("--------------------------------------");
                    } else {
                        	System.out.println("     >>File not Exist!!");
                    }
                    break;

                case 4:    //searching by genre
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        books = (ArrayList<Library>) ois.readObject();
                        ois.close();
                        boolean found = false;
                        System.out.println("\n     >>Enter genre");
                        String genre = sc.nextLine();
                        System.out.println("--------------------------------------");
                        li = books.listIterator();
                        while (li.hasNext()) {
                            Library e = (Library) li.next();
                            if (e.genre.equals(genre)) {
                                System.out.println("     "+e);
                                found = true;
                            }
                        }
                        if (!found)
                            System.out.println("     >>Genre not Available!!");
                        	System.out.println("--------------------------------------");
                    } else {
                        	System.out.println("     >>File not Exist!!");
                    }
                    break;

                case 5:
                	//deleting book from record
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        books = (ArrayList<Library>) ois.readObject();
                        ois.close();
                        boolean found = false;
                        System.out.println("     >>Enter Book ID to Delete");
                        int BookID = sc.nextInt();
                        System.out.println("--------------------------------------");
                        li = books.listIterator();
                        while (li.hasNext()) {
                            Library e = (Library) li.next();
                            if (e.BookID == BookID) {
                                li.remove();   //deleting the book
                                found = true;
                            }
                        }
                        if (found) {
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(books);
                            oos.close();
                            System.out.println("     >>Record deleted Sucessfully");
                        } else {
                            System.out.println("     >>Record not found");
                        }
                        System.out.println("--------------------------------------");
                    } else {
                        System.out.println("     >>File not Exist!!");
                    }
                    break;
                    
                case 6:{
                	//Displaying issued books data
                	if (f.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(f));
                        idata = (ArrayList<IssueData>) ois.readObject();
                        ois.close();
                        System.out.println("--------------------------------------");
                        System.out.println("     >>Books Issued: ");
                        li = idata.listIterator();  
                        while (li.hasNext())            //iterating for every entry
                            System.out.println("     "+ li.next());
                        	System.out.println("--------------------------------------");
                    } else {
                        	System.out.println("     File not Exist!!");
                    }
                    break;
                	
                }
            }

        } while (choice != 7);
        System.out.println("\n     >>Thank you for using our library resources.\n       Have a great day and Keep Exploring!");
        System.out.println("--------------------------------------");
        new login();
    }
}
