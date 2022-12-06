/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentalsystem;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Lenovo
 */
public class AdminMain extends javax.swing.JFrame {

    /**
     * Creates new form AdminMain
     */
    public AdminMain() {
        initComponents();

        //To make sure JFrame is located in the center of the screen regardless of monitor resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Set Welcome Message
        WelcomeMsg.setText("Welcome " + Global.CurrentAdmin.getUsername() + "!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        AddAdminBtn1 = new javax.swing.JButton();
        ViewEditCarBtn1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        WelcomeMsg = new javax.swing.JLabel();
        LogoutBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        AddAdminBtn = new javax.swing.JButton();
        ViewEditCarBtn = new javax.swing.JButton();
        ManageBookings = new javax.swing.JButton();
        ManageCustomerBtn = new javax.swing.JButton();
        AddCarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ManageAdmin = new javax.swing.JButton();
        BookingsHistory = new javax.swing.JButton();
        ApproveCustomer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        SystemLoginRecordBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        EventLogsBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        Reports = new javax.swing.JButton();
        PaymentCollection = new javax.swing.JButton();

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        AddAdminBtn1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddAdminBtn1.setLabel("New Admin");
        AddAdminBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAdminBtn1ActionPerformed(evt);
            }
        });

        ViewEditCarBtn1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ViewEditCarBtn1.setText("Manage Cars");
        ViewEditCarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewEditCarBtn1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Manage Bookings");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setText("Manage Customers");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton8.setText("Add New Cars");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(AddAdminBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(ViewEditCarBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddAdminBtn1)
                    .addComponent(ViewEditCarBtn1))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton7))
                .addGap(39, 39, 39)
                .addComponent(jButton6)
                .addGap(49, 49, 49))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        WelcomeMsg.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        WelcomeMsg.setText("Welcome ");

        LogoutBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        AddAdminBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddAdminBtn.setLabel("New Admin");
        AddAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAdminBtnActionPerformed(evt);
            }
        });

        ViewEditCarBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ViewEditCarBtn.setText("Manage Cars");
        ViewEditCarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewEditCarBtnActionPerformed(evt);
            }
        });

        ManageBookings.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ManageBookings.setText("Manage Bookings");
        ManageBookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageBookingsActionPerformed(evt);
            }
        });

        ManageCustomerBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ManageCustomerBtn.setText("Manage Customers");
        ManageCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageCustomerBtnActionPerformed(evt);
            }
        });

        AddCarBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AddCarBtn.setText("New Cars");
        AddCarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCarBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Management");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        ManageAdmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ManageAdmin.setText("Manage Admin");
        ManageAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageAdminActionPerformed(evt);
            }
        });

        BookingsHistory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BookingsHistory.setText("Booked History");
        BookingsHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookingsHistoryActionPerformed(evt);
            }
        });

        ApproveCustomer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ApproveCustomer.setText("Account Comfirmation");
        ApproveCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApproveCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(47, 47, 47)
                                .addComponent(ManageAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddCarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ManageBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewEditCarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ManageCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BookingsHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ApproveCustomer)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddAdminBtn)
                    .addComponent(ManageAdmin))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddCarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewEditCarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(ManageCustomerBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(ManageBookings)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookingsHistory)
                    .addComponent(ApproveCustomer))
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SystemLoginRecordBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SystemLoginRecordBtn.setLabel("User Login Record");
        SystemLoginRecordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SystemLoginRecordBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Others");

        EventLogsBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EventLogsBtn.setText("Event Logs");
        EventLogsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EventLogsBtnActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        Reports.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reports.setText("Reports");
        Reports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportsActionPerformed(evt);
            }
        });

        PaymentCollection.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PaymentCollection.setText("Collect Payment");
        PaymentCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentCollectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PaymentCollection, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(SystemLoginRecordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(EventLogsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SystemLoginRecordBtn)
                    .addComponent(EventLogsBtn))
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reports)
                    .addComponent(PaymentCollection))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LogoutBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(WelcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogoutBtn)
                .addGap(13, 13, 13)
                .addComponent(WelcomeMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        //clear profile
        Global.CurrentAdmin = null;

        this.setVisible(false);
        this.dispose();
        MainFrame page = new MainFrame();
        page.setVisible(true);
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void SystemLoginRecordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SystemLoginRecordBtnActionPerformed
        this.setVisible(false);
        this.dispose();
        SystemLoginRecord page = new SystemLoginRecord();
        page.setVisible(true);
    }//GEN-LAST:event_SystemLoginRecordBtnActionPerformed

    private void AddAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAdminBtnActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminNewAdmin page = new AdminNewAdmin();
        page.setVisible(true);
    }//GEN-LAST:event_AddAdminBtnActionPerformed

    private void ViewEditCarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewEditCarBtnActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminViewCar page = new AdminViewCar();
        page.setVisible(true);
    }//GEN-LAST:event_ViewEditCarBtnActionPerformed

    private void AddAdminBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAdminBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddAdminBtn1ActionPerformed

    private void ViewEditCarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewEditCarBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewEditCarBtn1ActionPerformed

    private void AddCarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCarBtnActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminNewCar page = new AdminNewCar();
        page.setVisible(true);
    }//GEN-LAST:event_AddCarBtnActionPerformed

    private void EventLogsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EventLogsBtnActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminEventLogs page = new AdminEventLogs();
        page.setVisible(true);
    }//GEN-LAST:event_EventLogsBtnActionPerformed

    private void ManageAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageAdminActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminViewAdmin page = new AdminViewAdmin();
        page.setVisible(true);
    }//GEN-LAST:event_ManageAdminActionPerformed

    private void ManageBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageBookingsActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminViewBooking page = new AdminViewBooking();
        page.setVisible(true);
    }//GEN-LAST:event_ManageBookingsActionPerformed

    private void BookingsHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookingsHistoryActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminViewBookingHistory page = new AdminViewBookingHistory();
        page.setVisible(true);
    }//GEN-LAST:event_BookingsHistoryActionPerformed

    private void ManageCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageCustomerBtnActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminViewCustomer page = new AdminViewCustomer();
        page.setVisible(true);
    }//GEN-LAST:event_ManageCustomerBtnActionPerformed

    private void ApproveCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveCustomerActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminComfirmCustomer page = new AdminComfirmCustomer();
        page.setVisible(true);
    }//GEN-LAST:event_ApproveCustomerActionPerformed

    private void ReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportsActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminReport page = new AdminReport();
        page.setVisible(true);
    }//GEN-LAST:event_ReportsActionPerformed

    private void PaymentCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentCollectionActionPerformed
        this.setVisible(false);
        this.dispose();
        AdminPaymentReceipt page = new AdminPaymentReceipt();
        page.setVisible(true);
    }//GEN-LAST:event_PaymentCollectionActionPerformed

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
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAdminBtn;
    private javax.swing.JButton AddAdminBtn1;
    private javax.swing.JButton AddCarBtn;
    private javax.swing.JButton ApproveCustomer;
    private javax.swing.JButton BookingsHistory;
    private javax.swing.JButton EventLogsBtn;
    private javax.swing.JButton LogoutBtn;
    private javax.swing.JButton ManageAdmin;
    private javax.swing.JButton ManageBookings;
    private javax.swing.JButton ManageCustomerBtn;
    private javax.swing.JButton PaymentCollection;
    private javax.swing.JButton Reports;
    private javax.swing.JButton SystemLoginRecordBtn;
    private javax.swing.JButton ViewEditCarBtn;
    private javax.swing.JButton ViewEditCarBtn1;
    private javax.swing.JLabel WelcomeMsg;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
