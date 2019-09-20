/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.model;

import br.cefetrj.mg.bsi.sisagenda.dao.BookDAO;
import br.cefetrj.mg.bsi.sisagenda.dao.ContatoDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cristian
 */
public class TableModel extends AbstractTableModel {

    private ContatoDAO dao = null;
    private ArrayList<Contato> contatos = null;
    private ArrayList<Book> books = null;
    private BookDAO bookDAO = null;
    private String colunas[];

    /**
     *
     * @param dao
     * @param books
     * @param colunas
     */
    public TableModel(BookDAO dao, ArrayList<Book> books, String colunas[]) {
        this.bookDAO = dao;
        this.books = books;
        this.colunas = colunas;
    }

    public TableModel(ContatoDAO dao, ArrayList<Contato> contatos, String colunas[]) {
        this.dao = dao;
        this.contatos = contatos;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        if (contatos != null) {
            return contatos.size();
        } else {
            return books.size();
        }
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contato contatoSelecionado = null;
        Book bookSelected = null;
        if (contatos != null) {
            contatoSelecionado = contatos.get(rowIndex);
        } else {
            bookSelected = books.get(rowIndex);
        }

        switch (columnIndex) {
            case 0:
                return contatoSelecionado != null ? contatoSelecionado.getId() : bookSelected.getId();
            case 1:
                return contatoSelecionado != null ? contatoSelecionado.getEmail() : bookSelected.getIsbn();

            case 2:
                return contatoSelecionado != null ? contatoSelecionado.getNome() : bookSelected.getTitle();
            case 3:
                return contatoSelecionado != null ? this.dao.getTipoContato(contatoSelecionado) : bookSelected.getPrice();

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

    public Book getBook(int rowIndex) {
        return books.get(rowIndex);
    }

    public void removeRow(int rowIndex) {
        if (contatos != null) {
            Contato c = getContato(rowIndex);
            this.dao.excluir(c);
        }
        else{
            Book b =getBook(rowIndex);
            this.dao.excluir(b);
        }

        fireTableRowsDeleted(getRowCount(), getRowCount() - 1);
    }

}
