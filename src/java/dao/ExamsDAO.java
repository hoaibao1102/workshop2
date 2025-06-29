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
import model.Exams;
import util.DBConnection;

/**
 *
 * @author MSI PC
 */
public class ExamsDAO {

    public List<Exams> getExamsByCategory(String categoryId) {
        String sql = "SELECT * FROM tblExams WHERE category_id = ?";
        List<Exams> exList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exams ex = new Exams(rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                exList.add(ex);
            }
            return exList;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExamsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean create(Exams ex) {
        String sql = "INSERT INTO tblExams (exam_title, subject, category_id, total_marks, duration) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ex.getExamTitle());
            ps.setString(2, ex.getSubject());
            ps.setInt(3, ex.getCategoryId());
            ps.setInt(4, ex.getTotalMarks());
            ps.setInt(5, ex.getDuration());

            int rowEffect = ps.executeUpdate();
            return rowEffect > 0 ;
        } catch (ClassNotFoundException ex1) {
            Logger.getLogger(ExamsDAO.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (SQLException ex1) {
            Logger.getLogger(ExamsDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }

        return false;
    }

}
