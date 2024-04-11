package universitysystem;

public class ReportData {
    private int studentId;
    private String firstName;
    private String lastName;
    private String courseName;
    private Float grades;

    public ReportData(int studentId, String firstName, String lastName, String courseName, Float grades) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseName = courseName;
        this.grades = grades;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Float getGrades() {
        return grades;
    }

    public void setGrades(Float grades) {
        this.grades = grades;
    }
}
