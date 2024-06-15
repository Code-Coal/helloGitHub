package TestGame;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login extends JFrame implements MouseListener, ActionListener {


    public Login(){
        //
        InitLogin();
        //添加图片
        InitImg();
        //**
        setVisible(true);
    }

    private void InitImg() {
        JLabel jLabelTop = new JLabel(new ImageIcon("image\\扫雷\\顶部图片.jpg"));
        jLabelTop.setBounds(160,20,40,40);
        jLabelTop.setBorder(new BevelBorder(2));
        getContentPane().add(jLabelTop);
        //添加简单图片
        JLabel jLabelEasy = new JLabel(new ImageIcon("image\\扫雷\\简单.jpg"));
        jLabelEasy.setBounds(120,100,120,47);
        jLabelEasy.setBorder(new BevelBorder(2));
        getContentPane().add(jLabelEasy);
        jLabelEasy.addMouseListener(this);
        JLabel jLabelMedium = new JLabel(new ImageIcon("image\\扫雷\\中等.jpg"));
        jLabelMedium.setBounds(120,167,120,47);
        jLabelMedium.setBorder(new BevelBorder(2));
        getContentPane().add(jLabelMedium);
        jLabelMedium.addMouseListener(this);
        JLabel jLabelDiff = new JLabel(new ImageIcon("image\\扫雷\\困难.jpg"));
        jLabelDiff.setBounds(120,234,120,47);
        jLabelDiff.setBorder(new BevelBorder(2));
        getContentPane().add(jLabelDiff);
        jLabelDiff.addMouseListener(this);

    }

    private void InitLogin() {
        setSize(400,490);
        setTitle("扫雷");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Object obj=e.getSource();
        JLabel jLabel=(JLabel) obj;
        if(jLabel.getX()==120&&jLabel.getY()==100){
            new GameJFrame_Easy();
        }
        else if(jLabel.getX()==120&&jLabel.getY()==167){
            new GameJFrame_Medium();
        }
        else if(jLabel.getX()==120&&jLabel.getY()==234){
            new GameJFrame_Diff();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}