/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.mg.bsi.agenda.view;

import br.cefetrj.mg.bsi.agenda.config.Settings;
import br.cefetrj.mg.bsi.agenda.controller.ContatoController;
import br.cefetrj.mg.bsi.agenda.model.Fidelidade;
import br.cefetrj.mg.bsi.utils.Utils;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author cristian
 */
public class FormContato extends javax.swing.JFrame {

    /**
     * Creates new form FormContato
     */
    ContatoController controller;

    public FormContato() {
        initComponents();
        controller = new ContatoController();
        controller.setForm(this);
        isCategoriaGeral();
        carregarTiposDeFidelidade();

    }

    public JTextField getjTxtEnd() {
        return jTxtEnd;
    }

    public void setjTxtEnd(JTextField jTxtEnd) {
        this.jTxtEnd = jTxtEnd;
    }

    public JButton getjBtnCadAtualizar() {
        return jBtnCadAtualizar;
    }

    public void setjBtnCadAtualizar(JButton jBtnCadAtualizar) {
        this.jBtnCadAtualizar = jBtnCadAtualizar;
    }

    public JButton getjBtnExcluir() {
        return jBtnExcluir;
    }

    public void setjBtnExcluir(JButton jBtnExcluir) {
        this.jBtnExcluir = jBtnExcluir;
    }

    public JFormattedTextField getjTxtDataNasc() {
        return jTxtDataNasc;
    }

    public void setjTxtDataNasc(JFormattedTextField jTxtDataNasc) {
        this.jTxtDataNasc = jTxtDataNasc;
    }

    public JTextField getjTxtEmail() {
        return jTxtEmail;
    }

    public void setjTxtEmail(JTextField jTxtEmail) {
        this.jTxtEmail = jTxtEmail;
    }

    public JTextField getjTxtNome() {
        return jTxtNome;
    }

    public void setjTxtNome(JTextField jTxtNome) {
        this.jTxtNome = jTxtNome;
    }

    public JFormattedTextField getjTxtTel() {
        return jTxtTel;
    }

    public void setjTxtTel(JFormattedTextField jTxtTel) {
        this.jTxtTel = jTxtTel;
    }

    public JComboBox<String> getjCboClasFidelidade() {
        return jCboClasFidelidade;
    }

    public void setjCboClasFidelidade(JComboBox<String> jCboClasFidelidade) {
        this.jCboClasFidelidade = jCboClasFidelidade;
    }

    public JComboBox<String> getjCboTipo() {
        return jCboTipo;
    }

    public void setjCboTipo(JComboBox<String> jCboTipo) {
        this.jCboTipo = jCboTipo;
    }

    public JSpinner getjSpnIndice() {
        return jSpnIndice;
    }

    public void setjSpnIndice(JSpinner jSpnIndice) {
        this.jSpnIndice = jSpnIndice;
    }

    public JFormattedTextField getjTxtDtUltimaCompra() {
        return jTxtDtUltimaCompra;
    }

    public void setjTxtDtUltimaCompra(JFormattedTextField jTxtDtUltimaCompra) {
        this.jTxtDtUltimaCompra = jTxtDtUltimaCompra;
    }

    public void isCategoriaGeral() {
        boolean flag = false;

        jLblClasFidelidade.setVisible(flag);
        jLblIndice.setVisible(flag);
        jLblUltimaCompra.setVisible(flag);

        jTxtDtUltimaCompra.setVisible(flag);
        jSpnIndice.setVisible(flag);
        jCboClasFidelidade.setVisible(flag);

    }

    public void isCliente() {
        boolean flag = false;

        jLblIndice.setVisible(flag);
        jSpnIndice.setVisible(flag);

        jLblClasFidelidade.setVisible(!flag);
        jCboClasFidelidade.setVisible(!flag);

        jLblUltimaCompra.setVisible(!flag);
        jTxtDtUltimaCompra.setVisible(!flag);
    }

    public void isFornecedor() {
        boolean flag = false;
        jLblIndice.setVisible(!flag);
        jSpnIndice.setVisible(!flag);

        jLblClasFidelidade.setVisible(flag);
        jCboClasFidelidade.setVisible(flag);

        jLblUltimaCompra.setVisible(flag);
        jTxtDtUltimaCompra.setVisible(flag);
    }

    private void limpar() {
        JTextField txt[] = {
            jTxtDataNasc,
            jTxtEmail,
            jTxtEnd,
            jTxtNome,
            jTxtTel,
            jTxtDtUltimaCompra
        };
        JComboBox cbo[] = {
            jCboClasFidelidade,
            jCboTipo
        };

        for (JComboBox c : cbo) {
            c.setSelectedIndex(-1);
        }
        for (JTextField t : txt) {
            t.setText("");
        }
        jSpnIndice.setValue(0);
        jTxtNome.requestFocus();
        jBtnCadAtualizar.setText("Cadastrar");

    }

