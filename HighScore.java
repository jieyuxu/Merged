import java.io.*;

public class HighScore{

    public static String read(){

	String fileName = "scores.csv";
	String line = null;
	String ans = "";

	try{
	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader =
		new BufferedReader(fileReader);	    
	    line = bufferedReader.readLine();
	    int i = 0;
	    while (line != null){
		ans += (line+"\n");
		i++;
		line = bufferedReader.readLine();
	    }
	    ans = ans.substring(0,ans.length()-1);
	    bufferedReader.close();
	}
	catch(FileNotFoundException ex){
	    System.out.println("couldn't open file");
	}
	catch(IOException ex){
	    System.out.println("couldn't read file");
	}
	return ans;
    }

    public static void write(String text){
	String fileName = "scores.csv";
	String old = read();
	try {
	    FileWriter fileWriter = new FileWriter(fileName);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    bufferedWriter.write(text);
	    //bufferedWriter.newLine();
	    //bufferedWriter.write("hello world");
	    bufferedWriter.close();
	}
	catch(IOException ex){
	    System.out.println("couldn't write to file");
	}
    }
    
	
   public static void main(String [] args){
	System.out.println(read());
	write("adding the next line now");
	System.out.println(read());	
    }	

    
}
	    

    
