/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitysystem;

import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Dell
 */
public class dashboardcontroller implements Initializable {

    @FXML
    private Button student_info;

    @FXML
    private Button Grades_stud;

    @FXML
    private Button Course_form;

    @FXML
    private AnchorPane visible_student;

    @FXML
    private TableView<StudentData> std_tbl;

    @FXML
    private TableColumn<StudentData, Integer> std_col;

    @FXML
    private TableColumn<StudentData, String> fn_col;

    @FXML
    private TableColumn<StudentData, String> ln_col;

    @FXML
    private TableColumn<StudentData, String> gn_col;

    @FXML
    private TableColumn<StudentData, String> ad_col;

    @FXML
    private TableColumn<StudentData, String> em_col;

    @FXML
    private TableColumn<StudentData, String> ph_col;

    @FXML
    private TableColumn<StudentData, Integer> deptid_std_col;

    @FXML
    private TextField id_btn;

    @FXML
    private TextField fn_btn;

    @FXML
    private TextField ln_btn;

    @FXML
    private TextField ge_btn;

    @FXML
    private TextField ad_btn;

    @FXML
    private TextField em_btn;

    @FXML
    private TextField ph_btn;
    @FXML
    private TextField deptid_std_btn;

    @FXML
    private AnchorPane visible_grades;

    @FXML
    private TableView<ReportData> grade_table;

    @FXML
    private TableColumn<ReportData, Integer> student_col;

    @FXML
    private TableColumn<ReportData, String> first_col;

    @FXML
    private TableColumn<ReportData, String> last_col;

    @FXML
    private TableColumn<ReportData, String> course_col;

    @FXML
    private TableColumn<ReportData, Float> grades_col;
    @FXML
    private TableColumn<ReportData, String> gpa_col;
    @FXML
    private TextField studentIdTextField;
    @FXML
    private AnchorPane visible_course;

    @FXML
    private TableView<CourseData> course_table;

    @FXML
    private TableColumn<CourseData, Integer> courseid_col;

    @FXML
    private TableColumn<CourseData, String> name_col;

    @FXML
    private TableColumn<CourseData, String> desc_col;

    @FXML
    private TableColumn<CourseData, Integer> credit_col;

    @FXML
    private TableColumn<CourseData, String> Dept_col;

    @FXML
    private TextField name_btn;

    @FXML
    private TextField credit_btn;

    @FXML
    private TextField desc_btn;

    @FXML
    private TextField courseid_btn;

    @FXML
    private TextField deptid_btn;

    @FXML
    private Button Department_Form;
    @FXML
    private AnchorPane visible_deparement;

    @FXML
    private TableView<DepartmentData> departement_table;

    @FXML
    private TableColumn<DepartmentData, Integer> Departement_id_col;

    @FXML
    private TableColumn<DepartmentData, String> Departement_name_col;

    @FXML
    private TableColumn<DepartmentData, String> manager_col;

    @FXML
    private TextField deptid_text;

    @FXML
    private TextField deptname_text;

    @FXML
    private TextField mngrname_text;

    @FXML
    private Button grades_form;

    @FXML
    private AnchorPane visible_grades_student;

    @FXML
    private TableView<StudentCourse> gradestudent_table;

    @FXML
    private TableColumn<StudentCourse, Integer> STDID_COL;

    @FXML
    private TableColumn<StudentCourse, Integer> courseID_col;

    @FXML
    private TableColumn<StudentCourse, Date> date_col;

    @FXML
    private TableColumn<StudentCourse, BigDecimal> gradesstudent_col;

    @FXML
    private TextField grades_text;

    @FXML
    private TextField studentid_text;

    @FXML
    private TextField courseG_id_text;

    @FXML
    private TextField date_text;
    @FXML
    private Button dept_report;
    @FXML
    private AnchorPane visible_dept_report;

    @FXML
    private TableView<DepartmentReportData> dept_report_table;

    @FXML
    private TableColumn<DepartmentReportData, String> dept_report_name;

    @FXML
    private TableColumn<DepartmentReportData, String> mngr_dept_name;

    @FXML
    private TableColumn<DepartmentReportData, String> course_dept_name;

    @FXML
    private TableColumn<DepartmentReportData, Integer> student_count_dept;

