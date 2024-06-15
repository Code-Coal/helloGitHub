package TestGame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameJFrame_Medium extends JFrame implements MouseListener, ActionListener {

    int[][] data=new int[14][11];//0:无，1：有 ，-2，2：标记，3:4-10：数量  -1:显示所有雷,-3:点击的有雷区域
    int flag=0;
    int boom=20;//记录剩余雷数量
    int record=0;//记录是否进行标记，0：不标记，1：标记

    JMenuItem jMenuItemRegame = new JMenuItem("重新游戏");
    JMenuItem jMenuItemExit = new JMenuItem("退出");
    JMenu jMenuAbout = new JMenu("关于");

    public GameJFrame_Medium(){
        //初始化数组
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 11; j++) {
                data[i][j]=0;
            }
        }
        //初始化界面
        InitLogin();
        //添加菜单
        addJMenuBar();
        //添加图片
        InitImage();

        setVisible(true);
    }

    private void InitImage() {
        //添加顶部方格
        this.getContentPane().removeAll();//清空
        JLabel jLableUp = new JLabel();
        jLableUp.setBounds(10,20,360,40);
        jLableUp.setBorder(new BevelBorder(1));
        getContentPane().add(jLableUp);
        //添加顶部图片
        JLabel jLabelUpImg = new JLabel(new ImageIcon("image\\扫雷\\顶部图片.jpg"));
        jLabelUpImg.setBounds(160,20,40,40);
        jLabelUpImg.setBorder(new BevelBorder(1));
        getContentPane().add(jLabelUpImg);
        //添加剩余雷数量
        JLabel jLabelNum = new JLabel("剩余数量：" + boom);
        jLabelNum.setBounds(20,30,100,20);
        jLabelNum.setBorder(new BevelBorder(3));
        getContentPane().add(jLabelNum);
        //添加插旗按钮
        JLabel jLabelRecord = new JLabel();
        jLabelRecord.setBounds(240,20,80,40);
        jLabelRecord.setIcon(new ImageIcon("image\\扫雷\\不标记按钮.jpg"));
        jLabelRecord.setBorder(new BevelBorder(1));
        getContentPane().add(jLabelRecord);
        //添加方格
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 12; j++) {
                JLabel jLabel = new JLabel();
                jLabel.setIcon(new ImageIcon("image\\扫雷\\未点击.jpg"));
                jLabel.addMouseListener(this);
                jLabel.setBounds(10+i*40,60+j*40,40,40);
                jLabel.setBorder(new BevelBorder(2));
                getContentPane().add(jLabel);
            }
        }
        getContentPane().repaint();
    }

    private void addJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuFunction = new JMenu("菜单");
        jMenuFunction.add(jMenuItemRegame);
        jMenuFunction.add(jMenuItemExit);
        jMenuBar.add(jMenuFunction);
        jMenuBar.add(jMenuAbout);
        jMenuItemRegame.addMouseListener(this);
        jMenuItemRegame.addActionListener(this);
        jMenuItemExit.addMouseListener(this);
        jMenuItemExit.addActionListener(this);

        jMenuAbout.addMouseListener(this);
        //添加菜单栏
        setJMenuBar(jMenuBar);
        //添加键盘监听

    }

    private void InitLogin() {
        setSize(400,610);
        setTitle("扫雷");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setLayout(null);
    }
    private void ResetImg(){
        //清空图片
        this.getContentPane().removeAll();
        //添加顶部
        JLabel jLableUp = new JLabel();
        jLableUp.setBounds(10,20,360,40);
        jLableUp.setBorder(new BevelBorder(1));
        getContentPane().add(jLableUp);
        //添加顶部图片
        JLabel jLabelUpImg = new JLabel(new ImageIcon("image\\扫雷\\顶部图片.jpg"));
        jLabelUpImg.setBounds(160,20,40,40);
        jLabelUpImg.setBorder(new BevelBorder(1));
        getContentPane().add(jLabelUpImg);
        //添加剩余雷数量
        JLabel jLabelNum = new JLabel("剩余数量：" + boom);
        jLabelNum.setBounds(20,30,100,20);
        jLabelNum.setBorder(new BevelBorder(1));
        getContentPane().add(jLabelNum);
        //添加标记按钮
        JLabel jLabelRecord = new JLabel();
        jLabelRecord.setBounds(240,20,80,40);
        jLabelRecord.setBorder(new BevelBorder(3));
        getContentPane().add(jLabelRecord);
        jLabelRecord.addMouseListener(this);
        if(record==0){
            jLabelRecord.setIcon(new ImageIcon("image\\扫雷\\不标记按钮.jpg"));
        }
        else if(record==1){
            jLabelRecord.setIcon(new ImageIcon("image\\扫雷\\标记按钮.jpg"));
        }
        //添加小方格
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                JLabel jLabel = new JLabel();
                //标记
                if(data[i+1][j+1]==2||data[i+1][j+1]==-2){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\标记图片.jpg"));
                }
                //四周无雷
                else if(data[i+1][j+1]==3){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\空白区.jpg"));
                }
                //4-7 四周含雷的数量，数字-3
                else if(data[i+1][j+1]==4){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\数字\\1.jpg"));
                }

                else if(data[i+1][j+1]==5){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\数字\\2.jpg"));
                }
                else if(data[i+1][j+1]==6){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\数字\\3.jpg"));
                }
                else if(data[i+1][j+1]==7){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\数字\\4.jpg"));
                }
                else if(data[i+1][j+1]==8){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\数字\\5.jpg"));
                }
                else if(data[i+1][j+1]==9){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\数字\\6.jpg"));
                }
                //显示所有雷
                else if(data[i+1][j+1]==-1){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\雷2.jpg"));
                }
                //显示所点击的雷
                else if(data[i+1][j+1]==-3){
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\雷1.jpg"));
                }
                //未点击区域显示
                else{
                    jLabel.setIcon(new ImageIcon("image\\扫雷\\未点击.jpg"));
                }
                jLabel.setBounds(10+j*40,60+i*40,40,40);
                jLabel.setBorder(new BevelBorder(2));
                jLabel.addMouseListener(this);
                getContentPane().add(jLabel);
            }
        }
        //刷新
        getContentPane().repaint();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(data[i][j]);
                System.out.print("   ");
            }
            System.out.println();

        }
    }
    private boolean Judge(int x,int y){
        //点击区域为雷
        if(data[x+1][y+1]==1){
            //将所有雷设置为-1，显示所有雷
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 9; j++) {
                    if(data[i+1][j+1]==1||data[i+1][j+1]==-2){
                        data[i+1][j+1]=-1;
                    }
                }
            }
            //所点击的雷设置为-3，显示特殊图片
            data[x+1][y+1]=-3;
            ResetImg();
