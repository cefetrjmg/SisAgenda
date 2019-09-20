/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.dao;

import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public interface DAO {
    public boolean inserir(Object o);
    public boolean atualizar(Object newObject, int id);
    public boolean excluir(Object o);
    public Object buscar(Object o);
    public ArrayList<Object> listar();
    
}
