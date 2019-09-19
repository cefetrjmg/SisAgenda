/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.dao;

import br.cefetrj.mg.bsi.agenda.config.Settings;
import br.cefetrj.mg.bsi.agenda.model.Cliente;
import br.cefetrj.mg.bsi.agenda.model.Contato;
import br.cefetrj.mg.bsi.agenda.model.Fornecedor;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class ContatoDAO implements DAO {

    private Contato c = null;
    private static final ArrayList<Contato> CONTATOS = new ArrayList<>();
    private String tipoContato = "";
    private static int id = 1;

    public String getTipoContato(Contato c) {
        if (c instanceof Cliente) {
            tipoContato = "Cliente";
        } else if (c instanceof Fornecedor) {
            tipoContato = "Fornecedor";
        } else {
            tipoContato = "Contato";
        }
        return tipoContato;
    }

    @Override
    public boolean inserir(Object o) {
        try {
            c = (Contato) o;
            if (c.isAUTO_INCREMENT()) {
                c.setId(id);
                if (CONTATOS.add(c)) {
                    //Seta true para que possa que se informa que o sistema está ok.
                    Settings.status = true;
                    Settings.msg = getTipoContato(c) + " inserido com sucesso.";
                    id++;

                } else {
                    Settings.msg = "Erro ao inserir " + getTipoContato(c);
                    Settings.status = false;
                }
            }
        } catch (Exception e) {
            Settings.status = false;
            Settings.msg = "Erro ao inserir o contato:" + e.getMessage();
        }
        return Settings.status;
    }

    @Override
    public boolean atualizar(Object newObject, int id) {
        int pos=-1;
        Contato oldContato=getContatoById(id);
        this.c=(Contato) newObject;
        if (newObject != null) {
            pos=getPos(oldContato);
        }

        try {
            if (pos != -1 && c != null) {
                CONTATOS.set(pos, c);
                Settings.status = true;
                Settings.msg = getTipoContato(c) + " atualizado com sucesso.";

            } else {
                Settings.status = false;
                Settings.msg = getTipoContato(c) + " não encontrado.";
            }
        } catch (Exception e) {
            Settings.status = false;
            Settings.msg = " Erro ao atualizar " + getTipoContato(c) + ":" + e.getMessage();

        }
        return Settings.status;
    }

    @Override
    public boolean excluir(Object o) {
        if (o != null) {
            c = (Contato) o;
            c = (Contato) buscar(o);
            if (CONTATOS.remove(c)) {
                Settings.msg = getTipoContato(c) + " removido com sucesso.";
                Settings.status = true;
            } else {
                Settings.msg = "Erro ao excluir " + getTipoContato(c);
                Settings.status = false;
            }

        }
        return Settings.status;
    }

    @Override
    public Object buscar(Object o) {
        if (o != null) {
            this.c = (Contato) o;
            for (Contato contato : CONTATOS) {
                if (contato.getEmail().equalsIgnoreCase(this.c.getEmail())) {
                    Settings.status = true;
                    Settings.msg = "";
                    return contato;
                }
            }
        }
        Settings.status = false;
        Settings.msg = "E-mail não cadastrado";
        return null;
    }
    public ArrayList<Contato> getContatosByNomeOrEmail(Object o){
        Contato c=(Contato) o;
        ArrayList<Contato> contatosByNomeOrEmail=new ArrayList<>();
        for(Contato contato : CONTATOS){
            if(contato.getNome().contains(c.getNome()) 
                    || contato.getEmail().contains(c.getEmail()))
                contatosByNomeOrEmail.add(contato);
                 
        }
        return contatosByNomeOrEmail;
        
    }

    @Override
    public ArrayList<Object> listar() {
        return null;
    }

    public ArrayList<Contato> getContatos() {
        return CONTATOS;
    }

    private int getPos(Object o) {
        return CONTATOS.indexOf(o);
    }

    private Contato getContatoById(int id) {
        for (Contato contato : CONTATOS) {
            if (contato.getId() == id) {
                return contato;
            }
        }
        return null;
    }

}
