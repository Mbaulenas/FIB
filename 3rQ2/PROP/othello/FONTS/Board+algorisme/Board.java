import java.io.*;
import java.util.*;
import java.awt.Point;

/**
 * Class Board
 *
 * Represents a board and contains operations to modify it.
 *
 *@author Alejandro Rodulfo
 * @version 1.0
 * */
public class Board {
	// Attributes
	/**
	 * Tiles of the board
	 */
	private char [][] tiles=new char[8][8];
	/**
	 * Tiles which are surrounded by at least one piece.
	 */
	private HashSet<Point> eadj=new HashSet<Point>();
	private HashSet<Point> badj=new HashSet<Point>();
	private HashSet<Point> wadj=new HashSet<Point>();
	/**
	 * Number of white pieces on the board
	 */
	private int white;
	/**
	 * Number of black pieces on the board
	 */
	private int black;

	/*
	 * Constructor
	 * @param gamemode rules of the game
	 * @return Returns a default board with the game mode specified by gamemode.
	 */
	public Board() {
		for(int i=0; i<8; ++i) for(int j=0; j<8; ++j) tiles[i][j]='E';
	}

	public Board(mode_joc gm) {
		if(gm==null) gm=new mode_joc();
		for(int i=0; i<8; ++i) for(int j=0; j<8; ++j) tiles[i][j]='E';
		tiles[3][3]='W';
		tiles[4][3]='B';
		tiles[4][4]='W';
		tiles[3][4]='B';
		if(gm.isDiagonal()) {
			eadj.add(new Point(2,2));
			eadj.add(new Point(2,5));
			eadj.add(new Point(5,2));
			eadj.add(new Point(5,5));
		}
		if(gm.isVertical()) {
			badj.add(new Point(2,3));
			wadj.add(new Point(2,4));
			wadj.add(new Point(5,3));
			badj.add(new Point(5,4));
		}
		if(gm.isHoritzontal()) {
			badj.add(new Point(3,2));
			wadj.add(new Point(4,2));
			wadj.add(new Point(3,5));
			badj.add(new Point(4,5));
		}
		black=2;
		white=2;
	}

	/*
	 * Constructor from a string
	 * @param name name of the file in which a board is saved
	 * @param gamemode rules of the game
	 * @return Returns a board which loads from the file specified by name and with the game mode specified by gamemode.
	 */
	public Board(String s, mode_joc gm) throws InvalidBoardException {
		if(gm==null) gm=new mode_joc();
		for(int i=0; i<64; ++i) {
			char c=s.charAt(i);
			tiles[i/8][i%8]=c;
			if(c=='B') black++;
			else if(c=='w') white++;
		}
		for(int i=0; i<64; ++i) if(is_valid(i/8,i%8,gm)) {
			boolean cpt=false;
			if(can_capture_gen(i/8,i%8,'W',gm)) {wadj.add(new Point(i/8,i%8));cpt=true;}
			if(can_capture_gen(i/8,i%8,'B',gm)) {badj.add(new Point(i/8,i%8));cpt=true;}
			if(!cpt) eadj.add(new Point(i/8,i%8));
		}
		if(!isCorrect(gm)) throw new InvalidBoardException();
	}

	/*
	 * Constructor from another board
	 * @param that Board to be copied.
	 * @return Returns a board which is a copy of that.
	 */
	public Board(Board that) {
		if(!(that==null)) {
			for(int i=0; i<8; ++i) for(int j=0; j<8; ++j) tiles[i][j]=that.tiles[i][j];
			for(Point p:that.wadj) this.wadj.add(p);
			for(Point p:that.badj) this.badj.add(p);
			for(Point p:that.eadj) this.eadj.add(p);
			white=that.white;
			black=that.black;
		}
	}

	/**
	 * Getter of the white/black pieces on the board
	 * @param player Integer being 0 for white pieces and 1 for black pieces
	 * @return Returns an integer representing the white/black pieces on the board
	 */
	public int pieces(char player) {
		if(player=='W') return white;
		else return black;
	}

