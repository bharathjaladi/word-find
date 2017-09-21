import javax.swing.*;
import java.applet.*;
import java.awt.*;

public class Form3 extends JApplet{

public void init()
{
JFrame frame = new JFrame("Form");
//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
JPanel panel = new JPanel();

JLabel label1 = new JLabel("");
JTextField field = new JTextField(20);
//JButton button1 = new JButton("OK");
//JButton button2 = new JButton("Cancel");
Container c;
c=frame.getContentPane();
c.setLayout(null);
JLabel name=new JLabel("Name :");
JLabel compcode=new JLabel("Company Code :");
JLabel cardno=new JLabel("Card Number: ");
JLabel cardtype=new JLabel("Card Type :");
JLabel pin=new JLabel("Pin :");
JLabel bldgrp=new JLabel("Blood Group :");
JLabel empcode=new JLabel("Employee Code :");
JLabel dob=new JLabel("DOB :");
JLabel valupto=new JLabel("Valid Upto :");
JLabel jdate=new JLabel("Joining Date :");
JLabel dept=new JLabel("Department :");
JLabel uid=new JLabel("UID :");

JTextField nametxt=new JTextField(10);
JComboBox compcodetxt=new JComboBox();
JTextField cardnumtxt=new JTextField(10);
JTextField cardtypetxt=new JTextField(10);
JTextField pintxt=new JTextField(10);
JComboBox bldgrptxt=new JComboBox();
JTextField empcodetxt=new JTextField(10);
JTextField dobtxt=new JTextField(10);
JTextField valuptotxt=new JTextField(10);
JTextField jdatetxt=new JTextField(10);
JTextField depttxt=new JTextField(10);
JTextField uidtxt=new JTextField(10);



name.setBounds(10, 10, 100, 25);
nametxt.setBounds(110, 10, 100, 25);
compcode.setBounds(10, 40, 100, 25);
compcodetxt.setBounds(110, 40, 100, 25);
cardno.setBounds(10, 70, 100, 25);
cardnumtxt.setBounds(110, 70, 100, 25);
pin.setBounds(10, 110, 100, 25);
pintxt.setBounds(110, 110, 100, 25);
bldgrp.setBounds(10, 140, 100, 25);
bldgrptxt.setBounds(110, 140, 100, 25);
empcode.setBounds(10, 170, 100, 25);
empcodetxt.setBounds(110, 170, 100, 25);
dob.setBounds(10, 200, 100, 25);
dobtxt.setBounds(110, 200, 100, 25);
valupto.setBounds(10, 230, 100, 25);
valuptotxt.setBounds(110, 230, 100, 25);
jdate.setBounds(10, 260, 100, 25);
jdatetxt.setBounds(110, 260, 100, 25);
dept.setBounds(10, 290, 100, 25);
depttxt.setBounds(110, 290, 100, 25);
uid.setBounds(10, 320, 100, 25);
uidtxt.setBounds(110, 320, 100, 25);

//button1.setBounds(10, 50, 75, 25);
//button2.setBounds(10, 70, 75, 25);

c.add(name); c.add(nametxt);
c.add(compcode); c.add(compcodetxt);
c.add(cardno); c.add(cardnumtxt);
c.add(pin); c.add(pintxt);
c.add(bldgrp); c.add(bldgrptxt);
c.add(empcode); c.add(empcodetxt);
c.add(dob); c.add(dobtxt);
c.add(valupto); c.add(valuptotxt);
c.add(jdate); c.add(jdatetxt);
c.add(dept); c.add(depttxt);
c.add(uid); c.add(uidtxt);


//panel.add(button1);
//panel.add(button2);
//frame.add(panel);
frame.setSize(350,400);
//frame.pack();
frame.setVisible(true);
  }

public static void main(String[] args) {
}
}