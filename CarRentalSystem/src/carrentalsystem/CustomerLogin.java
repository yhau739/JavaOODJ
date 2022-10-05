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

        CPassInput = new javax.swing.JTextField();
        LoginBtn = new javax.swing.JButton();
        BackBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        CustomerUsernameLabel = new javax.swing.JLabel();
        CustomerPasswordLabel = new javax.swing.JLabel();
        CUsernameInput = new javax.swing.JTextField();
        RegisterBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CPassInput.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CPassInput.setToolTipText("");
        CPassInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPassInputActionPerformed(evt);
            }
        });

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

        CUsernameInput.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CUsernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUsernameInputActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BackBtn)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CustomerUsernameLabel)
                            .addComponent(CustomerPasswordLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CUsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CPassInput, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackBtn)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomerUsernameLabel)
                    .addComponent(CUsernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CPassInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerPasswordLabel))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClearBtn)
                    .addComponent(LoginBtn)
                    .addComponent(RegisterBtn))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CPassInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPassInputActionPerformed
        
        
        
    }//GEN-LAST:event_CPassInputActionPerformed

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        //obtain inputs
        String username = CUsernameInput.getText();
        String password = CPassInput.getText();

        //check & get login status
        Boolean result = CheckLoginCustomer("customer.txt", username, password);

        //if login success
        if (result == true) {
            //Set up profile
            Global.Username = username;
            Global.Password = password;
            Global.Privilege = "customer";
            
            //dispose & go to next page
            this.setVisible(false);
            this.dispose();
            CustomerMain cmain = new CustomerMain();
            cmain.setVisible(true);

        } else if (result == false) {
            //clear inputs
            CUsernameInput.setText("");
            CPassInput.setText("");

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
        CUsernameInput.setText("");
        CPassInput.setText("");
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void CUsernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUsernameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CUsernameInputActionPerformed

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
    private javax.swing.JTextField CPassInput;
    private javax.swing.JTextField CUsernameInput;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JLabel CustomerPasswordLabel;
    private javax.swing.JLabel CustomerUsernameLabel;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JButton RegisterBtn;
    // End of variables declaration//GEN-END:variables
}