    private void carregarTiposDeFidelidade() {
        jCboClasFidelidade.addItem(Fidelidade.FIEL.getValor());
        jCboClasFidelidade.addItem(Fidelidade.FREQUENTE.getValor());
        jCboClasFidelidade.addItem(Fidelidade.POUCO_FREQUENTE.getValor());
        jCboClasFidelidade.addItem(Fidelidade.UMA_VEZ.getValor());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTxtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTxtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtTel = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtDataNasc = new javax.swing.JFormattedTextField();
        jBtnCadAtualizar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTxtEnd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jCboTipo = new javax.swing.JComboBox<>();
        jLblIndice = new javax.swing.JLabel();
        jSpnIndice = new javax.swing.JSpinner();
        jLblUltimaCompra = new javax.swing.JLabel();
        jTxtDtUltimaCompra = new javax.swing.JFormattedTextField();
        jLblClasFidelidade = new javax.swing.JLabel();
        jCboClasFidelidade = new javax.swing.JComboBox<>();
        jBtnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));
        getContentPane().add(jTxtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 780, 30));

        jLabel2.setText("E-mail");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        getContentPane().add(jTxtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 370, 30));

        jLabel3.setText("Telefone");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, -1));

        try {
            jTxtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(jTxtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 140, 30));

        jLabel4.setText("Data de Nasc.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, -1, -1));

        try {
            jTxtDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(jTxtDataNasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 150, 30));

        jBtnCadAtualizar.setText("Cadastrar");
        jBtnCadAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadAtualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnCadAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 780, 40));

        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 375, 780, 40));

        jLabel5.setText("Endereço");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        getContentPane().add(jTxtEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 780, 30));

        jLabel6.setText("Tipo");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jCboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categoria Geral", "Cliente", "Fornecedor" }));
        jCboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboTipoActionPerformed(evt);
            }
        });
        getContentPane().add(jCboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 210, 30));

        jLblIndice.setText("Indice");
        getContentPane().add(jLblIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        jSpnIndice.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        getContentPane().add(jSpnIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, 30));

        jLblUltimaCompra.setText("Última Compra");
        getContentPane().add(jLblUltimaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, -1));

        try {
            jTxtDtUltimaCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(jTxtDtUltimaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 160, 30));

        jLblClasFidelidade.setText("Classificação de Fidelidade");
        getContentPane().add(jLblClasFidelidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, -1));

        getContentPane().add(jCboClasFidelidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 260, 30));

        jBtnBuscar.setText("Buscar");
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCadAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadAtualizarActionPerformed
        // TODO add your handling code here:
        if (jBtnCadAtualizar.getText().equalsIgnoreCase("cadastrar")) {
            if (controller.inserir()) {
                limpar();

            } else {
                if (controller.atualizar()) {
                    limpar();
                }
            }
            Utils.print(Settings.msg, Settings.TITLE, Settings.status);
        }

    }//GEN-LAST:event_jBtnCadAtualizarActionPerformed

    private void jCboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboTipoActionPerformed
        // TODO add your handling code here:
        int index = jCboTipo.getSelectedIndex();
        switch (index) {
            case 0:
                isCategoriaGeral();
                break;
            case 1:
                isCliente();
                break;
            case 2:
                isFornecedor();

                break;
        }
    }//GEN-LAST:event_jCboTipoActionPerformed

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        // TODO add your handling code here:
        if (!controller.buscar()) {
            Utils.print(Settings.msg, Settings.TITLE, Settings.status);
        }


    }//GEN-LAST:event_jBtnBuscarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
        if (Utils.confirm(Settings.MSG_DELETE, Settings.TITLE) == JOptionPane.YES_OPTION) {
            controller.excluir();
            limpar();
        }
        Utils.print(Settings.msg, Settings.TITLE, Settings.status);
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormContato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormContato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnCadAtualizar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JComboBox<String> jCboClasFidelidade;
    private javax.swing.JComboBox<String> jCboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLblClasFidelidade;
    private javax.swing.JLabel jLblIndice;
    private javax.swing.JLabel jLblUltimaCompra;
    private javax.swing.JSpinner jSpnIndice;
    private javax.swing.JFormattedTextField jTxtDataNasc;
    private javax.swing.JFormattedTextField jTxtDtUltimaCompra;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtEnd;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JFormattedTextField jTxtTel;
    // End of variables declaration//GEN-END:variables
}
