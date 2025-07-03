<%-- 
    Document   : addQuestion
    Created on : 29-06-2025, 22:08:59
    Author     : MSI PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Question</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                background: linear-gradient(135deg, #1d2b64, #f8cdda);
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 700px;
                margin: 60px auto;
                background: white;
                padding: 40px;
                border-radius: 12px;
                box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
            }

            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
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

            label {
                font-weight: 500;
                display: block;
                margin: 15px 0 5px;
                color: #444;
            }

            input[type="text"],
            textarea,
            select {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 15px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }

            textarea {
                resize: vertical;
            }

            input[type="submit"] {
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 12px 20px;
                border: none;
                font-size: 16px;
                border-radius: 6px;
                cursor: pointer;
                margin-top: 20px;
                width: 100%;
            }

            input[type="submit"]:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }

            .error-message {
                color: #d8000c;
                background: #ffd2d2;
                padding: 10px;
                border-left: 6px solid #d8000c;
                border-radius: 6px;
                margin-bottom: 10px;
                font-size: 14px;
            }
        </style>
    </head>
    <body>

        <c:if test="${not empty addSuccess}">
            <script>alert("${addSuccess}")</script>
        </c:if>

        <div class="container">
            <a class="back-link" href="MainController?action=viewExamsByCategory&categoryId=${cateId}">← Back</a>

            <h2>Add Question to Exam: ${examTitle}</h2>

            <form action="MainController" method="post">
                <input type="hidden" name="action" value="addQuestion"/>
                <input type="hidden" name="categoryId" value="${cateId}"/>
                <input type="hidden" name="examId" value="${examId}"/>
                <input type="hidden" name="examTitle" value="${examTitle}"/>

                <label>Question:</label>
                <textarea name="questionText" rows="3" required>${questionText}</textarea>
                <c:if test="${not empty errorQuestion}">
                    <div class="error-message">${errorQuestion}</div>
                </c:if>

                <label>Option A:</label>
                <input type="text" name="optionA" value="${optionA}" required/>
                <c:if test="${not empty errorA}">
                    <div class="error-message">${errorA}</div>
                </c:if>

                <label>Option B:</label>
                <input type="text" name="optionB" value="${optionB}" required/>
                <c:if test="${not empty errorB}">
                    <div class="error-message">${errorB}</div>
                </c:if>

                <label>Option C:</label>
                <input type="text" name="optionC" value="${optionC}" required/>
                <c:if test="${not empty errorC}">
                    <div class="error-message">${errorC}</div>
                </c:if>

                <label>Option D:</label>
                <input type="text" name="optionD" value="${optionD}" required/>
                <c:if test="${not empty errorD}">
                    <div class="error-message">${errorD}</div>
                </c:if>

                <label>Correct Option:</label>
                <select name="correctOption">
                    <option value="">-- Select --</option>
                    <option value="A" ${correct == 'A' ? 'selected' : ''}>A</option>
                    <option value="B" ${correct == 'B' ? 'selected' : ''}>B</option>
                    <option value="C" ${correct == 'C' ? 'selected' : ''}>C</option>
                    <option value="D" ${correct == 'D' ? 'selected' : ''}>D</option>
                </select>
                <c:if test="${not empty errorCorrect}">
                    <div class="error-message">${errorCorrect}</div>
                </c:if>

                <input type="submit" value="Add Question"/>
            </form>
        </div>

    </body>
</html>
