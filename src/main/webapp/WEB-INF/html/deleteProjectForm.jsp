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

         <div>
              <div class="container">
                   <form action="/deleteProject" method="post">
                        <div class="form-group">
                            <label for="project_id">Enter the project ID you want to remove:</label><br>
                            <input type="text" class="form-control" id="project_id" placeholder="Enter ID" name="project_id"><br>
                         </div>
                         <input type="submit" value="Submit">
                   </form>
              </div>
         </div>
  </body>
</html>