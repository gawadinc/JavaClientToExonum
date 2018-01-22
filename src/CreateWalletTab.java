import communication.PipeHttp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWalletTab extends JPanel implements ActionListener  {

    JLabel label1, label2, label3;

    JButton btnMultiply, btnClear; //btnPlus, btnMinus,
    JTextField txtField1, txtField2, txtField3;

    public CreateWalletTab(){
        setLayout(new GridLayout(5,2));
        //initializing label
        label1 = new JLabel("Pub key :",JLabel.RIGHT);
        label2 = new JLabel("Result :",JLabel.RIGHT);
        //label3 = new JLabel("Amount :",JLabel.RIGHT);

        //initializing buttons;
        btnMultiply = new JButton("*");
        btnClear = new JButton("Send");

        //adding eventlistener
        btnMultiply.addActionListener(this);
        btnClear.addActionListener(this);
        //initializing textfield
        //for(int i = 0; i < 3; i++)
        txtField1 = new JTextField("03e657ae71e51be60a45b4bd20bcf79ff52f0c037ae6da0540a0e0066132b472");
        txtField2 = new JTextField();
        //txtField3 = new JTextField("10");

        //adding to the frame
        add(label1,0);
        add(txtField1);
        add(label2);
        add(txtField2);
        txtField2.setEnabled(false);
        //add(label3);
        //add(txtField3);
        //add(btnPlus);
        //add(btnMinus);
        add(btnMultiply);
        add(btnClear);
    }//end of constructor

    //action listener method
    public void actionPerformed(ActionEvent event){
        PipeHttp pipeTcp = new PipeHttp();
        pipeTcp.createWallet(txtField1.getText());
    }
}
