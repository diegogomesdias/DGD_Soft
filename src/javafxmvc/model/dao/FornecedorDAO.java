package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafxmvc.model.domain.Fornecedor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FornecedorDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
	
    int tipo = 1;
	
    public boolean inserir(Fornecedor fornecedor){
	String insert = "insert into empresa (fantasia,rsocial,cnpj,incsocial,tipoempresa,"
                        + "endereco,numero,complemento,bairro,cep,telefone,cidade,email,uf,imagem) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+tipo+",?)";
	try{
            PreparedStatement stmt = connection.prepareStatement(insert);
            stmt.setString(1,fornecedor.getFantasia());
            stmt.setString(2,fornecedor.getRsocial());
            stmt.setString(3,String.valueOf(fornecedor.getCnpj()));
            stmt.setString(4,String.valueOf(fornecedor.getIncsocial()));
            stmt.setString(5,String.valueOf(fornecedor.getTipoempresa()));
            stmt.setString(6,fornecedor.getEndereco());
            stmt.setString(7,String.valueOf(fornecedor.getNumero()));
            stmt.setString(8,String.valueOf(fornecedor.getComplemento()));
            stmt.setString(9,fornecedor.getBairro());
            stmt.setString(10,fornecedor.getCep());
            stmt.setString(11,fornecedor.getTelefone());
            stmt.setString(12,fornecedor.getCidade());
            stmt.setString(13,fornecedor.getEmail());
            stmt.setString(14,fornecedor.getUf());
            stmt.setString(15,fornecedor.getImagem());
            stmt.execute();
            return true;
	} catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }	
    }
    
    public boolean alterar(Fornecedor fornecedor) {
	String update = "update empresa set "
                    + "fantasia = ?,"
                    + "rsocial = ?,"
                    + "cnpj = ?,"
                    + "incsocial = ?,"
                    + "tipoempresa = ?,"
                    + "endereco = ?,"
                    + "numero = ?,"
                    + "complemento = ?,"
                    + "bairro = ?,"
                    + "cep = ?,"
                    + "telefone = ?,"
                    + "cidade = ?,"
                    + "email = ?,"
                    + "uf = ? ,"
                    + "imagem = ?, "
                    + "tipo = "+tipo+ ""
                    + "where id = ?  and tipo =  "+tipo+"";
	try {
            PreparedStatement stmt = connection.prepareStatement(update); 
            stmt.setString(1,fornecedor.getFantasia());
            stmt.setString(2,fornecedor.getRsocial());
            stmt.setString(3,String.valueOf(fornecedor.getCnpj()));
            stmt.setString(4,String.valueOf(fornecedor.getIncsocial()));
            stmt.setString(5,String.valueOf(fornecedor.getTipoempresa()));
            stmt.setString(6,fornecedor.getEndereco());
            stmt.setString(7,String.valueOf(fornecedor.getNumero()));
            stmt.setString(8,String.valueOf(fornecedor.getComplemento()));
            stmt.setString(9,fornecedor.getBairro());
            stmt.setString(10,fornecedor.getCep());
            stmt.setString(11,fornecedor.getTelefone());
            stmt.setString(12,fornecedor.getCidade());
            stmt.setString(13,fornecedor.getEmail());
            stmt.setString(14,fornecedor.getUf());
            stmt.setString(15,fornecedor.getImagem());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(Fornecedor fornecedor) {
        String sql = "DELETE FROM empresa WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,fornecedor.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Fornecedor> listar() {
	List<Fornecedor> retorno = new ArrayList<>();
	String select = "select * from empresa where tipo = "+tipo+" order by fantasia";
	try {
            PreparedStatement stmt = getConnection().prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(rs.getInt("id"));
		fornecedor.setFantasia(rs.getString("fantasia"));
		fornecedor.setRsocial(rs.getString("rsocial"));
		fornecedor.setCnpj(rs.getInt("cnpj"));
		fornecedor.setIncsocial(rs.getInt("incsocial"));
		fornecedor.setTipoempresa(rs.getInt("tipoempresa"));
		fornecedor.setEndereco(rs.getString("endereco"));
		fornecedor.setNumero(rs.getInt("numero"));
		fornecedor.setComplemento(rs.getInt("complemento"));
		fornecedor.setBairro(rs.getString("bairro"));
		fornecedor.setCep(rs.getString("cep"));
		fornecedor.setTelefone(rs.getString("telefone"));
		fornecedor.setCidade(rs.getString("cidade"));
		fornecedor.setEmail(rs.getString("email"));
		fornecedor.setUf(rs.getString("uf"));
		fornecedor.setImagem(rs.getString("imagem"));
		retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	return retorno;
    }
}
