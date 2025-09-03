package org.example.servertime.week13.jdbc_querying_and_transaction.practice.p1.DAO;

import org.example.servertime.week13.jdbc_querying_and_transaction.practice.p1.model.User;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO implements IUserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo_test?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "dinhduy561";

    private static final String INSERT_USER_SQL = "INSERT INTO user (name, email, country) VALUES (?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "select id, name, email, country from user where id = ?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USER_SQL = "delete from user where id = ?";
    private static final String UPDATE_USER_SQL = "update user set name = ?, email = ?, country = ? where id = ?";
    private static final String SEARCH_USER_BY_COUNTRY = "SELECT * FROM user WHERE country LIKE ?";
    private static final String SORT_USERS_BY_NAME = "SELECT * FROM user ORDER BY name ASC";
    private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY=? WHERE NAME=?";
    private static final String SQL_TABLE_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID serial,"
            + " NAME varchar(100) NOT NULL,"
            + " SALARY numeric(15, 2) NOT NULL,"
            + " CREATED_DATE timestamp,"
            + " PRIMARY KEY (ID)"
            + ")";
    private static final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS EMPLOYEE";

    //Bài tập thực hành 1: Gọi MySql Stored Procedures từ JDBC
    private static final String GET_ALL_USERS_SP = "{CALL get_all_users()}";
    private static final String UPDATE_USER_SP = "{CALL update_user(?,?,?,?)}";
    private static final String DELETE_USER_SP = "{CALL delete_user(?)}";
    //

    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USER_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<User> searchUserByCountry(String country) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USER_BY_COUNTRY)) {
            preparedStatement.setString(1, "%" + country + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String ctry = rs.getString("country");
                users.add(new User(id, name, email, ctry));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public List<User> sortUsersByName() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SORT_USERS_BY_NAME)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String query = "{CALL get_user_by_id(?)}";

        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    @Override
    public void insertUserStore(User user) throws SQLException {
        String query = "{CALL insert_user(?,?,?)}";

        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getCountry());
            System.out.println(callableStatement);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void addUserTransaction(User user, List<Integer> permission) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtAssignment= null;

        ResultSet rs = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            pstmt = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getCountry());

            int rowAffected = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            int userId = 0;

            if (rs.next()) {
                userId = rs.getInt(1);
            }

            if (rowAffected == 1) {
                String sqlPivot = "INSERT INTO user_permision(user_id,permision_id) " + "VALUES(?,?)";
                pstmtAssignment = connection.prepareStatement(sqlPivot);
                for (int permissionId : permission) {
                    pstmtAssignment.setInt(1, userId);
                    pstmtAssignment.setInt(2, permissionId);
                    pstmtAssignment.executeUpdate();
                }
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (pstmtAssignment != null) pstmtAssignment.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void insertUpdateWithoutTransaction() {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
             PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {
            statement.execute(SQL_TABLE_DROP);
            statement.execute(SQL_TABLE_CREATE);

            psInsert.setString(1, "Quynh");
            psInsert.setBigDecimal(2, new BigDecimal(10));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psInsert.setString(1, "Ngan");
            psInsert.setBigDecimal(2, new BigDecimal(20));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psUpdate.setBigDecimal(2, new BigDecimal(999.99));
            //psUpdate.setBigDecimal(1, new BigDecimal(999.99));
            psUpdate.setString(2, "Quynh");
            psUpdate.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUpdateWithTransaction() {
        try (
                Connection conn = getConnection();
                Statement statement = conn.createStatement();
                PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
                PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE);
            )
        {
            statement.execute(SQL_TABLE_DROP);
            statement.execute(SQL_TABLE_CREATE);

            conn.setAutoCommit(false);

            psInsert.setString(1, "Quynh");
            psInsert.setBigDecimal(2, new BigDecimal(10));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psInsert.setString(1, "Ngan");
            psInsert.setBigDecimal(2, new BigDecimal(20));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            //psUpdate.setBigDecimal(2, new BigDecimal(999.99));
            psUpdate.setBigDecimal(1, new BigDecimal(999.99));

            psUpdate.setString(2, "Quynh");
            psUpdate.execute();

            conn.commit();
            conn.setAutoCommit(true);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //Bài tập thực hành 1: Gọi MySql Stored Procedures từ JDBC
    @Override
    public List<User> getAllUsersSP() {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = getConnection();
                CallableStatement cs = connection.prepareCall(GET_ALL_USERS_SP);
            )
        {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUserSP(User user) throws SQLException {
        boolean rowUpdated;
        try (
                Connection connection = getConnection();
                CallableStatement cs = connection.prepareCall(UPDATE_USER_SP);
            )
        {
            cs.setInt(1, user.getId());
            cs.setString(2, user.getUsername());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getCountry());
            rowUpdated = cs.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteUserSP(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(DELETE_USER_SP)) {
            cs.setInt(1, id);
            rowDeleted = cs.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    //Bài tập thực hành 2:
    @Override
    public void addUserTransactionExercise(User user, List<Integer> permission) throws SQLException {
        Connection conn = null;
        PreparedStatement psUser = null;
        PreparedStatement psPermission = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); 

            String insertUserSQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";
            psUser = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, user.getUsername());
            psUser.setString(2, user.getEmail());
            psUser.setString(3, user.getCountry());
            psUser.executeUpdate();

            // Lấy id vừa insert
            ResultSet rs = psUser.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            String insertPermSQL = "INSERT INTO user_perm(user_id, perm_id) VALUES (?, ?)";
            psPermission = conn.prepareStatement(insertPermSQL);
            psPermission.setInt(1, userId);
            psPermission.setInt(2, 1); 
            psPermission.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); 
            }
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true); // Bật lại auto-commit
            if (psUser != null) psUser.close();
            if (psPermission != null) psPermission.close();
            if (conn != null) conn.close();
        }
    }
}
