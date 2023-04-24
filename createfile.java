import java.io.*;


class createfile {
	createfile() throws IOException{
	File f1 = new File("loginAdmin.txt");
	if(!f1.exists()) {
		f1.createNewFile();
		FileWriter fw = new FileWriter("loginAdmin.txt",true); //setting the append parameter to true
		fw.write("1200:raihan123:Raihan Ahmed\n");
		fw.write("1202:megh122:Meghraj\n");
		fw.write("1205:sahil144:Sahil\n");
		fw.close();
	}
	
	
	File f2 = new File("loginUser.txt");
	if(!f2.exists()) {
		f2.createNewFile();
		FileWriter fw = new FileWriter("loginUser.txt",true); //setting the append parameter to true
		fw.write("1900:user1:user");
		fw.close();
	}
	}
}

