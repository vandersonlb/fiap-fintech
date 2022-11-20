<head>
  <%@ include file="./_jsp/bootstrap-links.jsp" %>
  <%@ include file="./_jsp/header.jsp" %>
  <%@ include file="./_jsp/tag-libs.jsp" %>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous"></script>
  <link rel="stylesheet" href="./_style/dashboard.css">
</head>

<body>
    <nav class="navbar navbar-expand-lg shadow-sm">
      <div class="container px-3">
        <a class="navbar-brand" href="./dashboard.jsp">
          <img src="./_assets/ft_logo_hor.svg" alt="" width="150">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavDarkDropdown">
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-end" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="text-primary fs-4 me-3">${sessionScope.usuario.nome}</span>
                <i class="bi bi-person-circle fs-3 text-primary"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-end py-2">
                <li><a class="dropdown-item fs-5 py-2" data-bs-toggle="modal" data-bs-target="#updateModal">
                  <i class="bi bi-person me-3"></i>Editar perfil</a>
                </li>
                <li><a class="dropdown-item fs-5 py-2" href="login"><i class="bi bi-box-arrow-right me-3"></i>Sair</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
    <!-- Modal -->
    <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="updateModalLabel">Editar perfil</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          
            <c:if test="${not empty error}">
              <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </c:if>
            
            <form id="updateForm" class="row g-3 needs-validation" action="user" method="get" novalidate>
              <!--  <input type="hidden" name="action" value="update" >  -->
              <input type="hidden" name="email" value="${usuario.email}">
              
              <div class="col-12">
                <label for="nome" class="form-label" value="">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" maxlength="30" value="${usuario.nome}">
                <div class="invalid-feedback">
                  Prazer! Qual seu nome?
                </div>
              </div>
              <div class="col-12">
                <label for="tel" class="form-label">Celular</label>
                <input type="tel" class="form-control" id="tel" name="tel" maxlength="11" value="${usuario.celular}">
                <div class="invalid-feedback">
                  Prometo que n�o vou te incomodar.
                </div>
              </div>
              <div class="col-12">
                <label for="date" class="form-label">Data nascimento</label>
                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
                <input type="date" class="form-control" id="date" name="date" 
                value='<fmt:formatDate value="${usuario.dataNasc.time}" pattern="yyyy-MM-dd"/>' >
                <div class="invalid-feedback">
                  Desculpe perguntar, qual sua idade?
                </div>
              </div>
            </form>
            
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">Deixa pra l�</button>
            <button class="btn btn-primary" form="updateForm" type="submit">Isso a�, mudei mesmo!</button>
          </div>
        </div>
      </div>
    </div>

</body>
</html>