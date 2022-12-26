

<%@ include file="./tag-libs.jsp"%>
<ul>
  <li><b>Usuário</b>: <c:out value="${usuario}"></c:out></li>
  <li><b>Conta</b>: <c:out value="${conta}"></c:out></li>
  <li><b>Investimento</b>: <c:out value="${investimentos}"></c:out></li>
  <li><b>Ultimas</b>: <c:out value="${ultimas}"></c:out></li>
  <li><b>Tipos</b>: <c:out value="${tipos}"></c:out></li>
  <li><b>Categorias</b>: <c:out value="${grupoCategorias.size()}"></c:out></li>
  <li><b>Extrato</b>: <c:out value="${extrato}"></c:out></li>
</ul>
