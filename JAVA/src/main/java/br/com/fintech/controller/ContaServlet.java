package br.com.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Transacao;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ContaDAO contaDAO;

  @Override
  public void init() throws ServletException {
    super.init();
    contaDAO = DAOFactory.getContaDAO();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {

//      Map<String, String[]> params = request.getParameterMap();
//      params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));

      HttpSession session = request.getSession();

      long userCPF = Long.parseLong(request.getParameter("cpf"));
      String nomeConta = request.getParameter("nome");
      double saldoConta = Double.parseDouble(request.getParameter("saldo"));

      Conta conta = new Conta(0, userCPF, nomeConta, saldoConta);
      contaDAO.createConta(conta);

      List<Conta> contas = contaDAO.getAllConta(userCPF);
      if (contas.size() > 0) {
        conta = contas.get(0);
      }

      session.setAttribute("conta", conta);
      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }

  }

}
