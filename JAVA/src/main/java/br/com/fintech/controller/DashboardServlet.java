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
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private TransacaoDAO transacaoDAO;
  private InvestimentoDAO investDAO;

  @Override
  public void init() throws ServletException {
    super.init();
    transacaoDAO = DAOFactory.getTransacaoDAO();
    investDAO = DAOFactory.getInvestimentoDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Conta conta = (Conta) session.getAttribute("conta");

    List<Transacao> ultimasTransacoes = transacaoDAO.getLastestTransacao(conta.getNumConta());
    session.setAttribute("ultimas", ultimasTransacoes);
    
    List<Investimento> invests = investDAO.getAllInvestimentoByConta(conta.getNumConta());
    session.setAttribute("investimentos", invests);

    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
  }

}