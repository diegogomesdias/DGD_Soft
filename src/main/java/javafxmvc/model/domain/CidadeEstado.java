package javafxmvc.model.domain;


public class CidadeEstado {

	private long cod_cidade;
	private long cod_estado;
	private String nom_cidade;
	private String estado;
        private String pais;
        
	public long getCod_cidade() {
		return cod_cidade;
	}
	public void setCod_cidade(long cod_cidade) {
		this.cod_cidade = cod_cidade;
	}
	public long getCod_estado() {
		return cod_estado;
	}
	public void setCod_estado(long cod_estado) {
		this.cod_estado = cod_estado;
	}
	public String getNom_cidade() {
		return nom_cidade;
	}
	public void setNom_cidade(String nom_cidade) {
		this.nom_cidade = nom_cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
        public String getpais() {
		return pais;
	}
	public void setpais(String pais) {
		this.pais = pais;
	}
        
	//public String toString(){  return this.nom_cidade;   }
	
    
}
