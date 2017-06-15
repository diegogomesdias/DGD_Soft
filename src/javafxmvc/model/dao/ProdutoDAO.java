package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.Categoria;
import javafxmvc.model.domain.Produto;

public class ProdutoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto(nome, fabricante, valorcusto, valorvenda,estoque,un,"
                + "cnae,codbarras,categoria,estoquemin) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getFornecedor());
            stmt.setDouble(3, produto.getValorcusto());
            stmt.setDouble(4, produto.getValorvenda());
            stmt.setInt(5, produto.getEstoque());
            stmt.setString(6, produto.getUnidade());
            stmt.setString(7, produto.getCnae());
            stmt.setInt(8, produto.getCodbarras());
            stmt.setString(9, produto.getCategoria());
            stmt.setInt(10, produto.getEstoqueMin());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome=?, fabricante=?, valorcusto=?, valorvenda=?,estoque=?,un=?,cnae=?,"
                + "codbarras=?,categoria=?,estoquemin=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getFornecedor());
            stmt.setDouble(3, produto.getValorcusto());
            stmt.setDouble(4, produto.getValorvenda());
            stmt.setInt(5, produto.getEstoque());
            stmt.setString(6, produto.getUnidade());
            stmt.setString(7, produto.getCnae());
            stmt.setInt(8, produto.getCodbarras());
            stmt.setString(9, produto.getCategoria());
            stmt.setInt(10, produto.getEstoqueMin());
            stmt.setInt(11, produto.getCdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Produto produto) {
        String sql = "DELETE FROM produto WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getCdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM produto";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                Categoria categoria = new Categoria();
                produto.setCdProduto(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setFornecedor(rs.getString("fabricante"));
                produto.setValorcusto(rs.getDouble("valorcusto"));
                produto.setValorlucro(rs.getDouble("valorlucro"));
                produto.setValorvenda(rs.getDouble("valorlucro"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setUnidade(rs.getString("un"));
                produto.setCnae(rs.getString("cnae"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setCodbarras(rs.getInt("codbarras"));
                produto.setEstoqueMin(rs.getInt("estoquemin"));
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Produto> listarPorCategoria(Categoria categoria) {
        String sql = "SELECT * FROM produtos WHERE cdCategoria=?";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getCdCategoria());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCdProduto(resultado.getInt("cdProduto"));
                produto.setNome(resultado.getString("nome"));
                //produto.setPreco(resultado.getDouble("preco"));
                //produto.setQuantidade(resultado.getInt("quantidade"));
                //categoria.setCdCategoria(resultado.getInt("cdCategoria"));
                //produto.setCategoria(categoria);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Produto buscar(Produto produto) {
        String sql = "SELECT * FROM produtos WHERE cdProduto=?";
        Produto retorno = new Produto();
        Categoria categoria = new Categoria();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getCdProduto());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setCdProduto(resultado.getInt("cdProduto"));
                retorno.setNome(resultado.getString("nome"));
                //retorno.setPreco(resultado.getDouble("preco"));
                //retorno.setQuantidade(resultado.getInt("quantidade"));
                //categoria.setCdCategoria(resultado.getInt("cdCategoria"));
                //retorno.setCategoria(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
