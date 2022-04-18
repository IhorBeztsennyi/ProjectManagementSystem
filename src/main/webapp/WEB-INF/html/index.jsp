<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

         <div class="container">
               <h1 style="color: #2e6c80;">You can view: </h1>
                <p><a href="/salaryByProjectForm">Salary of all developers of a separate project</a></p>
                <p><a href="/developersListByProjectForm">Project developers list</a></p>
                <p><a href="/allJavaDevelopers">All java developers list</a></p>
                <p><a href="/allMiddleDevelopers">All middle developers list</a></p>
                <p><a href="/projectsList">Project list</a></p>
         </div>
    </body>
</html>
