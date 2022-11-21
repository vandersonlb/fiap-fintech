package br.com.fintech.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.Conta;
import br.com.fintech.bean.GrupoCategoria;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Tipo;
import br.com.fintech.bean.Transacao;
import br.com.fintech.bean.Usuario;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/login")
public class LoginServelt extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private UsuarioDAO usuarioDAO;
  private CategoriaDAO categoriaDAO;
  private ContaDAO contaDAO;
  private InvestimentoDAO investDAO;
  private TipoDAO tipoDAO;
  private TransacaoDAO transacaoDAO;

//  private Usuario usuario;
//  private Categoria categoria;
//  private Tipo tipo;
//  private Conta conta;
//  private Investimento invest;
//  private Transacao transac;

  /**
   * public LoginServelt() { super(); usuarioDAO = DAOFactory.getUsuarioDAO(); //
   * categoriaDAO = DAOFactory.getCategoriaoDAO(); // tipoDAO =
   * DAOFactory.getTipoDAO(); contaDAO = DAOFactory.getContaDAO(); investDAO =
   * DAOFactory.getInvestimentoDAO(); transacaoDAO = DAOFactory.getTransacaoDAO();
   * }
   **/

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
    HttpSession session = request.getSession();
    session.invalidate();
    request.getRequestDispatcher("login.jsp").forward(request, response);
//    return;
//		response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    long tempoInicial = System.currentTimeMillis();

    String email = request.getParameter("email");
    String senha = request.getParameter("password");

    Usuario usuario = new Usuario(email, senha);

    if (usuarioDAO.authUsuario(usuario)) {
      HttpSession session = request.getSession();

      usuario = usuarioDAO.getUsuario(usuario);
      session.setAttribute("usuario", usuario);
      
      List<GrupoCategoria> grupoCategorias = categoriaDAO.getAllCategoriaGrouping();
      session.setAttribute("grupoCategorias", grupoCategorias);
      
      List<Tipo> tipos = tipoDAO.getAllTipo();
      session.setAttribute("tipos", tipos);

      List<Conta> contas = contaDAO.getAllConta(usuario.getNumCPF());
      if (contas.size() > 0) {

        Conta conta = contas.get(0);
        List<Investimento> invests = investDAO.getAllInvestimentoByConta(conta.getNumConta());
        List<Transacao> ultimasTransacoes = transacaoDAO.getLastestTransacao(conta.getNumConta());
        
//        List<Transacao> extrato = transacaoDAO.getAllTransacao(conta.getNumConta());

        session.setAttribute("conta", conta);
        session.setAttribute("investimentos", invests);
        session.setAttribute("ultimas", ultimasTransacoes);
//        session.setAttribute("extrato", extrato);

      }

//      System.out.println("DEU BOA AQUI");
//      List<GrupoCategoria> grupoCategorias = categoriaDAO.getAllCategoriaGrouping();
//      List<Tipo> tipos = tipoDAO.getAllTipo();

//      session.setAttribute("grupoCategorias", grupoCategorias);
//      session.setAttribute("tipos", tipos);

      // Map<String, String[]> params = request.getParameterMap();
//    params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));
      
      long tempoFinal = System.currentTimeMillis();

      System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);

      request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    } else {
//      System.out.println("DEU RUIM AQUI");
      request.setAttribute("error", "Usuário ou senha incorreto. Suspeito!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
  }

}
