import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Run {


    public static ArrayList<String[]> getListOfStudentsFromCSV(String csvName){
	ArrayList<String[]> students = new ArrayList<String[]>();
	BufferedReader br = new BufferedReader(new FileReader(csvName));
	String line;
	while ((line = br.readLine()) != null) {
	    students.add(line.split(","));
	}
	return students;
    }

    public static void main(String [] args){
	ArrayList<String[]> a = getListOfStudentsFromCSV("programrequests.csv");
	System.out.println(a);
    }
}
