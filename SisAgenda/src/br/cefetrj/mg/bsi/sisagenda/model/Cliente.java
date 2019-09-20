/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.model;

import java.util.Date;

/**
 *
 * @author cristian
 */
public class Cliente extends Contato{
    private Date dataUltimaCompra;
    private String tipoFidelidade;

    public Date getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(Date dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public String getTipoFidelidade() {
        return tipoFidelidade;
    }

    public void setTipoFidelidade(String tipoFidelidade) {
        this.tipoFidelidade = tipoFidelidade;
    }

  
    

    
}
