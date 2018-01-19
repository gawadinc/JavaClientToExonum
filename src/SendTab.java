import communication.PipeHttp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendTab extends JPanel implements ActionListener  {

    JLabel label1, label2, label3;

    JButton btnMultiply, btnClear; //btnPlus, btnMinus,
    JTextField txtField1, txtField2, txtField3;

    public SendTab(){
        setLayout(new GridLayout(5,2));
        //setTitle("Simple Calculator using Swing");
        //initializing label
        label1 = new JLabel("From pub key :",JLabel.RIGHT);
        label2 = new JLabel("To pub key :",JLabel.RIGHT);
        label3 = new JLabel("Amount :",JLabel.RIGHT);

        //initializing buttons;
        //btnPlus = new JButton("+");
        //btnMinus = new JButton("-");
        btnMultiply = new JButton("*");
        btnClear = new JButton("Send");

        //adding eventlistener
        //btnPlus.addActionListener(this);
        //btnMinus.addActionListener(this);
        btnMultiply.addActionListener(this);
        btnClear.addActionListener(this);
        //initializing textfield
        //for(int i = 0; i < 3; i++)
        txtField1 = new JTextField("03e657ae71e51be60a45b4bd20bcf79ff52f0c037ae6da0540a0e0066132b472");
        txtField2 = new JTextField("d1e877472a4585d515b13f52ae7bfded1ccea511816d7772cb17e1ab20830819");
        txtField3 = new JTextField("10");

        //adding to the frame
        add(label1,0);
        add(txtField1);
        add(label2);
        add(txtField2);
        add(label3);
        add(txtField3);
        //add(btnPlus);
        //add(btnMinus);
        add(btnMultiply);
        add(btnClear);
    }//end of constructor

    //action listener method
    public void actionPerformed(ActionEvent event){
        PipeHttp pipeTcp = new PipeHttp();
        pipeTcp.sendFunds(txtField1.getText(), txtField2.getText(), txtField3.getText());
    }
}
