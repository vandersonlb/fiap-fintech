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

import br.com.fintech.bean.GrupoCategoria;
import br.com.fintech.bean.Tipo;
import br.com.fintech.bean.Usuario;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/user")
public class UsuarioServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private UsuarioDAO usuarioDAO;
  private TipoDAO tipoDAO;
  private CategoriaDAO categoriaDAO;
  private Usuario usuario;

  @Override
  public void init() throws ServletException {
    super.init();
    usuarioDAO = DAOFactory.getUsuarioDAO();
    tipoDAO = DAOFactory.getTipoDAO();
    categoriaDAO = DAOFactory.getCategoriaoDAO();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {

      HttpSession session = request.getSession();

      String nome = request.getParameter("nome");
      String celular = request.getParameter("tel");

      Calendar dataNasc = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      dataNasc.setTime(sdf.parse(request.getParameter("date")));

      Usuario usuarioTemp = new Usuario(request.getParameter("email"), "000000");
      usuario = usuarioDAO.getUsuario(usuarioTemp);
      usuario.setNome(nome);
      usuario.setCelular(celular);
      usuario.setDataNasc(dataNasc);

      usuarioDAO.updateUsuario(usuario);

      session.setAttribute("usuario", usuario);
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

      String nome = request.getParameter("nome");
      String email = request.getParameter("email");
      String celular = request.getParameter("tel");

      Calendar dataNasc = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      dataNasc.setTime(sdf.parse(request.getParameter("date")));

      String cpf = request.getParameter("cpf");
      String senha = request.getParameter("password");

      usuario = new Usuario(Long.parseLong(cpf), nome, dataNasc, email, celular, senha);

      usuarioDAO.createUsuario(usuario);

      session.setAttribute("usuario", usuario);

      List<GrupoCategoria> grupoCategorias = categoriaDAO.getAllCategoriaGrouping();
      session.setAttribute("grupoCategorias", grupoCategorias);

      List<Tipo> tipos = tipoDAO.getAllTipo();
      session.setAttribute("tipos", tipos);

      request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "Algo errado não tá certo!");
      request.getRequestDispatcher("cadastro.jsp").forward(request, response);
    }

  }

}
