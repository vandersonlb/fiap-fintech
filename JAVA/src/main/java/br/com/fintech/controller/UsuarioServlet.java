package br.com.fintech.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Usuario;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/user")
public class UsuarioServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private UsuarioDAO usuarioDAO;
  private Usuario usuario;

  public UsuarioServlet() {
    super();
    usuarioDAO = DAOFactory.getUsuarioDAO();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      
      String nome = request.getParameter("nome");
      String celular = request.getParameter("tel");
      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      Calendar dataNasc = Calendar.getInstance();
      try {
        dataNasc.setTime(format.parse(request.getParameter("date")));
      } catch (ParseException e) {
        e.printStackTrace();
      }

      Usuario usuarioTemp = new Usuario(request.getParameter("email"), "000000");
      usuario = usuarioDAO.getUsuario(usuarioTemp);
      usuario.setNome(nome);
      usuario.setCelular(celular);
      usuario.setDataNasc(dataNasc);
      
      usuarioDAO.updateUsuario(usuario);

//      Map<String, String[]> params = request.getParameterMap();
//      params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));
      
      request.setAttribute("error", "Entre novamente, fique à vontade.");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      String nome = request.getParameter("nome");
      String email = request.getParameter("email");
      String celular = request.getParameter("tel");

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      Calendar dataNasc = Calendar.getInstance();
      try {
        dataNasc.setTime(format.parse(request.getParameter("date")));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      String cpf = request.getParameter("cpf");
      String senha = request.getParameter("password");

      usuario = new Usuario(Long.parseLong(cpf), nome, dataNasc, email, celular, senha);

      System.out.println(usuario);

      usuarioDAO.createUsuario(usuario);

      HttpSession session = request.getSession();
      session.setAttribute("usuario", usuario);

      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

//    Map<String, String[]> params = request.getParameterMap();
//    params.forEach((k, v) -> System.out.println((k.toString() + ":" + v[0])));

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("cadastro.jsp").forward(request, response);
    }

  }

}
