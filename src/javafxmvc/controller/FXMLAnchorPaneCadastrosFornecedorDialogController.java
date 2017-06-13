
package javafxmvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmvc.model.domain.Fornecedor;

public class FXMLAnchorPaneCadastrosFornecedorDialogController implements Initializable {

    @FXML private TextField FornecedorNomeFantasia;
    @FXML private TextField FornecedorRazaoSocial;
    @FXML private TextField FornecedorCnpj;
    @FXML private TextField FornecedorInscSocial;
    @FXML private TextField FornecedorEndereco;
    @FXML private TextField FornecedorNumero;
    @FXML private TextField FornecedorBairro;
    @FXML private TextField FornecedorEmail;
    @FXML private TextField FornecedorCep;
    @FXML private TextField FornecedorTelefone;
    @FXML private ComboBox boxTipoEmpresa;
    @FXML private ComboBox boxUF;
    @FXML private ComboBox boxCidade;
            
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Fornecedor fornecedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    public Fornecedor getCliente() {
        return fornecedor;
    }
    
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
