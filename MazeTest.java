package MazeTest;

//Georgios Giatsos, 3202

class Maze{

	private int rows;			
	private int cols;
	private boolean[][] tfmaze;
	private char[][] maze;
	
	public Maze(int r, int c, boolean m[][]){
		rows = r;
		cols = c;
		tfmaze = new boolean[r][c];		
		maze = new char[r][c];		
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				tfmaze[i][j] = m[i][j];
				if (m[i][j] == true) {maze[i][j] = '0';}
				else{maze[i][j] = '1';}
			}
		}
	}
	
	public boolean solve(){
		char moves[] = new char[4];
		char m = '-';
		int mi = 0;
		int r = 0;
		int old_r = 0;
		int c = 0;
		int old_c = 0;
		int i = 0;
		int n = 0;
		
		StackElement curr;
		Stack s = new Stack();
		
		while (true) {
			System.out.println(">>> " + r + ", " + c + " <<<");
			System.out.println(m);
			maze[r][c] = '*';
			i = 0;
			if (r-1 >= 0 && tfmaze[r-1][c] == true && (r-1 != old_r || c != old_c)) {moves[i] = 'u'; i++;}
			if(c+1 < cols && tfmaze[r][c+1] == true && (r != old_r || c+1 != old_c)) {moves[i] = 'r'; i++;}
			if(r+1 < rows && tfmaze[r+1][c] == true && (r+1 != old_r || c != old_c)) {moves[i] = 'd'; i++;}
			if(c-1 >= 0 && tfmaze[r][c-1] == true && (r != old_r || c-1 != old_c)) {moves[i] = 'l'; i++;}
			n = 0;
			for(; i < 4; i++){
				moves[i] = '-';
				n++;
			}
			
			for(i = 0; i < 4; i++){
				System.out.println(moves[i]);
			}
			
			if (n == 4) {
				while(true){
					curr = s.pop();
					m = curr.getNextMove();
					mi = curr.getMoveIndex();
					if (m != '-') {break;}
				}
			}
			else{
				m = moves[0];
				mi = 0;
			}
			s.push(r, c, moves, mi+1);
		
			old_r = r;
			old_c = c;
			if (m == 'u'){
				r--;
			}
			else if (m == 'r'){
				c++;
			}
			else if (m == 'd'){
				r++;
			}
			else if (m == 'l'){
				c--;
			}
		
			if (r == rows-1 && c == cols - 1){
				maze[r][c] = '*';
				return true;
			}
			
			System.out.println("old: " + old_r + " " + old_c);
			
			try {
 Thread.sleep(1000);                 
} catch(InterruptedException ex) {
 Thread.currentThread().interrupt();
}
		}
	}

	public void printSolution(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	
	}
	
}

class StackElement{
	private int cell[] = new int[2];
	private char moves[] = new char[4];
	private int cur_move;
	private StackElement next = null;

	public StackElement(int r, int c, char m[], int move_index){
		this.cell[0] = r;
		this.cell[1] = c;
		for (int i = 0; i < 4; i++){
			this.moves[i] = m[i];
		}
		this.cur_move = move_index;
	}
	
	public char getNextMove(){
		this.cur_move++;
		if (this.cur_move < 4){
			return this.moves[cur_move];
		}
		else{
			return '-';
		}
	}
	
	public int getMoveIndex(){
		return this.cur_move;
	}

	public StackElement getNext(){
		return next;
	}

	public void setNext(StackElement element){
		next = element;
	}
}

class Stack{
	private StackElement head;
	private int size = 0;

	public StackElement pop(){
		if (size == 0){ 
			System.out.println("Pop from empty stack");
			System.exit(-1);
		}

		
		head = head.getNext();
		size --;

		return head;
	}

	public void push(int r, int c, char m[], int mi){
		StackElement element = new StackElement(r, c, m, mi);
		element.setNext(head);
		head = element;
		size ++;
	}
}
