/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.DBConnection;

/**
 *
 * @author MSI PC
 */
public class UserDAO {

    public User checkLogin(String userName, String password) {
        String sql = "SELECT * FROM tblUsers WHERE Username = ? AND Password = ? ";
        User user = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return user = new User(rs.getString("username"),
                        rs.getString("Name"),
                        rs.getString("password"),
                        rs.getString("Role"));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
