package javafxmvc.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Cliente {

    private int cdCliente;
    private String nome;
    private Date nasc;
    private String cpf;
    private String rg;
    private String sexo;
    private String nacionalidade;
    private String end;
    private Long   NumeroEnd;
    private String cidade;
    private String cep;
    private String bairro;
    private String uf;
    private String telefone;
    private String estcivil;
    private String imagem;

        public Cliente(){
        }

        public Cliente(int cdCliente, String nome, String cpf) {
            this.cdCliente = cdCliente;
            this.nome = nome;
            this.cpf = cpf;
        }

        public int getCdCliente() {
            return cdCliente;
        }

        public void setCdCliente(int cdCliente) {
            this.cdCliente = cdCliente;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        //nasc
        public Date getNasc(){
                    return this.nasc;
        }
        public void setNasc(Date novo){
                    this.nasc = novo;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
    
        //rg
	public String getRg(){
		return this.rg;
	}
	public void setRg(String novo){
		this.rg = novo;
	}
        
	//sexo
	public String getSexo(){
		return this.sexo;
	}
	public void setSexo(String novo){
		this.sexo = novo;
	}
        
	//end
	public String getEnd(){
		return this.end;
	}
	public void setEnd(String novo){
		this.end = novo;
	}
        
	//NumeroEnd
	public Long getNumeroEnd(){
		return this.NumeroEnd;
	}
	public void setNumeroEnd(long novo){
		this.NumeroEnd = novo;
	}
        
	//cidade
	public String getCidade(){
		return this.cidade;
	}
	public void setCidade(String novo){
		this.cidade = novo;
	}
        
	//cep
	public String getCep(){
		return this.cep;
	}
	public void setCep(String novo){
		this.cep = novo;
	}
        
	//bairro
	public String getBairro(){
		return this.bairro;
	}
	public void setBairro(String novo){
		this.bairro = novo;
	}
        
	//uf
	public String getUf(){
		return this.uf;
	}
	public void setUf(String novo){
		this.uf = novo;
	}
        
        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        //patch
	public String getImagem(){
		return this.imagem;
	}
	public void setImagem(String novo){
		this.imagem = novo;
	}
        
        //Nacionalidade
	public String getNacionalidade(){
		return this.nacionalidade;
	}
	public void setNacionalidade(String novo){
		this.nacionalidade = novo;
	}
        
         //Estado Civil
	public String getEstCivil(){
		return this.estcivil;
	}
	public void setEstCivil(String novo){
		this.estcivil = novo;
	}
        
        @Override
        public String toString() {
            return this.nome;
        }
    
}
