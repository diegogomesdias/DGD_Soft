package javafxmvc.model.domain;

import java.io.Serializable;

public class Produto implements Serializable {

    private int cdProduto;
    private String nome;
    private String fornecedor;
    private String categoria;
    private int codbarras;
    private int estoqueMin;
    private double valorcusto;
    private double valorlucro;
    private double valorvenda;
    private int estoque;
    private String unidade;
    private String cnae;

    public Produto() {
    }

    public Produto(int cdProduto, String nome, String fornecedor,String categoria, int codBarras, int estoqueMin,
                    double valorcusto, double valorlucro,double valorvenda, int estoque,String unidade,String cnae ) 
    {
        this.cdProduto = cdProduto;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.codbarras = codBarras;
        this.estoqueMin = estoqueMin;
        this.valorcusto = valorcusto;
        this.valorlucro = valorlucro;
        this.valorvenda = valorvenda;
        this.estoque = estoque;
        this.unidade = unidade;
        this.cnae = cnae;
    }

    public int getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(int cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCodbarras() {
        return codbarras;
    }

    public void setCodbarras(int codbarras) {
        this.codbarras = codbarras;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }
    
    public double getValorcusto() {
        return valorcusto;
    }

    public void setValorcusto(double valorcusto) {
        this.valorcusto = valorcusto;
    }

    public double getValorlucro() {
        return valorlucro;
    }

    public void setValorlucro(double valorlucro) {
        this.valorlucro = valorlucro;
    }

    public double getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(double valorvenda) {
        this.valorvenda = valorvenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
