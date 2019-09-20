/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.sisagenda.dao;

import br.cefetrj.mg.bsi.sisagenda.config.Settings;
import static br.cefetrj.mg.bsi.sisagenda.config.Settings.EXTENSAO_ARQUIVO;
import static br.cefetrj.mg.bsi.sisagenda.config.Settings.NOME_ARQUIVO;
import br.cefetrj.mg.bsi.sisagenda.model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class BookDAO implements DAO {

    private static ArrayList<Book> books = new ArrayList<>();
    private static int id = 0;
    private Book b;
    private File arquivo = null;

    public BookDAO() {
        Settings.NOME_ARQUIVO = "Books";
        Settings.EXTENSAO_ARQUIVO = ".txt";

        arquivo = new File(NOME_ARQUIVO.concat(EXTENSAO_ARQUIVO));
    }

    @Override
    public boolean inserir(Object o) {
        if (o != null) {
            b = (Book) o;
            if (b.isAUTO_INCREMENT()) {
                b.setId(++id);
            }
            if (books.add(b) && writeFile()) {
                Settings.msg = "Livro inserido com sucesso.";
                Settings.status = true;
            } else {
                Settings.msg = "Erro ao gravar livro.";
            }
        }
        return Settings.status;
    }

    @Override
    public boolean atualizar(Object newObject, int id) {
        if (newObject != null) {
            this.b = (Book) newObject;
            Book oldBook = getBookById(id);
            int pos = getPos(oldBook);
            if (pos != -1) {
                books.set(pos, this.b);
                if (writeFile()) {
                    Settings.status = true;
                    Settings.msg = "Livro atualizado com sucesso.";
                }

            } else {
                Settings.status = false;
                Settings.msg = "Erro ao atualizar o livro";
            }
        }
        return Settings.status;
    }

    @Override
    public boolean excluir(Object o) {
        if (o != null) {
            this.b = (Book) o;
            b=getBookById(b.getId());
            if (books.remove(b) && writeFile()) {
                Settings.msg = "Livro excluído com sucesso.";
                Settings.status = true;
            } else {
                Settings.msg = "Erro ao excluir o livro.";
                Settings.status = false;
            }
        }
        return Settings.status;
    }

    @Override
    public Object buscar(Object o) {
        if (o != null) {
            this.b = (Book) o;
            for (Book book : books) {
                if (book.getIsbn().equalsIgnoreCase(b.getIsbn())) {
                    return book;
                }
            }
        }
        Settings.msg="Livro não encontrado";
        Settings.status=false;
        return null;
    }

    public ArrayList<Book> getBookByIsbn(String isbn) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : this.books) {
            if (isbn.contains(book.getIsbn())) {
                books.add(book);
            }
            if(isbn.equals(""))
             return this.books;
        }

        return books;

    }

    @Override
    public ArrayList<?> listar() {
        return books;
    }

    public boolean writeFile() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {

            if (arquivo.exists()) {
                arquivo.delete();
                createFile();
            } else {
                createFile();
            }
            fw = new FileWriter(arquivo);
            bw = new BufferedWriter(fw);
            for (Book book : books) {
                bw.write(book.getId() + ";");
                bw.write(book.getTitle() + ";");
                bw.write(book.getIsbn() + ";");
                bw.write(book.getPublisherId() + ";");
                bw.write(book.getPrice() + ";");
                bw.write(book.getUrl() + ";");
                bw.newLine();
            }

            bw.close();
            fw.close();
            return Settings.status = true;
        } catch (IOException ex) {
            Settings.status = false;
        }

        return Settings.status;
    }

    public boolean readFile() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            while (br.ready()) {
                Book b = new Book();
                String row[] = br.readLine().split(";");
                id = Integer.parseInt(row[0]);
                b.setId(id);
                b.setTitle(row[1]);;
                b.setIsbn(row[2]);
                b.setPublisherId(Integer.parseInt(row[3]));
                b.setPrice(Float.parseFloat(row[4]));
                b.setUrl(row[5]);
                books.add(b);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Settings.status = false;
        } catch (IOException ex) {
            Settings.status = false;
        }

        return Settings.status;
    }

    private boolean createFile() {
        try {
            return arquivo.createNewFile();
        } catch (IOException ex) {
            return Settings.status = false;
        }
    }

    private int getPos(Object o) {
        return books.indexOf(o);
    }

    private Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

}
