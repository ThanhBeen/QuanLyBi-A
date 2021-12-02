/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import Entyti.KhachHang;
import Entyti.NhanVien;
import Utils.Auth;
import Utils.MsgBox;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thuyen PC
 */
public class KhachHangJD extends javax.swing.JDialog {

    KhachHangDAO dao = new KhachHangDAO();
    int row = 0;
    int so =0;
    int check = 0;
    
    public KhachHangJD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    private void init(){
        this.setLocationRelativeTo(null);
        fillTable();
        fillComboBoxMaNV();
        edit();
    }
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHang> list = dao.selectAll();
            for (KhachHang kh : list) {
                Object[] ob = new Object[]{
                    kh.getMaKH(), kh.getHoTen(), kh.getGioiTinh(), 
                    kh.getSdt(), kh.getMaNV(), kh.getNgayDK()
                };
                model.addRow(ob);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }
    
    void edit(){
        try {
            String maKH = (String)  tblKhachHang.getValueAt(this.row, 0);
            KhachHang kh = dao.selectById(maKH);
            if(kh != null){
                setForm(kh);
                tabsKhachHang.setSelectedIndex(0);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void setForm(KhachHang kh){
        txtHoTen.setText(kh.getHoTen());
        txtMaKH.setText(kh.getMaKH());
        txtNgayDK.setText(kh.getNgayDK() + "");
        txtSDT.setText(kh.getSdt() + "");
        cboMaNV.setSelectedItem(kh.getMaNV());
        if(kh.getGioiTinh().equals("Nam")){
            rdoNam.setSelected(true);
        }else{
            rdoNu.setSelected(true);
        }
    }
    
     KhachHang getForm() {
        KhachHang kh = new KhachHang();
        if(rdoNam.isSelected() == true){
            kh.setGioiTinh("Nam");
        }else{
            kh.setGioiTinh("Nữ");
        }
        kh.setHoTen(txtHoTen.getText());
        kh.setMaKH(txtMaKH.getText());
        kh.setMaNV(cboMaNV.getSelectedItem()+ "");
        try {
            String ngayDK = txtNgayDK.getText();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngayDK);
            kh.setNgayDK(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        kh.setSdt(txtSDT.getText());
        return kh;
    }
     
     void clearForm() {
        txtHoTen.setText("");
        txtMaKH.setText("");
        txtNgayDK.setText("");
        txtSDT .setText("");
        cboMaNV.setSelectedIndex(0);
        rdoNam.setSelected(false);
        rdoNam.setSelected(false);
    }

    void insert() {
        KhachHang kh = getForm();
        try {
            dao.insert(kh);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm mới thất bại");
        }
    }

    void update() {
        KhachHang kh = getForm();
        try {
            dao.update(kh);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật thất bại");
        }
    }

    void delete() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền xóa khách hàng");
        } else {
            if (MsgBox.comfirm(this, "Bạn có muốn xóa người học này?")) {
                String manv = txtMaKH.getText();
                try {
                    dao.delete(manv);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "Xóa thành công");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại");
                }
            }
        }
    }
    
    void updateState(){
        if(so == 1){
            txtMaKH.setEditable(false);
            txtMaKH.setForeground(Color.LIGHT_GRAY);
        }else{
            txtMaKH.setEditable(true);
            txtMaKH.setForeground(Color.BLACK);

        }
    }
    
    void fillComboBoxMaNV(){
        try{
            dao.comboBoxMaNV(cboMaNV);
        }catch(Exception e){
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    boolean checkForm() {
        boolean kq = true;
        StringBuilder sb = new StringBuilder();
        if (txtMaKH.getText().length() == 0) {
            sb.append("Mã KH không được để trống\n");
            kq = false;
        }
        if(check != 0){
            if(dao.selectById(txtMaKH.getText()) != null){
            sb.append("Mã khách hàng này đã tồn tại trong CSDL vui lòng nhập mã người học khác\n");
            kq = false;
            }
        }

        if (txtHoTen.getText().equals("")) {
            sb.append("Họ tên không được để trống\n");
            kq = false;
        }

        if (rdoNam.isSelected() == false && rdoNu.isSelected() == false) {
            sb.append("Vui lòng chọn giới tính\n");
            kq = false;
        }
        if (txtSDT.getText().equals("")) {
            sb.append("Số điện thoại không  được để trống\n");
            kq = false;
        } else {
            String rephone = "0\\d{9}";
            if (!txtSDT.getText().matches(rephone)) {
                sb.append("Số điện thoại không đúng định dạng (0xx xxx xxxx)\n");
            }
        }
        
        if (cboMaNV.getSelectedIndex() == 0) {
            sb.append("Vui lòng chọn mà nhân viên\n");
            kq = false;
        }
        
        try {
            if (txtNgayDK.getText().length() == 0) {
                sb.append("Ngày đăng ký không được để trống\n");
                kq = false;
            } else {
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgayDK.getText());
            }
        } catch (ParseException ex) {
            sb.append("Ngày sinh không đúng định dạng (yyyy-MM-dd)\n");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabsKhachHang = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtMaKH = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        cboMaNV = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtNgayDK = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        txtSDT = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KHÁCH HÀNG THÂN THIẾT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabsKhachHang.setBackground(new java.awt.Color(0, 102, 102));
        tabsKhachHang.setForeground(new java.awt.Color(0, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Mã Khách Hàng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Giới Tính");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Họ Và Tên");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Mã NV");

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 153, 153));
        btnThem.setText("Thêm");
        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtMaKH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaKH.setText("KH003");
        txtMaKH.setBorder(null);

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoTen.setText("Nguyễn Văn A");
        txtHoTen.setBorder(null);

        jSeparator2.setBackground(new java.awt.Color(0, 153, 153));

        jSeparator3.setBackground(new java.awt.Color(0, 153, 153));

        jSeparator4.setBackground(new java.awt.Color(0, 153, 153));

        jSeparator5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Số Điện Thoại");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jSeparator6.setBackground(new java.awt.Color(0, 153, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Ngày ĐK");

        txtNgayDK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNgayDK.setText("2021-07-07");
        txtNgayDK.setBorder(null);

        jSeparator7.setBackground(new java.awt.Color(0, 153, 153));

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSDT.setText("0988168268");
        txtSDT.setBorder(null);

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 153, 153));
        btnSua.setText("Sửa");
        btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
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

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 153, 153));
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 3));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(txtNgayDK, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator5)
                                .addComponent(cboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rdoNam)
                                    .addGap(66, 66, 66)
                                    .addComponent(rdoNu))))
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(93, 93, 93))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNam)
                        .addComponent(rdoNu))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayDK, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        tabsKhachHang.addTab("CẬP NHẬT", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblKhachHang.setForeground(new java.awt.Color(0, 102, 102));
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ tên", "Giới tính", "SĐT", "Ma NV", "Ngay DKl"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setRowHeight(24);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
        );

        tabsKhachHang.addTab("DANH SÁCH", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabsKhachHang)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabsKhachHang))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        check++;
        if (checkForm() == true)
            insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        check =0;
        if (checkForm() == true)
            update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        so = 2;
        clearForm();
        updateState();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        if(evt.getClickCount() == 2){
            so = 1;
            updateState();
            this.row = tblKhachHang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

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
            java.util.logging.Logger.getLogger(KhachHangJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangJD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhachHangJD dialog = new KhachHangJD(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboMaNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabsKhachHang;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgayDK;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
