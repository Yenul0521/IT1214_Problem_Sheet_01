class Student{
	private int studentId;
	private String name;
	private int daysAttended;
	
	Student(int sid, String nm, int dayat){
		studentId=sid;
	    name=nm;
		daysAttended=dayat;
	}
	
	public void setStudentId(int studentId){
		this.studentId=studentId;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setDaysAttended(int daysAttended){
		this.daysAttended=daysAttended;
	}
	
	int getStudentId(){
		return studentId;
	}
	String getName(){
		return name;
	}
	int getDaysAttended(){
		return daysAttended;
	}
}	
class Classroom{
	private Student[] stuarray=new Student[10];
	private int studentcount=0;
	
	 void addstudent(Student stu){
		if(studentcount < stuarray.length){
			stuarray[studentcount]=stu;
			studentcount++;
		}
		else{
			System.out.println("Classroom is full; can't add more students");
		}
	}
	
	 void updateattends(int studentId, int updates){
		for(int i=0;i<studentcount;i++){
			if(stuarray[i].getStudentId() == studentId){
				stuarray[i].setDaysAttended(updates);
				return;
			}
		}
		System.out.println("Student ID "+studentId+" is not founded. Please enter a valid student id");
	}

	 void display(){
		for(int i=0;i<studentcount;i++){
			System.out.println("StudentID	Name		DaysAttended" );
			System.out.println(stuarray[i].getStudentId()+"\t\t"+stuarray[i].getName()+"\t"+stuarray[i].getDaysAttended());
		}
		System.out.println("Class room is empty");
	}
}	
class AttendanceManagement {
public static void main(String[]args){
	Classroom cls=new Classroom();
	
	cls.addstudent(new Student(101,"Alice Smith",12));
	cls.addstudent(new Student(102,"Bob Jones",15));
	cls.addstudent(new Student(103,"Carol Lee",10));
	
	cls.updateattends(102,16);
	cls.updateattends(104,20);
	
	cls.display();
	}
}