	// Returns true if the tile in the position (x,y) is empty and there is at least one piece around, false otherwise.
	private boolean is_valid(int x,int y, mode_joc gm) {
		if(tiles[x][y]!='E') return false;
		if(gm.isVertical()) {
			if((x+1<8 && tiles[x+1][y]!='E') || (x-1>=0 && tiles[x-1][y]!='E')) return true;
		} if(gm.isHoritzontal()) {
			if((y+1<8 && tiles[x][y+1]!='E') || (y-1>=0 && tiles[x][y-1]!='E')) return true;
		} if(gm.isDiagonal()) {
			if((y+1<8 && x+1<8 && tiles[x+1][y+1]!='E') || (y-1>=0 && x+1<8 && tiles[x+1][y-1]!='E') || (y+1<8 && x-1>=0 && tiles[x-1][y+1]!='E') || (x-1>=0 && y-1>=0 && tiles[x-1][y-1]!='E')) return true;
		} return false;
	}

	// Returns true if any piece could be captured in the direction dir by setting a piece in the position (x,y) of the color pointed by color.
	private boolean can_capture(int x, int y, char color, int i, int j) {
		x+=i;
		y+=j;
		if(x>=0 && x<8 && y>=0 && y<8 && tiles[x][y]!='E' && tiles[x][y]!=color) {
			x+=i;
			y+=j;
			while(x>=0 && x<8 && y>=0 && y<8) {
				if(tiles[x][y]=='E') break;
				else if(tiles[x][y]==color) return true;
				x+=i;
				y+=j;
			}
		}
		return false;
	}

	private boolean can_capture_gen(int x, int y, char color, mode_joc gm) {
		int[] dirsi={-1,1,0,0,-1,-1,1,1};
		int[] dirsj={0,0,-1,1,-1,1,-1,1};
		if(gm.isVertical()) for(int i=0; i<2; ++i) if(can_capture(x,y,color,dirsi[i],dirsj[i])) return true;
		if(gm.isHoritzontal()) for(int i=2; i<4; ++i) if(can_capture(x,y,color,dirsi[i],dirsj[i])) return true;
		if(gm.isDiagonal()) for(int i=4; i<8; ++i) if(can_capture(x,y,color,dirsi[i],dirsj[i])) return true;
		return false;
	}

	/**
	 * Function to set new pieces on the board.
	 * @param x X coordinate of the piece to be set.
	 * @param y Y coordinate of the piece to be set.
	 * @param color Color of the piece to be set.
	 * @return Returns the number of new "color" pieces on the board after setting a piece in the position (x,y). If the value returned is 0, no piece was set.
	 */
	public int set(int x, int y, char color, mode_joc gm) {
		if(x<0 || y<0 || y>7 || x>7 || (color!='W' && color!='B')) throw new IndexOutOfBoundsException();
		if(!is_valid(x,y,gm)) return 0;
		if(color=='W') {
			if(!wadj.contains(new Point(x,y))) return 0;
		} else if(!badj.contains(new Point(x,y))) return 0;
		int cpt=capture(x, y, color,gm);
		tiles[x][y]=color;
		if(color=='W') {black-=cpt;white+=++cpt;}
		else {white-=cpt;black+=++cpt;}
		modify_adj(x,y,gm);
		return cpt;
	}

	public void setwc(int x, int y, char color) {
		tiles[x][y]=color;
	}

	public void delete(int x, int y) {
		tiles[x][y]='E';
	}

