

public class GameBoard {
	
	public Config.Player[][] board = new Config.Player[3][3];

	public Config.Player[][] getBoard() {
		return board;
	}
	
	public GameBoard() {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
				board[i][j] = Config.Player.valueOf("EMPTY");
			}
	}
	
	public void setBoard(Config.Player[][] board) {
		this.board = board;
	}
	
	public Boolean isGameOver() {
		if (didXWin())
			return true;
		if (didOWin())
			return true;
		
		for (Config.Player[] row : board) {
			for (Config.Player space : row) {
				if (space == Config.Player.valueOf("EMPTY"))
					return false;
			}
		}
		return true;
	}
	
	public Boolean didXWin() {
		for (Config.Player[] row : board) {
			if (row[0] == Config.Player.valueOf("X") && row[1] == Config.Player.valueOf("X") && row[2] == Config.Player.valueOf("X"))
				return true;	
		}
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == Config.Player.valueOf("X") && board[1][i] == Config.Player.valueOf("X") && board[2][i] == Config.Player.valueOf("X"))
				return true;
		}
		if (board[0][0] == Config.Player.valueOf("X")&& board[1][1] == Config.Player.valueOf("X") && board[2][2] == Config.Player.valueOf("X"))
			return true;
		if (board[2][0] == Config.Player.valueOf("X") && board[1][1] == Config.Player.valueOf("X") && board[0][2] == Config.Player.valueOf("X"))
			return true;
		return false;
	}
	
	public Boolean didOWin() {
		for (Config.Player[] row : board) {
			if (row[0] == Config.Player.valueOf("O") && row[1] == Config.Player.valueOf("O") && row[2] == Config.Player.valueOf("O"))
				return true;	
		}
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == Config.Player.valueOf("O") && board[1][i] == Config.Player.valueOf("O") && board[2][i] == Config.Player.valueOf("O"))
				return true;
		}
		if (board[0][0] == Config.Player.valueOf("O") && board[1][1] == Config.Player.valueOf("O") && board[2][2] == Config.Player.valueOf("O"))
			return true;
		if (board[2][0] == Config.Player.valueOf("O") && board[1][1] == Config.Player.valueOf("O") && board[0][2] == Config.Player.valueOf("O"))
			return true;
		return false;
	}
	
	public void makeMove(Config.Player player, int row, int col) {
		board[row][col] = player;
	}



}
