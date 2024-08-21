import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {
    private JLabel heading,clockLabel;
    private Font font1 = new Font("",Font.BOLD,30);
    private Font font2 = new Font("",Font.BOLD,20);
    private  JPanel mainPanel;

    //game instance variable

    int gameChances [] = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;

    private JButton []btns = new JButton[9];//there is no need to use

    int wps [][] = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,4,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {2,4,6}
    };
    int winner = 2;
    boolean gameOver = false;
    MyGame(){
        super.setTitle("Tic-Tac-Toe");
        super.setSize(500,500);
        ImageIcon icon  = new ImageIcon("D:\\JavaProjects\\project2Tictac\\img\\img_5.png");
        super.setIconImage(icon.getImage());
        super.setLocation(500,200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createGUI();
        super.setVisible(true);
    }

    private void createGUI(){
        this.getContentPane().setBackground(Color.decode("#9aeeff"));
        this.setLayout(new BorderLayout());
        //North section
        heading = new JLabel("TIC->TAC->TOE");
        ImageIcon HeadingImage = new ImageIcon("D:\\JavaProjects\\project2Tictac\\img\\img_5.png");
        Image image1 = HeadingImage.getImage();
        Image changedImage = image1.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon changedIcon = new ImageIcon(changedImage);
        heading.setIcon(new ImageIcon(changedImage));
        heading.setFont(font1);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setForeground(Color.decode("#fc4d4d"));
        this.add(heading,BorderLayout.NORTH);

        //Creating clock label
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font2);
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setForeground(Color.decode("#fc4d4d"));
        this.add(clockLabel,BorderLayout.SOUTH);

        Thread t = new Thread(){
            public void run(){

                try{
                    while(true){
                        String datetime = new Date().toLocaleString();

                        clockLabel.setText(datetime);

                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        t.start();

        //....panel section
        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(3,3));


        for(int i = 1;i<=9;i++){
            JButton btn = new JButton();
            btn.setBackground(Color.decode("#ADD8E6"));
            mainPanel.add(btn);
            btns[i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }

        this.add(mainPanel,BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton)e.getSource();

        String nameStr = currentButton.getName();

        int name = Integer.parseInt(nameStr.trim());//converting into integer.

        if(gameOver == true){
            JOptionPane.showMessageDialog(this,"Game over");
            return;
        }

        if(gameChances[name] == 2){
            if(activePlayer == 1){
                ImageIcon originalIcon = new ImageIcon("D:\\JavaProjects\\project2Tictac\\img\\img_3.png");

                // Resize the image
                Image image = originalIcon.getImage(); // transform it
                Image resizedImage = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                currentButton.setIcon(resizedIcon);

                gameChances[name] = activePlayer;
                activePlayer =0;

            }else{
                ImageIcon Icon = new ImageIcon("D:\\JavaProjects\\project2Tictac\\img\\img_4.png");

                //Resize the image
                Image image1 = Icon.getImage();
                Image change = image1.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                ImageIcon changeIcon = new ImageIcon(change);
                currentButton.setIcon(changeIcon);

                gameChances[name] = activePlayer;
                activePlayer=1;
            }
            ///find the winner
            for(int [] temp:wps){
                if (gameChances[temp[0]] == gameChances[temp[1]] && gameChances[temp[1]] == gameChances[temp[2]]
                && gameChances[temp[2]]!=2){
                    winner = gameChances[temp[0]];
                    gameOver = true;
                    JOptionPane.showMessageDialog(this,"Player "+ winner+" has won the game");
                    int i = JOptionPane.showConfirmDialog(this,"do you want to play more ??");
                    if(i==0){
                        this.setVisible(false);
                        new MyGame();
                    }else if(i==2){
                        System.exit(154);
                    }
                    else{

                    }
                    break;
                }
            }

            //draw logic

            int count = 0;
            for(int x:gameChances){
                if(x==2){
                    count++;
                    break;
                }
            }

            if(count ==0 && !gameOver){
                JOptionPane.showMessageDialog(null,"It's Draw..");
                int i = JOptionPane.showConfirmDialog(this,"Play more...");

                if(i==0){
                    this.setVisible(false);
                    new MyGame();
                }else if(i==2){
                    System.exit(154);
                }
                else{

                }
            }

        }else{
            JOptionPane.showMessageDialog(this,"position already occupied");
        }
    }
}
