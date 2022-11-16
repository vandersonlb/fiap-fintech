package br.com.fintech.dao;

import br.com.fintech.bean.Usuario;

public interface UsuarioDAO {

  void createUsuario(Usuario usuario);

  Usuario getUsuario(Usuario usuario);

  void updateUsuario(Usuario usuario);

  Boolean authUsuario(Usuario usuario);

}
