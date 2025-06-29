<%-- 
    Document   : createExam
    Created on : 29-06-2025, 16:55:57
    Author     : MSI PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create Exam</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                background: linear-gradient(135deg, #1d2b64, #f8cdda);
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 600px;
                margin: 70px auto;
                background: white;
                padding: 40px 30px;
                border-radius: 12px;
                box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
            }

            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }

            label {
                display: block;
                margin-top: 15px;
                font-weight: 500;
                color: #444;
            }

            input[type="text"],
            input[type="number"],
            select {
                width: 100%;
                padding: 12px;
                margin-top: 6px;
                border: 1px solid #ccc;
                border-radius: 6px;
                box-sizing: border-box;
                font-size: 15px;
            }

            .error-message {
                color: #d8000c;
                background: #ffd2d2;
                padding: 8px 10px;
                border-left: 5px solid #d8000c;
                border-radius: 6px;
                font-size: 14px;
                margin-top: 5px;
            }

            input[type="submit"] {
                width: 100%;
                margin-top: 30px;
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 14px;
                border: none;
                font-size: 16px;
                border-radius: 6px;
                cursor: pointer;
                transition: background 0.3s ease;
            }

            input[type="submit"]:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }

            .general-error {
                text-align: center;
                color: #d8000c;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <button onclick="history.back()">← Back</button>
            <h2>Create New Exam</h2>

            <form action="MainController" method="post">
                <input type="hidden" name="action" value="createExam"/>

                <label for="title">Title:</label>
                <input type="text" name="title" value="${not empty errorExam ? errorExam.examTitle :'' }" required />
                <c:if test="${not empty errorTitle}">
                    <div class="error-message">${errorTitle}</div>
                </c:if>

                <label for="subject">Subject:</label>
                <input type="text" name="subject" value="${not empty errorExam ? errorExam.subject :'' }" required />
                <c:if test="${not empty errorSubject}">
                    <div class="error-message">${errorSubject}</div>
                </c:if>

                <label for="categoryId">Category:</label>
                <select name="categoryId">
                    <option value="">-- Select --</option>
                    <option value="100" ${errorExam.categoryId == '100' ? 'selected' : ''}>Quiz</option>
                    <option value="101" ${errorExam.categoryId == '101' ? 'selected' : ''}>Midterm</option>
                    <option value="102" ${errorExam.categoryId == '102' ? 'selected' : ''}>Final</option>
                </select>
                <c:if test="${not empty errorCategory}">
                    <div class="error-message">${errorCategory}</div>
                </c:if>

                <label for="totalMarks">Total Marks:</label>
                <input type="number" name="totalMarks" value="${not empty errorExam ? errorExam.totalMarks :'' }" required />
                <c:if test="${not empty errorTotalMarks}">
                    <div class="error-message">${errorTotalMarks}</div>
                </c:if>

                <label for="duration">Duration (minutes):</label>
                <input type="number" name="duration" value="${not empty errorExam ? errorExam.duration :'' }" required />
                <c:if test="${not empty errorDuration}">
                    <div class="error-message">${errorDuration}</div>
                </c:if>

                <input type="submit" value="Create Exam" />
            </form>

            <c:if test="${not empty error}">
                <div class="general-error">${error}</div>
            </c:if>
        </div>

    </body>
</html>
