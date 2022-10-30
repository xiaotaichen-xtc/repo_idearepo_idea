<%--
  Created by IntelliJ IDEA.
  User: miss
  Date: 2022/1/11
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--文件上传三要数 :
    1.表单提交方式必须为post
    2.表单 enctype 属性必须为：multipart/from-data 媒体类型
    3.表单中必须有文件上传项
--%>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uplaod2">
<input type="file" name="upload">
<br>
<input type="text" name="name" >
<input type="text" name="password" >
<input type="submit" value="文件上传">
</form>

<img src="/upload/c8349f27c92b4ce3b900127558810e8c_QQ图片20210603192814.jpg">

</body>
</html>
