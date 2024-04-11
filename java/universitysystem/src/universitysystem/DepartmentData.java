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
public class DepartmentData {
    
    
    private int deptId;
    private String deptName;
    private String managerName;
    
    public DepartmentData (int deptId , String deptName , String managerName){
    
    this.deptId = deptId;
    this.deptName = deptName;
    this.managerName = managerName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
    
    
    
    
    
    
    
    
    
    
}
