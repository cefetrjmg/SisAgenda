/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.dao;

import br.cefetrj.mg.bsi.agenda.config.Settings;
import br.cefetrj.mg.bsi.agenda.model.Contato;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class ContatoDAO implements DAO {

    private Contato c = null;
    private static final ArrayList<Contato> CONTATOS = new ArrayList<>();

    @Override
    public boolean inserir(Object o) {
        try {
            c = (Contato) o;
            if (CONTATOS.add(c)) {
                //Seta true para que possa que se informa que o sistema está ok.
                Settings.status = true;
                Settings.msg = "Contato inserido com sucesso.";

            } else {
                Settings.msg = "Erro ao inserir o contato";
                Settings.status = false;
            }
        } catch (Exception e) {
            Settings.status = false;
            Settings.msg= "Erro ao inserir o contato:" + e.getMessage();
        }
        return Settings.status;
    }

    @Override
    public boolean atualizar(Object o) {
        c = (Contato) o;
        int pos = getPos(o);
        try {
            if (pos != -1 && c != null) {
                CONTATOS.set(pos, c);
                Settings.status = true;
                Settings.msg = "Contato atualizado com sucesso.";

            } else {
                Settings.status = false;
                Settings.msg="Contato não encontrado.";
            }
        } catch (Exception e) {
            Settings.status = false;
            Settings.msg = "Contato atualizado com sucesso.";

        }
        return Settings.status;
    }

    @Override
    public boolean excluir(Object o) {
        if (o != null) {
            c = (Contato) o;
            if (CONTATOS.remove(o)) {
                Settings.msg= "Contato removido com sucesso.";
                Settings.status=true;
            } else {
                Settings.msg= "Erro ao excluir contato.";
                Settings.status=false;
            }

        }
        return Settings.status;
    }

    @Override
    public Object buscar(Object o) {
        if (o != null) {
            for (Contato c : CONTATOS) {
                if (c.equals(o)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Object> listar() {
        return null;
    }

    public ArrayList<Contato> getContatos() {
        return CONTATOS;
    }

    private int getPos(Object o) {
        int pos = -1;
        for (Contato c : CONTATOS) {
            pos++;
            if (((Contato) o).equals(c)) {
                return pos;
            }
        }
        return -1;
    }

}
