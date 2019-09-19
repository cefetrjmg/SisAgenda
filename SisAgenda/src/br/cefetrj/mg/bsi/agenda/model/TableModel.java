/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.model;

import br.cefetrj.mg.bsi.agenda.dao.ContatoDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cristian
 */
public class TableModel extends AbstractTableModel {

    private static ContatoDAO dao = null;
    private ArrayList<Contato> contatos;
    private static String colunas[] = {
        "ID",
        "E-Mail",
        "NOME",
        "Tipo"
    };

    /**
     *
     * @param dao
     * @param contatos
     */
    public TableModel(ContatoDAO dao, ArrayList<Contato> contatos) {
        TableModel.dao = dao;
        this.contatos = contatos;
    }

    @Override
    public int getRowCount() {
        return contatos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contato contatoSelecionado = contatos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return contatoSelecionado.getId();
            case 1:
                return contatoSelecionado.getEmail();

            case 2:
                return contatoSelecionado.getNome();
            case 3:
                return TableModel.dao.getTipoContato(contatoSelecionado);

        }

        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public Contato getContato(int rowIndex) {
        return contatos.get(rowIndex);
    }

    public void removeRow(int rowIndex) {
        Contato c = getContato(rowIndex);
        TableModel.dao.excluir(c);
        fireTableRowsDeleted(getRowCount(), getRowCount() - 1);
    }

}
