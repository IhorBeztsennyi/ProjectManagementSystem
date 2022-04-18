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
              <div class="container">
                   <form action="/salaryByProject">
                        <div class="form-group">
                            <label for="projectNumber">Enter the project number:</label><br>
                            <input type="text" class="form-control" id="projectNumber" placeholder="Enter the project number" name="projectNumber"><br>
                         </div>
                         <input type="submit" value="Submit">
                   </form>

                   <h3>Project salary is <c:out value="${sum}"/> $</h3>
              </div>
         </div>
    </body>
</html>