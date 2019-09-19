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
import br.cefetrj.mg.bsi.utils.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            tipoContato = "Geral";
        }
        return tipoContato;
    }

    @Override
    public boolean inserir(Object o) {
        try {
            c = (Contato) o;
            if (c.isAUTO_INCREMENT()) {
                c.setId(id);
                if (CONTATOS.add(c) && gravarArquivo()) {
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
        int pos = -1;
        Contato oldContato = getContatoById(id);
        this.c = (Contato) newObject;
        if (newObject != null) {
            pos = getPos(oldContato);
        }

        try {
            if (pos != -1 && c != null) {
                CONTATOS.set(pos, c);
                if (gravarArquivo()) {
                    Settings.status = true;
                    Settings.msg = getTipoContato(c) + " atualizado com sucesso.";
                }

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
            if (CONTATOS.remove(c) && gravarArquivo()) {
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

    public ArrayList<Contato> getContatosByNomeOrEmail(Object o) {
        Contato c = (Contato) o;
        ArrayList<Contato> contatosByNomeOrEmail = new ArrayList<>();
        for (Contato contato : CONTATOS) {
            if (contato.getNome().contains(c.getNome())
                    || contato.getEmail().contains(c.getEmail())) {
                contatosByNomeOrEmail.add(contato);
            }

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

    private boolean gravarArquivo() {
        BufferedWriter bw = null;
        try {
            if (!Settings.ARQUIVO.exists()) {
                criarArquivo();
            } else {
                Settings.ARQUIVO.delete();
                criarArquivo();
            }

            bw = new BufferedWriter(new FileWriter(Settings.ARQUIVO));
            for (Contato contato : CONTATOS) {
                bw.write(contato.getId() + ";");
                String tipo = getTipoContato(contato);
                bw.write(tipo + ";");
                bw.write(contato.getNome() + ";");
                bw.write(contato.getTel()+";");
                bw.write(contato.getEmail() + ";");
                bw.write(contato.getEnd() + ";");
                bw.write(Utils.dateToText(contato.getDataNasc()) + ";");
                if (tipo.equalsIgnoreCase("cliente")) {
                    Cliente cli = (Cliente) contato;
                    bw.write(cli.getTipoFidelidade() + ";");
                    bw.write(Utils.dateToText(cli.getDataUltimaCompra()) + ";");
                } else if (tipo.equalsIgnoreCase("fornecedor")) {
                    Fornecedor f = (Fornecedor) contato;
                    bw.write(f.getIndiceQuali() + ";");
                }
                bw.newLine();

            }

            bw.close();
            Settings.status = true;
        } catch (IOException ex) {
            Settings.status = false;
            Settings.msg = "Erro ao criar arquivo:" + ex.getMessage();
        }

        return Settings.status;
    }

    private boolean criarArquivo() {
        try {
            return Settings.ARQUIVO.createNewFile();
        } catch (IOException ex) {
            Settings.msg = "Erro ao criar arquivo:" + ex.getMessage();
            return Settings.status = false;
        }
    }

    public boolean carregarArquivo() {
        BufferedReader bf = null;
        try {
            if (Settings.ARQUIVO.exists()) {
                bf = new BufferedReader(new FileReader(Settings.ARQUIVO));
                while(bf.ready()){
                    Contato contato=new Contato();
                    String linha []=bf.readLine().split(";");
                    if(linha[1].equalsIgnoreCase("cliente")){
                        contato =new Cliente();
                        Cliente cliente=(Cliente) contato;
                        cliente.setTipoFidelidade(linha[7]);
                        cliente.setDataUltimaCompra(Utils.textToDate(linha[8]));
                        contato=cliente;
                    }
                    else if(linha[1].equalsIgnoreCase("fornecedor")){
                        contato=new Fornecedor();
                        Fornecedor f=(Fornecedor) contato;
                        f.setIndiceQuali(Integer.parseInt(linha[7]));
                        contato=f;
                    }
                    contato.setId(Integer.parseInt(linha[0]));
                    contato.setNome(linha[2]);
                    contato.setTel(linha[3]);
                    contato.setEmail(linha[4]);
                    contato.setEnd(linha[5]);
                    contato.setDataNasc(Utils.textToDate(linha[6]));
                    CONTATOS.add(contato);
                }
                
                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Settings.status;
    }

}
