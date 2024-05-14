package com.mycompany.empolyee;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Team extends JFrame {
private JLabel jLabel1;
private JPanel TitlePanel;
private JPanel panelBoutons;
private JPanel affpanel;
private JButton Return;
private JButton show;
private JScrollPane jScrollPane2;
private JTable table;


public Team(){
    setTitle("EMPLOYER List");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    
    jLabel1 = new JLabel();
    jLabel1.setFont(new Font("Comic Sans MS", 0, 36));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("The EMPLOYEES are :");
    
    TitlePanel = new JPanel();
    TitlePanel.setBackground(new Color(138, 138, 138));
    TitlePanel.setLayout(new FlowLayout());
    TitlePanel.add(jLabel1);
    
    Return = new JButton("Return");
    Return.setFont(new Font("Serif", 0, 18));
     Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
     
     show = new JButton("Show");
     show.setFont(new Font("Serif", 0, 18));
     show.addActionListener(e->findActionPerformed());
    
    panelBoutons = new JPanel();
    panelBoutons.setBackground(new Color(138, 138, 138));
    panelBoutons.setLayout(new FlowLayout());
    panelBoutons.add(show);
    panelBoutons.add(Return);
    
    
    affpanel= new JPanel();
    affpanel.setBackground(new Color(138, 138, 138));
    affpanel.setLayout(new FlowLayout());
    
    table = new JTable();
    table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Name", "Gender", "Age", "Phone", "Email", "position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, 
                java.lang.String.class, 
                java.lang.String.class,
                java.lang.String.class, 
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
    
    jScrollPane2 = new JScrollPane();
    jScrollPane2.setViewportView(table);
    affpanel.add(jScrollPane2);
    
    setLayout(new GridLayout(3,1));
    add(TitlePanel);
    
    add(affpanel);
    add(panelBoutons);
    
}

public static Connection seconnecter() {
try {
Class.forName("com.mysql.cj.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/employees";
String nom_user = "root";
String mdp = "";
Connection cnx = DriverManager.getConnection(url, nom_user, mdp);
System.out.println("Connexion établie");
return cnx;
} catch (ClassNotFoundException | SQLException e) {
e.printStackTrace(); // Afficher la trace de la pile pour le débogage
return null;
}
}

public void findActionPerformed() {
    try {
        Connection connection = seconnecter();
        String selectQuery = "SELECT name, gender, age,phone,email,position FROM team";
       

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = selectStatement.executeQuery()) {
           
            while (resultSet.next()) {
                    
                    String name = resultSet.getString("name");
                    String gender = resultSet.getString("gender");
                    String age = resultSet.getString("age");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    String pos = resultSet.getString("position");
                    
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    Object[] rowData = {name, gender, age, phone, email, pos};
                    tableModel.addRow(rowData);
                    
                   
                }
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void ReturnActionPerformed(ActionEvent evt) {
        Employee p = new Employee();
        p.setVisible(true);
        dispose();
        }
      
}