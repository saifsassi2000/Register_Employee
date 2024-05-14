package com.mycompany.empolyee;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class Employee extends JFrame {
    
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
 
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton registration;
    private JButton ShowTeam;

    public Employee()
    {
    setTitle("Welcome to Employees Form");
    setSize(550, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    
    jPanel1 = new JPanel();
    jLabel1 = new JLabel();
    
    jPanel2 = new JPanel();
    jLabel2 = new JLabel();
    
    jPanel3 = new JPanel();
    jLabel3 = new JLabel();
    
    registration = new JButton();
    ShowTeam = new JButton();
    
    jPanel1.setBackground(new Color(138, 138, 138));
    jPanel2.setBackground(new Color(138, 138, 138));
    jPanel3.setBackground(new Color(138, 138, 138));
    
    jLabel1.setFont(new Font("Comic Sans MS", 0, 36));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Welcome to Employees Form");
    jLabel1.setBorder(new EmptyBorder(20,0,0,0));
    
    jPanel1.setLayout(new FlowLayout());
    jPanel1.add(jLabel1);
    
    jPanel2.setLayout(new FlowLayout());
    jPanel2.add(registration);
    
    jPanel3.setLayout(new FlowLayout());
    jPanel3.add(ShowTeam);
    
    setLayout(new GridLayout(3,1));
    add(jPanel1);
    add(jPanel2);
    add(jPanel3);
    
    registration.setFont(new java.awt.Font("Serif", 0, 18)); 
    registration.setText("Register The employee");
    registration.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            registrationActionPerformed(evt);
        }
        });
    
        ShowTeam.setFont(new java.awt.Font("Serif", 0, 18)); 
        ShowTeam.setText("Show Team");
        ShowTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showTActionPerformed(evt);
            }
        });
    
  
    
    }    
     private void showTActionPerformed(ActionEvent evt) {                                     
        Team t = new Team();
        t.setVisible(true);
        
        dispose();
    }                                    

    private void registrationActionPerformed(ActionEvent evt) {                                             
        Register r = new Register();
        r.setVisible(true);
        dispose();
   } 

    public static void main(String[] args) {
        new Employee().setVisible(true);
    }
}