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
import model.Question;
import util.DBConnection;

/**
 *
 * @author MSI PC
 */
public class QuestionDAO {

    public boolean addQuestion(Question q) {
        String sql = "INSERT INTO tblQuestions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, q.getExamId());
            ps.setString(2, q.getQuestionText());
            ps.setString(3, q.getOptionA());
            ps.setString(4, q.getOptionB());
            ps.setString(5, q.getOptionC());
            ps.setString(6, q.getOptionD());
            ps.setString(7, String.valueOf(q.getCorrectOption()));

            int rowEffect = ps.executeUpdate();
            return rowEffect > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public List<Question> getQuestionsByExamId(String examId) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM tblQuestions WHERE exam_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, examId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("question_id"),
                        rs.getInt("exam_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option").charAt(0)
                );
                list.add(q);
            }
            return list;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
