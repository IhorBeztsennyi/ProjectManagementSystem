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
            <form action="/updateCustomer" method="post">
                <div class="form-group">
                    <label for="customer_id">Customer ID you want to update:</label><br>
                    <input type="text" class="form-control" id="customer_id" placeholder="ID" name="customer_id"><br>
                </div>
                <div class="form-group">
                    <label for="first_name">First Name:</label><br>
                    <input type="text" class="form-control" id="first_name" placeholder="Enter first name" name="first_name"><br>
                </div>
                <div class="form-group">
                     <label for="last_name">Last Name:</label><br>
                     <input type="text" class="form-control" id="last_name" placeholder="Enter last name" name="last_name"><br>
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
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>