
public class UltimateBoard extends GameBoard{
	
	public GameBoard[][] boardOfBoards = new GameBoard[3][3];

	public UltimateBoard() {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
				boardOfBoards[i][j] = new GameBoard();
				board[i][j] = Config.Player.valueOf("EMPTY");
			}
	}
	
	public void updateBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (boardOfBoards[i][j].didXWin())
					board[i][j] = Config.Player.valueOf("X");
				else if (boardOfBoards[i][j].didOWin())
					board[i][j] = Config.Player.valueOf("O");
				else if (boardOfBoards[i][j].isGameOver())
					board[i][j] = Config.Player.valueOf("TIE");
			}
		}
		
	}

}
