package edu.uwp.cs.csci380.project.PA.simpleaccess_V1;
import java.sql.*;
public class MySqlSimpleQuery {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    /**
     * Constructor for MySqlSimpleQuery object
     * @param dbUrl String for the url of the database
     * @param dbUser String for the username accessing the database
     * @param dbPassword String for the user's password to the database
     */
    public MySqlSimpleQuery(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * This method creates a connection to the database and then executes an SQL query
     * @throws SQLException
     */
    public void executeQuery() throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            //Create a query
            String query = "SELECT * FROM CUSTOMER";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                System.out.println("Query result");
                System.out.println("------------");
                System.out.println("{");
                System.out.println("  [");
                while (resultSet.next()) {
                    // Print each row of the result set
                    System.out.println("    { CustomerID: " + resultSet.getInt("CustomerID") +
                                       ", LastName: '" + resultSet.getString("LastName") +
                                       "', FirstName: '" + resultSet.getString("FirstName") +
                                       "', EmailAddress: '" + resultSet.getString("EmailAddress") +
                                       "', Phone: '" + resultSet.getString("Phone") + "' },");
                }
                System.out.println("  ]");
                System.out.println("}");
            }
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
