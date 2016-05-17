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
    _gpa = getGpa();
  }
  
  private double getGpa(){
    int total = 0;
    int numClasses = 0;
    for (int i = 0; i < grades.length; i++){
      for (int j = 0; j < grades[i].length; j++){
        total += grades[i][j];
        numClasses++;
      }
    }
    return total * 1.0 / numClasses;
  }
  
  
  
}
