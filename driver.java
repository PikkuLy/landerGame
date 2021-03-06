import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by pikku on 10/17/2015.
 */

//ring picture of Pluto: http://www.wired.com/wp-content/uploads/2015/07/nh_01_stern_05_pluto_hazenew.jpg



public class driver extends JFrame{
    private final int WIDTH = 1000, HEIGHT = 900;
    private JLabel titleLabel, landerLabel, backgroundLabel; //for the title of the game
    private JButton startButton, practiceLanding, help;
    private eventHandler handles;
    private ImageIcon lander, background;

    public driver(){
        Container pane = getContentPane();
        handles = new eventHandler();
        setTitle("Lander on Pluto");
        setSize(WIDTH,HEIGHT);
        setLayout(null);

        setIconImage(Toolkit.getDefaultToolkit().getImage("./Images/lander.png")); //changing the icon for the game in the title bar and in the task bar
        pane.setBackground(Color.BLACK);

        titleLabel = new JLabel("Lander on Pluto!",SwingConstants.CENTER);
        startButton = new JButton("Initiate Landing");
        help = new JButton("Info/Rules");
        practiceLanding = new JButton("Training");



        startButton.setSize(200,50);
        startButton.setLocation((WIDTH/2+10),700);

        help.setSize(90,50);
        help.setLocation(850,50);

        practiceLanding.setSize(200,50);
        practiceLanding.setLocation((WIDTH/2-210),700);

        try {
            background = new ImageIcon(ImageIO.read(new File("./images/ringPluto.jpg")));
        }
        catch(IOException e){
            System.out.println("Error, image could not be found");
        }
        backgroundLabel = new JLabel(background);
        backgroundLabel.setSize(800,800);
        backgroundLabel.setLocation(0,0);

        try {
            lander = new ImageIcon(ImageIO.read(new File("./images/lander.png")));
        }
        catch(IOException e){
            System.out.println("Error, image could not be found");
        }

        landerLabel = new JLabel(lander);
        landerLabel.setSize(300,200);
        landerLabel.setLocation((WIDTH/2-150),400);


        //pane.add(titleLabel);
        pane.add(help);
        pane.add(landerLabel);
        pane.add(startButton);
        pane.add(practiceLanding);

        pane.add(backgroundLabel);

        startButton.addActionListener(handles);
        help.addActionListener(handles);
        practiceLanding.addActionListener(handles);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public class eventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == startButton){
                dispose();
                new startGame();
            }
            if(e.getSource() == practiceLanding){
                dispose();
                try{
                    new practiceLevel();
                }catch(InterruptedException sleep){
                    Thread.currentThread().interrupt();
                }
                //new practiceLevel();
            }
            if(e.getSource() == help){
                JOptionPane.showMessageDialog(null, "You're landing on a new planet, but it's a little \"rocky\" \n" +
                        "Guide your lander through the rocks in the planet's atmosphere to get more points and experience\n" +
                        "Good luck pilot! ");
            }

        }
    }

    public static void main(String[] args){
        driver window = new driver();
    }
}
