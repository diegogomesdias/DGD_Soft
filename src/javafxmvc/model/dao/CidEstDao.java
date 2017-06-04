    package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.CidadeEstado;


public class CidEstDao {
	
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<String> listaEstados() {
	String sql = "select nom_estado from estado order by nom_estado";
	List<String> listaEstado = new ArrayList<String>();//Crie uma lista para armazenar os dados do banco
		
            try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();			
		while(result.next()) {
		listaEstado.add(result.getString("nom_estado"));//Adicione cada linha retornada do banco
                }			
                    result.close();
                    statement.close();
            } catch (SQLException e) {
            }	
		return listaEstado;//Retorne a lista de String com todos os nome do banco de dados
    }
	
        public List<String> listaPais() {
		String sql = "select nom_pais from pais order by nom_pais";
		List<String> listaPaises = new ArrayList<String>();//Crie uma lista para armazenar os dados do banco
			
		try {
                    try (PreparedStatement statement = connection.prepareStatement(sql); 
                            ResultSet result = statement.executeQuery()) {
                        while(result.next()) {
                            listaPaises.add(result.getString("nom_pais"));//Adicione cada linha retornada do banco
                        }
                    }
		} catch (SQLException e) {
            }	
            return listaPaises;//Retorne a lista de String com todos os nome do banco de dados
	}
   
	public List<CidadeEstado> getCidadeByEstado(CidadeEstado cid) throws Exception {
            String select = "SELECT nom_cidade FROM cidade WHERE estado = ?";
            CidadeEstado cidade = null;
            PreparedStatement stmt = getConnection().prepareStatement(select);
            List<CidadeEstado> cidades = new ArrayList<>();	
            stmt.setString(1, cid.getEstado());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cidade = new CidadeEstado();
                cidade.setNom_cidade(rs.getString("nom_cidade"));
                cidades.add(cidade);
            }
            rs.close();
            stmt.close();
            return cidades;
        }

    
        
}
