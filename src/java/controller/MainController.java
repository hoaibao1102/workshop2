/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ExamCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.ExamCategory;

/**
 *
 * @author MSI PC
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "Login.jsp";
    private static final String USER_CONTROLLER = "UserController";
    private static final String EXAM_CONTROLLER = "ExamController";

    private boolean isUserAction(String action) {
        return "login".equals(action)
                || "logout".equals(action);
    }

    private boolean isExamAction(String action) {
        return "viewExamsByCategory".equals(action)
                || "createExam".equals(action)
                || "goToAddQuestion".equals(action)
                || "addQuestion".equals(action)
                || "takeExam".equals(action)
                || "submitExam".equals(action)
                || "filterSubject".equals(action);
    }

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
        String action = request.getParameter("action");
        String url = LOGIN_PAGE; // Default

        try {

            if (action == null) {
                url = LOGIN_PAGE;
            } else if (isExamAction(action)) {
                url = EXAM_CONTROLLER;
            }else if (isUserAction(action)) {
                url = USER_CONTROLLER;
            }else if (action.equals("dashboard")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    url = "Dashboard.jsp";
                    List<ExamCategory> ec = new ExamCategoryDAO().readAll();
                    request.setAttribute("listExamCategory", ec);
                    url = "Dashboard.jsp";
                }
            }

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

}
