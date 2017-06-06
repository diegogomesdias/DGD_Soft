package javafxmvc.model.domain;

import java.sql.Date;

public class CompraVenda {

    private long id;
    private int numeroNota;
    private String tipoMovimento;
    private int serie;
    private String Fornecedor;
    private String FormaPag;
    private String Produto;
    private double preco;
    private int desconto;
    private int estoque;
    private double valortotal;    
    private String DtEntrada;
    private String DtNegociacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(int numeroNota) {
        this.numeroNota = numeroNota;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getFormaPag() {
        return FormaPag;
    }

    public void setFormaPag(String FormaPag) {
        this.FormaPag = FormaPag;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String Produto) {
        this.Produto = Produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public String getDtEntrada() {
        return DtEntrada;
    }

    public void setDtEntrada(String DtEntrada) {
        this.DtEntrada = DtEntrada;
    }

     public String getDtNegociacao() {
        return DtNegociacao;
    }

    public void setDtNegociacao(String DtNegociacao) {
        this.DtNegociacao = DtNegociacao;
    }
}

