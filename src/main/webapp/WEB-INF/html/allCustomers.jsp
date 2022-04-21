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
         <h3>All customers:</h3>
              <div class="container">
                   <table class="table table-hover">
                      <tr>
                          <td>Id</td>
                          <td>First Name</td>
                          <td>Last Name</td>
                          <td>Age</td>
                          <td>Gender</td>
                          <td>Email</td>
                          <td>Phone</td>
                      </tr>
                      <c:forEach items="${customers}" var="customer">
                      <tr>
                          <td>
                              <c:out value="${customer.customer_id}" />
                          </td>
                          <td>
                              <c:out value="${customer.first_name}" />
                          </td>
                          <td>
                              <c:out value="${customer.last_name}" />
                          </td>
                          <td>
                              <c:out value="${customer.age}" />
                          </td>
                           <td>
                              <c:out value="${customer.gender}"/>
                           </td>
                           <td>
                              <c:out value="${customer.email}"/>
                           </td>
                           <td>
                               <c:out value="${customer.phone}"/>
                           </td>
                      </tr>
                      </c:forEach>
                  </table>
              </div>
         </div>
    </body>
</html>