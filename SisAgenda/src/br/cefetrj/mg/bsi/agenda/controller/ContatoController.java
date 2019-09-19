/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.controller;

import br.cefetrj.mg.bsi.agenda.config.Settings;
import br.cefetrj.mg.bsi.agenda.dao.ContatoDAO;
import br.cefetrj.mg.bsi.agenda.model.Cliente;
import br.cefetrj.mg.bsi.agenda.model.Contato;
import br.cefetrj.mg.bsi.agenda.model.Fornecedor;
import br.cefetrj.mg.bsi.agenda.model.TableModel;
import br.cefetrj.mg.bsi.agenda.view.FormContato;
import br.cefetrj.mg.bsi.utils.Utils;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author cristian
 */
public class ContatoController implements Controller {

    private FormContato form;
    private Contato c = null;
    private Cliente cli = null;
    private Fornecedor f = null;
    private static ContatoDAO dao;
    private String msg = "";
    private JTable table;
    private TableModel model;

    public static ContatoDAO getDao() {
        if (dao == null) {
            dao = new ContatoDAO();
        }
        return dao;
    }

    public ContatoController(FormContato form) {
        this.form = form;
        this.carregarArquivo();

    }

    @Override
    public boolean inserir() {
        try {
            switch (form.getjCboTipo().getSelectedIndex()) {
                case 0:
                    c = new Contato();
                    break;
                case 1:
                    cli = new Cliente();
                    cli.setDataUltimaCompra(Utils.textToDate(form.getjTxtDtUltimaCompra().getText()));
                    cli.setTipoFidelidade(form.getjCboClasFidelidade().getSelectedItem().toString());
                    c = cli;
                    break;
                case 2:
                    f = new Fornecedor();
                    f.setIndiceQuali((int) form.getjSpnIndice().getValue());
                    
                    c = f;
                    break;

            }
            c.setEmail(form.getjTxtEmail().getText());
            c.setEnd(form.getjTxtEnd().getText());
            c.setNome(form.getjTxtNome().getText());
            c.setTel(form.getjTxtTel().getText());
            c.setDataNasc(Utils.textToDate(form.getjTxtDataNasc().getText()));

            getDao().inserir(c);
            renderizar(getDao().getContatos());
            form.getjTpnContato().setSelectedIndex(1);
        } catch (ParseException ex) {
            Settings.status = false;
            Settings.msg = ex.getMessage();
        }
        return Settings.status;
    }

    @Override
    public boolean atualizar() {

        try {
            switch (form.getjCboTipo().getSelectedIndex()) {
                case 0:
                    c = new Contato();
                    break;
                case 1:
                    cli = new Cliente();
                    cli.setDataUltimaCompra(Utils.textToDate(form.getjTxtDtUltimaCompra().getText()));
                    cli.setTipoFidelidade(form.getjCboClasFidelidade().getSelectedItem().toString());
                    c = cli;
                    break;
                case 2:
                    f = new Fornecedor();
                    f.setIndiceQuali((int) form.getjSpnIndice().getValue());
                    c = f;
                    break;
            }

            c.setEmail(form.getjTxtEmail().getText());
            c.setEnd(form.getjTxtEnd().getText());
            c.setNome(form.getjTxtNome().getText());
            c.setTel(form.getjTxtTel().getText());
            c.setDataNasc(Utils.textToDate(form.getjTxtDataNasc().getText()));
            c.setId(Integer.parseInt(form.getjTxtId().getText()));
            getDao().atualizar(c, c.getId());
            renderizar(getDao().getContatos());
            form.getjTpnContato().setSelectedIndex(1);
        } catch (ParseException ex) {
            Settings.status = false;
            Settings.msg = ex.getMessage();
        }
        return Settings.status;
    }

    @Override
    public boolean excluir() {
        if (!form.getjTxtEmail().getText().isEmpty()) {
            c = new Contato();
            c.setEmail(form.getjTxtEmail().getText());
            getDao().excluir(c);
            form.getjTpnContato().setSelectedIndex(1);
        } else {
            Settings.msg = "Por favor, Preencha pelo o menos o email para realizar a exclusão.";
            Settings.status = false;
        }
        renderizar(getDao().getContatos());
        return Settings.status;

    }

    @Override
    public boolean buscar() {
        c = new Contato();
        c.setEmail(form.getjTxtEmail().getText());
        c = (Contato) getDao().buscar(c);
        if (c != null) {
            form.getjTxtNome().setText(c.getNome());
            form.getjTxtDataNasc().setText(Utils.dateToText(c.getDataNasc()));
            form.getjTxtEnd().setText(c.getEnd());
            form.getjTxtTel().setText(c.getTel());
            form.getjCboTipo().setSelectedIndex(0);
            form.getjTxtId().setText(String.valueOf(c.getId()));
            if (c instanceof Cliente) {
                cli = (Cliente) c;
                form.getjCboTipo().setSelectedIndex(1);
                form.isCliente();
                form.getjCboClasFidelidade().setSelectedItem(cli.getTipoFidelidade());
                form.getjTxtDtUltimaCompra().setText(Utils.dateToText(cli.getDataUltimaCompra()));
            } else if (c instanceof Fornecedor) {
                f = (Fornecedor) c;
                form.getjCboTipo().setSelectedIndex(2);
                form.getjSpnIndice().setValue(f.getIndiceQuali());
                form.isFornecedor();
            }
            form.getjBtnCadAtualizar().setText("Atualizar");
        }

        return Settings.status;
    }

    public void buscarNaBarraDePesquisa(int rowIndex) {
        form.getjTxtEmail().setText(model.getValueAt(rowIndex, 1).toString());
        buscar();
        form.getjTpnContato().setSelectedIndex(0);
    }

    @Override
    public boolean listar() {
        renderizar(getDao().getContatos());
        return Settings.status;
    }

    public ArrayList<Contato> getContatos() {
        return getDao().getContatos();
    }

    public void getContatosByNomeOrEmail() {
        c = new Contato();
        c.setNome(form.getjTxtPesquisar().getText());
        c.setEmail(form.getjTxtPesquisar().getText());
        renderizar(getDao().getContatosByNomeOrEmail(c));

    }
    private boolean carregarArquivo(){
        return getDao().carregarArquivo();
    }

    private void renderizar(ArrayList<Contato> contatos) {
        
        model = new TableModel(getDao(), contatos);
        form.getjTblContatos().setModel(model);
    }

}
