/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.controller;

import br.cefetrj.mg.bsi.sisagenda.config.Settings;
import br.cefetrj.mg.bsi.sisagenda.dao.BookDAO;
import br.cefetrj.mg.bsi.sisagenda.model.Book;
import br.cefetrj.mg.bsi.sisagenda.model.TableModel;
import br.cefetrj.mg.bsi.sisagenda.view.FormContato;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class BookController implements Controller {

    private static BookDAO dao = null;
    private TableModel model = null;
    private static FormContato form = null;
    private Book b;

    public BookController(FormContato form) {
        this.form = form;
        readFile();
    }

    @Override
    public boolean inserir() {
        b = new Book();
        b.setIsbn(form.getjTxtIsbn().getText());
        b.setPrice(Float.parseFloat(form.getjTxtPrice().getText()));
        b.setPublisherId(Integer.parseInt(form.getjTxtIdPub().getText()));
        b.setTitle(form.getjTxtTitle().getText());
        b.setUrl(form.getjTxtUrl().getText());
        getDAO().inserir(b);
        render(getBooks());
        return Settings.status;
    }

    @Override
    public boolean atualizar() {
        b.setId(Integer.parseInt(form.getjTxtIdBook().getText()));
        b.setIsbn(form.getjTxtIsbn().getText());
        b.setPrice(Float.parseFloat(form.getjTxtPrice().getText()));
        b.setPublisherId(Integer.parseInt(form.getjTxtIdPub().getText()));
        b.setTitle(form.getjTxtTitle().getText());
        b.setUrl(form.getjTxtUrl().getText());
        getDAO().atualizar(b, b.getId());
        return Settings.status;
    }

    @Override
    public boolean excluir() {
        b = new Book();
        b.setId(Integer.parseInt(form.getjTxtIdBook().getText()));
        return getDAO().excluir(b);

    }

    @Override
    public boolean buscar() {
        b = new Book();
        b.setIsbn(form.getjTxtIsbn().getText());
        b=(Book) getDAO().buscar(b);
        if (b != null) {
            form.getjTxtIdBook().setText(String.valueOf(b.getId()));
            form.getjTxtIsbn().setText(b.getIsbn());
            form.getjTxtIdPub().setText(String.valueOf(b.getPublisherId()));
            form.getjTxtPrice().setText(String.valueOf(b.getPrice()));
            form.getjTxtTitle().setText(b.getTitle());
            form.getjTxtUrl().setText(b.getUrl());
            form.getjBtnCadAtualizarBook().setText("Atualizar");
            Settings.status = true;
        }
        return Settings.status;
    }

    @Override
    public boolean listar() {
        return Settings.status;
    }

    private BookDAO getDAO() {
        if (dao == null) {
            dao = new BookDAO();
        }
        return dao;
    }

    private boolean readFile() {
        return getDAO().readFile();
    }

    private void render(ArrayList<Book> books) {
        String columns[] = {
            "ID",
            "ISBN",
            "TÍTULO",
            "PREÇO"

        };
        model = new TableModel(dao, books, columns);
        form.getjTblList().setModel(model);

    }

    private ArrayList<Book> getBooks() {
        return (ArrayList<Book>) getDAO().listar();
    }

    public void findIsbn(int rowIndex) {
        String isbn=(String) model.getValueAt(rowIndex,1);
        form.getjTxtIsbn().setText(isbn);
        buscar();
    }

    public void getBookByIsbn(String isbn) {
        render(getDAO().getBookByIsbn(isbn));
    }

}
