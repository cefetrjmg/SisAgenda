/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.model;

/**
 *
 * @author cristian
 */
public enum Fidelidade {
    FIEL("Fiel"), FREQUENTE("Frequente"), POUCO_FREQUENTE("Pouco Frequente"), UMA_VEZ("Uma vez");
    private String valor;
    
    Fidelidade(String valor){
        this.valor=valor;
    }
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
