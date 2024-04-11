/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitysystem;

/**
 *
 * @author Dell
 */
public class DepartmentReportData {

    private String departmentName;
    private String managerName;
    private String courseName;
    private int studentsCount;

    public DepartmentReportData(String departmentName, String managerName, String courseName, int studentsCount) {
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.courseName = courseName;
        this.studentsCount = studentsCount;
    }

    // Getters and setters (you can generate these using your IDE)

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }
}

