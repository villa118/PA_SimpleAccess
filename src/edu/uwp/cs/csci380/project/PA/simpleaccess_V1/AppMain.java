package edu.uwp.cs.csci380.project.PA.simpleaccess_V1;

/**
 * AppMain class. This class is the main driver for SimpleAccess_V1
 *
 * @author Tyler Villalobos
 * @bugs None
 */

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.*;

public class AppMain {
    public static void main(String[] args) {
        System.out.println("Building the session...done.");
        // Initialize SSH connection
        SshConnection sshConnection = new SshConnection("basil.cs.uwp.edu", "villalot", "1qaz@WSX3edc$RFV", 22, 4321, 3306);
        try {
            // Connect to SSH server
            sshConnection.connect();
            System.out.println("Establishing SSH Connection...done.");
            System.out.println("Assigned port: " + sshConnection.getLocalPort());
            System.out.println("Query from Mysql Database!");
            // Define MySQL database URL
            String dbUrl = "jdbc:mysql://localhost:4321/QACS_db?characterEncoding=utf8";
            System.out.println("Connection URL: " + dbUrl);
            // Initialize MySQL query executor
            MySqlSimpleQuery simpleQuery = new MySqlSimpleQuery(dbUrl, "villalot", "uZ=8tzTg");
            // Execute MySQL query
            simpleQuery.executeQuery();
        } catch (JSchException | SQLException e) {
        } finally {
            // Disconnect SSH session
            sshConnection.disconnect();
        }
    }
}