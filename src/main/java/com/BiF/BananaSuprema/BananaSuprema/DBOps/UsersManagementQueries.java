package com.BiF.BananaSuprema.BananaSuprema.DBOps;

import com.BiF.BananaSuprema.BananaSuprema.Service.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.ResultSet;

public class UsersManagementQueries extends DBBaseOps{
    public static final String CREATE_USER = "INSERT INTO Users (username, email, password) VALUES (?,?,?)";
    public static final String GET_USER_USERNAME = "SELECT id, email, privilege, password FROM Users WHERE username = ? AND active = 1";
    public static final String GET_USER_EMAIL = "SELECT id, username, privilege, password FROM Users WHERE email = ? AND active = 1";
    public static final String UPDATE_PRIVILEGE = "UPDATE Users SET privilege = ? WHERE id = ?";
    public static final String DEACTIVATE_USER = "UPDATE Users SET active = 0 WHERE id = ?";

    public void createUser(String username, String email, String password) throws Exception{
        openDb();
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        executeQuery(CREATE_USER, new Object[] {username, email, hash});
        closeDb();
        System.out.println("New user created: " + username + "\t" + email);
    }

    public User getUserUsername(String username, String password) throws Exception{
        User user = null;
        openDb();
        ResultSet rs = (ResultSet) executeQuery(GET_USER_USERNAME, new Object[] {username});
        if (!rs.next()) {
            System.out.println("No data");
        } else {
            String hash = rs.getString("password");
            if(BCrypt.checkpw(password, hash)) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                int privilege = rs.getInt("privilege");
                user = new User(id, username, email, privilege);
            }  else {
                System.out.println("Wrong password");
            }
        }
        closeDb();
        return user;
    }

    public User getUserEmail(String email, String password) throws Exception{
        User user = null;
        openDb();
        ResultSet rs = (ResultSet) executeQuery(GET_USER_EMAIL, new Object[] {email});
        if (!rs.next()) {
            System.out.println("No data");
        } else {
            String hash = rs.getString("password");
            if(BCrypt.checkpw(password, hash)) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int privilege = rs.getInt("privilege");
                user = new User(id, username, email, privilege);
            } else {
                System.out.println("Wrong password");
            }
        }
        closeDb();
        return user;
    }

    public void updateUserPrivilege(int userId, int newPrivilege) throws Exception{
        openDb();
        executeQuery(UPDATE_PRIVILEGE, new Object[] {newPrivilege, userId});
        closeDb();
    }

    public void deactivateUser(int userId) throws Exception{
        openDb();
        executeQuery(DEACTIVATE_USER, new Object[] {userId});
        closeDb();
    }

    //Fast testing
    public static void main(String[] args) {
        UsersManagementQueries umq = new UsersManagementQueries();
        User user;
        //Try create user
        /*try {
            umq.createUser("AAA", "a@a.a", "gelato");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //Try update privilege
        try {
            umq.updateUserPrivilege(2, 9999);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Try retrieve user
        try {
            user = umq.getUserUsername("AAA", "gelato");
            if (user != null) {
                System.out.println(user.getId() + "; " + user.getUsername() + "; " + user.getEmail() + "; " + user.getPrivilege());
            } else {
                System.out.println("↑ Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
