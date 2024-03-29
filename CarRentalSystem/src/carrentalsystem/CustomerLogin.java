/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import static carrentalsystem.Customer.CheckLoginCustomer;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author leere
 */
public class CustomerLogin extends javax.swing.JFrame {

    /**
     * Creates new form CustomerLogin
     */
    public CustomerLogin() {
        initComponents();
        
        //To make sure JFrame is located in the center of the screen regardless of monitor resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginBtn = new javax.swing.JButton();
        BackBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        CustomerUsernameLabel = new javax.swing.JLabel();
        CustomerPasswordLabel = new javax.swing.JLabel();
        UsernameInput = new javax.swing.JTextField();
        RegisterBtn = new javax.swing.JButton();
        PassInput = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LoginBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LoginBtn.setText("Login");
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        BackBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BackBtn.setText("Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        ClearBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ClearBtn.setText("Clear");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        CustomerUsernameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CustomerUsernameLabel.setText("Username :");

        CustomerPasswordLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CustomerPasswordLabel.setText("Password :");

        UsernameInput.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        UsernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameInputActionPerformed(evt);
            }
        });

        RegisterBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RegisterBtn.setText("Register");
        RegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BackBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CustomerPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CustomerUsernameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(UsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PassInput, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackBtn)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerUsernameLabel)
                    .addComponent(UsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerPasswordLabel)
                    .addComponent(PassInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClearBtn)
                    .addComponent(LoginBtn)
                    .addComponent(RegisterBtn))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        //obtain inputs
        String username = UsernameInput.getText();
        char[] password = PassInput.getPassword();
        
        //Convert to String data type
        String StrPassword = String.valueOf(password);
        
        //check & get login status
        Customer result = Customer.CheckLoginCustomer(username, StrPassword);

        //if login success
        if (result != null) {        
            //dispose & go to next page
            this.setVisible(false);
            this.dispose();
            CustomerMain cmain = new CustomerMain();
            cmain.setVisible(true);

        } else if (result == null) {
            //clear inputs
            UsernameInput.setText("");
            PassInput.setText("");

            //fake refresh
            this.setVisible(false);
            this.setVisible(true);
        }
    }//GEN-LAST:event_LoginBtnActionPerformed

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        //close page and dispose
        this.setVisible(false);
        this.dispose();

        //create object and open page
        MainFrame mainpage = new MainFrame();
        mainpage.setVisible(true);
    }//GEN-LAST:event_BackBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        //clear inputs
        UsernameInput.setText("");
        PassInput.setText("");
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void UsernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameInputActionPerformed

    private void RegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterBtnActionPerformed
        //close page and dispose
        this.setVisible(false);
        this.dispose();

        //create object and open page
        CustomerRegister cregister = new CustomerRegister();
        cregister.setVisible(true);
    }//GEN-LAST:event_RegisterBtnActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JLabel CustomerPasswordLabel;
    private javax.swing.JLabel CustomerUsernameLabel;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPasswordField PassInput;
    private javax.swing.JButton RegisterBtn;
    private javax.swing.JTextField UsernameInput;
    // End of variables declaration//GEN-END:variables
}
