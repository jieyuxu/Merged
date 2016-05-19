public class Run {
  
  import java.io.BufferedReader;
  import java.io.FileNotFoundException;
  import java.io.FileReader;
  import java.io.IOException;

  public static ArrayList<String[]> getListOfStudentsFromCSV(csvName){
    ArrayList<String[]> students = new ArrayList<String[]>();
    br = new BufferedReader(new FileReader(csvName));
		while ((line = br.readLine()) != null) {
		  students.add(line.split",");
		}
		return students;
  }

  public static void main(String [] args){
    ArrayList<String> a = getListOfStudentsFromCSV("programrequests.csv")
    print a;
  }
}
