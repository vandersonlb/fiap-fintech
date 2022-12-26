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
      <div class="background_img cadastro"></div>
    </div>

    <div id="card_wrap"  class="card shadow cadastro overflow-auto">
        
        <div class="card-body pt-0">
          <img src="./_assets/ft_logo_hor.svg" alt="Logo Fintech" class="mx-auto d-block pt-2" />
          <p class="card-title text-center fs-5 text-muted pt-3 px-5">
            Cadastre-se para desbloquear os seus superpoderes financeiros
          </p>
        </div>
        
        <c:if test="${not empty error}">
          <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
            ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
          </div>
        </c:if>
        
        <form class="row g-3 needs-validation" action="user" method="post" novalidate>
          <div class="col-12">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" maxlength="30" placeholder="Digite seu nome completo" required>
            <div class="invalid-feedback">
              Prazer! Qual seu nome?
            </div>
          </div>
          <div class="col-12">
            <label for="email" class="form-label">E-mail</label>
            <input type="email" class="form-control" id="email" name="email" maxlength="30" placeholder="nome@email.com.br" required>
            <div class="invalid-feedback">
              Aqui tem que digitar um e-mail, se liga!
            </div>
          </div>
          <div class="col-12">
            <label for="tel" class="form-label">Celular</label>
            <input type="tel" class="form-control" id="tel" name="tel" maxlength="11" placeholder="XXXXX-XXXX" required>
            <div class="invalid-feedback">
              Prometo que não vou te incomodar.
            </div>
          </div>
          <div class="col-12">
            <label for="date" class="form-label">Data nascimento</label>
            <input type="date" class="form-control" id="date" name="date" required>
            <div class="invalid-feedback">
              Desculpe perguntar, qual sua idade?
            </div>
          </div>
          <div class="col-12">
            <label for="cpf" class="form-label">CPF</label>
            <input type="tel" class="form-control" id="cpf" name="cpf" minlength="11" maxlength="11" placeholder="Somente números" required>
            <div class="invalid-feedback">
              Cadastro de Pessoa F@da.
            </div>
          </div>
          <div class="col-12">
            <label for="password">Senha</label>
            <div class="input-group">
              <input type="password" class="form-control" id="password" name="password" minlength="6" maxlength="20" required />
              <span class="input-group-text" type="button">
                <i id="eye" class="bi bi-eye-slash"></i>
              </span>
             </div>
            <div class="invalid-feedback">
                Olha o hacker aí, digite pelo menos 6 caracteres. 
              </div>
          </div>
          
          <div class="col-12 d-grid gap-2 py-4 mt-4">
            <button class="btn btn-primary btn-lg" type="submit">Cadastrar</button>
          </div>
        </form>
        
        <div class="text-center">
          <a href="./login.jsp" class="text-primary text-decoration-none">Já tenho cadastro</a>
        </div>
        
    </div>
  </main>
  
  <script src="./_script/form-validation.js"></script>
  <script src="./_script/eyeButton.js"></script>
  
</body>

</html>