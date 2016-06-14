import java.util.ArrayList;
import java.io.*;

public class ScoreTest{

    
    public static int _score;
    public static String _name;
   
    public static boolean saveHighScore(int score, String name){
	String scoresText = HighScore.read();
        String[] splitLines = scoresText.split("\n");
	ArrayList<String> namesNums = new ArrayList<String>();
	for (int i = 0; i < splitLines.length; i++){
	    String curr = splitLines[i];
	    namesNums.add(curr.substring(0,curr.indexOf(",")));
	    namesNums.add(curr.substring(curr.indexOf(",")+1));
	}
	ArrayList<Integer> nums = new ArrayList<Integer>();
	for (int i = 1; i < namesNums.size(); i += 2)
	    nums.add(Integer.parseInt(namesNums.get(i)));
	ArrayList<String> names = new ArrayList<String>();
	for (int i = 0; i < namesNums.size(); i += 2)
	    names.add(namesNums.get(i));
	if (score <= nums.get(nums.size()-1))
	    return false; //doesn't belong on list
	int pos = nums.size()-1;
	while (pos >= 0){
	    if (score > nums.get(pos)) pos--;
	    else break;
	}
	pos++;
	nums.add(pos, score);
	nums.remove(nums.size()-1);
	names.add(pos, name);
	names.remove(names.size()-1);
	String output = "";
	for (int i = 0; i < nums.size(); i++)
	    output += names.get(i)+","+nums.get(i)+"\n";
	output = output.substring(0, output.length()-1);	
        HighScore.write(output);
	return true;
    }
	

    public static void main(String[] args){	
	System.out.println(HighScore.read());
	System.out.println("---------------------");
	_score = 10;
	_name = "BOO";
	saveHighScore(32,"WOO");
	System.out.println(HighScore.read());	
    }

}

	

	
	
