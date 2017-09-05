/*
 * MyBatis.java
 * 
 * Created on __DATE__, __TIME__
 */

package com.toolkit.auto.mybatis.frame;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import com.google.gson.Gson;
import com.toolkit.auto.mybatis.entity.FrameInfo;
import com.toolkit.auto.mybatis.service.FileCreate;
import com.toolkit.auto.mybatis.utils.Data;

/**
 * Mybatis generator
 * 
 * @author __USER__
 */
public class MyBatis extends javax.swing.JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Creates new form MyBatis */
    public MyBatis() {
        this.initComponents();
        Data.setPosition(this);
        String str = "";//Data.readFromSys("mybatis-5623487");
        if (str != null && !str.equals("")) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            Gson gson = new Gson();
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<HashMap<String, Object>>() {
            }.getType();
            hashMap = gson.fromJson(str, type);
            this.jTextField_ip.setText(hashMap.get("ip").toString());
            this.jTextField_port.setText(hashMap.get("port").toString());
            this.jTextField_dbName.setText(hashMap.get("schema").toString());
            this.jTextField_tbName.setText(hashMap.get("tables").toString());
            this.jTextField_userName.setText(hashMap.get("user").toString());
            this.jPasswordField_password.setText(hashMap.get("password").toString());
            this.jTextField_package_path.setText(hashMap.get("package_base").toString());
        }
    }

    //GEN-BEGIN:initComponents
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        this.jPanel1 = new javax.swing.JPanel();
        this.jLabel_ip = new javax.swing.JLabel();
        this.jLabel_port = new javax.swing.JLabel();
        this.jLabel_dbName = new javax.swing.JLabel();
        this.jLabel_tbName = new javax.swing.JLabel();
        this.jLabel_msg = new javax.swing.JLabel();
        this.jLabel_userName = new javax.swing.JLabel();
        this.jLabel_password = new javax.swing.JLabel();
        this.jTextField_ip = new javax.swing.JTextField();
        this.jTextField_port = new javax.swing.JTextField();
        this.jTextField_dbName = new javax.swing.JTextField();
        this.jTextField_tbName = new javax.swing.JTextField();
        this.jTextField_userName = new javax.swing.JTextField();
        this.jPasswordField_password = new javax.swing.JPasswordField();
        this.jPanel2 = new javax.swing.JPanel();
        this.jLabel_package_path = new javax.swing.JLabel();
        this.jTextField_package_path = new javax.swing.JTextField();
        this.jButton1 = new javax.swing.JButton();
        this.jButton2 = new javax.swing.JButton();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);默认点击关闭
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //mybaits代码生成1.0版本
        this.setTitle("mybatis\u4ee3\u7801\u751f\u62101.0\u7248");
        this.setResizable(true);
        //数据库信息
        this.jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("\u6570\u636e\u5e93\u4fe1\u606f,\u591a\u4e2a\u5e93\u6216\u591a\u4e2a\u8868\u4e4b\u95f4\u7528\",\"\u9694\u5f00"));
        //IP地址:
        this.jLabel_ip.setText("IP\u5730\u5740\uff1a");
        //端口:
        this.jLabel_port.setText("\u7aef\u53e3\uff1a");
        //库名:
        this.jLabel_dbName.setText("\u5e93\u540d\uff1a");
        //表名:
        this.jLabel_tbName.setText("\u8868\u540d\uff1a");
        //提示信息:
        this.jLabel_msg.setText("\u591a\u4e2a\u5e93\u6216\u591a\u4e2a\u8868\u4e4b\u95f4\u7528\",\"\u9694\u5f00");
        //用户名:
        this.jLabel_userName.setText("\u7528\u6237\u540d\uff1a");
        //密码:
        this.jLabel_password.setText("\u5bc6\u7801\uff1a");
        //IP地址输入框
        this.jTextField_ip.setText("127.0.0.1");
        this.jTextField_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jTextFieldIpActionPerformed(evt);
            }
        });
        //端口输入框
        this.jTextField_port.setText("3306");
        this.jTextField_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jTextFieldPortActionPerformed(evt);
            }
        });
        //库名输入框,多个库名之间用","隔开
        this.jTextField_dbName.setText("test");
        this.jTextField_dbName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jTextFieldDbNameActionPerformed(evt);
            }
        });
        //表名输入框,多个表之间用","隔开
        this.jTextField_tbName.setText("test");
        this.jTextField_tbName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jTextFieldTbNameActionPerformed(evt);
            }
        });
        //用户名输入框
        this.jTextField_userName.setText("root");
        this.jTextField_userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jTextFieldUserNameActionPerformed(evt);
            }
        });
        //密码输入框
        this.jPasswordField_password.setText("system");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        //按照水平来确定
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(this.jLabel_password).addComponent(this.jLabel_userName).addComponent(this.jLabel_tbName).addComponent(this.jLabel_dbName).addComponent(this.jLabel_port)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.jTextField_userName, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE).addComponent(this.jTextField_dbName, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE).addComponent(this.jTextField_tbName, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE).addComponent(this.jPasswordField_password, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE).addComponent(this.jTextField_port, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel_ip).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        //按照垂直来确定
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_ip).addComponent(this.jTextField_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_port).addComponent(this.jTextField_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_dbName).addComponent(this.jTextField_dbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_tbName).addComponent(this.jTextField_tbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_userName).addComponent(this.jTextField_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_password).addComponent(this.jPasswordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        //文件包名
        this.jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("\u6587\u4ef6\u5305\u540d"));
        //包路径:
        this.jLabel_package_path.setText("\u5305\u57fa\u672c\u8def\u5f84\uff1a");
        //包路径输入框
        this.jTextField_package_path.setText("com.fetchview");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel_package_path).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField_package_path, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabel_package_path).addComponent(this.jTextField_package_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));
        //确定按钮
        this.jButton1.setText("\u786e\u5b9a");
        this.jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jButton1ActionPerformed(evt);
            }
        });
        //退出按钮
        this.jButton2.setText("\u9000\u51fa");
        this.jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyBatis.this.jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(136, 136, 136).addComponent(this.jButton1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE).addComponent(this.jButton2)).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.jButton1).addComponent(this.jButton2)).addContainerGap()));

        this.pack();
    }// </editor-fold>
     //GEN-END:initComponents

    protected void jTextFieldIpActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub

    }

    protected void jTextFieldPortActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub

    }

    protected void jTextFieldDbNameActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub

    }

    protected void jTextFieldTbNameActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub

    }

    protected void jTextFieldUserNameActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // 退出
        System.exit(0);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // 确定 
        FrameInfo info = new FrameInfo();
        info.setIp(this.jTextField_ip.getText().trim());
        info.setPort(this.jTextField_port.getText().trim());
        info.setSchema(this.jTextField_dbName.getText().trim());
        info.setTables(this.jTextField_tbName.getText().trim());
        info.setUser(this.jTextField_userName.getText().trim());
        info.setPassword(new String(this.jPasswordField_password.getPassword()));
        info.setPackage_base(this.jTextField_package_path.getText().trim());
        try {
            String path_base = "E:\\U\\jcode\\code\\yw-project";
            info.setPath_base(path_base);
            info.setProject_name("yw-pay");
            info.setPackage_base("pay");
            info.setDubbo_port("20880");//dubbo占用端口
            //TODO 校验,生成代码框架信息,放置到项目下面
            //            if (FileCreate.start(info, 1)) {
            //                //生成代码成功
            //                Data.printINFO(this, "\u751f\u6210\u4ee3\u7801\u6210\u529f\uff0c\u653e\u5230\u9879\u76ee\u5f55\u4e0b");
            //            } else {
            //                //生成代码失败
            //                Data.printINFO(this, "\u751f\u6210\u4ee3\u7801\u5931\u8d25");
            //            }
            //TODO 校验,生成代码框架信息,放置到根目录下面2
            info.setPackage_base("communication");
            info.setDefinedPackage("comm");
            if (FileCreate.start(info, 2)) {
                //生成代码成功
                Data.printINFO(this, "\u751f\u6210\u4ee3\u7801\u6210\u529f\uff0c\u653e\u5230\u6839\u76ee\u5f55\u4e0b");
            } else {
                //生成代码失败
                Data.printINFO(this, "\u751f\u6210\u4ee3\u7801\u5931\u8d25");
            }
        } catch (Exception e) {
            //生成代码失败
            Data.printINFO(this, "\u751f\u6210\u4ee3\u7801\u5931\u8d25");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyBatis().setVisible(true);
            }
        });
    }

    //GEN-BEGIN:variables
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel_ip;
    private javax.swing.JLabel jLabel_port;
    private javax.swing.JLabel jLabel_dbName;
    private javax.swing.JLabel jLabel_tbName;
    private javax.swing.JLabel jLabel_msg;
    private javax.swing.JLabel jLabel_userName;
    private javax.swing.JLabel jLabel_password;
    private javax.swing.JLabel jLabel_package_path;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField_password;
    private javax.swing.JTextField jTextField_ip;
    private javax.swing.JTextField jTextField_port;
    private javax.swing.JTextField jTextField_dbName;
    private javax.swing.JTextField jTextField_tbName;
    private javax.swing.JTextField jTextField_userName;
    private javax.swing.JTextField jTextField_package_path;
    // End of variables declaration//GEN-END:variables

}