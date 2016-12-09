/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributed.computing;

import distributed.computing.appender.JTextAreaAppender;
import distributed.computing.bootstrap.Bootstrap;
import distributed.computing.bootstrap.BootstrapShutdownHook;
import distributed.computing.config.BootstrapServerConfig;
import distributed.computing.config.NodeContext;
import distributed.computing.domain.model.PeerNode;
import distributed.computing.listner.Listener;
import distributed.computing.listner.UdpListener;
import distributed.computing.messaging.broadcast.MessageCache;
import distributed.computing.util.SearchUtil;
import distributed.computing.util.Utils;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author yasitham
 */
public class peerForm extends javax.swing.JFrame {

    private static final Logger LOGGER = LogManager.getLogger(peerForm.class.getName());


    /**
     * Creates new form peerForm
     */
    public peerForm() {
        initComponents();
        JTextAreaAppender.addTextArea(this.logTxtArea);
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
        bsIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bsPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NodePort = new javax.swing.JTextField();
        applyButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NodeName = new javax.swing.JTextField();
        bsRegButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        keyWord = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLocalFileNames = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        logTxtArea = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        peerName1 = new javax.swing.JLabel();
        peerName2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Peer Properties");
        setName("PeerConfig"); // NOI18N

        jLabel1.setText("BS IP:");

        bsIP.setEnabled(false);
        bsIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsIPActionPerformed(evt);
            }
        });

        jLabel2.setText("BS Port:");

        bsPort.setEnabled(false);

        jLabel3.setText("Node Port:");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Node Name:");

        bsRegButton.setText("Register");
        bsRegButton.setEnabled(false);
        bsRegButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsRegButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Peer 1:");

        jLabel6.setText("Peer 2:");

        jLabel7.setText("Key Word:");

        keyWord.setEnabled(false);

        searchButton.setText("Search");
        searchButton.setEnabled(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        resultArea.setEditable(false);
        resultArea.setColumns(20);
        resultArea.setRows(5);
        jScrollPane1.setViewportView(resultArea);

        jLabel8.setText("Result:");

        txtLocalFileNames.setEditable(false);
        txtLocalFileNames.setColumns(20);
        txtLocalFileNames.setRows(5);
        jScrollPane2.setViewportView(txtLocalFileNames);

        logTxtArea.setEditable(false);
        logTxtArea.setBackground(new java.awt.Color(1, 1, 1));
        logTxtArea.setColumns(20);
        logTxtArea.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        logTxtArea.setForeground(new java.awt.Color(82, 181, 97));
        logTxtArea.setRows(5);
        logTxtArea.setLineWrap(true);
        logTxtArea.setWrapStyleWord(true);
        logTxtArea.setEditable (false);
        jScrollPane3.setViewportView(logTxtArea);
        jScrollPane3.setVisible(true);

        jLabel9.setText("File names");

        jLabel10.setText("Log");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(NodeName, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addComponent(bsIP))
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(NodePort, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(bsPort, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bsRegButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(applyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel9)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(27, 27, 27)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(keyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25)
                                    .addComponent(searchButton)))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(peerName2))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(peerName1))))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NodePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(applyButton)
                    .addComponent(jLabel4)
                    .addComponent(NodeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bsIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(bsPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bsRegButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(peerName1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(peerName2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(keyWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bsIPActionPerformed

    private void bsRegButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsRegButtonActionPerformed
        String bsIpStr = bsIP.getText();
        String bsPortStr = bsPort.getText();
        
        boolean valid = true;
        if (bsIpStr.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Bootstrap IP cannot be empty");
            valid = false;
        }
        
        if (bsPortStr.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Bootstrap Port cannot be empty");
            valid = false;
        }
         
        try{
           int intBsPort = Integer.parseInt(bsPortStr);
           if (!(intBsPort >= 1024 && intBsPort <= 65535)) {
                JOptionPane.showMessageDialog(rootPane, "Bootstrap Port should be a valid port number (1024 - 65535)");
                valid = false;
           }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Bootstrap Port should be a valid port number (1024 - 65535)");
            valid = false;
        }
        
        if (valid) {
            BootstrapServerConfig.setHost(bsIP.getText());
            BootstrapServerConfig.setPort(Integer.parseInt(bsPort.getText()));
            LOGGER.info("BS IP: " + bsIP.getText() + ", BS Port: " +  bsPort.getText());

            Listener udpListener = UdpListener.getInstance();
            udpListener.initListener(NodeContext.getPort());

            Bootstrap.register();
            int i = 0;
            for (PeerNode node : NodeContext.getChildren()){
                if(i == 0)
                    peerName1.setText(node.getUsername());
                else if (i == 1){
                    peerName2.setText(node.getUsername());
                }
                i++;
            } 
            Runtime.getRuntime().addShutdownHook(new BootstrapShutdownHook());
            MessageCache.initCachingScheduler();//init caching scheduler
            
            keyWord.setEnabled(true);
            searchButton.setEnabled(true);
            resultArea.setEnabled(true);
            txtLocalFileNames.setEnabled(true);
            txtLocalFileNames.setEditable(valid);
        }
        
        
    }//GEN-LAST:event_bsRegButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        String nodePortStr = NodePort.getText();
        String nodeNameStr = NodeName.getText();
        
        boolean valid = true;
        if (nodeNameStr.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Node name cannot be empty");
            valid = false;
        }
        
        if (nodeNameStr.contains(" ")){
            JOptionPane.showMessageDialog(rootPane, "Node name should be a single word");
            valid = false;
        }
        
        if (nodePortStr.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Node port cannot be empty");
            valid = false;
        }
        
        try{
           int intPort = Integer.parseInt(nodePortStr);
           if (!(intPort >= 1024 && intPort <= 65535)) {
                JOptionPane.showMessageDialog(rootPane, "Node port should be a valid port number (1024 - 65535)");
                valid = false;
           }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Node port should be a valid port number (1024 - 65535)");
            valid = false;
        }
        if (valid) {
            NodeContext.setIp(Utils.getIP());
            NodeContext.setPort(Integer.parseInt(NodePort.getText()));
            NodeContext.setUserName(NodeName.getText()); 
            LOGGER.info("Node Name: "+ NodeName.getText() + ", Node IP: "+  Utils.getIP()+", Node Port:"+ NodePort.getText());
            bsIP.setEnabled(true);
            bsPort.setEnabled(true);
            bsRegButton.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_applyButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        SearchUtil.search(keyWord.getText());

    }//GEN-LAST:event_searchButtonActionPerformed
   
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
            java.util.logging.Logger.getLogger(peerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(peerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(peerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(peerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new peerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NodeName;
    private javax.swing.JTextField NodePort;
    private javax.swing.JButton applyButton;
    private javax.swing.JTextField bsIP;
    private javax.swing.JTextField bsPort;
    private javax.swing.JButton bsRegButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField keyWord;
    private javax.swing.JTextArea logTxtArea;
    private javax.swing.JLabel peerName1;
    private javax.swing.JLabel peerName2;
    private javax.swing.JTextArea resultArea;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextArea txtLocalFileNames;
    // End of variables declaration//GEN-END:variables

  
}
