class Student2{
	private String name ;
	private int exam1;
	private int exam2;
	private int exam3;

boolean validatemarks(int marks){
			if(marks<=100 && marks>=0){
				return true;
			}
			else{
				return false;
			}
		}

Student2(String name,int exam1, int exam2,int exam3)throws Exception{
		this.name=name;
		if (validatemarks(exam1)&&validatemarks(exam2)&&validatemarks(exam3)){
				this.exam1=exam1;
				this.exam2=exam2;
				this.exam3=exam3;
		}
		else {
			System.out.println("Setteing marks values to 0");
			throw new Exception("Invalid Marks");	
			}
}
		
			
	String getName(){
		return name;
	}
	int getExam1(){
		return exam1;
	}
	int getExam2(){
		return exam2;
	}
	int getExam3(){
		return exam3;
	}
	
double Average(int exam1,int exam2, int exam3){
		if (validatemarks(exam1)&&validatemarks(exam2)&&validatemarks(exam3)){
			return(exam1+exam2+exam3)/3;
			 
		}
		else{
			return 0;
		}
		
	}
	
}

class StudentDemo{
	public static void main(String[]args){
	
		try{
		Student2 s1=new Student2("Jhon",75,110,90);
		System.out.println("Name of the student is "+s1.getName());
		System.out.println("Result of exam "+s1.getExam1()+", "+s1.getExam2()+", "+s1.getExam3());
		System.out.println("Average is: "+s1.Average(s1.getExam1(),s1.getExam2(),s1.getExam3()));
		}
		catch(Exception e){
			System.out.println("Exception is "+e.getMessage());
		}
		try{
		Student2 s2=new Student2("Eve",99,88,75);
		
		
		System.out.println("Name of the student is "+s2.getName());
		System.out.println("Result of exam "+s2.getExam1()+", "+s2.getExam2()+", "+s2.getExam3());
		System.out.println("Average is: "+s2.Average(s2.getExam1(),s2.getExam2(),s2.getExam3()));
		}
		
		catch(Exception e){
			System.out.println("Exception is "+e.getMessage());
		}
		
	}
}