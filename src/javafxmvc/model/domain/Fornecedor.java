package javafxmvc.model.domain;


public class Fornecedor {

	private int id;
	private String fantasia;
	private String rsocial;
	private int cnpj;
	private int incsocial;
	private int tipoempresa;
	private String endereco;
	private int numero;
	private int complemento;
	private String bairro;
	private String cep;
	private String telefone;
	private String cidade;
	private String email;
	private String uf;
	private String  imagem;
        
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getRsocial() {
		return rsocial;
	}
	public void setRsocial(String rsocial) {
		this.rsocial = rsocial;
	}
	public int getCnpj() {
		return cnpj;
	}
	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}
	public int getIncsocial() {
		return incsocial;
	}
	public void setIncsocial(int incsocial) {
		this.incsocial = incsocial;
	}
	public int getTipoempresa() {
		return tipoempresa;
	}
	public void setTipoempresa(int tipoempresa) {
		this.tipoempresa = tipoempresa;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getComplemento() {
		return complemento;
	}
	public void setComplemento(int complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	//patch
	public String getImagem(){
		return this.imagem;
	}
	public void setImagem(String novo){
		this.imagem = novo;
	}
}
