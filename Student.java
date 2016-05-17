public class Student{
  
  //Private vars: osis, name, grade (2D array), gpa (calcGPA fxn), ap slots
  private int _osis, _apslots;
  private double _gpa;
  private String _name;
  private int[][] _grades;
  
  public Student(int osis, int apslots, String name, int[][] grades){
    _osis = osis;
    _apslots = apslots;
    _name = name;
    _grades = grades;
    _gpa = calcGPA();
  }
  
  private double calcGPA(){
    int total = 0;
    int numClasses = 0;
    for (int i = 0; i < _grades.length; i++){
      for (int j = 0; j < _grades[i].length; j++){
        total += _grades[i][j];
        numClasses++;
      }
    }
    return total * 1.0 / numClasses;
  }
  
  // accessor methods
  public double getGpa(){
    return _gpa;
  }
  public int getOSIS(){
    return _osis;
  }
  public int getAPslots(){
    return _apslots;
  }
  public String getName(){
    return _name;
  }
  public int[][] getGrades(){
    return _grades;
  }
  
  
}
