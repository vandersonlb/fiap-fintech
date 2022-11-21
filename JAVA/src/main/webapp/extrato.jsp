<!doctype html>
<html lang="pt-br">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="/assets/fintech.ico">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
  <link rel="stylesheet" href="./style/color_primary.css">
  <link rel="stylesheet" href="./style/color_secondary.css">
  <link rel="stylesheet" href="./style/dashboard.css">
  <title>Fintech - Te ajudando a realizar suas metas financeiras</title>
</head>

<body>
  <header>
    <nav class="navbar navbar-expand-lg shadow-sm">
      <div class="container px-3">
        <a class="navbar-brand" href="./index.html">
          <img src="./assets/ft_logo_hor.svg" alt="" width="150">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavDarkDropdown">
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-end" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <span class="text-primary fs-4 me-3">Nome da pessoa</span>
                <i class="bi bi-person-circle fs-3 text-primary"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-end py-2">
                <li><a class="dropdown-item fs-5 py-2" href="#"><i class="bi bi-person me-3"></i>Editar perfil</a></li>
                <li><a class="dropdown-item fs-5 py-2" href="#"><i class="bi bi-box-arrow-right me-3"></i>Sair</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>

  <main class="overflow-hidden">

    <div class="container-md extrato bg-white p-5 overflow-hide">
      <h1 class="h3 fw-bold pb-4">Extrato mensal</h1>

      <!-- ITEM -->
      <div class="row flex-wrap flex-lg-nowrap py-1 hide-actions">
        <div class="col-8 col-md-8 col-lg-4 d-flex">
          <i class="bi bi-basket fs-3 border border-primary rounded-circle text-primary icone"></i>
          <div class="d-flex flex-column ps-3">
            <span class="fs-6 text-truncate">Mercado</span>
            <span class="fs-5 text-truncate">Mercado Market</span>
          </div>
        </div>
        <div class="col-4 col-md-4 col-lg-2 d-flex">
          <div class="d-flex flex-column">
            <span class="fs-6 text-truncate">Sicredi</span>
            <span class="h5">- R$ 200,00</span>
          </div>
        </div>
        <div class="col-4 col-md-3 col-lg-2 d-flex pt-4 pt-lg-0">
          <div class="d-flex flex-column">
            <span class="fs-6 text-truncate">Data</span>
            <span class="fs-5 text-truncate">16 de junho</span>
          </div>
        </div>
        <div class="col-4 col-md-6 col-lg-2 d-flex pt-4 pt-lg-0">
          <div class="d-flex flex-column">
            <span class="fs-6 text-truncate w-50">Descrição</span>
            <span class="fs-5 text-truncate w-50">Lorem ipsum dolor ament dolow sit ament dasf sfd as fsa fsad</span>
          </div>
        </div>
        <div class="col-4 col-md-3 col-lg-2 d-flex align-items-center justify-content-end pt-4 pt-lg-0">
          <button type="button" class="btn btn-link">
            <a href="#">
              <i class="bi-pencil text-primary fs-4 fw-bold"></i>
            </a>
          </button>
          <button type="button" class="btn btn-link">
            <a href="#">
              <i class="bi-trash3 text-danger fs-4 fw-bold"></i>
            </a>
          </button>
        </button>
        </div>
      </div>
      <hr class="pt-1 pb-0">
      <!-- FIM ITEM -->

    </div>

    <div class="position-fixed bottom-0 start-50 translate-middle-x">
      <div class="row badge bg-secondary mb-4 p-2 p-sm-3 d-sm-flex">
        <div class="col fs-5 fw-normal lh-sm">Saldo atual</div>
        <div class="col fs-5 fw-bold lh-sm">R$ 1.500,00</div>
      </div>
    </div>
  </main>
</body>

</html>