    @FXML
    private TextField studentIdTextField1;
    @FXML
    private TextField dept_id_report;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void addStudentAdd() {
        String insertData = "INSERT INTO student (student_id, first_name, last_name, gender, address, email, phone,dept_id) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection connect = Database.connect();
                PreparedStatement prepare = connect.prepareStatement(insertData)) {

            Alert alert;
            if (id_btn.getText().isEmpty()
                    || fn_btn.getText().isEmpty()
                    || ln_btn.getText().isEmpty()
                    || ge_btn.getText().isEmpty()
                    || ad_btn.getText().isEmpty()
                    || em_btn.getText().isEmpty()
                    || ph_btn.getText().isEmpty()
                    || deptid_std_btn.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                String checkData = "SELECT * FROM student WHERE student_id = ?";
                try (PreparedStatement checkStatement = connect.prepareStatement(checkData)) {
                    checkStatement.setString(1, id_btn.getText());
                    ResultSet result = checkStatement.executeQuery();

                    if (result.next()) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Student #" + id_btn.getText() + " already exists!");
                        alert.showAndWait();
                    } else {
                        // Set parameters for the prepared statement
                        prepare.setString(1, id_btn.getText());
                        prepare.setString(2, fn_btn.getText());
                        prepare.setString(3, ln_btn.getText());
                        prepare.setString(4, ge_btn.getText());
                        prepare.setString(5, ad_btn.getText());
                        prepare.setString(6, em_btn.getText());
                        prepare.setString(7, ph_btn.getText());
                        prepare.setString(8, deptid_std_btn.getText());

                        // Execute the update
                        prepare.executeUpdate();

                        // Commit the transaction
                        // connect.commit();
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Added!");
                        alert.showAndWait();

                        addStudentsShowListData();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }

    public void addStudentUpdate() throws SQLException {
        String procedureCall = "{call update_info(?, ?, ?, ?, ?, ?, ?, ?)}";

        connect = Database.connect();

        try {
            Alert alert;
            if (id_btn.getText().isEmpty()
                    || fn_btn.getText().isEmpty()
                    || ln_btn.getText().isEmpty()
                    || ge_btn.getText().isEmpty()
                    || ad_btn.getText().isEmpty()
                    || em_btn.getText().isEmpty()
                    || ph_btn.getText().isEmpty()
                    || deptid_std_btn.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields ");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update student " + fn_btn.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    CallableStatement cs = connect.prepareCall(procedureCall);

                    // Set the parameters for the stored procedure
                    cs.setInt(1, Integer.parseInt(id_btn.getText()));
                    cs.setString(2, fn_btn.getText());
                    cs.setString(3, ln_btn.getText());
                    cs.setString(4, ge_btn.getText());
                    cs.setString(5, ad_btn.getText());
                    cs.setString(6, em_btn.getText());
                    cs.setString(7, ph_btn.getText());
                    cs.setInt(8, Integer.parseInt(deptid_std_btn.getText()));

                    cs.execute();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addStudentsShowListData();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources and commit the transaction
            if (connect != null) {
                connect.close();
            }
        }
    }

    public void addStudentDelete() {
        String deleteData = "DELETE FROM student WHERE student_id = ?";

        try (Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteData)) {

            Alert alert;
            if (id_btn.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please provide a student_id");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Student #" + id_btn.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    preparedStatement.setString(1, id_btn.getText());
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Deleted!");
                        alert.showAndWait();

                        addStudentsShowListData();
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("No student found with ID: " + id_btn.getText());
                        alert.showAndWait();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }

    public ObservableList<StudentData> addStudentsListData() {
        ObservableList<StudentData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";
        try (Connection connect = Database.connect();
                PreparedStatement prepare = connect.prepareStatement(sql);
                ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                // Assuming StudentData has a constructor that takes the fields in order
                StudentData student = new StudentData(
                        result.getInt("student_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("gender"),
                        result.getString("address"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getInt("dept_id")
                );

                listStudents.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
        }

        return listStudents;
    }
    private ObservableList<StudentData> addStudentsListD;

    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        std_col.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        fn_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ln_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        gn_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        ad_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        em_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        ph_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
        deptid_std_col.setCellValueFactory(new PropertyValueFactory<>("deptid"));

        std_tbl.setItems(addStudentsListD);
    }

    public void addStudentSelect() {
        StudentData student = std_tbl.getSelectionModel().getSelectedItem();
        int num = std_tbl.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        id_btn.setText(String.valueOf(student.getStudentId()));
        fn_btn.setText(String.valueOf(student.getFirstName()));
        ln_btn.setText(String.valueOf(student.getLastName()));
        ge_btn.setText(String.valueOf(student.getGender()));
        ad_btn.setText(String.valueOf(student.getAddress()));
        em_btn.setText(String.valueOf(student.getEmail()));
        ph_btn.setText(String.valueOf(student.getPhone()));
        deptid_std_btn.setText(String.valueOf(student.getDeptid()));

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == student_info) {
            visible_student.setVisible(true);
            visible_grades.setVisible(false);
            visible_course.setVisible(false);
            visible_deparement.setVisible(false);
            visible_grades_student.setVisible(false);
            visible_dept_report.setVisible(false);

            student_info.setStyle("-fx-background-color: #28798F;");
            Grades_stud.setStyle("-fx-background-color:transparent");
            Course_form.setStyle("-fx-background-color:transparent");
            Department_Form.setStyle("-fx-background-color:transparent");
            grades_form.setStyle("-fx-background-color:transparent");
            dept_report.setStyle("-fx-background-color:transparent");

            addStudentsShowListData();
        } else if (event.getSource() == Grades_stud) {
            visible_student.setVisible(false);
            visible_grades.setVisible(true);
            visible_course.setVisible(false);
            visible_deparement.setVisible(false);
            visible_grades_student.setVisible(false);
            visible_dept_report.setVisible(false);

            Grades_stud.setStyle("-fx-background-color: #28798F;");
            student_info.setStyle("-fx-background-color:transparent");
            Course_form.setStyle("-fx-background-color:transparent");
            Department_Form.setStyle("-fx-background-color:transparent");
            grades_form.setStyle("-fx-background-color:transparent");
            dept_report.setStyle("-fx-background-color:transparent");
            addStudentCourseShowListData();
        } else if (event.getSource() == Course_form) {
            visible_student.setVisible(false);
            visible_grades.setVisible(false);
            visible_course.setVisible(true);
            visible_deparement.setVisible(false);
            visible_grades_student.setVisible(false);
            visible_dept_report.setVisible(false);

            Course_form.setStyle("-fx-background-color: #28798F;");
            student_info.setStyle("-fx-background-color:transparent");
            Grades_stud.setStyle("-fx-background-color:transparent");
            Department_Form.setStyle("-fx-background-color:transparent");
            grades_form.setStyle("-fx-background-color:transparent");
            dept_report.setStyle("-fx-background-color:transparent");
            addCoursesShowListData();

        } else if (event.getSource() == Department_Form) {
            visible_student.setVisible(false);
            visible_grades.setVisible(false);
            visible_course.setVisible(false);
            visible_deparement.setVisible(true);
            visible_grades_student.setVisible(false);
            visible_dept_report.setVisible(false);

            Department_Form.setStyle("-fx-background-color: #28798F;");
            student_info.setStyle("-fx-background-color:transparent");
            Grades_stud.setStyle("-fx-background-color:transparent");
            Course_form.setStyle("-fx-background-color:transparent");
            grades_form.setStyle("-fx-background-color:transparent");
            dept_report.setStyle("-fx-background-color:transparent");
            addDepartmentShowListData();
        } else if (event.getSource() == grades_form) {
            visible_student.setVisible(false);
            visible_grades.setVisible(false);
            visible_course.setVisible(false);
            visible_deparement.setVisible(false);
            visible_grades_student.setVisible(true);
            visible_dept_report.setVisible(false);

            grades_form.setStyle("-fx-background-color: #28798F;");
            student_info.setStyle("-fx-background-color:transparent");
            Grades_stud.setStyle("-fx-background-color:transparent");
            Course_form.setStyle("-fx-background-color:transparent");
            Department_Form.setStyle("-fx-background-color:transparent");
            dept_report.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == dept_report) {
            visible_student.setVisible(false);
            visible_grades.setVisible(false);
            visible_course.setVisible(false);
            visible_deparement.setVisible(false);
            visible_grades_student.setVisible(false);
            visible_dept_report.setVisible(true);

            dept_report.setStyle("-fx-background-color: #28798F;");
            student_info.setStyle("-fx-background-color:transparent");
            Grades_stud.setStyle("-fx-background-color:transparent");
            Course_form.setStyle("-fx-background-color:transparent");
            Department_Form.setStyle("-fx-background-color:transparent");
            grades_form.setStyle("-fx-background-color:transparent");

        }
    }
////////////////////////////////////////////////

    @FXML
    private void handleGenerateDepartmentReportButton(ActionEvent event) {
        String departmentId = dept_id_report.getText();
        ObservableList<DepartmentReportData> departmentReportDataList = generateDepartmentReport(departmentId);

        // Display the report in the TableView
        dept_report_name.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        mngr_dept_name.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        course_dept_name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        student_count_dept.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));

        dept_report_table.setItems(departmentReportDataList);
    }

    private ObservableList<DepartmentReportData> generateDepartmentReport(String departmentId) {
        ObservableList<DepartmentReportData> departmentReportDataList = FXCollections.observableArrayList();

        String sql = "SELECT d.dept_name AS department_name, d.manager_name, c.course_name, COUNT(sc.student_id) AS students_count "
                + "FROM department d "
                + "LEFT JOIN course c ON d.dept_id = c.dept_id "
                + "LEFT JOIN student_course sc ON c.course_id = sc.course_id "
                + "WHERE d.dept_id = ? "
                + "GROUP BY d.dept_name, d.manager_name, c.course_name";

        try (Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, departmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    DepartmentReportData departmentReportData = new DepartmentReportData(
                            resultSet.getString("department_name"),
                            resultSet.getString("manager_name"),
                            resultSet.getString("course_name"),
                            resultSet.getInt("students_count")
                    );

                    departmentReportDataList.add(departmentReportData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
        }

        return departmentReportDataList;
    }

/////////////////
    ////////
    @FXML
    private void handleGenerateReportButton(ActionEvent event) {
        String studentId = studentIdTextField.getText();
        ObservableList<ReportData> reportDataList = generateReport(studentId);

        // Display the report in the TableView
        student_col.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        first_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        last_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        course_col.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        grades_col.setCellValueFactory(new PropertyValueFactory<>("grades"));

        grade_table.setItems(reportDataList);

        // Fetch GPA from the database
        String gpa = getGPAForStudent(studentId);

        // Check if GPA is available before displaying the Alert
        if (gpa != null && !gpa.isEmpty()) {
            // Display GPA information in an Alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("GPA Information");
            alert.setHeaderText(null);
            alert.setContentText("Student ID: " + studentId + "\nGPA: " + gpa);
            alert.showAndWait();
        } else {
            // GPA is not available
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("GPA information not available for the specified student.");
            alert.showAndWait();
        }
    }

    private ObservableList<ReportData> generateReport(String studentId) {
        ObservableList<ReportData> reportDataList = FXCollections.observableArrayList();

        String sql = "SELECT s.student_id, s.first_name, s.last_name, c.course_name, sc.grades "
                + "FROM student s "
                + "JOIN student_course sc ON s.student_id = sc.student_id "
                + "JOIN course c ON sc.course_id = c.course_id "
                + "WHERE s.student_id = ?";

        try (Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ReportData reportData = new ReportData(
                            resultSet.getInt("student_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("course_name"),
                            resultSet.getFloat("grades")
                    );

                    reportDataList.add(reportData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
        }

        return reportDataList;
    }

    private String getGPAForStudent(String studentId) {
        try (Connection connection = Database.connect();
                CallableStatement callableStatement = connection.prepareCall("{ ? = call PROJECT.calc_gpa(?) }")) {

            // Register the output parameter for the GPA
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            // Set the input parameter for the student ID
            callableStatement.setString(2, studentId);

            // Execute the function
            callableStatement.execute();

            // Get the GPA from the output parameter
            return callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
            return null;
        }
    }

    //////////////////////
//////////////////////////////////////////////////////////////////////////////
    public void addCourseAdd() throws SQLException {
        String insertData = "INSERT INTO course"
                + "(course_id, course_name, description, credit_hours, dept_id)"
                + "VALUES (?, ?, ?, ?, ?)";

        Connection connect = Database.connect();
        try {
            Alert alert;
            if (courseid_btn.getText().isEmpty()
                    || name_btn.getText().isEmpty()
                    || desc_btn.getText().isEmpty()
                    || credit_btn.getText().isEmpty()
                    || deptid_btn.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String checkData = "SELECT * FROM course WHERE course_id = '"
                        + courseid_btn.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Course id" + courseid_btn.getText() + " already exists!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(insertData);
                    prepare.setInt(1, Integer.parseInt(courseid_btn.getText())); // Parse to Integer for numeric column
                    prepare.setString(2, name_btn.getText());
                    prepare.setString(3, desc_btn.getText());
                    prepare.setInt(4, Integer.parseInt(credit_btn.getText())); // Parse to Integer for numeric column
                    prepare.setInt(5, Integer.parseInt(deptid_btn.getText()));  // Parse to Integer for numeric column

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addCoursesShowListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourse() throws SQLException {
        String updateData = "UPDATE course SET "
                + "course_name = '" + name_btn.getText()
                + "', description = '" + desc_btn.getText()
                + "', credit_hours = " + credit_btn.getText() // Removed single quotes for numeric value
                + ", dept_id = " + deptid_btn.getText() // Removed single quotes for numeric value
                + " WHERE course_id = " + courseid_btn.getText(); // Removed single quotes for numeric value

        connect = Database.connect();

        try {
            Alert alert;
            if (courseid_btn.getText().isEmpty()
                    || name_btn.getText().isEmpty()
                    || desc_btn.getText().isEmpty()
                    || credit_btn.getText().isEmpty()
                    || deptid_btn.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Course " + name_btn.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addCoursesShowListData();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse() throws SQLException {
        Alert alert;
        String courseId = courseid_btn.getText();

        if (courseId.isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a Course ID to delete");
            alert.showAndWait();
            return;
        }

        try (Connection connect = Database.connect();
                Statement statement = connect.createStatement()) {

            String checkData = "SELECT * FROM course WHERE course_id = '" + courseId + "'";
            result = statement.executeQuery(checkData);

            if (result.next()) {
                // Course exists, confirm deletion
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Course ID " + courseId + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    String deleteData = "DELETE FROM course WHERE course_id = '" + courseId + "'";
                    statement.executeUpdate(deleteData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addCoursesShowListData();
                }
            } else {
                // Course does not exist
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Course ID " + courseId + " does not exist!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCourseSelect() {
        CourseData course = course_table.getSelectionModel().getSelectedItem();

        if (course == null) {
            // No item selected, handle accordingly
            return;
        }

        int num = course_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        // Assuming these are your text fields for displaying data
        courseid_btn.setText(String.valueOf(course.getCourseId()));
        name_btn.setText(course.getCourseName());
        desc_btn.setText(course.getDescription());

        // Assuming credit_btn and deptid_btn are your text fields for displaying credit hours and department ID
        credit_btn.setText(String.valueOf(course.getCreditHours()));  // Convert int to String
        deptid_btn.setText(String.valueOf(course.getDeptId()));        // Convert int to String
    }

    public ObservableList<CourseData> addCoursesListData() {
        ObservableList<CourseData> listCourses = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course";
        try (Connection connect = Database.connect();
                PreparedStatement prepare = connect.prepareStatement(sql);
                ResultSet result = prepare.executeQuery()) {

            while (result.next()) {

                CourseData course = new CourseData(
                        result.getInt("course_id"),
                        result.getString("course_name"),
                        result.getString("description"),
                        result.getInt("credit_hours"),
                        result.getInt("dept_id")
                );

                listCourses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
        }

        return listCourses;
    }
    private ObservableList<CourseData> addCoursesListD;

    public void addCoursesShowListData() {
        addCoursesListD = addCoursesListData();

        courseid_col.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        credit_col.setCellValueFactory(new PropertyValueFactory<>("creditHours"));
        Dept_col.setCellValueFactory(new PropertyValueFactory<>("deptId"));

        course_table.setItems(addCoursesListD);
    }

/////////////////////////////////////////////////////////////////    
    public void addDepartmentAdd() throws SQLException {
        String insertData = "insert into department (dept_id, dept_name, manager_name) values (?,?,?)";

        Alert alert;

        try (Connection connect = Database.connect();
                PreparedStatement prepare = connect.prepareStatement(insertData);
                Statement statement = connect.createStatement()) {

            if (deptid_text.getText().isEmpty() || deptname_text.getText().isEmpty() || mngrname_text.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkData = "SELECT * FROM department WHERE dept_id = '" + deptid_text.getText() + "'";
                ResultSet result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Department id " + deptid_text.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    prepare.setInt(1, Integer.parseInt(deptid_text.getText()));
                    prepare.setString(2, deptname_text.getText());
                    prepare.setString(3, mngrname_text.getText());
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addDepartmentShowListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatedepartment() throws SQLException {

        String updateData = "update department set "
                + "dept_name ='" + deptname_text.getText() + "', "
                + "manager_name='" + mngrname_text.getText() + "' "
                + "where dept_id = " + deptid_text.getText();

        connect = Database.connect();
        try {
            Alert alert;
            if (deptid_text.getText().isEmpty()
                    || deptname_text.getText().isEmpty()
                    || mngrname_text.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Department " + deptname_text.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    addDepartmentShowListData();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteDepartment() throws SQLException {
        Alert alert;
        String deptId = deptid_text.getText();

        if (deptId.isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a Department ID to delete");
            alert.showAndWait();
            return;
        }

        try (Connection connect = Database.connect();
                Statement statement = connect.createStatement()) {

            String checkData = "SELECT * FROM department WHERE dept_id = '" + deptId + "'";
            ResultSet result = statement.executeQuery(checkData);

            if (result.next()) {
                // Department exists, confirm deletion
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Department ID " + deptId + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    String deleteData = "DELETE FROM department WHERE dept_id = '" + deptId + "'";
                    statement.executeUpdate(deleteData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addDepartmentShowListData();
                }
            } else {
                // Department does not exist
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Department ID " + deptId + " does not exist!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDepartmentSelect() {

        DepartmentData department = departement_table.getSelectionModel().getSelectedItem();
        if (department == null) {

            return;
        }

        int num = departement_table.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        deptid_text.setText(String.valueOf(department.getDeptId()));
        deptname_text.setText(department.getDeptName());
        mngrname_text.setText(department.getManagerName());

    }

    public ObservableList<DepartmentData> addDepartmentListData() {
        ObservableList<DepartmentData> listDepartment = FXCollections.observableArrayList(); // corrected method call

        String sql = "Select * from department"; // corrected variable name
        try (Connection connect = Database.connect();
                PreparedStatement prepare = connect.prepareStatement(sql);
                ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                DepartmentData department = new DepartmentData(
                        result.getInt("dept_id"),
                        result.getString("dept_name"),
                        result.getString("manager_name")
                );
                listDepartment.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
        }

        return listDepartment;
    }

    private ObservableList<DepartmentData> addDepartmentListD;

    public void addDepartmentShowListData() {
        addDepartmentListD = addDepartmentListData();

        Departement_id_col.setCellValueFactory(new PropertyValueFactory<>("deptId"));
        Departement_name_col.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        manager_col.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        departement_table.setItems(addDepartmentListD);
    }

//////////////////////////////////////////////////////////////////
    public void addStudentCourseAdd() throws SQLException {
        String insertData = "INSERT INTO student_course"
                + "(grades, student_id, course_id, date_g)"
                + "VALUES (?, ?, ?, ?)";

        Connection connect = Database.connect();
        try {
            Alert alert;
            if (grades_text.getText().isEmpty()
                    || studentid_text.getText().isEmpty()
                    || courseG_id_text.getText().isEmpty()
                    || date_text.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                // Check if the entry already exists for the given composite primary key
                String checkData = "SELECT * FROM student_course WHERE student_id = ? AND course_id = ? AND date_g = ?";
                prepare = connect.prepareStatement(checkData);
                prepare.setInt(1, Integer.parseInt(studentid_text.getText()));
                prepare.setInt(2, Integer.parseInt(courseG_id_text.getText()));
                prepare.setDate(3, Date.valueOf(date_text.getText()));
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student Course entry already exists for the given key!");
                    alert.showAndWait();
                } else {
                    // If entry doesn't exist, proceed with insertion
                    prepare = connect.prepareStatement(insertData);
                    prepare.setBigDecimal(1, new BigDecimal(grades_text.getText()));
                    prepare.setInt(2, Integer.parseInt(studentid_text.getText()));
                    prepare.setInt(3, Integer.parseInt(courseG_id_text.getText()));
                    prepare.setDate(4, Date.valueOf(date_text.getText()));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    addStudentCourseShowListData();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudentCourse() throws SQLException {
        String updateData = "UPDATE student_course SET "
                + "grades = " + grades_text.getText()
                + " WHERE student_id = " + studentid_text.getText()
                + " AND course_id = " + courseG_id_text.getText()
                + " AND date_g = TO_DATE('" + date_text.getText() + "', 'YYYY-MM-DD')";

        Connection connect = Database.connect();

        try {
            Alert alert;
            if (grades_text.getText().isEmpty()
                    || studentid_text.getText().isEmpty()
                    || courseG_id_text.getText().isEmpty()
                    || date_text.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Student Course with ID "
                        + studentid_text.getText() + ", Course ID " + courseG_id_text.getText() + ", and Date " + date_text.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    try (Statement statement = connect.createStatement()) {
                        int affectedRows = statement.executeUpdate(updateData);

                        if (affectedRows > 0) {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully Updated!");
                            alert.showAndWait();

                            addStudentCourseShowListData();
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("No matching record found for update.");
                            alert.showAndWait();
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources (if necessary) in a finally block
            if (connect != null) {
                connect.close();
            }
        }
    }

    public void addStudentCourseSelect() {
        StudentCourse studentCourse = gradestudent_table.getSelectionModel().getSelectedItem();

        if (studentCourse == null) {
            // No item selected, handle accordingly
            return;
        }

        int num = gradestudent_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        // Assuming these are your JavaFX components for displaying data
        studentid_text.setText(String.valueOf(studentCourse.getStudentId()));
        courseG_id_text.setText(String.valueOf(studentCourse.getCourseId()));
        grades_text.setText(String.valueOf(studentCourse.getGrades()));
        date_text.setText(studentCourse.getDateG().toString());  // Assuming dateGField is a TextField with date format
    }

    public void deleteStudentCourse() {
        String deleteData = "DELETE FROM student_course WHERE student_id = ? AND course_id = ? AND date_g = ?";

        try (Connection connection = Database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteData)) {

            if (studentid_text.getText().isEmpty() || courseG_id_text.getText().isEmpty() || date_text.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Error Message", "Please provide student_id, course_id, and date_g");
                return;
            }

            showAlert(AlertType.CONFIRMATION, "Confirmation Message",
                    "Are you sure you want to delete Student #" + studentid_text.getText()
                    + " from Course #" + courseG_id_text.getText()
                    + " with Date #" + date_text.getText() + "?",
                    () -> {
                        try {
                            preparedStatement.setString(1, studentid_text.getText());
                            preparedStatement.setString(2, courseG_id_text.getText());

                            // Parse the date string and convert it to SQL Date using SimpleDateFormat
                            String dateString = date_text.getText();
                            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                            Date sqlDate = new Date(utilDate.getTime());
                            preparedStatement.setDate(3, sqlDate);

                            int rowsAffected = preparedStatement.executeUpdate();

                            addStudentCourseShowListData();
                            if (rowsAffected > 0) {
                                showAlert(AlertType.INFORMATION, "Information Message", "Successfully Deleted!");
                                // Call a method to update the displayed data in your UI
                                // Example: updateCourseStudentListData();
                            } else {
                                showAlert(AlertType.ERROR, "Error Message",
                                        "No record found with Student ID: " + studentid_text.getText()
                                        + ", Course ID: " + courseG_id_text.getText()
                                        + ", and Date: " + date_text.getText());
                            }
                        } catch (SQLException | ParseException e) {
                            e.printStackTrace();
                            showAlert(AlertType.ERROR, "Error Message", "Error during deletion: " + e.getMessage());
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(AlertType alertType, String title, String content, Runnable onConfirm) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            onConfirm.run();
        }
    }

    public ObservableList<StudentCourse> getStudentCourseListData() {
        ObservableList<StudentCourse> listStudentCourse = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_course";
        try (Connection connect = Database.connect();
                PreparedStatement prepare = connect.prepareStatement(sql);
                ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                StudentCourse studentCourse = new StudentCourse(
                        result.getInt("student_id"),
                        result.getInt("course_id"),
                        result.getBigDecimal("grades"),
                        result.getDate("date_g")
                );
                listStudentCourse.add(studentCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately in your application
        }

        return listStudentCourse;
    }
    private ObservableList<StudentCourse> studentCourseList;

    public void addStudentCourseShowListData() {
        studentCourseList = getStudentCourseListData();

        STDID_COL.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        courseID_col.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        gradesstudent_col.setCellValueFactory(new PropertyValueFactory<>("grades"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("dateG"));

        gradestudent_table.setItems(studentCourseList);
    }

///////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addStudentsShowListData();
        addCoursesShowListData();
        addDepartmentShowListData();
        addStudentCourseShowListData();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
