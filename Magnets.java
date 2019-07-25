
import java.util.ArrayList;

public class Magnets {

	static char[][] board = { 
			{ 'L', 'R', 'L', 'R', 'T', 'T' }, 
			{ 'L', 'R', 'L', 'R', 'D', 'D' },
			{ 'T', 'T', 'T', 'T', 'L', 'R' }, 
			{ 'D', 'D', 'D', 'D', 'T', 'T' }, 
			{ 'L', 'R', 'L', 'R', 'D', 'D' } 
			};

	static int[] top = { 1, -1, -1, 2, 1, -1 };
	static int[] bottom = { 2, -1, -1, 2, -1, 3 };
	static int[] left = { 2, 3, -1, -1, -1 };
	static int[] right = { -1, -1, -1, 1, -1 };
	static String[] words = { "+-", "-+", "xx" };
	static int counter = 0;

	static ArrayList<Slot> slots;

	public static class Slot {
		int row;
		int col;
		boolean isV;

		public Slot(int r, int c, boolean isV) {
			this.row = r;
			this.col = c;
			this.isV = isV;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		slots = new ArrayList<>();
		setSlots();
		magnets_si(0);

	}

	public static void setSlots() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'L') {
					slots.add(new Slot(i, j, false));
				} else if (board[i][j] == 'T') {
					slots.add(new Slot(i, j, true));
				}
			}
		}
	}

	public static void magnets_si(int slot) {
		if (slot == slots.size()) {

			for (int i = 0; i < top.length; i++) {
				if (top[i] == -1 && bottom[i] == -1) {
					continue;
				}
				int counterp = 0;
				int counterm = 0;
				for (int j = 0; j < board.length; j++) {
					if (board[j][i] == '+') {
						counterp++;
					}
					if (board[j][i] == '-') {
						counterm++;
					}
				}
				if (top[i] != -1 && counterp != top[i]) {
					return;

				}
				if (bottom[i] != -1 && counterm != bottom[i]) {
					return;
				}

			}

			for (int i = 0; i < left.length; i++) {
				if (left[i] == -1 && right[i] == -1) {
					continue;
				}
				int counterp = 0;
				int counterm = 0;
				for (int j = 0; j < board[0].length; j++) {
					if (board[i][j] == '+') {
						counterp++;
					}
					if (board[i][j] == '-') {
						counterm++;
					}
				}

				if (left[i] != -1 && counterp != left[i]) {
					return;

				}
				if (right[i] != -1 && counterm != right[i]) {
					return;
				}

			}
			display();
			return;
		}
		Slot cs = slots.get(slot);
		int r = cs.row;
		int c = cs.col;

		for (int i = 0; i < words.length; i++) {
			if (cs.isV) {
				if (cpv(r, c, words[i])) {
					board[r][c] = words[i].charAt(0);
					board[r + 1][c] = words[i].charAt(1);
					magnets_si(slot + 1);
					board[r][c] = 'T';
					board[r + 1][c] = 'D';
				}
			} else {
				if (cph(r, c, words[i])) {
					board[r][c] = words[i].charAt(0);
					board[r][c + 1] = words[i].charAt(1);
					magnets_si(slot + 1);
					board[r][c] = 'L';
					board[r][c + 1] = 'R';
				}
			}
		}

	}

	public static void display() {
		counter++;
		System.out.println("------------ " + counter + " --------------");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print("|" + board[i][j] + "|");
			}
			System.out.println();
		}
		System.out.println("------------ " + counter + " --------------");
	}

	public static boolean cpv(int i, int j, String word) {
		if (word.charAt(0) == 'x' && word.charAt(1) == 'x') {
			return true;
		}
		if (i - 1 >= 0 && board[i - 1][j] == word.charAt(0)) {
			return false;
		}
		if (j + 1 < board[0].length && board[i][j + 1] == word.charAt(0)) {
			return false;
		}
		if (i + 1 < board.length && j + 1 < board[0].length && board[i + 1][j + 1] == word.charAt(1)) {
			return false;
		}
		if (i + 2 < board.length && board[i + 2][j] == word.charAt(1)) {
			return false;
		}
		if (i + 1 < board.length && j - 1 >= 0 && board[i + 1][j - 1] == word.charAt(1)) {
			return false;
		}
		if (j - 1 >= 0 && board[i][j - 1] == word.charAt(0)) {
			return false;
		}
		return true;
	}

	public static boolean cph(int i, int j, String word) {
		if (word.charAt(0) == 'x' && word.charAt(1) == 'x') {
			return true;
		}
		if (i - 1 >= 0 && board[i - 1][j] == word.charAt(0)) {
			return false;
		}
		if (i - 1 >= 0 && j + 1 < board[0].length && board[i - 1][j + 1] == word.charAt(1)) {
			return false;
		}
		if (j + 2 < board[0].length && board[i][j + 2] == word.charAt(1)) {
			return false;
		}
		if (i + 1 < board.length && j + 1 < board[0].length && board[i + 1][j + 1] == word.charAt(1)) {
			return false;
		}
		if (i + 1 < board.length && board[i + 1][j] == word.charAt(0)) {
			return false;
		}
		if (j - 1 >= 0 && board[i][j - 1] == word.charAt(0)) {
			return false;
		}
		return true;
	}

}
