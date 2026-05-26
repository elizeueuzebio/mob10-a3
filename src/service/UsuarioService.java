package service;

import java.util.ArrayList;

import model.Motorista;
import model.Passageiro;
import model.Usuario;

public class UsuarioService {
    private ArrayList<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    // O cadastro protege a lista de IDs duplicados.
    public boolean cadastrar(Usuario usuario) {
        if (usuario == null || buscarPorId(usuario.getId()) != null) {
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    public int cadastrar(ArrayList<? extends Usuario> novosUsuarios) {
        int totalCadastrado = 0;

        if (novosUsuarios == null) {
            return totalCadastrado;
        }

        for (Usuario usuario : novosUsuarios) {
            if (cadastrar(usuario)) {
                totalCadastrado++;
            }
        }

        return totalCadastrado;
    }

    public boolean atualizar(Usuario usuarioAtualizado) {
        if (usuarioAtualizado == null) {
            return false;
        }

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuarioAtualizado.getId()) {
                usuarios.set(i, usuarioAtualizado);
                return true;
            }
        }

        return false;
    }

    public boolean removerPorId(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null) {
            return false;
        }
        return usuarios.remove(usuario);
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

    public Motorista buscarMotoristaPorId(int id) {
        Usuario usuario = buscarPorId(id);
        return usuario instanceof Motorista ? (Motorista) usuario : null;
    }

    public Passageiro buscarPassageiroPorId(int id) {
        Usuario usuario = buscarPorId(id);
        return usuario instanceof Passageiro ? (Passageiro) usuario : null;
    }

    public ArrayList<Motorista> listarMotoristas() {
        ArrayList<Motorista> motoristas = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Motorista) {
                motoristas.add((Motorista) usuario);
            }
        }
        return motoristas;
    }

    public ArrayList<Motorista> listarMotoristasDisponiveis() {
        ArrayList<Motorista> motoristasDisponiveis = new ArrayList<>();
        for (Motorista motorista : listarMotoristas()) {
            if (motorista.isDisponibilidade()) {
                motoristasDisponiveis.add(motorista);
            }
        }
        return motoristasDisponiveis;
    }

    public ArrayList<Passageiro> listarPassageiros() {
        ArrayList<Passageiro> passageiros = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Passageiro) {
                passageiros.add((Passageiro) usuario);
            }
        }
        return passageiros;
    }

    @Override
    public String toString() {
        return "UsuarioService{"
                + "usuarios=" + usuarios
                + '}';
    }
}
