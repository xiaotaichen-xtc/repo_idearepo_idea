<%--
  Created by IntelliJ IDEA.
  User: miss
  Date: 2022/1/8
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试超链接</title>
</head>
<body>

    <a href="${pageContext.request.contextPath}/test?methodName=addCourse">新建课程</a><br>

    <a href="${pageContext.request.contextPath}/test?methodName=findByName">根据课程名进行查询</a><br>

    <a href="${pageContext.request.contextPath}/test?methodName=findByStatus">根据课程状态查询课程</a><br>

    <a href="${pageContext.request.contextPath}/test2?methodName=show">TestServlet2 show方法执行了</a><br>
</body>
</html>
