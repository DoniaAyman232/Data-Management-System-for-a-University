/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Dell
 */
public class Database {
    
    public static Connection connect() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "project", "project");
    }
    
}
