<!DOCTYPE html>
<html lang="pt-br">

<head>
  <%@ include file="./_jsp/bootstrap-links.jsp" %>
  <%@ include file="./_jsp/header.jsp" %>
  <%@ include file="./_jsp/tag-libs.jsp" %>
</head>

<body>
  <header>
  <%@ include file="./navbar.jsp" %>
  </header>

  <main class="container-fluid mb-4">

    <div class="container-lg">
      <div class="row my-2 pt-4 px-3">
        <div class="col align-self-center">
          <h1 class="h2">Controle Financeiro</h1>
        </div>
        <div class="align-items-baseline align-self-center col d-flex justify-content-end">
          <p class="d-inline me-3 fs-5 d-none d-md-inline w-100 w-md-50 text-end">Escolha o mês:</p>
          <select class="form-select text-center border border-primary text-primary fs-5 fw-semibold w-100 w-md-50" aria-label="Meses" style="max-width: 300px;">
            <option value="1">Janeiro</option>
            <option value="2">Fevereiro</option>
            <option value="3">Março</option>
          </select>
        </div>
      </div>


      <div class="row">

        <div class="col-12 col-lg-6 px-3">
          <h2 class="h4 p-3">Investimentos</h2>
          <div class="card shadow p-4">
            
            <!-- Exbir quando não tiver investimentos -->
            <div class="row p-5 pb-3 align-items-md-center">
              <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                <img src="./_assets/img_investimento.svg" class="rounded mx-auto d-block" width="232" height="152">
              </div>
              <div class="col-12 col-md-6 col-lg-12 col-xl-6 pt-4 pt-md-0 pt-lg-4 pt-xl-0">
                <p class="fw-semibold">
                  Com um bom planejamento você chega até na lua.
                </p>
                <p>
                  Defina uma meta e comece a investir em seus sonhos! :)
                </p>
              </div>
            </div>
            <!-- FIM -->
            
            <!-- Exibir quando tiver investimentos-->
            <!--
            <div class="mb-4 hide-actions">
              <span class="h6 mb-1 d-block">Renda</span>
              <span class="h5 text-success">R$ 10.000,00</span>
              <div class="progress mt-2">
                <div class="progress-bar bg-success" role="progressbar" style="width: 75%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
              </div>
              <div class="position-absolute top-0 end-0 pe-3 pt-4">
                <button type="button" class="btn btn-link">
                  <a href="#">
                    <i class="bi-trash3 text-danger fs-4 fw-bold"></i>
                  </a>
                </button>
              </div>
            </div>
            -->
            <!-- FIM -->

            <div class="mt-3 d-grid gap-2 d-md-block d-lg-grid d-xl-block justify-content-center">
              <button class="btn btn-lg btn-primary px-5" type="button">
                <a href="#" class="text-decoration-none text-white">
                  Adicionar investimento
                </a>
              </button>
            </div>
          </div>
        </div>


        <div class="col-12 col-lg-6 px-3 mb-5 pt-4 pt-lg-0">
          <h2 class="h4 p-3">Extrato <span class="fs-5 fw-normal">&nbsp; (últimos lançamentos)</span> </h2>
          <div class="card shadow p-4">

            <!-- Exbir quando não tiver conta criada -->
            <!--
            <div class="row p-5 pb-3 align-items-md-center">
              <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                <img src="./_assets/img_conta.svg" class="rounded mx-auto d-block" width="232" height="152">
              </div>
              <div class="col-12 col-md-6 col-lg-12 col-xl-6 pt-4 pt-md-0 pt-lg-4 pt-xl-0">
                <p class="fw-semibold">
                  Antes de mais nada, está na hora de cadastrar sua conta.
                </p>
                <p>
                  Crie uma conta e fique de olho no seu rico dinheirinho! ;)
                </p>
              </div>
            </div>
            -->
            <!-- FIM -->

            <!-- Exibir quando tiver conta criada mas sem transações -->
            <!--
            <div class="row p-5 pb-3 align-items-md-center">
              <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                <img src="./_assets/img_transacao.svg" class="rounded mx-auto d-block" width="232" height="152">
              </div>
              <div class="col-12 col-md-6 col-lg-12 col-xl-6 pt-4 pt-md-0 pt-lg-4 pt-xl-0">
                <p class="fw-semibold">
                  Que a gente saiba, não aconteceu nada neste mês...
                </p>
                <p>
                  Que tal adicionar transações e acompanhar suas finanças? :D
                </p>
              </div>
            </div>
            -->
            <!-- FIM -->

            <!-- Exibir quando tiver conta criada com lançamentos -->
            
            <div class="row my-3 flex-wrap flex-sm-nowrap">
              <div class="col d-flex">
                <i class="bi bi-basket fs-3 border border-primary rounded-circle text-primary icone"></i>
                <div class="d-flex flex-column ps-2">
                  <span class="fs-6 text-truncate">Mercado</span>
                  <span class="fs-5 text-truncate">Mercado Market</span>
                </div>
              </div>
              <div class="col-12 col-sm-5 d-flex align-items-center justify-content-end pt-3 pt-sm-0">
                <span class="h5">- R$ 200,00</span>
              </div>
            </div>
            <div class="row my-3 flex-wrap flex-sm-nowrap">
              <div class="col d-flex">
                <i class="bi bi-bag fs-3 border border-secondary rounded-circle text-secondary icone"></i>
                <div class="d-flex flex-column ps-2">
                  <span class="fs-6 text-truncate">Compras</span>
                  <span class="fs-5 text-truncate">Shopping Center</span>
                </div>
              </div>
              <div class="col-12 col-sm-5 d-flex align-items-center justify-content-end pt-3 pt-sm-0">
                <span class="h5">- R$ 500,00</span>
              </div>
            </div>
            <div class="row my-3 flex-wrap flex-sm-nowrap">
              <div class="col d-flex">
                <i class="bi bi-currency-dollar fs-3 border border-success rounded-circle text-success icone"></i>
                <div class="d-flex flex-column ps-2">
                  <span class="fs-6 text-truncate">Remuneração</span>
                  <span class="fs-5 text-truncate">Trabalho principal</span>
                </div>
              </div>
              <div class="col-12 col-sm-5 d-flex align-items-center justify-content-end pt-3 pt-sm-0">
                <span class="h5 text-success">R$ 5.000,00</span>
              </div>
            </div>
            <div class="row my-3 flex-wrap flex-sm-nowrap">
              <div class="col d-flex">
                <i class="bi bi-house-door fs-3 border border-info rounded-circle text-info icone"></i>
                <div class="d-flex flex-column ps-2">
                  <span class="fs-6 text-truncate">Moradia</span>
                  <span class="fs-5 text-truncate">Aluguel janeiro</span>
                </div>
              </div>
              <div class="col-12 col-sm-5 d-flex align-items-center justify-content-end pt-3 pt-sm-0">
                <span class="h5 text-success">R$ 2.000,00</span>
              </div>
            </div>
            <div class="row my-3 flex-wrap flex-sm-nowrap">
              <div class="col d-flex">
                <i class="bi bi-dpad fs-3 border border-danger rounded-circle text-danger icone"></i>
                <div class="d-flex flex-column ps-2">
                  <span class="fs-6 text-truncate">Jogos</span>
                  <span class="fs-5 text-truncate">Super Mario Bros.</span>
                </div>
              </div>
              <div class="col-12 col-sm-5 d-flex align-items-center justify-content-end pt-3 pt-sm-0">
                <span class="h5">R$ 35,00</span>
              </div>
            </div>
            
            <!-- FIM -->

            <!-- Exibir quando não tiver conta criada -->
            <!--
            <div class="mt-3 d-grid gap-2 d-md-block d-lg-grid d-xl-block justify-content-center">
              <button class="btn btn-lg btn-primary px-5" type="button">
                <a href="#" class="text-decoration-none text-white">
                  &nbsp; &nbsp; Adicionar conta &nbsp; &nbsp;
                </a>
              </button>
            </div>
            -->
            <!-- FIM -->
            
            <!-- Exibir quando tiver conta criada -->
            
            <div class="row">
              <div class="col">
                <div class="d-flex flex-column flex-sm-row gap-2 mt-3">
                  <button class="btn btn-lg btn-outline-primary flex-fill">
                    <a href="#" class="text-decoration-none text-primary">
                      Ver extrato
                    </a>
                  </button>
                  <button class="btn btn-lg btn-primary flex-fill" type="button" data-bs-toggle="offcanvas" data-bs-target="#transacao" aria-controls="transacao"> 
                    <!-- <a href="#" class="text-decoration-none text-white"> -->
                      Adicionar transação
                    <!-- </a> -->
                  </button>
                </div>
              </div>
            </div>
            
            <!-- FIM -->
            
            
          </div>
        </div>
      </div>
    </div>

    <!-- Exibir quando tiver uma conta criada -->
    <!--
    <div class="position-fixed bottom-0 start-50 translate-middle-x">
      <div class="row badge bg-secondary mb-4 p-2 p-sm-3 d-sm-flex">
        <div class="col fs-5 fw-normal lh-sm">Saldo atual</div>
        <div class="col fs-5 fw-bold lh-sm">R$ 1.500,00</div>
      </div>
    </div>
    -->
    <!-- FIM -->
  </main>

  <!-- Offcanvas de adicionar transação -->
  <div class="offcanvas offcanvas-end pe-4" tabindex="-1" id="transacao" aria-labelledby="transacaoLabel">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="transacaoLabel">Adicionar transação</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <form class="row g-4 needs-validation">
        <div class="col-12">
          <label for="nome" class="form-label">Nome transação</label>
          <input type="text" class="form-control" id="nome" placeholder="Nome transação" required>
        </div>
        <div class="col-12">
          <label for="valor" class="form-label">Valor</label>
          <input type="number" class="form-control" id="valor" placeholder="Valor" required>
        </div>
        <div class="col-12">
          <label for="investimento" class="form-label">Investimento</label>
          <select class="form-select" id="investimento" aria-label="investimento">
            <option value="0" selected>Investimento</option>
            <option value="1">Janeiro</option>
            <option value="2">Fevereiro</option>
            <option value="3">Março</option>
          </select>
        </div>
        <div class="col-12">
          <label for="tipo" class="form-label">Isso é uma:</label>
          <select class="form-select" id="tipo" aria-label="tipo">
            <option value="0" selected>Isso é uma:</option>
            <option value="1">Janeiro</option>
            <option value="2">Fevereiro</option>
            <option value="3">Março</option>
          </select>
        </div>
        <div class="col-12">
          <label for="data" class="form-label">Data</label>
          <input type="date" class="form-control" id="data" required>
        </div>
        <div class="col-12">
          <label for="categoria" class="form-label">Investimento</label>
          <select class="form-select" id="categoria" aria-label="categoria">
            <option value="0" selected>Categoria</option>
            <option value="1">Janeiro</option>
            <option value="2">Fevereiro</option>
            <option value="3">Março</option>
          </select>
        </div>
        <div class="col-12 pb-5 mb-5">
          <div class="form-floating">
            <textarea class="form-control" id="observacao" placeholder="Leave a comment here" ></textarea>
            <label for="observacao">Observação</label>
          </div>
        </div>

        <div class="d-grid pb-3 col-12 position-absolute bottom-0">
          <button class="btn btn-primary btn-lg" type="submit">Salvar</button>
        </div>
      </form>
    </div>
  </div>
  <!-- FIM -->

  <footer class="background fixed-bottom container-fluid p-0 d-none d-lg-block">
    <div class="background__img">
    </div>
    <div class="background__wave background__wave--mask">
    </div>
  </footer>
</body>

</html>