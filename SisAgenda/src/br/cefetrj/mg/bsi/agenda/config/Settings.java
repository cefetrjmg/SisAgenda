/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.config;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author cristian
 */
public class Settings {
    public static final String TITLE="Agenda";
    public static String msg="";
    //Variável define se o sistema gerou um erro ou não, quando está falso o sistema gerou algum errou
    public static boolean status;
    public static final String MSG_DELETE="Deseja realamente excluir?";
    public static  String NOME_ARQUIVO="contatos";
    public static  String EXTENSAO_ARQUIVO=".txt";

    /**
     *
     */
    public static final File ARQUIVO =new File(NOME_ARQUIVO.concat(EXTENSAO_ARQUIVO));
    
    
 }
