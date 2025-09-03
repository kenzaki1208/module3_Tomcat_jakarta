package org.example.servertime.week13.jdbc_querying_and_transaction.practice.p1.DAO;

import org.example.servertime.week13.jdbc_querying_and_transaction.practice.p1.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    void insertUser(User user) throws SQLException;
    User selectUser(int id);
    List <User> selectAllUsers();
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(int id) throws SQLException;
    User getUserById(int id);
    void insertUserStore(User user) throws SQLException;
    void addUserTransaction(User user, List<Integer> permission);
    void insertUpdateWithoutTransaction_p1();
    void insertUpdateWithoutTransaction_p2();

    //Bài thực hành 1: Gọi MySql Stored Procedures từ JDBC
    List<User> getAllUsersSP();
    boolean updateUserSP(User user) throws SQLException;
    boolean deleteUserSP(int id) throws SQLException;
}
