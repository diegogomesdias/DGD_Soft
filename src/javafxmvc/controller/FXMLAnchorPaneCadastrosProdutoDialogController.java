/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafxmvc.model.domain.Fornecedor;
import javafxmvc.model.domain.Produto;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class FXMLAnchorPaneCadastrosProdutoDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Produto produto;
    
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

    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
