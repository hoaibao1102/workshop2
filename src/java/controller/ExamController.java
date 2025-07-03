/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ExamCategoryDAO;
import dao.ExamsDAO;
import dao.QuestionDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ExamCategory;
import model.Exams;
import model.Question;
import util.AuthUtils;

/**
 *
 * @author MSI PC
 */
@WebServlet(name = "ExamController", urlPatterns = {"/ExamController"})
public class ExamController extends HttpServlet {

    private static final String DASHBOARD_PAGE = "Dashboard.jsp";
    private static final String EXAMS_PAGE = "viewExams.jsp";
    private static final String CREATE_EXAM_PAGE = "createExam.jsp";
    private static final String ADD_EXAM_PAGE = "addQuestion.jsp";
    private static final String TAKE_EXAM_PAGE = "takeExam.jsp";
    private static final String RESULT = "result.jsp";

    private final ExamsDAO eDAO = new ExamsDAO();
    private final QuestionDAO qDAO = new QuestionDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DASHBOARD_PAGE;
        String action = request.getParameter("action");
        ExamsDAO eDAO = new ExamsDAO();
        QuestionDAO qDAO = new QuestionDAO();

        System.out.println(action);
        try {
            if ("viewExamsByCategory".equals(action)) {
                url = handleViewExamsByCategory(request, response);

            } else if ("createExam".equals(action)) {
                url = handleCreateExam(request, response);
            } else if ("goToAddQuestion".equals(action)) {
                url = handleGoToAddQuestion(request, response);
            } else if (action.equals("addQuestion")) {
                url = handleAddQuestion(request, response);
            } else if ("takeExam".equals(action)) {
                url = handleTakeExam(request, response);
            } else if ("submitExam".equals(action)) {
                url = handleSubmitExam(request, response);
            } else if ("filterSubject".equals(action)) {
                url = handleFilterSubject(request, response);
            }

        } catch (Exception e) {
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String handleViewExamsByCategory(HttpServletRequest request, HttpServletResponse response) {
        String url = DASHBOARD_PAGE;
        String categoryId = request.getParameter("categoryId");
        List<Exams> exams = eDAO.getExamsByCategory(categoryId);
        request.setAttribute("examList", exams);
        List<String> subjectFilter = eDAO.getSubject(categoryId);
        request.setAttribute("subjectFilter", subjectFilter);
        request.setAttribute("categoryId", categoryId);
        url = EXAMS_PAGE;
        return url;
    }

    private String handleCreateExam(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        if (AuthUtils.isInstructor(request)) {
            String title = request.getParameter("title");
            String subject = request.getParameter("subject");
            String categoryIdStr = request.getParameter("categoryId");
            String totalMarksStr = request.getParameter("totalMarks");
            String durationStr = request.getParameter("duration");
            boolean check = true;

            // Kiểm tra dữ liệu
            if (title == null || title.trim().isEmpty() || title.length() > 25 || title.length() < 5) {
                request.setAttribute("errorTitle", "Title must be between 5 and 25 characters.");
                check = false;
            }

            if (subject == null || subject.trim().isEmpty() || !subject.matches("[a-zA-Z]+")) {
                request.setAttribute("errorSubject", "Subject must contain only letters and cannot be empty.");
                check = false;
            }

            if (categoryIdStr == null || categoryIdStr.isEmpty()) {
                request.setAttribute("errorCategory", "Please select a category.");
                check = false;
            }

            if (totalMarksStr == null || !totalMarksStr.matches("\\d+") || Integer.parseInt(totalMarksStr) <= 0) {
                request.setAttribute("errorTotalMarks", "Total Marks must be a positive number.");
                check = false;
            }

            if (durationStr == null || !durationStr.matches("\\d+") || Integer.parseInt(durationStr) < 10) {
                request.setAttribute("errorDuration", "Duration must be a number and at least 10 minutes.");
                check = false;
            }

            int categoryId = Integer.parseInt(categoryIdStr);
            int totalMarks = Integer.parseInt(totalMarksStr);
            int duration = Integer.parseInt(durationStr);

            Exams ex = new Exams(0, title, subject, categoryId, totalMarks, duration);
            System.out.println(check);
            if (check) {
                boolean succ = eDAO.create(ex);

                if (succ) {
                    ExamCategoryDAO ecDAO = new ExamCategoryDAO();
                    List<ExamCategory> ec = ecDAO.readAll();
                    request.setAttribute("listExamCategory", ec);
                    request.setAttribute("success", "Exam created successfully!");
                    url = DASHBOARD_PAGE;
                }
            } else {
                request.setAttribute("errorExam", ex);
                request.setAttribute("error", "Failed to create exam.");
                url = CREATE_EXAM_PAGE;
            }
        }
        return url;
    }

    private String handleGoToAddQuestion(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        if (AuthUtils.isInstructor(request)) {
            String examTitle = request.getParameter("examTitle");
            String examId = request.getParameter("examId");
            String cateId = request.getParameter("cateId");
            request.setAttribute("examTitle", examTitle);
            request.setAttribute("examId", examId);
            request.setAttribute("cateId", cateId);

            url = ADD_EXAM_PAGE;

        }
        return url;
    }

    private String handleAddQuestion(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        if (AuthUtils.isInstructor(request)) {
            String cateId = request.getParameter("categoryId");
            String examIdStr = request.getParameter("examId");
            String questionText = request.getParameter("questionText");
            String a = request.getParameter("optionA");
            String b = request.getParameter("optionB");
            String c = request.getParameter("optionC");
            String d = request.getParameter("optionD");
            String correct = request.getParameter("correctOption");
            String examTitle = request.getParameter("examTitle");

            boolean valid = true;

            // Kiểm tra câu hỏi
            if (questionText.isEmpty()) {
                request.setAttribute("errorQuestion", "Question cannot be empty.");
                valid = false;
            } else if (questionText.length() < 5) {
                request.setAttribute("errorQuestion", "Question must be at least 5 characters.");
                valid = false;
            }

            // Kiểm tra từng option
            if (a.isEmpty()) {
                request.setAttribute("errorA", "Option A cannot be empty.");
                valid = false;
            }
            if (b.isEmpty()) {
                request.setAttribute("errorB", "Option B cannot be empty.");
                valid = false;
            }
            if (c.isEmpty()) {
                request.setAttribute("errorC", "Option C cannot be empty.");
                valid = false;
            }
            if (d.isEmpty()) {
                request.setAttribute("errorD", "Option D cannot be empty.");
                valid = false;
            }

            if (correct == null || !correct.matches("[ABCD]")) {
                request.setAttribute("errorCorrect", "Please select a correct option (A–D).");
                valid = false;
            }

            if (valid) {
                int examId = Integer.parseInt(examIdStr);
                Question q = new Question(0, examId, questionText, a, b, c, d, correct.charAt(0));
                boolean success = qDAO.addQuestion(q);

                if (success) {
                    request.setAttribute("addSuccess", "Question added successfully!");
                    // Reset fields
                    request.setAttribute("questionText", "");
                    request.setAttribute("optionA", "");
                    request.setAttribute("optionB", "");
                    request.setAttribute("optionC", "");
                    request.setAttribute("optionD", "");
                    request.setAttribute("cateId", cateId);
                    request.setAttribute("examTitle", examTitle);
                } else {
                    request.setAttribute("errorQuestion", "Database error!");
                }
            } else {
                // Gửi lại dữ liệu để giữ lại form nếu lỗi
                request.setAttribute("examId", examIdStr);
                request.setAttribute("questionText", questionText);
                request.setAttribute("optionA", a);
                request.setAttribute("optionB", b);
                request.setAttribute("optionC", c);
                request.setAttribute("optionD", d);
                request.setAttribute("correct", correct);
                request.setAttribute("cateId", cateId);
                request.setAttribute("examTitle", examTitle);
            }
            url = ADD_EXAM_PAGE;
        }
        return url;
    }

    private String handleTakeExam(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        String examTitle = request.getParameter("examTitle");
        String examId = request.getParameter("examId");
        String cateId = request.getParameter("cateId");
        List<Question> questionList = qDAO.getQuestionsByExamId(examId);

        request.setAttribute("examTitle", examTitle);
        request.setAttribute("examId", examId);
        request.setAttribute("cateId", cateId);
        request.setAttribute("questionList", questionList);
        url = TAKE_EXAM_PAGE;
        return url;
    }

    private String handleSubmitExam(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        int correctNum = 0;
        String examId = request.getParameter("examId");
        List<Question> questionList = qDAO.getQuestionsByExamId(examId);
        int total = questionList.size();
        for (Question q : questionList) {
            String answer = request.getParameter("answer" + q.getQuestionId());
            if (answer != null && answer.equalsIgnoreCase(String.valueOf(q.getCorrectOption()))) {
                correctNum++;
            }
        }
        double score = ((double) correctNum / total) * 100;
        request.setAttribute("total", total);
        request.setAttribute("correctNum", correctNum);
        request.setAttribute("score", score);
        url = RESULT;
        return url;
    }

    private String handleFilterSubject(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        String subject = request.getParameter("subject");
        String categoryId = request.getParameter("categoryId");
        List<Exams> examsbySubject = eDAO.getExamsBySubject(subject, categoryId);
        List<String> subjectFilter = eDAO.getSubject(categoryId);
        
        request.setAttribute("subjectFilter", subjectFilter);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("examListSubject", examsbySubject);
        url = EXAMS_PAGE;
        return url;
    }
}
