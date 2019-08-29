import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//import java.util.logging.Logger;
 
public class UltimateFrame extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	Integer turn = 1;
	GameListener listener = new GameListener();
	UltimateBoard game = new UltimateBoard();
	GamePanel[][] panels = new GamePanel[3][3];
	Boolean[][] finished = new Boolean[3][3];
	JLabel label = new JLabel();
	JMenuBar menuBar = new JMenuBar();
	
	class GamePanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		JButton [][] buttons= new JButton[3][3];
		JLabel label = new JLabel();
		
		public GamePanel(int k, int l) {
			setLayout(new GridLayout(3,3));
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++) {
					buttons[i][j]=new JButton();
					buttons[i][j].putClientProperty("SUBINDEX", new Integer[]{i,j});
					buttons[i][j].putClientProperty("INDEX", new Integer[]{k,l});
					buttons[i][j].putClientProperty("OWNER", null);
					buttons[i][j].addActionListener(listener);
					buttons[i][j].setSize(100, 100);
					add(buttons[i][j]);
				}
		}
	
		
		public void enable() {
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++) {
					buttons[i][j].setEnabled(true);
				}
		}
		
		public void disable() {
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++) {
					buttons[i][j].setEnabled(false);
				}
		}
		
		public void hide() {
			disable();
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++) {
					buttons[i][j].setVisible(false);
					
				}
		}
	}

		
		
	class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton b=(JButton)e.getSource();
			if (turn % 2 == 1)
			{
				b.putClientProperty("OWNER", Config.Player.valueOf("X"));
				b.setText("X");
			}
			
			if (turn % 2 == 0)
			{
				b.putClientProperty("OWNER", Config.Player.valueOf("O"));
				b.setText("O");
			}
			Integer[] index = (Integer[]) b.getClientProperty("INDEX");
			Integer[] subIndex = (Integer[]) b.getClientProperty("SUBINDEX");
			b.setEnabled(false);
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++) {
					panels[i][j].disable();
				}
			panels[subIndex[0]][subIndex[1]].enable();
					
			game.boardOfBoards[index[0]][index[1]].board[subIndex[0]][subIndex[1]] = (Config.Player) b.getClientProperty("OWNER");
			
			game.updateBoard();
			
			
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++) {
					if (game.board[i][j] != Config.Player.valueOf("EMPTY")) {
						if (finished[i][j] == false) {
							panels[i][j].hide();
							panels[i][j].label.setText(game.board[i][j].toString());
							panels[i][j].add(panels[i][j].label);				
							panels[i][j].label.setVisible(true);
							finished[i][j] = true;
						}
					}
				}
			if (game.board[subIndex[0]][subIndex[1]] != Config.Player.valueOf("EMPTY")) {
				for(int i = 0; i < 3; i++)
					for(int j = 0; j < 3; j++) {
						if (game.board[i][j] == Config.Player.valueOf("EMPTY")) {
							panels[i][j].enable();					
						}
						else
							panels[i][j].disable();
					}
			}
				
			if (game.isGameOver()) {
				for(int i = 0; i < 3; i++)
					for(int j = 0; j < 3; j++) {
						panels[i][j].removeAll();
					
					}
				if (game.didXWin()) {
					label.setText("X Wins");
				}
				else if (game.didOWin()) {
					label.setText("O Wins");
				}
				else
					label.setText("Its a tie");
				addLabel();
			}
			
			
			turn++;
//			if (turn == Config.Player.valueOf("X"))
//				turn = Config.Player.valueOf("O");
//			
//			if (turn == Config.Player.valueOf("O"))
//				turn = Config.Player.valueOf("X");	
		}
	 
		

	}
	
	public UltimateFrame() {
		setLayout(new GridLayout(3,3));
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
				finished[i][j] = false;
				panels[i][j] = new GamePanel(i, j);
				panels[i][j].setVisible(true);
				add(panels[i][j]);
			}
		//menuBar.setVisible(true);
		//setJMenuBar(menuBar);
		setSize(750, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		validate();		
		
	}
	public void addLabel() {
		this.add(label);
	}
	
}
