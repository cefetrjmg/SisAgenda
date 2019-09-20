/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.model;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author cristian
 */
public class Contato implements Comparable<Contato>{

    private int id;
    private String nome;
    private String email;
    private String tel;
    private String end;
    private Date dataNasc;
    private final boolean AUTO_INCREMENT=true;

    public boolean isAUTO_INCREMENT() {
        return AUTO_INCREMENT;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public int compareTo(Contato o) {
        return this.nome.compareToIgnoreCase(o.getNome());
    }

    

  

   

}
