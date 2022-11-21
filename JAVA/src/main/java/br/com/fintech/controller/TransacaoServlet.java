package br.com.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Tipo;
import br.com.fintech.bean.Transacao;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private UsuarioDAO usuarioDAO;
  private CategoriaDAO categoriaDAO;
  private ContaDAO contaDAO;
  private InvestimentoDAO investDAO;
  private TipoDAO tipoDAO;
  private TransacaoDAO transacaoDAO;

  @Override
  public void init() throws ServletException {
    super.init();
    usuarioDAO = DAOFactory.getUsuarioDAO();
    categoriaDAO = DAOFactory.getCategoriaoDAO();
    tipoDAO = DAOFactory.getTipoDAO();
    contaDAO = DAOFactory.getContaDAO();
    investDAO = DAOFactory.getInvestimentoDAO();
    transacaoDAO = DAOFactory.getTransacaoDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      
      HttpSession session = request.getSession();
      
      int numConta = Integer.parseInt(request.getParameter("conta"));
      String nomeTransacao = request.getParameter("nome");
      double valor = Double.parseDouble(request.getParameter("valor"));
      int numInvestimento = Integer.parseInt(request.getParameter("investimento"));
      int codTipo = Integer.parseInt(request.getParameter("tipo"));
      Calendar data = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      data.setTime(sdf.parse(request.getParameter("data")));
      int codCategoria = Integer.parseInt(request.getParameter("categoria"));
      String obs = request.getParameter("observacao");
            
      Conta conta = contaDAO.getConta(numConta);
      Investimento invest = null;
      if (numInvestimento > 0) {
        invest = investDAO.getInvestimento(numInvestimento);
      }
      Tipo tipo = tipoDAO.getTipo(codTipo);
      Categoria categoria = categoriaDAO.getCategoria(codCategoria);

      transacaoDAO.createTransacao(new Transacao(conta, 0, nomeTransacao, invest, tipo, valor, data, categoria, obs));

//      Map<String, String[]> params = request.getParameterMap();
//      params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));
      
      List<Investimento> invests = investDAO.getAllInvestimentoByConta(conta.getNumConta());
      List<Transacao> ultimasTransacoes = transacaoDAO.getLastestTransacao(conta.getNumConta());

      session.setAttribute("conta", conta);
      session.setAttribute("investimentos", invests);
      session.setAttribute("ultimas", ultimasTransacoes);
      
      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    


  }

}
