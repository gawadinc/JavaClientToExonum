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
        txtField1 = new JTextField();
        txtField2 = new JTextField();
        txtField3 = new JTextField();

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
        String opt = event.getActionCommand();
        int num1, num2, num = 0;
        num1 = Integer.parseInt(txtField1.getText());
        num2 = Integer.parseInt(txtField2.getText());


        if(opt.equals("+"))
            num = num1 + num2;
        else if(opt.equals("-"))
            num = num1 - num2;
        else if(opt.equals("*"))
            num = num1 * num2;
        else if(opt.equals("Send")){
            txtField1.setText("");
            txtField2.setText("");
            txtField3.setText("");
        }
        txtField3.setText(Integer.toString(num));

    }
}
