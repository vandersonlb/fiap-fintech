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

  <main class="container-fluid mb-4">
  
    <!-- 
    <%@ include file="./_jsp/debug.jsp" %>
     -->   

    <div class="container-lg">
      <div class="row my-2 pt-4 px-3">
        <div class="col align-self-center">
          <h1 class="h2">Controle Financeiro</h1>
        </div>
    </div>

      <div class="flex-column-reverse flex-lg-row row">

        <div class="col-12 col-lg-6 px-3">
          <h2 class="h4 p-3">Investimentos</h2>
          <div class="card shadow p-4">
            
            <!-- Exbir quando n�o tiver investimentos -->
            <c:if test="${investimentos == null || investimentos.size() == 0}">
            <div class="row p-5 pb-3 align-items-md-center">
              <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                <img src="./_assets/img_investimento.svg" class="rounded mx-auto d-block" width="232" height="152">
              </div>
              <div class="col-12 col-md-6 col-lg-12 col-xl-6 pt-4 pt-md-0 pt-lg-4 pt-xl-0">
                <p class="fw-semibold">
                  Com um bom planejamento voc� chega at� na lua.
                </p>
                <p>
                  Defina uma meta e comece a investir em seus sonhos! :)
                </p>
              </div>
            </div>
            </c:if>
            <!-- FIM -->
            
            <!-- Exibir quando tiver investimentos-->
            <c:if test="${investimentos != null && investimentos.size() > 0}">
              <c:forEach var="invest" items="${investimentos}">
              <div class="mb-4 hide-actions position-relative">
                <span class="h6 mb-1 d-block">${invest.nomeInvestimento}</span>
                <span class="h5 text-success">
                  <fmt:formatNumber value = "${invest.saldo}" type = "currency"/>
                </span>
                <div class="progress mt-2">
                  <c:set var="percent" value="${Math.round(invest.saldo/invest.meta * 100)}"></c:set>
                  <c:choose>
                    <c:when test="${percent <= 15}">
                      <c:set var="investColor" value="bg-danger"></c:set>
                    </c:when>    
                    <c:when test="${percent < 50}">
                      <c:set var="investColor" value="bg-warning"></c:set>
                    </c:when>    
                    <c:when test="${percent < 85}">
                      <c:set var="investColor" value="bg-info"></c:set>
                    </c:when>    
                    <c:otherwise>
                      <c:set var="investColor" value="bg-success"></c:set>
                    </c:otherwise>
                  </c:choose>
                  <div class="progress-bar ${investColor}" role="progressbar" style="width: ${percent}%" aria-valuenow="${percent}" aria-valuemin="0" aria-valuemax="${invest.meta}"></div>
                </div>
                <div class="position-absolute top-0 end-0">
                  <button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#deleteInvestimentoModal"
                  onclick="codigo.value = ${invest.codInvestimento}">
                    <i class="bi-trash3 text-danger fs-4 fw-bold"></i>
                  </button>
                </div>
              </div>
              </c:forEach>
            </c:if>
            <!-- FIM -->

            <div class="mt-3 d-grid gap-2 d-md-block d-lg-grid d-xl-block justify-content-center">
              <c:if test="${conta != null}">
              <button class="btn btn-lg btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#addInvestimentoModal">
                Adicionar investimento
              </button>
              </c:if>
              <c:if test="${conta == null}">
              <button class="btn btn-lg btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#addInvestimentoModal" disabled>
                Adicionar investimento
              </button>
              </c:if>
            </div>
          </div>
        </div>

        <div class="col-12 col-lg-6 px-3 mb-5 pt-4 pt-lg-0">
          <h2 class="h4 p-3">Extrato <span class="fs-5 fw-normal">&nbsp; (�ltimos lan�amentos)</span> </h2>
          <div class="card shadow p-4">

            <!-- Exbir quando n�o tiver conta criada -->
            <c:if test="${conta == null}">
            <div class="row p-5 pb-3 align-items-md-center">
              <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                <img src="./_assets/img_conta.svg" class="rounded mx-auto d-block" width="232" height="152">
              </div>
              <div class="col-12 col-md-6 col-lg-12 col-xl-6 pt-4 pt-md-0 pt-lg-4 pt-xl-0">
                <p class="fw-semibold">
                  Antes de mais nada, est� na hora de cadastrar sua conta.
                </p>
                <p>
                  Crie uma conta e fique de olho no seu rico dinheirinho! ;)
                </p>
              </div>
            </div>
            </c:if>
            <!-- FIM -->

            <!-- Exibir quando tiver conta criada mas sem transa��es -->
            <c:if test="${conta != null && ultimas == null || ultimas.size() == 0}">
            <div class="row p-5 pb-3 align-items-md-center">
              <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                <img src="./_assets/img_transacao.svg" class="rounded mx-auto d-block" width="232" height="152">
              </div>
              <div class="col-12 col-md-6 col-lg-12 col-xl-6 pt-4 pt-md-0 pt-lg-4 pt-xl-0">
                <p class="fw-semibold">
                  Que a gente saiba, n�o aconteceu nada neste m�s...
                </p>
                <p>
                  Que tal adicionar transa��es e acompanhar suas finan�as? :D
                </p>
              </div>
            </div>
            </c:if>
            <!-- FIM -->

            <!-- Exibir quando tiver conta criada com lan�amentos -->
            <c:if test="${conta != null && ultimas != null && ultimas.size() > 0}">
            <c:forEach var="transacao" items="${ultimas}">
            <div class="row my-3 flex-wrap flex-sm-nowrap">
              <div class="col d-flex">
                <i class="bi bi-basket fs-3 border border-primary rounded-circle text-primary icone"></i>
                <div class="d-flex flex-column ps-2">
                  <span class="fs-6 text-truncate">${transacao.categoria.nomeCategoria}</span>
                  <span class="fs-5 text-truncate">${transacao.nome}</span>
                </div>
              </div>
              <div class="col-12 col-sm-5 d-flex align-items-center justify-content-end pt-3 pt-sm-0">
                <c:if test="${transacao.tipo.codTipo == 1 || transacao.tipo.codTipo == 4}">
					<c:set var="valorColor" value="text-success"> </c:set>
					<c:set var="signal" value=""> </c:set>
                </c:if>
                <c:if test="${transacao.tipo.codTipo == 2 || transacao.tipo.codTipo == 3}">
					<c:set var="valorColor" value="text-body"> </c:set>
					<c:set var="signal" value="-"> </c:set>
                </c:if>
                <span class="h5 ${valorColor}">
                  ${signal} <fmt:formatNumber value="${transacao.valor}" type = "currency"/>
                </span>
              </div>
            </div>
            </c:forEach>
            </c:if>
            <!-- FIM -->

            <!-- Exibir quando n�o tiver conta criada -->
            <c:if test="${conta == null}">
            <div class="mt-3 d-grid gap-2 d-md-block d-lg-grid d-xl-block justify-content-center">
              <button class="btn btn-lg btn-primary px-5" type="button" data-bs-toggle="modal" data-bs-target="#addContaModal">
                &nbsp; &nbsp; Adicionar conta &nbsp; &nbsp;
              </button>
            </div>
            </c:if>
            <!-- FIM -->
            
            <!-- Exibir quando tiver conta criada -->
            <c:if test="${conta != null}">
            <div class="row">
              <div class="col">
                <div class="d-flex flex-column flex-sm-row gap-2 mt-3">
                  <a class="btn btn-lg btn-outline-primary flex-fill" href="transacao?action=extrato" role="button">
                    Ver extrato
                  </a>
                  <button class="btn btn-lg btn-primary flex-fill" type="button" data-bs-toggle="offcanvas" data-bs-target="#transacao" aria-controls="transacao"> 
                    Adicionar transa��o
                  </button>
                </div>
              </div>
            </div>
            </c:if>
            <!-- FIM -->
            
          </div>
        </div>
      </div>
    </div>

    <!-- Exibir quando tiver uma conta criada -->
    <c:if test="${conta != null}">
    <div class="position-fixed bottom-0 start-50 translate-middle-x">
      <div class="row badge bg-secondary mb-4 p-2 p-sm-3 d-sm-flex">
        <div class="col fs-5 fw-normal lh-sm">Saldo atual</div>
        <div class="col fs-5 fw-bold lh-sm">
          <fmt:formatNumber value = "${conta.saldo}" type = "currency"/>
        </div>
      </div>
    </div>
    </c:if>
    <!-- FIM -->
  </main>

 

  <footer class="background fixed-bottom container-fluid p-0 d-none d-lg-block">
    <div class="background__img">
    </div>
    <div class="background__wave background__wave--mask">
    </div>
  </footer>
  
    <!-- Offcanvas ADICIONAR TRANSA��O -->
    <div class="offcanvas offcanvas-end pe-4" tabindex="-1" id="transacao" aria-labelledby="transacaoLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="transacaoLabel">Adicionar transa��o</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <form class="row g-4 needs-validation" action="transacao" method="POST" novalidate>
          
          <input type="hidden" name="action" value="create" >
          <input type="hidden" name="conta" value="${conta.numConta}" >

          <div class="col-12">
            <label for="nome" class="form-label">Nome transa��o</label>
            <input type="text" class="form-control" id="nome" name="nome" maxlength="20" placeholder="Nome transa��o" required>
          </div>
          <div class="col-12">
            <label for="valor" class="form-label">Valor</label>
            <input type="number" class="form-control" id="valor" name="valor" maxlength="10" placeholder="Valor" required>
          </div>
          <c:if test="${investimentos != null && investimentos.size() > 0}">
          <div class="col-12">
            <label for="investimento" class="form-label">Investimento</label>
            <select class="form-select" id="investimento" name="investimento" aria-label="investimento">
              <option value="0" selected>-- Nenhum investimento --</option>
              <c:forEach var="invest" items="${investimentos}">
                <option value="${invest.codInvestimento}"> ${invest.nomeInvestimento} </option>
              </c:forEach>
            </select>
          </div>  
          </c:if>
          <!--  MELHORAR ESSA PARTE, S� APARECER OS TIPOS 3 E 4 SEEE INVESTIMENTO FOR SELECIONADO -->
          <div class="col-12">
            <label for="tipo" class="form-label">Isso � uma:</label>
            <select class="form-select" id="tipo" name="tipo" aria-label="tipo">
              <option value="${tipos[0].codTipo}" >${tipos[0].nomeTipo.toLowerCase()}</option>
              <option value="${tipos[1].codTipo}" >${tipos[1].nomeTipo.toLowerCase()}</option>
              <c:if test="${investimentos != null && investimentos.size() > 0}">
              <option value="${tipos[2].codTipo}" >${tipos[2].nomeTipo.toLowerCase()}</option>
              <option value="${tipos[3].codTipo}" >${tipos[3].nomeTipo.toLowerCase()}</option>
              </c:if>
            </select>
          </div>
          <div class="col-12">
            <label for="data" class="form-label">Data</label>
            <input type="date" class="form-control" id="data" name="data" required>
          </div>
          <div class="col-12">
            <label for="categoria" class="form-label">Categoria</label>
            <select class="form-select" id="categoria" name="categoria" aria-label="categoria">
              <c:forEach var="grupoCategoria" items="${grupoCategorias}">
                <span>grupoCategoria.nomeGrupo</span>
                <option disabled> ${grupoCategoria.nomeGrupo} </option>
                <c:forEach var="categoria" items="${grupoCategoria.categorias}">
                  <option value="${categoria.codCategoria}">${categoria.nomeCategoria}</option>
                </c:forEach>
              </c:forEach>
            </select>
          </div>
          <div class="col-12 pb-5 mb-5">
            <div class="form-floating">
              <textarea class="form-control" id="observacao" name="observacao" maxlength="60"></textarea>
              <label for="observacao">Observa��o</label>
            </div>
          </div>
  
          <div class="d-grid pb-3 col-12 position-absolute bottom-0">
            <button class="btn btn-primary btn-lg" type="submit">Salvar</button>
          </div>
        </form>
      </div>
    </div>
 
  
    <!-- Modal ADICIONAR CONTA -->
    <div class="modal fade" id="addContaModal" tabindex="-1" aria-labelledby="addContaModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5">Adicionar conta</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          
            <c:if test="${not empty error}">
              <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </c:if>
            
            <form id="addContaForm" class="row g-3 needs-validation" action="conta" method="POST" novalidate>
              <input type="hidden" name="cpf" value="${usuario.numCPF}">
              <!--  <input type="hidden" name="action" value="update" >  -->
                            
              <div class="col-12">
                <label for="nome" class="form-label">Nome da conta</label>
                <input type="text" class="form-control" id="nome" name="nome" maxlength="20" placeholder="Nomeie a sua conta" required>
                <div class="invalid-feedback">
                  Campo obrigat�rio, por mais rid�culo que seja.
                </div>
              </div>
              <div class="col-12">
                <label for="saldo" class="form-label">Saldo atual</label>
                <input type="number" class="form-control" id="saldo" name="saldo" maxlength="12" placeholder="R$" required>
                <div class="invalid-feedback">
                  Relaxa, n�o vamos contar pra ningu�m.
                </div>
              </div>
            </form>
            
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Mudei de ideia</button>
            <button class="btn btn-primary" form="addContaForm" type="submit">Adiciona a�, please!</button>
          </div>
        </div>
      </div>
    </div>
   
    
    <!-- Modal ADICIONAR INVESTIMENTO -->
    <div class="modal fade" id="addInvestimentoModal" tabindex="-1" aria-labelledby="addInvestimentoModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5">Adicionar investimento</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          
            <c:if test="${not empty error}">
              <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </c:if>
            
            <form id="addInvestimentoForm" class="row g-3 needs-validation" action="investimento" method="POST" novalidate>
              <input type="hidden" name="numConta" value="${conta.numConta}">
              <!--  <input type="hidden" name="action" value="update" >  -->
                            
              <div class="col-12">
                <label for="nome" class="form-label">Nome do investimento</label>
                <input type="text" class="form-control" id="nome" name="nome" maxlength="20" placeholder="Nomeie o seu investimento" required>
                <div class="invalid-feedback">
                  Campo obrigat�rio, por mais rid�culo que seja.
                </div>
              </div>
              <div class="col-12">
                <label for="saldo" class="form-label">Saldo atual</label>
                <input type="number" class="form-control" id="saldo" name="saldo" maxlength="12" placeholder="R$" required>
                <div class="invalid-feedback">
                  Relaxa, n�o vamos contar pra ningu�m.
                </div>
              </div>
              <div class="col-12">
                <label for="meta" class="form-label">Meta</label>
                <input type="number" class="form-control" id="meta" name="meta" maxlength="12" placeholder="R$" required>
                <div class="invalid-feedback">
                  Fique de olho no pr�mio, defina um objetivo.
                </div>
              </div>
            </form>
            
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Depois eu fa�o</button>
            <button class="btn btn-primary" form="addInvestimentoForm" type="submit">Vai dar certo, confia!</button>
          </div>
        </div>
      </div>
    </div>
 
 
    <!-- Modal DELETAR INVESTIMENTO -->
    <div class="modal fade" id="deleteInvestimentoModal" tabindex="-1" aria-labelledby="deleteInvestimentoModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5">Confirmar exclus�o</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          
            <c:if test="${not empty error}">
              <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </c:if>
            <p>Voc� est� prestes a me deletar :S</p>
          </div>
          <div class="modal-footer">
            <form id="deleteInvestimentoForm" class="row g-3 needs-validation" action="investimento" method="GET" novalidate>
              <input type="hidden" name="numConta" value="${conta.numConta}">
              <input type="hidden" name="codigo" id="codigo">
            </form>
            <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Deixa isso a�</button>
            <button class="btn btn-primary" form="deleteInvestimentoForm" type="submit">Isso mesmo, pode excluir!</button>
          </div>
        </div>
      </div>
    </div>    
  
    <script src="./_script/form-validation.js"></script>
  
</body>

</html>