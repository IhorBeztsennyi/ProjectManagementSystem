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
            <form action="/saveNewProject" method="post">
                <div class="form-group">
                    <label for="name">Name:</label><br>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name"><br>
                </div>
                 <div class="form-group">
                     <label for="customer_id">Customer ID:</label><br>
                     <input type="text" class="form-control" id="customer_id" placeholder="id" name="customer_id"><br>
                 </div>
                 <div class="form-group">
                     <label for="company_id">Company ID:</label><br>
                     <input type="text" class="form-control" id="company_id" placeholder="id" name="company_id"><br>
                 </div>
                 <div class="form-group">
                     <label for="begin_data">Gender:</label><br>
                     <input type="text" class="form-control" id="begin_data" placeholder="1430227800" name="begin_data"><br>
                 </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>