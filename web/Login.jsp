<%-- 
    Document   : Login
    Created on : Jun 21, 2025, 12:53:17 AM
    Author     : MSI PC
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Login</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                background: linear-gradient(135deg, #1d2b64, #f8cdda);
                margin: 0;
                padding: 0;
            }

            .login-container {
                max-width: 420px;
                margin: 80px auto;
                background: white;
                padding: 40px 30px;
                border-radius: 12px;
                box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
            }

            .login-container h2 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
                font-weight: bold;
            }

            label {
                font-weight: 500;
                display: block;
                margin-bottom: 6px;
                color: #444;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 14px;
                margin-bottom: 18px;
                border: 1px solid #ccc;
                border-radius: 6px;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus,
            input[type="password"]:focus {
                border-color: #007bff;
                outline: none;
            }

            input[type="submit"] {
                width: 100%;
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 12px;
                font-size: 16px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background 0.3s;
            }

            input[type="submit"]:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }

            .error-message {
                color: #ff4d4f;
                background: #ffeaea;
                padding: 10px;
                border-radius: 5px;
                margin-top: 15px;
                text-align: center;
                font-size: 14px;
            }
            .error-message::before {
                content: "⚠ ";
                font-weight: bold;
                margin-right: 5px;
            }
        </style>
    </head>
    <body>

        <div class="login-container">
            <h2>Login</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login" />

                <label for="userName">Username:</label>
                <input type="text" id="userName" name="userName" required placeholder="Enter your username" />

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Enter your password" />

                <input type="submit" value="Login" />
            </form>

            <c:if test="${not empty errorNoti or not empty errorMessage or not empty sessionScope.errorLogin}">
                <div class="error-message">
                    <c:if test="${not empty errorNoti}">
                        ${errorNoti}
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        ${errorMessage}
                    </c:if>
                    <c:if test="${not empty sessionScope.errorLogin}">
                        ${sessionScope.errorLogin}
                        <c:remove var="errorLogin" scope="session" />
                    </c:if>
                </div>
            </c:if>
        </div>

    </body>
</html>
