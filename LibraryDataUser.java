import java.util.*;
import java.io.*;


//Library class is already available in LibraryData class
class IssueData implements Serializable {   //for serialize or deserialize data
	
	int BookId;
	String UserId;
	String bname;
	String genre;
	IssueData(int BookId, String UserId,String bname, String genre){
		this.BookId=BookId;
		this.UserId=UserId;
		this.bname=bname;
		this.genre=genre;
	}
	public String toString() {
    	int len = 35-bname.length();
    	if(!(len==0)) {
    		for(int i=0;i<len;i++) {
    			bname=bname+" ";  //for making uniformity while displaying results
    		}
    	}
        return UserId+"     "+BookId + "        " + bname + "  " + genre;
    }
	
	
}
class LibraryDataUser {
	LibraryDataUser(String id) throws Exception {
		String userid=id;
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        File file = new File("Library.txt");
        File f = new File("IssueData.txt");
        ArrayList<Library> books = new ArrayList<>();
        ArrayList<IssueData> idata = new ArrayList<>();
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        ListIterator li = null;
        
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            books = (ArrayList<Library>) ois.readObject();
            ois.close();
        }
        if (f.isFile()) {  //if the file is normal then create input stream for reading from the file
            ois = new ObjectInputStream(new FileInputStream(f));
            idata = (ArrayList<IssueData>) ois.readObject(); //deserializes  and cast to the ArrayList
            ois.close();
        }

        do {
        	System.out.println("\n-----Please select from the options below-----");
            
            System.out.println("     1.Display all books");
            System.out.println("     2.Search by BookID");
            System.out.println("     3.Search by Genre");
            System.out.println("     4.Issue Book");   
            System.out.println("     5.List of Issued Books");   
            System.out.println("     6.Return Book");  //not yet done
            System.out.println("     7.Logout");
            System.out.print("     >>");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
               
                case 1:{
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
            }

                case 2:{
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
            }

                case 3:{
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
                }

                case 4:{
                    //Issue book
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
                        	
                        boolean found = false;
                        System.out.print("     >>Enter Book ID: ");
                        int BookID = sc.nextInt();
                        
                        System.out.println("--------------------------------------");
                        System.out.println("     >>Book Issued Successfully");
                        li = books.listIterator();
                        while (li.hasNext()) {
                            Library e = (Library) li.next();
                            if (e.BookID == BookID) {
                                System.out.println("     "+e);
                                found = true;
                                
                                if(f.isFile()) {                            		
                            		IssueData i1 = new IssueData(e.BookID,userid,e.BookName,e.genre);
                            		idata.add(i1);
                            		oos = new ObjectOutputStream(new FileOutputStream(f));
                                    oos.writeObject(idata);
                                    oos.close();
                                   
                            	}                                                               
                            }
                        }
                        if (!found)
                        
                        	System.out.println("Book is not Available");
                        System.out.println("--------------------------------------");
                        
                        
                    } else {
                        System.out.println("File not Exist.");
                    }
                    break;
                }
                
                case 5:{
                	//Listing the issued books
                	if (f.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(f));
                        idata = (ArrayList<IssueData>) ois.readObject();
                        ois.close();                        
                        boolean found = false;
                        
                        System.out.println("--------------------------------------");
                        System.out.println("     >>Books Issued: ");
                        li = idata.listIterator();
                        while (li.hasNext()) {
                            IssueData e = (IssueData) li.next();
                            if (e.UserId.equals(userid)) {  //checking for current user data
                            	
                                System.out.println("     "+e);
                                found = true;                                                                
                            }
                        }
                        if (!found)
                            System.out.println("     >>No Books Issued Yet!!");
                        	System.out.println("--------------------------------------");
                    } else {
                        	System.out.println("     >>File not Exist!!");
                    }
                    break;
                   
                }
                
                case 6:{  //return book 
                	if(f.isFile()) {
                		ois =new ObjectInputStream(new FileInputStream(f));
                		idata = (ArrayList<IssueData>) ois.readObject();
                		ois.close();
                		
                		boolean found = false;
                        
                        System.out.println("--------------------------------------");
                        System.out.println("     >>Books Issued: ");
                        li = idata.listIterator();
                        while (li.hasNext()) {
                            IssueData e = (IssueData) li.next();
                            if (e.UserId.equals(userid)) {  //checking for current user data
                            	
                                System.out.println("     "+e);
                                found = true;                                                                
                            }
                        }
                        if (!found)
                            System.out.println("     >>No Books Issued Yet!!");
                        	System.out.println("--------------------------------------");
                		
                		
                		
                		
                		boolean found1 = false;
                        System.out.print("     >>Enter Book ID to Return: ");
                        int BookID = sc.nextInt();
                        System.out.println("--------------------------------------");
                        li = idata.listIterator();
                        while (li.hasNext()) {
                            IssueData e = (IssueData) li.next();
                            if (e.BookId == BookID) {
                                li.remove();   
                                found1 = true;
                            }
                        }
                        if (found1) {
                            oos = new ObjectOutputStream(new FileOutputStream(f));
                            oos.writeObject(idata);
                            oos.close();
                            System.out.println("     >>Book Returned");
                        } else {
                            System.out.println("     >>Record not found");
                        }
                        System.out.println("--------------------------------------");
                		
                	}
                		
                		
                	else {
                		System.out.println("     >>File not Exist!!");
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
