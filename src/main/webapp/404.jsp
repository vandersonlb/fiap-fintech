<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <%@ include file="./_jsp/bootstrap-links.jsp" %>
  <%@ include file="./_jsp/header.jsp" %>
    <style>
    .bckgrnd {
      height: 55vh;
    }
    
    .bckgrnd-image {
      background: url("./_assets/404_img.svg") no-repeat center top;
      background-size: contain;
      height: 100%;
    }
  </style>
</head>

<body>

  <header>
    <%@ include file="./navbar.jsp" %>
  </header>
  <main>
  
  <div class="bckgrnd p-3">
    <div class="text-center fs-1 text-primary text-center my-5">
      <span class="fw-bold">Oops!</span> Página não encontrada!
    </div>
    <div class="bckgrnd-image"></div>
  </div>

  </main>
</body>
</html>
