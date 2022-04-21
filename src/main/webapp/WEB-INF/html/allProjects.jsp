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
                   <table class="table table-hover">
                      <tr>
                          <td>Id</td>
                          <td>Name</td>
                          <td>Customer ID</td>
                          <td>Company ID</td>
                          <td>Begin Data</td>
                      </tr>
                      <c:forEach items="${projects}" var="project">
                      <tr>
                          <td>
                              <c:out value="${project.project_id}" />
                          </td>
                          <td>
                              <c:out value="${project.name}" />
                          </td>
                          <td>
                              <c:out value="${project.customer_id}" />
                          </td>
                          <td>
                              <c:out value="${project.company_id}" />
                          </td>
                           <td>
                              <c:out value="${project.begin_data}"/>
                           </td>
                      </tr>
                      </c:forEach>
                  </table>
              </div>
         </div>
    </body>
</html>