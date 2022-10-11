import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> studentList =null;

    public StudentList() {
        studentList = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> list) {
        studentList = list;
    }

    public void addStudent(Student student){
        getStudentList().add(student);
        
    }
    public ArrayList<Student> searchGender(String gender){
        ArrayList genderSearch = new ArrayList<>();
        System.out.println("Searching by gender: " + gender);
        for(int i = 0; i<studentList.size(); i++ ){
            if(gender.trim().equalsIgnoreCase(studentList.get(i).getGender())){
               genderSearch.add(studentList.get(i));
              
            }
        }
//      
		return genderSearch;
        }
    }




