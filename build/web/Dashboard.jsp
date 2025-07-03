<%-- 
    Document   : CreateProject
    Created on : Jun 21, 2025, 1:36:56 AM
    Author     : MSI PC
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background: linear-gradient(135deg, #1d2b64, #f8cdda);
            margin: 0;
            padding: 0;
        }

        .dashboard-container {
            max-width: 1000px;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .header h3 {
            margin: 0;
            color: #333;
        }

        .user-info a, .user-info button {
            background: linear-gradient(to right, #007bff, #0056b3);
            color: white;
            padding: 10px 18px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            margin-left: 10px;
            text-decoration: none;
            transition: background 0.3s;
        }

        .user-info a:hover, .user-info button:hover {
            background: linear-gradient(to right, #0056b3, #003c7c);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
            border-radius: 8px;
            overflow: hidden;
        }

        table th, table td {
            padding: 14px 20px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        table th {
            background-color: #007bff;
            color: white;
        }

        table td a {
            color: #007bff;
            text-decoration: none;
            font-weight: 500;
        }

        table td a:hover {
            text-decoration: underline;
        }

        .success-alert {
            background-color: #d4edda;
            color: #155724;
            padding: 15px;
            border-left: 6px solid #28a745;
            border-radius: 6px;
            font-size: 15px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <div class="dashboard-container">
        <c:if test="${not empty success}">
            <div class="success-alert">${success}</div>
        </c:if>

        <div class="header">
            <h3>Hello, ${user.name}</h3>
            <div class="user-info">
                <a href="MainController?action=logout">Logout</a>
                <c:if test="${user.role eq 'Instructor'}">
                    <a href="createExam.jsp"><button>Create New Exam</button></a>
                </c:if>
            </div>
        </div>

        <table>
            <tr>
                <th>Category Name</th>
                <th>Description</th>
            </tr>
            <c:if test="${not empty listExamCategory}">
                <c:forEach var="cate" items="${listExamCategory}">
                    <tr>
                        <td>
                            <a href="MainController?action=viewExamsByCategory&categoryId=${cate.categoryId}">
                                ${cate.categoryName}
                            </a>
                        </td>
                        <td>${cate.description}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

</body>
</html>

