<%-- 
    Document   : result
    Created on : 30-06-2025, 00:29:19
    Author     : MSI PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Exam Result</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, sans-serif;
                background: linear-gradient(135deg, #1d2b64, #f8cdda);
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 600px;
                margin: 80px auto;
                background: white;
                padding: 40px;
                border-radius: 12px;
                box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
                text-align: center;
            }

            h2 {
                color: #333;
                margin-bottom: 30px;
            }

            p {
                font-size: 18px;
                color: #444;
                margin: 10px 0;
            }

            strong {
                font-size: 20px;
                color: #007bff;
            }

            button {
                background: linear-gradient(to right, #007bff, #0056b3);
                color: white;
                padding: 12px 24px;
                border: none;
                border-radius: 6px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 30px;
                transition: background 0.3s ease;
            }

            button:hover {
                background: linear-gradient(to right, #0056b3, #003c7c);
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>Exam Result</h2>

            <p>Total Questions: ${total}</p>
            <p>Correct Answers: ${correct}</p>
            <p><strong>Your Score: ${score}</strong></p>

            <a href="MainController?action=dashboard" class="back-link">← Back to Dashboard</a>

        </div>

    </body>
</html>


