<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" 
  import="br.com.fintech.util.EnumCategoria, br.com.fintech.util.EnumGrupo"%>
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

    <div class="container-md extrato bg-white overflow-hide p-4 p-sm-5">
      <h1 class="h3 fw-bold pb-4">Extrato de transações</h1>

      <!-- ITEM -->
      <c:forEach var="transacao" items="${extrato}">
      <div class="row flex-wrap hide-actions">
        <div class="col-12 col-sm-8 col-lg-4 d-flex px-2 px-sm-0">
        
          <!-- Pegando a cor do ícone pelo ENUM do grupo -->
          <c:set var="iconColor" value="${EnumGrupo.values()[transacao.categoria.codGrupo-1].label}"></c:set>
              
          <!-- Pegando o ícone pelo ENUM da categoria -->
          <c:set var="nomeCategoria" value="${transacao.categoria.nomeCategoria.toUpperCase().replaceAll('[\\\s\\\/]+', '_')}"></c:set>
          <c:set var="icone" value='${EnumCategoria.valueOf(nomeCategoria).label}'></c:set>
                
          <i class="bi ${icone} fs-3 border ${iconColor} rounded-circle icone"></i>
          <div class="d-flex flex-column ps-3">
            <span class="fs-6 text-truncate">${transacao.categoria.nomeCategoria}</span>
            <span class="fs-5 text-truncate">${transacao.nome}</span>
          </div>
        </div>
        <div class="col-6 col-sm-4 col-lg-2 d-flex pt-3 pb-1 p-sm-0">
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
        <div class="col-6 col-sm-3 col-lg-2 d-flex pt-3 pb-1 p-sm-0 mt-sm-3 mt-lg-0">
          <div class="d-flex flex-column">
            <span class="fs-6 text-truncate">Data</span>
            <span class="fs-5 text-truncate">
              <fmt:formatDate type="date" dateStyle="short" value="${transacao.data.time}"/>
            </span>
          </div>
        </div>
        <div class="col-8 col-sm-6 col-lg-2 d-flex py-2 p-sm-0 mt-sm-3 mt-lg-0">
          <div class="d-flex flex-column" style="width: -webkit-fill-available">
            <span class="fs-6">Descrição</span>
              <span class="fs-5 text-truncate">${transacao.obsevacao}</span>
          </div>
        </div>
        <div class="col-4 col-sm-3 col-lg-2 d-flex align-items-center justify-content-end py-2 p-sm-0 mt-sm-3">
          <button class="btn btn-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#editarTransacaoModal${transacao.sequencia}"
            aria-controls="editarTransacaoModal${transacao.sequencia}"> 
            <i class="bi-pencil text-primary fs-4 fw-bold"></i>
          </button>
          <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#deleteTransacaoModal"
            onclick="codigo.value = ${transacao.sequencia}">
            <i class="bi-trash3 text-danger fs-4 fw-bold"></i>
          </button>
        </div>
      </div>
      <hr class="pt-1 pb-0">

      
    <!-- Offcanvas EDITAR TRANSAÇÃO -->
    <div class="offcanvas offcanvas-end pe-4" tabindex="-1" id="editarTransacaoModal${transacao.sequencia}" aria-labelledby="editarTransacaoModal${transacao.sequencia}Label">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="editarTransacaoModal${transacao.sequencia}Label">Adicionar transação</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <form class="row g-4 needs-validation" action="transacao" method="POST" novalidate>
          
          <input type="hidden" name="action" value="edit" >
          <input type="hidden" name="codigo" value="${transacao.sequencia}" >

          <div class="col-12">
            <label for="nome" class="form-label">Nome transação</label>
            <input type="text" class="form-control" id="nome" name="nome" maxlength="20" value="${transacao.nome}" required>
          </div>
          <!-- 
          <div class="col-12">
            <label for="valor" class="form-label">Valor</label>
            <input type="number" class="form-control" id="valor" name="valor" maxlength="10" value="${transacao.valor}" required>
          </div>
          -->
          <div class="col-12">
            <label for="data" class="form-label">Data</label>
              <input type="date" class="form-control" id="date" name="date" required
              value='<fmt:formatDate value="${transacao.data.time}" pattern="yyyy-MM-dd"/>' >
          </div>
          <div class="col-12">
            <label for="categoria" class="form-label">Categoria</label>
            <select class="form-select" id="categoria" name="categoria" aria-label="categoria">
              <c:forEach var="grupoCategoria" items="${grupoCategorias}">
                <span>grupoCategoria.nomeGrupo</span>
                <option disabled> ${grupoCategoria.nomeGrupo} </option>
                <c:forEach var="categoria" items="${grupoCategoria.categorias}">
                  <c:if test="${categoria.codCategoria == transacao.categoria.codCategoria}">
                    <option value ="${categoria.codCategoria}" selected>${categoria.nomeCategoria}</option>
                  </c:if>
                  <c:if test="${categoria.codCategoria != transacao.categoria.codCategoria}">
                    <option value ="${categoria.codCategoria}">${categoria.nomeCategoria}</option>
                  </c:if>
                </c:forEach>
              </c:forEach>
            </select>
          </div>
          <div class="col-12 pb-5 mb-5">
            <div class="form-floating">
              <textarea class="form-control" id="observacao" name="observacao" maxlength="60">${transacao.obsevacao}</textarea>
              <label for="observacao">Observação</label>
            </div>
          </div>
  
          <div class="d-grid pb-3 col-12 position-absolute bottom-0">
            <button class="btn btn-primary btn-lg" type="submit">Salvar</button>
          </div>
        </form>
      </div>
    </div>
    <!-- FIM OFFCANVAS -->
    
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
            <form id="deleteInvestimentoForm" class="row g-3 needs-validation" action="transacao" method="POST" novalidate>
              <input type="hidden" name="action" value="delete">
              <!--  <input type="hidden" name="numConta" value="${conta.numConta}"> -->
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