//            getContentPane().repaint();
            System.out.println("雷");
            return true;
        }
        //若点击区域已显示雷，则也返回true
        else if(data[x+1][y+1]==-1||data[x+1][y+1]==-3){
            return true;
        }
        //无雷则返回false
        return false;
    }

    //标记雷
    private void FlagBoom(int x,int y){
        //若点击区未标记，且标记数量大于0
        //若标记区没有雷，标记为2，有雷标记为-2
        if(data[x][y]==1&&boom>0){
            data[x][y]=-2;
            boom--;
        }
        else if(data[x][y]==0&&boom>0){
            data[x][y]=2;
            boom--;
        }
        //若点击区域已标记，则再次点击时取消标记

        else if(data[x][y]==2){
            data[x][y]=0;
            boom++;
        }
        else if(data[x][y]==-2){
            data[x][y]=1;
            boom++;
        }

    }

    private void Regame(){
        //将数组全设置为0
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 11; j++) {
                data[i][j]=0;
            }
        }
        //重新标记雷
        flag=0;
        //将未标记雷数量设置为10
        boom=20;
        //重新设置图片
        ResetImg();
        System.out.println("重新游戏");
    }

    private void About(){
        //清空图片
        getContentPane().removeAll();
        JLabel jLabelImg = new JLabel(new ImageIcon("image\\扫雷\\logo.png"));
        jLabelImg.setBounds(25,30,350,74);
        getContentPane().add(jLabelImg);
        //添加文字
        JLabel jlwechat1 = new JLabel("狗熊岭设计局® 设计");
        jlwechat1.setBounds(50,30,200,20);
        getContentPane().add(jlwechat1);

        JLabel jlwechat2 = new JLabel("版本：1.2.0");
        jlwechat2.setBounds(50,60,200,20);
        getContentPane().add(jlwechat2);

        JLabel jlwechat3 = new JLabel("更新时间：2024-5-31");
        jlwechat3.setBounds(50,90,200,20);
        getContentPane().add(jlwechat3);
        //刷新
        getContentPane().repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==jMenuItemRegame){
            System.out.println("点击重新重新游戏");
            Regame();

            return;
        }
        else if(obj==jMenuItemExit){
            new Login();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x,y;//单元格对应数组
        //标记所选方格
        Object obj=e.getSource();
        if(obj==jMenuItemRegame){
            System.out.println("点击重新重新游戏");
            Regame();

            return;
        }
        //功能暂未实现

        if(obj==jMenuAbout){
            About();
            return;
        }
        if(obj==jMenuItemExit){
            new Login();
            return;
        }

        JLabel o = (JLabel) obj;

        if(o.getX()==240&&o.getY()==20){
            System.out.println("点击标记");
            record=record==1?0:1;
            ResetImg();
            return;
        }
        y=(o.getX()-10)/40;
        x=(o.getY()-60)/40;
        System.out.println(x+"     "+y);
        flag++;

        //布雷
        if(flag==1){//第一次点击后布雷，防止第一次点踩到雷
            Algorithm.initBoom(data,x+1,y+1,12,9,boom);
        }
        //标记
        if(record==1){
            FlagBoom(x+1,y+1);
            ResetImg();
            return;
        }
        //判断是否有雷
        if(!Judge(x,y)){
            Algorithm.algorithm(data,x+1,y+1,12,9);
        }
        ResetImg();

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

