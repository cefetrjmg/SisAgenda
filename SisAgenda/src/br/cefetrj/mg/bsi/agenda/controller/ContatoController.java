/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.controller;

import br.cefetrj.mg.bsi.agenda.config.Settings;
import br.cefetrj.mg.bsi.agenda.dao.ContatoDAO;
import br.cefetrj.mg.bsi.agenda.model.Contato;
import br.cefetrj.mg.bsi.agenda.view.FormContato;
import br.cefetrj.mg.bsi.utils.Utils;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristian
 */
public class ContatoController implements Controller {

    private FormContato form;
    private Contato c = null;
    private static ContatoDAO dao;
    private String msg="";
    public void setForm(FormContato form) {
        this.form = form;
    }

    public static ContatoDAO getDao() {
        if (dao == null) {
            dao = new ContatoDAO();
        }
        return dao;
    }

   
  
    @Override
    public boolean inserir() {
        try {
            c = new Contato();
            c.setEmail(form.getjTxtEmail().getText());
            c.setEnd(form.getjTxtEnd().getText());
            c.setNome(form.getjTxtNome().getText());
            c.setTel(form.getjTxtTel().getText());
            c.setDataNasc(Utils.textToDate(form.getjTxtDataNasc().getText()));
            getDao().inserir(c);
           
        } catch (ParseException ex) {
            Settings.status=false;
            Settings.msg=ex.getMessage();
        }
         return Settings.status;
    }

    @Override
    public boolean atualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
