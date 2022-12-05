package br.com.fintech.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.fintech.bean.Usuario;

@WebFilter("/*")
public class LoginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    String url = req.getRequestURI();

    if (!url.endsWith("Fintech/") && !url.contains("login") && !url.contains("cadastro")) {

      Usuario usuario = (Usuario) session.getAttribute("usuario");

      if (usuario == null) {

        request.setAttribute("error", "Entre com o usuário e senha!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
      } else {
        chain.doFilter(request, response);
      }

    } else {
      chain.doFilter(request, response);
    }

  }

}