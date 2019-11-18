<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qhq13
  Date: 2019/11/14
  Time: 20:43
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
    <link type="text/css" href="../assets/pages/css/test.css" rel="stylesheet">
    <script src="../assets/pages/js/test.js"></script>
    <base href="<%=basePath%>">
</head>
<body>
<form onsubmit="return checkFull()" action="Result" method="post">
    <div class="main">
        <div class="content">
            <div class="container">
                <h4>一、填空题</h4>
                <ol id="inputQ">
                    <jsp:useBean id="input" scope="application" type="java.util.List"/>
                    <c:forEach items="${input}" var="input">
                        <li>
                            <span>${input.lContent}</span>
                                <input class="iq" type="text" name="i${input.qId}">
                            <span>${input.rContent}</span>。
                        </li>
                    </c:forEach>
                </ol>
            </div>
            <div class="container">
                <h4>二、单选题</h4>
                <ol id="singleChoice">
                    <jsp:useBean id="singleChoices" scope="application" type="java.util.List"/>
                    <c:forEach items="${singleChoices}" var="singleChoice">
                        <li>
                            <p>${singleChoice.qName}</p>
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sc${singleChoice.qId}" value="A">A.${singleChoice.choiceA}
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sc${singleChoice.qId}" value="B">B.${singleChoice.choiceB}
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sc${singleChoice.qId}" value="C">C.${singleChoice.choiceC}
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sc${singleChoice.qId}" value="D">D.${singleChoice.choiceD}
                        </li>
                    </c:forEach>
                </ol>
            </div>
            <div class="container">
                <h4>三、多选题</h4>
                <ol id="multiChoice">
                    <jsp:useBean id="multiChoices" scope="application" type="java.util.List"/>
                    <c:forEach items="${multiChoices}" var="multiChoice">
                        <li>
                            <p>${multiChoice.qName}</p>
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="mc${multiChoice.qId}" value="A">A.${multiChoice.choiceA}
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="mc${multiChoice.qId}" value="B">B.${multiChoice.choiceB}
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="mc${multiChoice.qId}" value="C">C.${multiChoice.choiceC}
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="mc${multiChoice.qId}" value="D">D.${multiChoice.choiceD}
                        </li>
                    </c:forEach>
                </ol>
            </div>
            <div class="container">
                <h4>四、判断题</h4>
                <ol id="checkQ">
                    <jsp:useBean id="check" scope="application" type="java.util.List"/>
                    <c:forEach items="${check}" var="check">
                        <li>
                            <span>${check.qName}</span>
                            <input type="radio" name="ck${check.qId}" value="T">T
                            <input type="radio" name="ck${check.qId}" value="F">F
                        </li>
                    </c:forEach>
                </ol>
            </div>
            <div class="bt">
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </div>
        </div>
    </div>
</form>
</body>
</html>
