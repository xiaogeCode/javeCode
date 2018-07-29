package ui;

import manage.CodingManager;
import util.CommUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: MainFrame
 * Author:   xiaoge
 * Date:     2018/7/29 18:09
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class MainFrame extends JFrame implements KeyListener ,ActionListener{
    JTextField inputTextField;
    JTextField outputTextField;
    String imageUrl = CommUtil.getImageUrl2("image0.png");//getClass().getResource("../pic/image0.png");
    public MainFrame(){
        setFrame();
        setView();
    }

    private void setFrame() {
        System.out.println("setFrame");
        this.setTitle("记忆编码");
        this.setLayout(null);
        this.setBackground(Color.yellow);
        this.setSize(400 , 400);
        this.setLocation(400, 50);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(this);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);//
            }
        });
    }
    private void setView(){
        this.getContentPane().removeAll();
        JPanel jp1=new JPanel();
        jp1.setLocation(20,20);
        jp1.setSize(460, 360);
        jp1.setLayout(null);

        inputTextField = new JTextField();
        inputTextField.setLocation(20, 20);
        inputTextField.setSize(100, 30);
        //inputTextField.setToolTipText("input a string to code");

        outputTextField = new JTextField();
        outputTextField.setLocation(200, 320);
        outputTextField.setSize(100, 30);
        outputTextField.setEditable(false);

        JButton randomButton = new JButton();
        randomButton.setText("random");
        randomButton.setSize(100, 50);
        randomButton.setLocation(200, 20);
        randomButton.addActionListener(this);

        JButton codeButton = new JButton();
        codeButton.setText("code");
        codeButton.setSize(100, 50);
        codeButton.setLocation(20, 300);
        codeButton.addActionListener(this);

        ImageIcon imageIcon=new ImageIcon(imageUrl);

        JLabel imageLabel=new JLabel();
        imageLabel.setSize(200,200);
        imageLabel.setLocation(130,100);
        if (imageIcon.getImage().getWidth(null)>0){
            imageLabel.setIcon(imageIcon);
        }



        //jp1.add(inputTextField);
        //jp1.add(randomButton);

        this.add(inputTextField);
        this.add(randomButton);
        this.add(codeButton);
        this.add(outputTextField);
        this.add(imageLabel);

        this.getContentPane().repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("random"))
        {
            String string = CodingManager.getRandomStr();
            inputTextField.setText(string);

        }
        if(e.getActionCommand().equals("code"))
        {
            String string = CodingManager.getStrCoding(inputTextField.getText());
            String inputString = inputTextField.getText();
            System.out.println("url0: "+imageUrl);
            imageUrl = CommUtil.getImageUrl2("image"+inputTextField.getText()+".png");
            if (imageUrl == null) {
                imageUrl = CommUtil.getImageUrl2("image0.png");
            }
            System.out.println();
            setView();
            inputTextField.setText(inputString);
            outputTextField.setText(inputString+"： "+string);

        }
    }
}
