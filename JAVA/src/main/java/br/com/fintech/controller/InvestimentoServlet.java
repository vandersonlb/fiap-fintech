package br.com.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Investimento;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private InvestimentoDAO investDAO;

  @Override
  public void init() throws ServletException {
    super.init();
    investDAO = DAOFactory.getInvestimentoDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {

//    Map<String, String[]> params = request.getParameterMap();
//    params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));

      HttpSession session = request.getSession();

      int codigo = Integer.parseInt(request.getParameter("codigo"));
      int numConta = Integer.parseInt(request.getParameter("numConta"));

      investDAO.deleteInvestimento(codigo);

      List<Investimento> invests = investDAO.getAllInvestimentoByConta(numConta);

      session.setAttribute("investimentos", invests);
      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {

//      Map<String, String[]> params = request.getParameterMap();
//      params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));

      HttpSession session = request.getSession();

      int numConta = Integer.parseInt(request.getParameter("numConta"));
      String nomeInvest = request.getParameter("nome");
      double saldo = Double.parseDouble(request.getParameter("saldo"));
      double meta = Double.parseDouble(request.getParameter("meta"));

      Investimento invest = new Investimento(numConta, 0, nomeInvest, saldo, meta);
      investDAO.createInvestimento(invest);

      List<Investimento> invests = investDAO.getAllInvestimentoByConta(numConta);

      session.setAttribute("investimentos", invests);
      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }

  }

}
