<%-- 
    Document   : takeExam
    Created on : 29-06-2025, 23:29:50
    Author     : MSI PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Take Exam</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                background: linear-gradient(135deg, #1d2b64, #f8cdda);
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 800px;
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

            .question-block {
                margin-bottom: 30px;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background: #f9f9f9;
            }

            .question-block p {
                font-weight: 500;
                margin-bottom: 10px;
            }

            label {
                display: block;
                margin: 6px 0;
                font-size: 15px;
            }

            input[type="submit"] {
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 14px 24px;
                border: none;
                font-size: 16px;
                border-radius: 6px;
                cursor: pointer;
                width: 100%;
                margin-top: 30px;
            }

            input[type="submit"]:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>${examTitle}</h2>

            <form action="MainController" method="post">
                <input type="hidden" name="action" value="submitExam"/>
                <input type="hidden" name="examId" value="${examId}"/>

                <c:forEach var="q" items="${questionList}" varStatus="i">
                    <div class="question-block">
                        <p><strong>Question ${i.index + 1}:</strong> ${q.questionText}</p>
                        <label><input type="radio" name="answer${q.questionId}" value="A"/> A. ${q.optionA}</label>
                        <label><input type="radio" name="answer${q.questionId}" value="B"/> B. ${q.optionB}</label>
                        <label><input type="radio" name="answer${q.questionId}" value="C"/> C. ${q.optionC}</label>
                        <label><input type="radio" name="answer${q.questionId}" value="D"/> D. ${q.optionD}</label>
                    </div>
                </c:forEach>

                <input type="submit" value="Submit Exam"/>
            </form>
        </div>

    </body>
</html>

