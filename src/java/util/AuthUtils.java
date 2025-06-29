/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author MSI PC
 */

public class AuthUtils {

    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            return (User) session.getAttribute("user");
        } else {
            return null;
        }
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getCurrentUser(request) != null;
    }

    public static boolean hasRole(HttpServletRequest request, String role) {
        User user = getCurrentUser(request);
        if (user != null) {
            return user.getRole().equals(role);
        } else {
            return false;
        }
    }

    public static boolean isStudent(HttpServletRequest request) {
        return hasRole(request, "Student");
    }

    public static boolean isInstructor(HttpServletRequest request) {
        return hasRole(request, "Instructor");
    }

    public static String getLoginURL() {
        return "MainController";
    }

    public static String getAccessDeniedMessage(String action) {
        return "You can not access to " + action + ". Please contact administrator.";
    }
}
