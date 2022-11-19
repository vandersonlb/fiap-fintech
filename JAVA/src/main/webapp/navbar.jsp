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
                <li><a class="dropdown-item fs-5 py-2" href="#"><i class="bi bi-person me-3"></i>Editar perfil</a></li>
                <li><a class="dropdown-item fs-5 py-2" href="login"><i class="bi bi-box-arrow-right me-3"></i>Sair</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</body>
</html>