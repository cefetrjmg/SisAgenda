/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.model;

/**
 *
 * @author cristian
 */
public class Fornecedor extends Contato{
    private String classificacao;
    private int indiceQuali;

    public int getIndiceQuali() {
        return indiceQuali;
    }

    public void setIndiceQuali(int indiceQuali) {
        this.indiceQuali = indiceQuali;
    }
    
    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

   
    
}
