/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thepiggame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author SydnaAgnehs
 */
public class theGame implements ActionListener {
    private JPanel panel;
    private JFrame frame;
    private JTextField myPoint, opPoint, display, accumPointDisplay;
    private JButton roll, skip;
    private int turnCount;
    private AiSystem AS;
    private final int LOSE_ROLLED_ONE=1;
    private final int WIN_NUM_REACHED=0;
    private int accumPt;
        
    
    public theGame(JFrame frame) {
        this.frame=frame;
        this.initLook();
        AS=new AiSystem();
        this.gameSystem();
        
        
    }
    private void initLook() {
        panel=new JPanel();
        JPanel interPan=new JPanel();
        interPan.setLayout(new GridLayout(1, 2));
        display = new JTextField("Round: ~~~");
        display.setPreferredSize(new Dimension(400, 80));
        display.setVisible(true);
        display.setEditable(false);
        panel.add(display);
        myPoint = new JTextField("0");
        myPoint.setEditable(false);
        opPoint = new JTextField("0");
        opPoint.setEditable(false);
        myPoint.setVisible(true);
        opPoint.setVisible(true);
        myPoint.setPreferredSize(new Dimension(50, 50));
        opPoint.setPreferredSize(new Dimension(50, 50));
        JPanel slightlySmallerPanMy=new JPanel();
        JLabel myName = new JLabel("My Points");
        myName.setVisible(true);
        JLabel opName = new JLabel("Opponent's Points");
        opName.setVisible(true);
        slightlySmallerPanMy.add(myName);
        slightlySmallerPanMy.add(myPoint);
        JPanel slightlySmallerPanOp=new JPanel();
        slightlySmallerPanOp.add(opName);
        slightlySmallerPanOp.add(opPoint);
        slightlySmallerPanOp.setVisible(true);
        slightlySmallerPanMy.setVisible(true);
        interPan.setPreferredSize(new Dimension(500, 100));
        interPan.add(slightlySmallerPanMy);
        interPan.add(slightlySmallerPanOp);
        interPan.setVisible(true);
        panel.add(interPan);
        JPanel interinterPan=new JPanel();
        interinterPan.setLayout(new GridLayout(1, 2));
        interinterPan.setPreferredSize(new Dimension(450, 50));
        JLabel accumLabel=new JLabel("Accumlated Points");
        accumLabel.setVisible(true);
        accumPointDisplay=new JTextField("");
        accumPointDisplay.setVisible(true);
        accumPointDisplay.setEditable(false);
        accumPointDisplay.setPreferredSize(new Dimension(50, 50));
        interinterPan.add(accumLabel);
        interinterPan.add(accumPointDisplay);
        panel.add(interinterPan);
        roll=new JButton("Roll Dice!");
        skip=new JButton("Skip");
        roll.setVisible(true);
        roll.setActionCommand("roll");
        skip.setVisible(true);
        skip.setActionCommand("skip");
        skip.addActionListener(this);
        roll.addActionListener(this);
        this.disableBut();
        panel.add(roll);
        panel.add(skip);
        
        
        panel.setVisible(true);
        frame.add(panel);        
    }
    
    private void gameSystem() {
        ++turnCount;
        display.setText("Round: "+turnCount+".   YOUR TURN!");
        this.enableBut();
        accumPt=0;
        
        
        
        
    }
    
    private boolean checkEnd() {
        if(Integer.parseInt(myPoint.getText())>=100||Integer.parseInt(opPoint.getText())>=100) {
            return true;
        }
        return false;
    }
    
    private void endingSeq(String player) {
        if(player.equals("You")) {
           JOptionPane.showMessageDialog(frame, "You win,\nbecause you got to 100\nTotal turns: "+turnCount+"\n",  "Results", JOptionPane.ERROR_MESSAGE);
          } else {
           JOptionPane.showMessageDialog(frame, "You lose,\nbecause AI got to 100\nTotal turns: "+turnCount+"\n",  "Results", JOptionPane.ERROR_MESSAGE);
        }
        panel.setVisible(false);
        panel=null;
        frame.setVisible(false);
        frame=null;
        new ThePigGame();
        
    }
    
    private void roll(String player) {
        if(this.checkEnd()) {
            this.endingSeq(player);
            return;
        }
        int roll1=(int)(1+Math.random()*6);
        int roll2=(int)(1+Math.random()*6);
        
        int sum=roll1+roll2;
        
        JOptionPane.showMessageDialog(frame, player+" roll a "+roll1+" + "+roll2+" = "+sum,  "Results", JOptionPane.INFORMATION_MESSAGE);
        
        accumPt+=sum;
        accumPointDisplay.setText(""+accumPt);
        
        if(player.equals("AI")) {
            AS.setAIAcumPoints(accumPt);
        }
        if(roll1==1&&roll2==1) {
            JOptionPane.showMessageDialog(frame, player+" rolled two 1,\nTherefore forfeited all the points and and loses control.",  "Results", JOptionPane.INFORMATION_MESSAGE);
            accumPt=0;
            accumPointDisplay.setText(""+accumPt);
            if(player.equals("You")) {
                myPoint.setText("0");
                AS.setHuPoints(0);
                this.disableBut();
                this.aiTurn();
            } else {
                AS.setAiPoints(0);
                AS.resetAIAcumPoints();
                opPoint.setText("0");
                this.enableBut();
            }
            return;
        } else if(roll1==1||roll2==1) {
            JOptionPane.showMessageDialog(frame, player+" rolled a 1,\nTherefore forfeited all the points gained this round and loses control.",  "Results", JOptionPane.INFORMATION_MESSAGE);
            accumPt=0;
            accumPointDisplay.setText(""+accumPt);
            if(player.equals("You")) {
                this.disableBut();
                AS.resetAIAcumPoints();
                this.aiTurn();
            } else if(player.equals("AI")){
                AS.resetAIAcumPoints();
                this.enableBut();
                System.out.println("Enabled");
            }
            return;
        }
        
        
        if(player.equals("AI")&&AS.actTurn()) {
            this.roll(player);
            return;
        } else if(player.equals("AI")&&!AS.actTurn()) {
            opPoint.setText((Integer.parseInt(opPoint.getText())+accumPt)+"");
            accumPt=0;
            AS.resetAIAcumPoints();
            AS.setAiPoints((Integer.parseInt(opPoint.getText())));
            accumPointDisplay.setText(""+accumPt);
            this.enableBut();
            display.setText("Round: "+(++turnCount)+".   "+"Your TURN!");
            
            if(this.checkEnd()) {
                this.endingSeq(player);
                return;
            }
            
        }
        if(player.equals("You")) {
            this.enableBut();
        }
        
        
        
        
    }
    private void aiTurn() {
        if(AS.actTurn()) {
            this.roll("AI");
        }
    }
    

    
    private void enableBut() {
        roll.setEnabled(true);
        skip.setEnabled(true);
    }
    private void disableBut() {
        roll.setEnabled(false);
        skip.setEnabled(false);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.disableBut();
        if(e.getActionCommand().equals("roll")) {
            this.roll("You");
        } else if(e.getActionCommand().equals("skip")) {
            if(this.checkEnd()) {
            this.endingSeq("You");
            return;
        }
            myPoint.setText((Integer.parseInt(myPoint.getText())+accumPt)+"");
            accumPt=0;
            AS.setHuPoints((Integer.parseInt(myPoint.getText())));
            this.aiTurn();
            
        }
    }
    
    
}
