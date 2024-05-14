package com.mycompany.empolyee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;
public class Register extends JFrame {
    private JTextField FirstName;
    private JTextField LastName;
    private JTextField Age;
    private JTextField Email;
    private JTextField Phone;
    private JRadioButton Homme;
    private JRadioButton Femme;
    private JComboBox<String> Position ;
    private JButton Submit;
    private JButton Reset;
    private JButton Exit;
    private JButton Return;
    private JLabel Cpos;
    
    public Register(){
    setTitle("Register an employee");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    FirstName = new JTextField(20);
    LastName = new JTextField(20);
    Age = new JTextField(20);
    Phone = new JTextField(20);
    Email = new JTextField(20);
    
    JLabel labelFName = new JLabel("First Name:");
    labelFName.setFont(new Font("Serif", 0, 18));
    JLabel labelLName = new JLabel("Last Name:");
    labelLName.setFont(new Font("Serif", 0, 18));
    JLabel labelAge = new JLabel("Age:");
    labelAge.setFont(new Font("Serif", 0, 18));
    JLabel labelPhone = new JLabel("phone:");
    Phone.setFont(new Font("Serif", 0, 18));
    JLabel labelEmail = new JLabel("Email:");
    Email.setFont(new Font("Serif", 0, 18));
    
    
    
    JLabel labelGender = new JLabel("Gender");
    labelGender.setFont(new Font("Serif", 0, 18));
    Homme = new JRadioButton("Male");
    Femme = new JRadioButton("Female");
    ButtonGroup genreGroup = new ButtonGroup();
    genreGroup.add(Homme);
    Homme.setBackground(new Color(138, 138, 138));
    genreGroup.add(Femme);
    Femme.setBackground(new Color(138, 138, 138));
    
    Submit = new JButton("Submit");
    Submit.setFont(new Font("Serif", 0, 18));
    Submit.addActionListener(e->enregistrerFormulaire());
    Reset = new JButton("Reset");
    Reset.setFont(new Font("Serif", 0, 18));
    Reset.addActionListener(e->viderFormulaire());
    Exit = new JButton("Exit");
    Exit.setFont(new Font("Serif", 0, 18));
//    Submit.addActionListener(e -> submit());
//    Reset.addActionListener(e -> reset());
    Exit.addActionListener(e -> exit());
    
    JPanel ptitle=new JPanel();
    ptitle.setBackground(new Color(138, 138, 138));
    JLabel title =new JLabel("REGISTER AN EMPLOYEE ");
    title.setFont(new Font("Comic Sans MS", 0, 24));
    ptitle.add(title);
    
    JPanel panelInformation = new JPanel();
    panelInformation.setBackground(new Color(138, 138, 138));
    panelInformation.setLayout(new FlowLayout());
    panelInformation.add(labelFName);
    panelInformation.add(FirstName);
    panelInformation.add(labelLName);
    panelInformation.add(LastName);
    
    JPanel panelAgeGender = new JPanel();
    panelAgeGender.setBackground(new Color(138, 138, 138));
    panelAgeGender.setLayout(new FlowLayout());
    panelAgeGender.add(labelAge);
    panelAgeGender.add(Age);

    JPanel panelGender = new JPanel();
    panelGender.setBackground(new Color(138, 138, 138));
    panelGender.setLayout(new FlowLayout());
    panelGender.add(labelGender);
    panelGender.add(Homme);
    panelGender.add(Femme);
    
    
    JPanel panelPhone = new JPanel();
    panelPhone.setBackground(new Color(138, 138, 138));
    panelPhone.setLayout(new FlowLayout());
    panelPhone.add(labelPhone);
    panelPhone.add(Phone);
    
    
    JPanel panelEmail = new JPanel();
    panelEmail.setBackground(new Color(138, 138, 138));
    panelEmail.setLayout(new FlowLayout());
    panelEmail.add(labelEmail);
    panelEmail.add(Email);
    
    
    Return = new JButton("Return");
    Return.setFont(new Font("Serif", 0, 18));
     Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        
    JPanel panelBoutons = new JPanel();
    panelBoutons.setBackground(new Color(138, 138, 138));
    panelBoutons.setLayout(new FlowLayout());
    panelBoutons.add(Submit);
    panelBoutons.add(Reset);
    panelBoutons.add(Return);
    panelBoutons.add(Exit);
    
    JPanel pos=new JPanel();
    pos.setBackground(new Color(138, 138, 138));
    pos.setLayout(new FlowLayout());
    Position  = new JComboBox<>();
    Cpos  = new JLabel();
    Cpos.setText("Choose The Position:");
    Position.setModel(
            new DefaultComboBoxModel<>(new String[] 
           { "CEO",
             "CTO",
             "PROJECT MANAGER",
             "SALES MANAGER",
             "SOFTWARE DEVELOPER",
             "SYSTEM INTEGRATOR",
             "DEVOPS ENGINEER",
             "SOFTWARE ARCHITECT",
             "SUPPORT TECHNICIAN",
             "MAINTENANCE MANAGER",
             "HR MANAGER"}
            ));
    pos.add(Cpos);
    pos.add(Position);
    
    setLayout(new GridLayout(8, 1));
    add(ptitle);
    add(panelInformation);
    add(panelAgeGender);
    add(panelGender);
    add(panelPhone);
    add(panelEmail);
    add(pos);
    add(panelBoutons);

    }
    
    private void viderFormulaire() {
     Age.setText("");
     Email.setText("");
     FirstName.setText("");
     LastName.setText("");
     Phone.setText("");
     Homme.setSelected(false);
     Femme.setSelected(false);
     
}
    void exit(){
    dispose();
    }
    
    void enregistrerFormulaire(){
String name = FirstName.getText()+" "+LastName.getText();
        String age1=Age.getText();
        String phone1=Phone.getText();
        String email1=Email.getText();
        String p="";
        String gender1="";
        if (Homme.isSelected()){
            gender1="Male";
        }else if(Femme.isSelected()){
            gender1="Female";
        }
        if(Position.getSelectedItem()!=null){
            p=(String) Position.getSelectedItem();
           try {
Connection connection = seconnecter();
String sql = "INSERT INTO team (name, gender, age,phone,email,position)VALUES (?, ?, ?, ?, ?, ?)";
try (PreparedStatement statement = connection.prepareStatement(sql)) {
statement.setString(1, name);
statement.setString(2, gender1);
statement.setString(3, age1);
statement.setString(4, phone1);
statement.setString(5, email1);
statement.setString(6,p);
int rowsInserted = statement.executeUpdate();
if (rowsInserted > 0) {
    JOptionPane.showMessageDialog(this, "Successful registration to the team !");
     Age.setText("");
     Email.setText("");
     FirstName.setText("");
     LastName.setText("");
     Phone.setText("");
     Homme.setSelected(false);
     Femme.setSelected(false);
     
    
System.out.println("Successful registration !");
}
}
} catch (SQLException e) {
System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
} 
           
        }}
    
    
    
    
    
public static Connection seconnecter() {
    String url = "jdbc:mysql://localhost:3306/employees";
    String nom_user = "root";
    String mdp = "";
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection cnx = DriverManager.getConnection(url, nom_user, mdp);
        System.out.println("Connexion établie");
        return cnx;
    }
    catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace(); // Afficher la trace de la pile pour le débogage
    return null;
    }
}

private void ReturnActionPerformed(ActionEvent evt) {
        Employee p = new Employee();
        p.setVisible(true);
        dispose();
        }
}
