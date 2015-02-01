/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thepiggame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author SydnaAgnehs
 */
public class ThePigGame implements ActionListener{

    /**
     * @param args the command line arguments
     */
    private JFrame frame;
    private JPanel panel;
    
    
    public ThePigGame() {
        this.initLook();
    }
    private void initLook() {
        frame=new JFrame();
        panel=new JPanel();
        panel.setLayout(null);
        JButton start=new JButton("Start Game!");
        start.setBounds(540/2-100, 380/5, 200, 100);
        start.setVisible(true);
        start.setActionCommand("start");
        start.addActionListener(this);
        
        JButton how=new JButton("How to Play?");
        how.setBounds(540/2-100, 380/2, 200, 100);
        how.setVisible(true);
        how.setActionCommand("how");
        how.addActionListener(this);
        
        panel.add(start);
        panel.add(how);
        
        JLabel author=new JLabel("By Sydna Agnehs");
        author.setBounds(400, 320, 250, 50);
        author.setVisible(true);
        panel.add(author);
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 380);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        panel.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("start")) {
            panel.setVisible(false);
            panel=null;
            new theGame(frame);
            
        } else if(e.getActionCommand().equals("how")) {
            JOptionPane.showMessageDialog(frame, "In this game, the user competes against the computer.\n"
                    + " On each turn, the current player rolls a pair of dice and accumulates points. \n"
                    + "The goal is to reach 100 points before your opponent does.\n"
                    + "If, on any turn, the player rolls a 1, all points accumulated for that round are forfeited and control of the dice moves to the other player.\n"
                    + "If the player rolls two 1s in one turn, the player loses all points accumulated thus far in the game and loses control of the dice.\n"
                    + "The player may voluntarily turn over the dice after each roll.",  "Results", JOptionPane.ERROR_MESSAGE);
          
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new ThePigGame();
    }
}
