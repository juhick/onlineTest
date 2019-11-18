<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/11/11
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
  <head>
      <title>在线智力测试系统</title>
      <link href="assets/pages/css/index.css" rel="stylesheet" type="text/css">
      <base href="<%=basePath%>">
  </head>
  <body>
    <div class="main">
        <div class="content" id="single-list">
            <a href="onlineTest">开始测试</a>
        </div>
    </div>
  </body>
</html>
