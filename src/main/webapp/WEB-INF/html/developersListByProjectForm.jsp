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
                   <form action="/developersListByProject">
                        <div class="form-group">
                            <label for="projectNumber">Enter the project number:</label><br>
                            <input type="text" class="form-control" id="projectNumber" placeholder="Enter the project number" name="projectNumber"><br>
                         </div>
                         <input type="submit" value="Submit">
                   </form>
              </div>
         </div>
  </body>
</html>