	// Returns the number of captured pieces of the color "color" and performs the pertinent changes on the board
	private int capture(int x, int y, char color, mode_joc gm) {
		int[] dirsi={-1,1,0,0,-1,-1,1,1};
		int[] dirsj={0,0,-1,1,-1,1,-1,1};
		int cpt=0;
		if(gm.isVertical()) for(int i=0; i<2; ++i) {
			if(can_capture(x,y,color,dirsi[i],dirsj[i])) {
				tiles[x+dirsi[i]][y+dirsj[i]]=color;
				cpt+=capture(x+dirsi[i],y+dirsj[i],color,gm)+1;
			}
		}
		if(gm.isHoritzontal()) for(int i=2; i<4; ++i) {
			if(can_capture(x,y,color,dirsi[i],dirsj[i])) {
				tiles[x+dirsi[i]][y+dirsj[i]]=color;
				cpt+=capture(x+dirsi[i],y+dirsj[i],color,gm)+1;
			}
		}
		if(gm.isDiagonal()) for(int i=4; i<8; ++i) {
			if(can_capture(x,y,color,dirsi[i],dirsj[i])) {
				tiles[x+dirsi[i]][y+dirsj[i]]=color;
				cpt+=capture(x+dirsi[i],y+dirsj[i],color,gm)+1;
			}
		}
		return cpt;
	}

	// Maintains the adj array updated
	private void modify_adj(int x, int y, mode_joc gm) {
		int[] dirsi={-1,1,0,0,-1,-1,1,1};
		int[] dirsj={0,0,-1,1,-1,1,-1,1};
		wadj.remove(new Point(x,y));
		badj.remove(new Point(x,y));
		for(Point p:badj) {
			boolean cpt=true;
			if(!can_capture_gen(p.x,p.y,'B',gm)) {badj.remove(p);cpt=false;}
			if(can_capture_gen(p.x,p.y,'W',gm)) {wadj.add(p);cpt=true;}
			if(!cpt) eadj.add(p);
		}
		for(Point p:wadj) {
			boolean cpt=true;
			if(!can_capture_gen(p.x,p.y,'W',gm)) {wadj.remove(p);cpt=false;}
			if(can_capture_gen(p.x,p.y,'B',gm)) {badj.add(p);cpt=true;}
			if(!cpt) eadj.add(p);
		}
		for(Point p:eadj) {
			if(can_capture_gen(p.x,p.y,'B',gm)) {badj.add(p);eadj.remove(p);}
			if(can_capture_gen(p.x,p.y,'W',gm)) {wadj.add(p);eadj.remove(p);}
		}
		if(gm.isVertical()) for(int k=0; k<2; ++k) {
			int i=x+dirsi[k];
			int j=y+dirsj[k];
			if(i>=0 && i<8 && j>=0 && j<8 && tiles[i][j]=='E') {
				boolean cpt=false;
				if(can_capture_gen(i,j,'W',gm)) {wadj.add(new Point(i,j));cpt=true;}
				if(can_capture_gen(i,j,'B',gm)) {badj.add(new Point(i,j));cpt=true;}
				if(!cpt) eadj.add(new Point(i,j));
			}
		}
		if(gm.isHoritzontal()) for(int k=2; k<4; ++k) {
			int i=x+dirsi[k];
			int j=y+dirsj[k];
			if(i>=0 && i<8 && j>=0 && j<8 && tiles[i][j]=='E') {
				boolean cpt=false;
				if(can_capture_gen(i,j,'W',gm)) {wadj.add(new Point(i,j));cpt=true;}
				if(can_capture_gen(i,j,'B',gm)) {badj.add(new Point(i,j));cpt=true;}
				if(!cpt) eadj.add(new Point(i,j));
			}
		}
		if(gm.isDiagonal()) for(int k=4; k<8; ++k) {
			int i=x+dirsi[k];
			int j=y+dirsj[k];
			if(i>=0 && i<8 && j>=0 && j<8 && tiles[i][j]=='E') {
				boolean cpt=false;
				if(can_capture_gen(i,j,'W',gm)) {wadj.add(new Point(i,j));cpt=true;}
				if(can_capture_gen(i,j,'B',gm)) {badj.add(new Point(i,j));cpt=true;}
				if(!cpt) eadj.add(new Point(i,j));
			}
		}
	}

