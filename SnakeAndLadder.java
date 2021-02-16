package javaexperts;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Random;

public class SnakeAndLadder extends JFrame implements ActionListener{

 JButton player2 = new JButton("Player 2");
 JButton player1 = new JButton("Player 1");
 JButton newGame = new JButton("New Game");
 
 JLabel player2no = new JLabel("");
 JLabel player1no = new JLabel("");
 
 JLabel player2pos = new JLabel("");
 JLabel player1pos = new JLabel("");
 
 HashMap<Integer,Integer> ladder = new HashMap<Integer,Integer>();
 HashMap<Integer,Integer> snake = new HashMap<Integer,Integer>();
 
 int p1count = 1;
 int p2count = 1;
 
 int w=15,h=15;
 int x=0,y=0;
 
 StringBuffer p2list = null;
 StringBuffer p1list = new StringBuffer();
  
 Random dies = new Random();
 
 public SnakeAndLadder()  throws Throwable{
	  super("Snake And Ladder");
	  setLayout(null);
	 // setLayout(new BorderLayout());
	  setVisible(true);
	  setResizable(false);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  //getContentPane().setBackground(Color.LIGHT_GRAY);
	  
	  setBounds(10, 10, 900, 800);
	  
	  //Snake and ladder Board
	  JLabel background=new JLabel(new ImageIcon("E:\\Snake-and-Ladder.png"));
	  background.setBounds(10, 10, 699, 699);
	  add(background);
	  
	  //New game button
	  newGame.setBounds(780, 140, 90, 30);
	  add(newGame);
	
	  //Player 1 button
	  player1.setBounds(750, 200, 70, 30);
	  add(player1);
	  
	  //Player 2 button
	  player2.setBounds(750, 250, 70, 30);
	  add(player2);
	  player2.setVisible(false);
	  
	  //Player 1 colour
	  player1no.setBounds(840, 200, 30, 30);
	  player1no.setOpaque(true);
	  player1no.setBackground(Color.BLUE);
	  player1no.setForeground(Color.GREEN);
	  add(player1no);
	  
	  //Player 2 colour
	  player2no.setBounds(840, 250, 30, 30);
	  player2no.setOpaque(true);
	  player2no.setBackground(Color.RED);
	  player2no.setForeground(Color.GREEN);
	  add(player2no);
	    
	  //Player 2 coin
	  coinPosition(1,p2count);
	  player2pos.setBounds(x, y, w, h);
	  player2pos.setOpaque(true);
	  player2pos.setBackground(Color.RED);
	  background.add(player2pos);
	  
	  //Player 1 coin
	  coinPosition(2,p1count);
	  player1pos.setBounds(x, y, w, h);
	  player1pos.setOpaque(true);
	  player1pos.setBackground(Color.BLUE);
	  background.add(player1pos);
	  
	  repaint();
	  
	  ladder.put(4, 56);
	  ladder.put(12, 50);
	  ladder.put(14, 55);
	  ladder.put(22, 58);
	  ladder.put(41, 79);
	  ladder.put(54, 88);
	  
	  snake.put(28, 10);
	  snake.put(37, 3);
	  snake.put(47, 16);
	  snake.put(77, 32);
	  snake.put(96, 42);
	  snake.put(96, 71);
	  
	  newGame.addActionListener(this);
	  player1.addActionListener(this);
	  player2.addActionListener(this);
	   
}
 
  public static void main(String args[])  throws Throwable {
	  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	  new SnakeAndLadder();
  }
  
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == newGame) {
	    p1count = 1;
	    p2count = 1;
	    coinPosition(1,p2count);
	    player2pos.setBounds(x, y, w, h);
	    coinPosition(2,p1count);
	    player1pos.setBounds(x, y, w, h);
	    player1no.setText("");
	    player2no.setText("");
	    player1.setVisible(true);
	    player2.setVisible(false);
	    repaint();
   } else if (e.getSource() == player1) {
	   	p2list = new StringBuffer();
  
	    int playAgain = playDies(2);
	    coinPosition(2,p1count);
	    player1pos.setBounds(x, y, w, h);
	    repaint();
	    if(ladder.containsKey(p1count)) {
	    	p1count = ladder.get(p1count);
	    } else if (snake.containsKey(p1count)){
	    	p1count = snake.get(p1count);
	    }
	    coinPosition(2,p1count);
	    player1pos.setBounds(x, y, w, h);
	    repaint();
	    if(p1count == 100) {
	    	player1.setVisible(false);
	    	JOptionPane.showMessageDialog(this, "Player 1 win");
	    } else if(playAgain == 0) {
	    	player1.setVisible(false);
	    	player2.setVisible(true);
	    	}	  
   		}
   	else if(e.getSource()==player2) {
   		p1list = new StringBuffer();
   		
		int playAgain = playDies(1);
	    coinPosition(1,p2count);
	    player2pos.setBounds(x, y, w, h);
	    repaint();
	    if(ladder.containsKey(p2count)) {
	    	p2count = ladder.get(p2count);
	    } else if (snake.containsKey(p2count)){
	    	  p2count = snake.get(p2count);
	    }
	    coinPosition(1,p2count);
	    player2pos.setBounds(x, y, w, h);
	    repaint();
	    if(p2count == 100) {
	    	player2.setVisible(false);
		    JOptionPane.showMessageDialog(this, "Player 2 win");
	      }
	    else if(playAgain==0) {
	    	player1.setVisible(true);
	    	player2.setVisible(false);
	    }
   	}
  }
 
  private int playDies(int player) {
	   int playAgain = 0;
	   int diesResult = 0;
	   while(diesResult == 0) {
		   diesResult = dies.nextInt(7);
	   }
	   if(player == 2){
		    p1list.append(String.valueOf(diesResult));
		    p1list.append(",");
		    player1no.setText(p1list.toString());
		    if(p1count+diesResult <= 100) {
			     p1count = p1count+diesResult;
			     if(diesResult == 1 || diesResult == 6) {
			    	 playAgain = 1;
			     }
		    }
	   } else {
		    p2list.append(String.valueOf(diesResult));
		    p2list.append(",");
		    player2no.setText(p2list.toString());
		    if(p2count+diesResult <= 100) {
			     p2count = p2count+diesResult;
			     if(diesResult == 1 || diesResult == 6) {
			    	 playAgain = 1;
			     }
		    }
	   	}
	   return playAgain;
  }
 
  private void coinPosition(int compOrYou, int count) {
	  //count=1
	  //xpos=1
	  //ypos=0
   int xpos = count%10;
   int ypos = count/10;
   if(xpos == 0) {
    xpos = 10;
    ypos = ypos-1;
   }
   if(compOrYou == 1) {
    x = 5 + (xpos*70) - 70;
   } else {
    x = 25 + (xpos*70) - 70;
   }
   y = 680 - (ypos*70);
  }
}