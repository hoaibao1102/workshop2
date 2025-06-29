/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ExamCategory;
import util.DBConnection;

/**
 *
 * @author MSI PC
 */
public class ExamCategoryDAO {

    public List<ExamCategory> readAll() {
        List<ExamCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM tblExamCategories ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExamCategory ex = new ExamCategory(rs.getInt("category_id"),
                                                    rs.getString("category_name"),
                                                    rs.getString("description"));
                
                list.add(ex);
            }
            return list;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExamCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

}
