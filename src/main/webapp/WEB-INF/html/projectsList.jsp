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
                          <td>Begin data</td>
                          <td>Name</td>
                          <td>Count of developers</td>
                      </tr>
                      <c:forEach items="${projects}" var="project">
                      <tr>
                          <td>
                              <c:out value="${project.begin_data}" />
                          </td>
                          <td>
                              <c:out value="${project.name}" />
                          </td>
                          <td>
                              <c:out value="${project.countOfDevelopers}" />
                          </td>
                      </tr>
                      </c:forEach>
                  </table>
              </div>
         </div>
    </body>
</html>

