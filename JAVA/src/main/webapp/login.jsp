<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <%@ include file="./_jsp/bootstrap-links.jsp" %>
  <%@ include file="./_jsp/header.jsp" %>
  <%@ include file="./_jsp/tag-libs.jsp" %>
  <link rel="stylesheet" href="./_style/login.css" />
</head>

<body>
  <main>
    <div id="background" class="background">
      <div class="background__up"></div>
      <div class="background__down"></div>
      <div class="background__wave"></div>
      <div class="background_img login"></div>
    </div>

    <div id="card" class="card shadow login p-5">

      <div class="card-body pt-0">
        <img src="./_assets/ft_logo_hor.svg" alt="Logo Fintech" class="mx-auto d-block pt-3" />
        <p class="card-title text-center fs-5 text-muted pt-3">
          Te ajudando a realizar suas metas e organizar seu dinheiro
        </p>
      </div>
      
      <form class="row g-3 needs-validation" action="login" method="post" novalidate>
        <div class="col-12">
          <label for="email" class="form-label">E-mail</label>
          <input type="email" class="form-control" id="email" name="email" maxlength="30" placeholder="nome@email.com.br" required>
          <div class="invalid-feedback">
            Aqui tem que digitar um e-mail, se liga!
          </div>
        </div>
        <div class="col-12">
          <label for="password">Senha</label>
          <div class="input-group">
            <input type="password" class="form-control" id="password" name="password" minlength="6" maxlength="20" required />
            <span class="input-group-text" type="button">
              <i id="eye" class="bi bi-eye-slash"></i>
            </span>
	        <div class="invalid-feedback">
              Olha o hacker aí, digite pelo menos 6 caracteres. 
            </div>
          </div>
        </div>
        <div class="col-12">
          <div class="form-text text-end">
            <a href="#" class="text-primary fs-6 text-decoration-none">Esqueci minha senha</a>
          </div>
        </div>

        <div class="col-12 d-grid gap-2 py-4">
          <button class="btn btn-primary btn-lg" type="submit">Entrar</button>
        </div>
      </form>
      
      <c:if test="${not empty error}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
          ${error}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
      </c:if>
      <div class="text-center">
        <a href="./cadastro.jsp" class="text-primary text-decoration-none">Não tenho cadastro</a>
      </div>

    </div>
  </main>
  
  <script src="./_script/form-validation.js"></script>
  <script src="./_script/eyeButton.js"></script>

</body>

</html>