	/**
	 * Function to return the content of a tyle of the board. Returns E for an empty tile, B for a black piece and W for a white one.
	 */
	public char contains(int i,int j) {
		return tiles[i][j];
	}

	/**
	 * Getter of the adj HashSet.
	 * @return Returns an HashSet containing all the positions in the board with at least one piece around.
	 */
	public HashSet<Point> available_positions(char color) {
		if(color=='W') return wadj;
		else if(color=='B') return badj;
		else return eadj;
	}

	/**
	 * Function to know whether a player can play.
	 * @param color Character identifying the color of the player
	 * @return Returns Returns true if there is at least one position in which the player can set a piece, false otherwise.
	 */
	public boolean can_play(char color) {
		if(color=='W' && wadj.size()>0) return true;
		else if(color=='B' && badj.size()>0) return true;
		return false;
	}

	/**
	 * Overriden function
	 * @return Returns a string representation of the board
	 */
	public String toString() {
		char[] s=new char[64];
		for(int i=0; i<64; ++i) s[i]=tiles[i/8][i%8];
		return new String(s);
	}

	/**
	 * Overriden function
	 * @param obj Object to be compared with the board.
	 * @return Returns true if obj is equal to the board, false otherwise.
	 */
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		Board b=(Board)obj;
		for(int i=0; i<8; ++i) for(int j=0; j<8; ++j) if(b.tiles[i][j]!=tiles[i][j]) return false;
		return true;
	}

	/**
	 * Function to change the game mode
	 * @param v Boolean indicating whether the vertical captures are allowed
	 * @param h Boolean indicating whether the horizontal captures are allowed
	 * @param d Boolean indicating whether the diagonal captures are allowed
	 */
/*
	public void change_gm(boolean v, boolean h, boolean d) {
		gm.h=h;
		gm.v=v;
		gm.d=d;
		adj=new HashSet<Point>();
		for(int i=0; i<8; ++i) for(int j=0; j<8; ++j) if(is_valid(i,j)) adj.add(new Point(i,j));
	}
*/

	private boolean isCorrect(mode_joc gm) {
		int[] dirsi={-1,1,0,0,-1,-1,1,1};
		int[] dirsj={0,0,-1,1,-1,1,-1,1};
		if(wadj.size()==0 && badj.size()==0) return false;
		boolean[][] visited=new boolean[8][8];
		Point p=new Point(-1,-1);
		for(int i=0; i<8; ++i) for(int j=0; j<8; ++j) if(tiles[i][j]!='E') {p=new Point(i,j); break;}
		Queue<Point> q=new LinkedList<Point>();
		int pieces=0;
		q.add(p);
		while(!(q.size()==0)) {
			Point x=q.peek();
			q.poll();
			if(!visited[x.x][x.y]) {
				visited[x.x][x.y]=true;
				pieces++;
				if(gm.isVertical()) for(int i=0; i<2; ++i) {
					Point y=new Point(x.x+dirsi[i],x.y+dirsj[i]);
					if(y.x>=0 && y.x<8 && y.y>=0 && y.y<8 && tiles[y.x][y.y]!='E') q.add(y);
				}
				if(gm.isHoritzontal()) for(int i=2; i<4; ++i) {
					Point y=new Point(x.x+dirsi[i],x.y+dirsj[i]);
					if(y.x>=0 && y.x<8 && y.y>=0 && y.y<8 && tiles[y.x][y.y]!='E') q.add(y);
				}
				if(gm.isDiagonal()) for(int i=4; i<8; ++i) {
					Point y=new Point(x.x+dirsi[i],x.y+dirsj[i]);
					if(y.x>=0 && y.x<8 && y.y>=0 && y.y<8 && tiles[y.x][y.y]!='E') q.add(y);
				}
			}
		}
		if(pieces!=white+black) return false;
		return true;
	}
}