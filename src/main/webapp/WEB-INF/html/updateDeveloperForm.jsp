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
            <form action="/updateDeveloper" method="post">
                <div class="form-group">
                    <label for="id">Developers ID you want to update:</label><br>
                    <input type="text" class="form-control" id="id" placeholder="ID" name="id"><br>
                </div>
                <div class="form-group">
                    <label for="firstName">First Name:</label><br>
                    <input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName"><br>
                </div>
                <div class="form-group">
                     <label for="lastName">Last Name:</label><br>
                     <input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName"><br>
                 </div>
                 <div class="form-group">
                     <label for="age">Age:</label><br>
                     <input type="text" class="form-control" id="age" placeholder="22" name="age"><br>
                 </div>
                 <div class="form-group">
                     <label for="gender">Gender:</label><br>
                     <input type="text" class="form-control" id="gender" placeholder="male/female" name="gender"><br>
                 </div>
                 <div class="form-group">
                     <label for="email">Email:</label><br>
                     <input type="text" class="form-control" id="email" placeholder="email@mail.com" name="email"><br>
                 </div>
                 <div class="form-group">
                      <label for="phone">Phone:</label><br>
                      <input type="text" class="form-control" id="phone" placeholder="012 123-45-67" name="phone"><br>
                 </div>
                 <div class="form-group">
                      <label for="salary">Salary:</label><br>
                      <input type="text" class="form-control" id="salary" placeholder="1000.00" name="salary"><br>
                 </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>