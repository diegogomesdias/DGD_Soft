package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Cliente;

public class ClienteDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, cpf, rg,endereco,cidade,bairro,uf,telefone,sexo,"
                + "nacionalidade,estcivil) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEnd());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getUf());
            stmt.setString(8, cliente.getTelefone());
            //stmt.setString(9, cliente.getImagem());
            stmt.setString(9, cliente.getSexo());
            stmt.setString(10, cliente.getNacionalidade());
            //stmt.setDate(11, (Date) cliente.getNasc());
            stmt.setString(11, cliente.getEstCivil());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, rg=?,endereco=?,cidade=?bairro=?,uf=,telefone = ?,"
                + "sexo=?,nacionalidade+?,estcivil=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEnd());
            stmt.setString(5, cliente.getCidade());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getUf());
            stmt.setString(8, cliente.getTelefone());
            //stmt.setString(9, cliente.getImagem());
            stmt.setString(9, cliente.getSexo());
            stmt.setString(10, cliente.getNacionalidade());
            //stmt.setDate(11, (Date) cliente.getNasc());
            stmt.setString(11, cliente.getEstCivil());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getCdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCdCliente(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setRg(rs.getString("rg"));
		cliente.setEnd(rs.getString("endereco"));
		cliente.setCidade(rs.getString("cidade"));
		cliente.setBairro(rs.getString("bairro"));
		cliente.setUf(rs.getString("uf"));
		cliente.setTelefone(rs.getString("telefone"));
                cliente.setImagem(rs.getString("imagem"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setNacionalidade(rs.getString("nacionalidade"));
                cliente.setCep(rs.getString("cep"));
                //cliente.setNasc(rs.getDate("nasc"));
                cliente.setEstCivil(rs.getString("estcivil"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM clientes WHERE cdCliente=?";
        Cliente retorno = new Cliente();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getCdCliente());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setTelefone(resultado.getString("telefone"));
                retorno = cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
