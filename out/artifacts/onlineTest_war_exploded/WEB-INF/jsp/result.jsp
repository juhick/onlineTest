<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/11/18
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>在线测试系统</title>
    <base href="<%=basePath%>">
    <link href="../../assets/pages/css/result.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main">
    <div class="content" id="single-list">
        <p>你的分数为：<%=request.getAttribute("score")%>分</p>
    </div>
</div>
</body>
</html>
