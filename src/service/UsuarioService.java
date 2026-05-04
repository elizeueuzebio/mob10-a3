package service;

import java.util.ArrayList;

import model.Usuario;

public class UsuarioService {
    private ArrayList<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrar(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    public ArrayList<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UsuarioService{"
                + "usuarios=" + usuarios
                + '}';
    }
}
