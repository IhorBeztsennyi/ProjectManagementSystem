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
                   <form action="/developersListByProject">
                        <div class="form-group">
                            <label for="projectNumber">Enter the project number:</label><br>
                            <input type="text" class="form-control" id="projectNumber" placeholder="Enter the project number" name="projectNumber"><br>
                         </div>
                         <input type="submit" value="Submit">
                   </form>
                   <table class="table table-hover">
                      <tr>
                          <td>Id</td>
                          <td>First Name</td>
                          <td>Last Name</td>
                          <td>Age</td>
                          <td>Gender</td>
                          <td>Email</td>
                          <td>Phone</td>
                          <td>Salary</td>
                      </tr>
                      <c:forEach items="${developers}" var="developer">
                      <tr>
                          <td>
                              <c:out value="${developer.nameId}" />
                          </td>
                          <td>
                              <c:out value="${developer.firstName}" />
                          </td>
                          <td>
                              <c:out value="${developer.lastName}" />
                          </td>
                          <td>
                              <c:out value="${developer.age}" />
                          </td>
                           <td>
                              <c:out value="${developer.gender}"/>
                           </td>
                           <td>
                              <c:out value="${developer.email}"/>
                           </td>
                           <td>
                               <c:out value="${developer.phone}"/>
                           </td>
                           <td>
                                <c:out value="${developer.salary}"/>
                           </td>
                      </tr>
                      </c:forEach>
                  </table>
              </div>
         </div>
    </body>
</html>