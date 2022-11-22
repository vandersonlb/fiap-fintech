<!DOCTYPE html>
<html lang="pt-br">

<head>
  <%@ include file="./_jsp/bootstrap-links.jsp" %>
  <%@ include file="./_jsp/header.jsp" %>
  <%@ include file="./_jsp/tag-libs.jsp" %>
  <link rel="stylesheet" href="./_style/dashboard.css">
</head>

<body>
  <header>
  <fmt:setLocale value = "pt_BR"/>
  <%@ include file="./navbar.jsp" %>
  </header>

  <main class="overflow-hidden">
  
    <!-- 
    <%@ include file="./_jsp/debug.jsp" %>
     -->   

    <div class="container-md extrato bg-white p-5 overflow-hide">
      <h1 class="h3 fw-bold pb-4">Extrato de transações</h1>

      <!-- ITEM -->
      <c:forEach var="transacao" items="${extrato}">
      <div class="row flex-wrap flex-lg-nowrap py-1 hide-actions">
        <div class="col-8 col-md-8 col-lg-4 d-flex">
          <i class="bi bi-basket fs-3 border border-primary rounded-circle text-primary icone"></i>
          <div class="d-flex flex-column ps-3">
            <span class="fs-6 text-truncate">${transacao.categoria.nomeCategoria}</span>
            <span class="fs-5 text-truncate">${transacao.nome}</span>
          </div>
        </div>
        <div class="col-4 col-md-4 col-lg-2 d-flex">
          <div class="d-flex flex-column">
            <span class="fs-6 text-truncate">${conta.nomeConta}</span>
            <c:if test="${transacao.tipo.codTipo == 1 || transacao.tipo.codTipo == 4}">
              <c:set var="valorColor" value="text-success"> </c:set>
              <c:set var="signal" value=""> </c:set>
            </c:if>
            <c:if test="${transacao.tipo.codTipo == 2 || transacao.tipo.codTipo == 3}">
              <c:set var="valorColor" value="text-body"> </c:set>
              <c:set var="signal" value="-"> </c:set>
            </c:if>
            <span class="fs-5 fw-semibold ${valorColor}">
              ${signal} <fmt:formatNumber value="${transacao.valor}" type = "currency"/>
            </span>
          </div>
        </div>
        <div class="col-4 col-md-3 col-lg-2 d-flex pt-4 pt-lg-0">
          <div class="d-flex flex-column">
            <span class="fs-6 text-truncate">Data</span>
            <span class="fs-5 text-truncate">
              <fmt:formatDate type="date" dateStyle="short" value="${transacao.data.time}"/>
            </span>
          </div>
        </div>
        <div class="col-4 col-md-6 col-lg-2 d-flex pt-4 pt-lg-0">
          <div class="d-flex flex-column">
            <span class="fs-6 w-50">Descrição</span>
              <span class="fs-5 w-50">${transacao.obsevacao}</span>
          </div>
        </div>
        <div class="col-4 col-md-3 col-lg-2 d-flex align-items-center justify-content-end pt-4 pt-lg-0">
          <button type="button" class="btn btn-link">
            <i class="bi-pencil text-primary fs-4 fw-bold"></i>
          </button>
          <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#deleteTransacaoModal"
            onclick="codigo.value = ${transacao.sequencia}">
            <i class="bi-trash3 text-danger fs-4 fw-bold"></i>
          </button>
        </div>
      </div>
      <hr class="pt-1 pb-0">
      </c:forEach>
      <!-- FIM ITEM -->

    </div>

    <div class="position-fixed bottom-0 start-50 translate-middle-x">
      <div class="row badge bg-secondary mb-4 p-2 p-sm-3 d-sm-flex">
        <div class="col fs-5 fw-normal lh-sm">Saldo atual</div>
        <div class="col fs-5 fw-bold lh-sm">
          <fmt:formatNumber value = "${conta.saldo}" type = "currency"/>
        </div>
      </div>
    </div>
  </main>
  
    <!-- Modal DELETAR TRANSACAO -->
    <div class="modal fade" id="deleteTransacaoModal" tabindex="-1" aria-labelledby="deleteTransacaoModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5">Confirmar exclusão</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          
            <c:if test="${not empty error}">
              <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </c:if>
            <p>Está certo disso? Se eu sair não volto mais hein!</p>
          </div>
          <div class="modal-footer">
            <form id="deleteInvestimentoForm" class="row g-3 needs-validation" action="transacao" method="GET" novalidate>
              <input type="hidden" name="action" value="delete">
              <input type="hidden" name="numConta" value="${conta.numConta}">
              <input type="hidden" name="codigo" id="codigo">
            </form>
            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Fica vai!</button>
            <button class="btn btn-primary" form="deleteInvestimentoForm" type="submit">Tchau e benção</button>
          </div>
        </div>
      </div>
    </div>  
  
  <script src="./_script/form-validation.js"></script>
  
</body>

</html>