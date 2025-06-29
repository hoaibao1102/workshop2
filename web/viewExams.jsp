<%-- 
    Document   : viewExams
    Created on : 29-06-2025, 16:23:02
    Author     : MSI PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Exams List</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                background: linear-gradient(135deg, #1d2b64, #f8cdda);
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 1000px;
                margin: 50px auto;
                background: white;
                padding: 30px 40px;
                border-radius: 16px;
                box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
            }

            .container h2 {
                margin: 0 0 25px 0;
                color: #333;
                font-size: 24px;
                font-weight: 600;
                text-align: center;
            }

            .back-link {
                display: inline-block;
                margin-bottom: 25px;
                text-decoration: none;
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 10px 20px;
                border-radius: 8px;
                font-size: 14px;
                transition: background 0.3s ease;
            }

            .back-link:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
            }

            table th, table td {
                padding: 14px 18px;
                border-bottom: 1px solid #eee;
                text-align: left;
            }

            table th {
                background-color: #007bff;
                color: white;
                font-weight: 500;
            }

            table tr:last-child td {
                border-bottom: none;
            }

            table td form {
                margin: 0;
            }

            button {
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 14px;
                transition: background 0.3s ease;
            }

            button:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }
        </style>
    </head>
    <body>

        <div class="container">
            <a href="MainController?action=dashboard" class="back-link">← Back to Dashboard</a>

            <h2>Available Exams</h2>

            <table>
                <tr>
                    <th>Exam Title</th>
                    <th>Subject</th>
                    <th>Total Marks</th>
                    <th>Duration</th>
                    <th>Action</th>
                </tr>

                <c:if test="${not empty examList}">
                    <c:forEach var="exam" items="${examList}">
                        <tr>                     
                            <td>${exam.examTitle}</td>
                            <td>${exam.subject}</td>                       
                            <td>${exam.totalMarks}</td>
                            <td>${exam.duration}</td>
                            <td>
                                <form action="MainController" method="post">
                                    <input type="hidden" name="action" value="${user.role eq 'Instructor' ? 'goToAddQuestion' : 'takeExam'}"/>
                                    <input type="hidden" name="examTitle" value="${exam.examTitle}"/>
                                    <input type="hidden" name="examId" value="${exam.examId}"/>
                                    <input type="hidden" name="cateId" value="${exam.categoryId}"/>
                                    <button type="submit">
                                        ${user.role eq 'Instructor' ? 'Add Question' : 'Take Exam'}
                                    </button>
                                </form>    
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>

    </body>
</html>
