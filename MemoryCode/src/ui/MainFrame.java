package ui;

import manage.CodingManager;
import util.CommInterface;
import util.CommUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
public class MainFrame extends JFrame implements KeyListener ,ActionListener, CommInterface{
    private int lan_typ = language_type_zh;
    private JTextField inputTextField;
    private JTextField outputTextField;
    String imageUrl = CommUtil.getImageUrl2("image0.png");//getClass().getResource("../pic/image0.png");
    public MainFrame(){
        setFrame();
        setView();
    }

    private void setFrame() {
        System.out.println("setFrame");
        this.setTitle(CommUtil.getLanguageString("memoy_code",lan_typ));
        this.setLayout(null);
        this.setBackground(Color.yellow);
        this.setSize(Coded_Frame_Width , Coded_Frame_Height);
        this.setLocation(Coded_Frame_Location_X, Coded_Frame_Location_Y);
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
        setMenuPanel();
        setStrCodePanel();
        //setCardListPanel();

        this.getContentPane().repaint();

    }
    /**
     * 功能描述: <br>
     * 〈菜单视图〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/3 1:09
     */
    private void setMenuPanel(){
        JPanel menuPanel=new JPanel();
        menuPanel.setLocation(Menu_Panel_Location_X,Menu_Panel_Location_Y);
        menuPanel.setSize(Menu_Panel_Width, Menu_Panel_Height);
        menuPanel.setBackground(Color.red);
        menuPanel.setLayout(null);

        JButton codeInfoButton = new JButton();
        codeInfoButton.setText(CommUtil.getLanguageString("code_info",lan_typ));
        codeInfoButton.setSize(Menu_Panel_Code_Info_Btn_Width, Menu_Panel_Code_Info_Btn_Height);
        codeInfoButton.setLocation(Menu_Panel_Code_Info_Btn_Location_X, Menu_Panel_Code_Info_Btn_Location_Y);
        codeInfoButton.addActionListener(this);

        JButton applyButton = new JButton();
        applyButton.setText(CommUtil.getLanguageString("apply",lan_typ));
        applyButton.setSize(Menu_Panel_Apply_Btn_Width, Menu_Panel_Apply_Btn_Height);
        applyButton.setLocation(Menu_Panel_Apply_Btn_Location_X, Menu_Panel_Apply_Btn_Location_Y);
        applyButton.addActionListener(this);

        menuPanel.add(codeInfoButton);
        menuPanel.add(applyButton);

        this.add(menuPanel);
        this.getContentPane().repaint();

    }
    /**
     * 功能描述: <br>
     * 〈输入转为记忆编码并输出图像视图〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/3 1:09
     */
    private void setStrCodePanel(){
        System.out.println("setstrcide");
        JPanel jp1=new JPanel();
        jp1.setLocation(Str_Coded_Panel_Location_X,Str_Coded_Panel_Location_Y);
        jp1.setSize(Str_Coded_Panel_Width, Str_Coded_Panel_Height);
        jp1.setBackground(Color.blue);
        jp1.setLayout(null);

        inputTextField = new JTextField();
        inputTextField.setLocation(Str_Coded_Panel_Input_TxtField_Location_X, Str_Coded_Panel_Input_TxtField_Location_Y);
        inputTextField.setSize(Str_Coded_Panel_Input_TxtField_Width, Str_Coded_Panel_Input_TxtField_Height);

        outputTextField = new JTextField();
        outputTextField.setLocation(Str_Coded_Panel_Output_TxtField_Location_X, Str_Coded_Panel_Output_TxtField_Location_Y);
        outputTextField.setSize(Str_Coded_Panel_Output_TxtField_Width, Str_Coded_Panel_Output_TxtField_Height);
        outputTextField.setEditable(false);

        JButton randomButton = new JButton();
        randomButton.setText(CommUtil.getLanguageString("random",lan_typ));
        randomButton.setSize(Str_Coded_Panel_Ramdom_Btn_Width, Str_Coded_Panel_Ramdom_Btn_Height);
        randomButton.setLocation(Str_Coded_Panel_Ramdom_Btn_Location_X, Str_Coded_Panel_Ramdom_Btn_Location_Y);
        randomButton.addActionListener(this);

        JButton codeButton = new JButton();
        codeButton.setText(CommUtil.getLanguageString("code",lan_typ));
        codeButton.setSize(Str_Coded_Panel_Code_Btn_Width, Str_Coded_Panel_Code_Btn_Height);
        codeButton.setLocation(Str_Coded_Panel_Code_Btn_Location_X, Str_Coded_Panel_Code_Btn_Location_Y);
        codeButton.addActionListener(this);

        ImageIcon imageIcon= null;
        try {
            imageIcon = new ImageIcon(ImageIO.read(new File(imageUrl)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel imageLabel=new JLabel();
        imageLabel.setSize(180,200);
        imageLabel.setLocation((Str_Coded_Panel_Width-imageIcon.getIconWidth())/2,(Str_Coded_Panel_Height-imageIcon.getIconHeight())/2);
        if (imageIcon.getImage().getWidth(null)>0){
            imageLabel.setIcon(imageIcon);
        }

        jp1.add(inputTextField);
        jp1.add(randomButton);
        jp1.add(codeButton);
        jp1.add(outputTextField);
        jp1.add(imageLabel);

        this.add(jp1);
        this.getContentPane().repaint();
    }
    public void setCardListPanel(){
        System.out.println("setcardlist");
        CardListPanel cardListPanel = new CardListPanel();
        cardListPanel.setLocation(0,0);
        cardListPanel.setSize(500,500);
        cardListPanel.setLayout(null);

        Color[] colorArray = {Color.red,Color.blue,Color.yellow,Color.gray,Color.green};
        for (int i=0;i<5;i++){
            CardPanel cardPanel= new CardPanel();
            cardPanel.setLoaction_index(i);
            cardPanel.setLocation(120+20*i,100);
            cardPanel.setSize(100,100);
            cardPanel.setBackground(colorArray[i]);
//            cardListPanel.add(cardPanel,new Integer(i));
            cardListPanel.getPanelList().add(cardPanel);
        }
        cardListPanel.showPanel();

        this.add(cardListPanel);
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
        if(e.getActionCommand().equals(CommUtil.getLanguageString("random",lan_typ)))
        {
            String string = CodingManager.getRandomStr();
            inputTextField.setText(string);

        }
        if(e.getActionCommand().equals(CommUtil.getLanguageString("code",lan_typ)))
        {
            System.out.println("url0: "+imageUrl);
            String string = CodingManager.getStrCoding(inputTextField.getText());
            String inputString = inputTextField.getText();
            imageUrl = CommUtil.getImageUrl2("image"+inputTextField.getText()+".png");
            if (imageUrl == null) {
                imageUrl = CommUtil.getImageUrl2("image0.png");
            }
            System.out.println();
            setView();
            inputTextField.setText(inputString);
            outputTextField.setText(inputString+"： "+string);

        }
        if(e.getActionCommand().equals(CommUtil.getLanguageString("code_info",lan_typ)))
        {
            this.getContentPane().removeAll();
            setMenuPanel();
            setStrCodePanel();


        }
        if(e.getActionCommand().equals(CommUtil.getLanguageString("apply",lan_typ)))
        {
            this.getContentPane().removeAll();
            setMenuPanel();
            setCardListPanel();
        }
    }
}
