<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"
  import="java.util.Map, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="./_jsp/tag-libs.jsp"%>
</head>
<body>
  USUÁRIO LOGADO!!!
  <br>

  <c:out value="${usuario}"> </c:out>
  
  <c:forEach var="item2" items="${sessionScope.grupoCategorias}">
    <p>${item2}</p>
  </c:forEach>
  
  <c:forEach var="item3" items="${sessionScope.tipos}">
    <p>${item3}</p>
  </c:forEach>
  
    <c:forEach var="item4" items="${sessionScope.ultimasTransacoes}">
    <p>${item4}</p>
  </c:forEach>
 

</body>
</html>
