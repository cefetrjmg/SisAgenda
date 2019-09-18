/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.controller;

/**
 *
 * @author cristian
 */
public interface Controller {
    public boolean inserir();
    public boolean atualizar();
    public boolean excluir();
    public boolean buscar();
    public boolean listar();
    
}
