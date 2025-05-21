import java.text.DecimalFormat;

// Student.java
class Student {
    private String name;
    private int exam1;
    private int exam2;
    private int exam3;

    // Constructor with input validation
    public Student(String name, int exam1, int exam2, int exam3) {
        this.name = name;
        setExam1(exam1); // Use setter for validation
        setExam2(exam2); // Use setter for validation
        setExam3(exam3); // Use setter for validation
    }

    // Private helper method for exam score validation
    private void validateExamScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Exam scores must be between 0 and 100.");
        }
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getExam1() {
        return exam1;
    }

    public int getExam2() {
        return exam2;
    }

    public int getExam3() {
        return exam3;
    }

    // Setter methods (also include validation)
    public void setExam1(int exam1) {
        validateExamScore(exam1);
        this.exam1 = exam1;
    }

    public void setExam2(int exam2) {
        validateExamScore(exam2);
        this.exam2 = exam2;
    }

    public void setExam3(int exam3) {
        validateExamScore(exam3);
        this.exam3 = exam3;
    }

    // Method to calculate the average of the three exam scores
    public double calculateAverage() {
        return (exam1 + exam2 + exam3) / 3.0;
    }
}

// Main.java
 class Main {
    public static void main(String[] args) {
        // Formatter for two decimal places
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("--- Attempting to create Student 'John' with an invalid score ---");
        try {
            // Create a Student object for a student named "John" with exam scores 75, 110, and 90.
            // (Notice 110 is invalid.)
            Student studentJohn = new Student("John", 75, 110, 90);
            System.out.println("Student John created successfully.");
            System.out.println("Student: " + studentJohn.getName() +
                               ", Average Score: " + df.format(studentJohn.calculateAverage()));
        } catch (IllegalArgumentException e) {
            // If an exception is caught, print the exception message.
            System.err.println("Error creating student John: " + e.getMessage());
        }

        System.out.println("\n--- Attempting to create Student 'Jane' with valid scores ---");
        try {
            // Create a Student object for a student named "Jane" with valid exam scores
            Student studentJane = new Student("Jane", 85, 90, 75);
            System.out.println("Student Jane created successfully.");
            // If creation succeeds, print the student's name and average exam score.
            System.out.println("Student: " + studentJane.getName() +
                               ", Average Score: " + df.format(studentJane.calculateAverage()));

            // Demonstrate changing a score (will also trigger validation)
            System.out.println("\n--- Attempting to change Jane's exam2 score to an invalid value ---");
            try {
                studentJane.setExam2(105);
            } catch (IllegalArgumentException e) {
                System.err.println("Error updating Jane's exam2 score: " + e.getMessage());
            }

            System.out.println("\n--- Changing Jane's exam2 score to a valid value ---");
            studentJane.setExam2(95);
            System.out.println("Student: " + studentJane.getName() +
                               ", New Average Score: " + df.format(studentJane.calculateAverage()));


        } catch (IllegalArgumentException e) {
            System.err.println("Error creating student Jane: " + e.getMessage());
        }
    }
}