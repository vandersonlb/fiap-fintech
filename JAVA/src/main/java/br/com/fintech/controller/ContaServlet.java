package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Conta;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ContaDAO contaDAO;

  /**
  public ContaServlet() {
    super();
    contaDAO = DAOFactory.getContaDAO();
  }
  **/
  
  @Override
  public void init() throws ServletException {
    super.init();
    contaDAO = DAOFactory.getContaDAO();
  }

//  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
//    response.getWriter().append("Served at: ").append(request.getContextPath());
//  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      
      HttpSession session = request.getSession();

//      Map<String, String[]> params = request.getParameterMap();
//      params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));

      long userCPF = Long.parseLong(request.getParameter("cpf"));
      String nomeConta = request.getParameter("nome");
      double saldoConta = Double.parseDouble(request.getParameter("saldo"));

//      Usuario usuario = new Usuario(userEmail, "00000");
//      Conta(int numConta, long numCPF, String nomeConta, double saldo)
      
      Conta conta = new Conta(0, userCPF, nomeConta, saldoConta);
      contaDAO.createConta(conta);
      
      session.setAttribute("conta", conta);
      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

//      contaDAO.createConta(null);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }

  }

}
