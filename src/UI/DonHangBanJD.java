/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import DAO.BanDAO;
import DAO.DonHangBanDAO;
import Entyti.Ban;
import Entyti.DonHangBan;
import Utils.JDBCHelper;
import Utils.MsgBox;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Thuyen PC
 */
public class DonHangBanJD extends javax.swing.JDialog {

    DonHangBanDAO dao = new DonHangBanDAO();
    BanDAO BanDAO = new BanDAO();

    public DonHangBanJD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        this.setLocationRelativeTo(null);
        Date now = new Date();
        gioBatDau();
        fillComboBoxMaBan();
    }
    
    void gioBatDau(){
        Date now = new Date();
        String check = new SimpleDateFormat("HH:mm").format(now);
        txtGioBatDau.setText(check);
    }

    void soGio() {
        try {
            String gioBatDau = txtGioBatDau.getText();
            String gioKetThuc = txtGioKetThuc.getText();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date d1 = format.parse(gioBatDau);
            Date d2 = format.parse(gioKetThuc);
            float diff = d2.getTime() - d1.getTime();
            Float soGio = diff / (60 * 60 * 1000) % 24;
            String.format("%.2f", soGio);
            txtSoGio.setText(soGio + "");

        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void tongTien() {
        Float a = Float.parseFloat(txtSoGio.getText());
        Float b = Float.parseFloat(txtDonGia.getText());
        Float tongTien = a * b;
        String.format("%.2f", tongTien);
        txtTongTien.setText(tongTien + "");
    }

    void fillComboBoxMaBan() {
        try {
            dao.comboBoxMaBan(cboMaBan);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void fillDonGia() {
        try {
            String a = dao.fillDonGia(cboMaBan);
            txtDonGia.setText(a);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void clearForm() {
        txtDonGia.setText("");
        txtGioBatDau.setText("");
        txtGioKetThuc.setText("");
        txtMaHĐBan.setText("");
        gioBatDau();
        txtTongTien.setText("");
        cboMaBan.setSelectedIndex(0);

    }

    DonHangBan getForm() {
        DonHangBan dhb = new DonHangBan();
        dhb.setGia(Float.parseFloat(txtDonGia.getText()));
        dhb.setGioThue(Float.parseFloat(txtSoGio.getText()));
        dhb.setMaBan(Integer.parseInt((String) cboMaBan.getSelectedItem()));
        dhb.setMaDHB(txtMaHĐBan.getText());
        dhb.setThoiGianBatDau(txtGioBatDau.getText());
        dhb.setThoiGianKetThuc(txtGioKetThuc.getText());
        dhb.setTongTien(Float.parseFloat(txtTongTien.getText()));
        return dhb;
    }

    void insert() {
        DonHangBan dhb = getForm();
        try {
            dao.insert(dhb);
            //this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Thêm hóa đơn bàn thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm hóa đơn bàn thất bại");
        }
    }

    boolean checkForm() {
        boolean kq = true;
        StringBuilder sb = new StringBuilder();
        if (txtMaHĐBan.getText().length() == 0) {
            sb.append("Mã ĐH Bàn không được để trống\n");
            kq = false;
        }

        if (dao.selectById(txtMaHĐBan.getText()) != null) {
            sb.append("Mã ĐH Bàn này đã tồn tại trong CSDL vui lòng nhập mã ĐH bàn khác\n");
            kq = false;

        }

        if (cboMaBan.getSelectedItem().equals("Chọn mã bàn")) {
            sb.append("Mã bàn không  được để trống\n");
            kq = false;
        }

        /*if (cboMaNV.getSelectedIndex() == 0) {
            sb.append("Vui lòng chọn mà nhân viên\n");
            kq = false;
        }*/
        try {
            if (txtGioBatDau.getText().length() == 0) {
                sb.append("Giờ bắt đầu không được để trống\n");
                kq = false;
            } else {
                Date d = new SimpleDateFormat("HH:mm").parse(txtGioBatDau.getText());
                //Date d = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayDK.getText());
            }
        } catch (ParseException ex) {
            sb.append("Giờ bắt đầu không đúng định dạng (HH:mm)\n");
            kq = false;
        }
        
        try {
            if (txtGioKetThuc.getText().length() == 0) {
                sb.append("Giờ kết thúc không được để trống\n");
                kq = false;
            }else {
                Date d = new SimpleDateFormat("HH:mm").parse(txtGioKetThuc.getText());
                //Date d = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayDK.getText());
            }
        } catch (ParseException ex) {
            sb.append("Giờ kết thúc không đúng định dạng (HH:mm)\n");
            kq = false;
        }
       
        if (txtSoGio.getText().equals("")) {
            sb.append("Số giờ không  được để trống\n");
            kq = false;
        }else if (Float.parseFloat(txtSoGio.getText()) < 0){
            sb.append("Vui lòng nhập giờ kết thúc lớn hơn giờ bắt đầu\n");
            kq = false;
        }
        if (txtDonGia.getText().equals("")) {
            sb.append("Đơn giá không  được để trống\n");
            kq = false;
        }

        if (txtTongTien.getText().equals("")) {
            sb.append("Tổng tiền không  được để trống\n");
            kq = false;
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Invalidate", JOptionPane.ERROR_MESSAGE);
        }
        return kq;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGioBatDau = new javax.swing.JTextField();
        txtGioKetThuc = new javax.swing.JTextField();
        txtSoGio = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        cboMaBan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtMaHĐBan = new javax.swing.JTextField();
        btnQuayLai1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HÓA ĐƠN BÀN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(224, 224, 224))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Giờ Bắt Đầu");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Giờ Kết Thúc");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số Giờ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Mã Bàn");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Đơn Giá");

        txtGioBatDau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGioBatDau.setBorder(null);

        txtGioKetThuc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGioKetThuc.setBorder(null);
        txtGioKetThuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGioKetThucFocusLost(evt);
            }
        });

        txtSoGio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoGio.setBorder(null);

        txtDonGia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDonGia.setBorder(null);

        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTongTien.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tổng Tiền");

        btnXacNhan.setBackground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(0, 153, 153));
        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(0, 153, 153));
        btnMoi.setText("Mới");
        btnMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        cboMaBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboMaBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboMaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaBanActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Ma ĐH Bàn");

        txtMaHĐBan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaHĐBan.setBorder(null);

        btnQuayLai1.setBackground(new java.awt.Color(255, 255, 255));
        btnQuayLai1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnQuayLai1.setForeground(new java.awt.Color(0, 153, 153));
        btnQuayLai1.setText("Quay Lại");
        btnQuayLai1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        btnQuayLai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLai1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuayLai1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(txtMaHĐBan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGioBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGioKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoGio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboMaBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHĐBan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoGio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuayLai1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboMaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaBanActionPerformed
        fillDonGia();
    }//GEN-LAST:event_cboMaBanActionPerformed

    private void txtGioKetThucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGioKetThucFocusLost
        soGio();
        tongTien();
    }//GEN-LAST:event_txtGioKetThucFocusLost

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (checkForm() == true){
            //JOptionPane.showMessageDialog(this, "Hí ae");
            Ban b = new Ban();
            b.setMaBan(Integer.parseInt((String) cboMaBan.getSelectedItem()));
            BanDAO.update(b);
            insert();
        }   
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnQuayLai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLai1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnQuayLai1ActionPerformed

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
            java.util.logging.Logger.getLogger(DonHangBanJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonHangBanJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonHangBanJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonHangBanJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DonHangBanJD dialog = new DonHangBanJD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnQuayLai1;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cboMaBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGioBatDau;
    private javax.swing.JTextField txtGioKetThuc;
    private javax.swing.JTextField txtMaHĐBan;
    private javax.swing.JTextField txtSoGio;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
