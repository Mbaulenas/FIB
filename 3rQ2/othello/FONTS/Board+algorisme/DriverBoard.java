import java.io.*;
import java.util.*;
import java.awt.Point;

public class DriverBoard {
	static void TCES() {
		GameMode gm=new GameMode();
		Board b=new Board(gm);
		System.out.println("Default board created. Showing contents...");
		b.print();
		Board b2=new Board("board1.txt",gm);
		System.out.println("Board loaded from board1.txt. Showing contents...");
		b2.print();
		System.out.println("Let's compare the two boards:");
		System.out.println((b.equals(b2))? "equal":"not equal");
		b=new Board(b2);
		System.out.println("Copy created. Showing contents...");
		b.print();
		System.out.println("Let's compare the two boards:");
		System.out.println((b.equals(b2))? "equal":"not equal");
		System.out.println("Finally, let's transform one of the boards into a string:");
		System.out.println(b.toString());
		System.out.println("Test successfully completed");
	}

	static void testLimits() {
		GameMode gm=new GameMode();
		Board b=new Board(gm);
		System.out.println(b.set(2,3,'B')+" new black pieces.");
		System.out.println(b.set(2,4,'W')+" new white pieces.");
		System.out.println(b.set(1,5,'B')+" new black pieces.");
		for(Point p:b.available_positions()) System.out.print("("+p.x+","+p.y+")"+" ");
		System.out.println();
		System.out.println(b.pieces(0)+" white pieces.");
		System.out.println(b.pieces(1)+" black pieces.");
		System.out.println(b.set(1,4,'W')+" new white pieces.");
		System.out.println(b.set(0,6,'B')+" new black pieces.");
		System.out.println(b.set(0,6,'W')+" new white pieces.");
		System.out.println(b.set(0,5,'B')+" new black pieces.");
		System.out.println(b.set(0,7,'B')+" new black pieces.");
		System.out.println(b.set(4,2,'W')+" new white pieces.");
		System.out.println(b.set(5,1,'B')+" new black pieces.");
		System.out.println(b.set(4,1,'W')+" new white pieces.");
		System.out.println(b.set(6,1,'W')+" new white pieces.");
		System.out.println(b.set(6,0,'B')+" new black pieces.");
		System.out.println(b.set(6,2,'B')+" new black pieces.");
		System.out.println(b.set(5,3,'B')+" new black pieces.");
		System.out.println(b.set(5,2,'W')+" new white pieces.");
		System.out.println(b.set(7,0,'W')+" new white pieces.");
		System.out.println((b.can_play('W'))?"yes":"no");
		b.print();
		System.out.println("Test successfully completed");
	}

	static void test_gms() {
		Board b=new Board(new GameMode());
		System.out.println(b.set(3,2,'B')+" new black pieces.");
		System.out.println("Let's allow only vertical captures");
		b.change_gm(true,false,false);
		for(Point p:b.available_positions()) System.out.print("("+p.x+","+p.y+")"+" ");
		System.out.println();
		System.out.println("Let's allow only diagonal captures");
		b.change_gm(false,false,true);
		for(Point p:b.available_positions()) System.out.print("("+p.x+","+p.y+")"+" ");
		System.out.println();
		b.print();
		System.out.println(b.set(5,4,'B')+" new black pieces.");
		System.out.println("Let's allow vertical and diagonal captures");
b.change_gm(true,false,true);
		System.out.println(b.set(5,4,'B')+" new black pieces.");
		System.out.println("Test successfully completed");
	}

	static void test_err() {
		System.out.println("Warning! Before executing this test you should copy the contents of the boards directory and restore it when the test finishes, otherwise you won't be able to repeat the test. Type y whenever you are ready to continue, any other key to go back.");
		Scanner scn=new Scanner(System.in);
		if(scn.next().charAt(0)=='y') {
			System.out.println("Let's try to load a board with a wrong number of characters");
			Board b=new Board("board2.txt",new GameMode());
			System.out.println("Let's try to load a board with wrong characters");
			b=new Board("board3.txt",new GameMode());
			System.out.println("Let's try to load a board that doesn't exist");
			b=new Board("board3.txt",new GameMode());
			System.out.println("Let's try to create a board with a null game mode");
			b=new Board((GameMode)null);
			System.out.println("Let's try to copy a null board");
			b=new Board((Board)null);
			System.out.println("Let's try to set a piece in a non existent position");
			b=new Board(new GameMode());
			try {
				b.set(3,8,'B');
			} catch(IndexOutOfBoundsException ex1) {
				System.out.println("This operation would arise an exception.");
			}
			System.out.println("Finally, let's try to ask whether a non existent player can play or not");
			try {
				b.can_play('X');
			} catch(IndexOutOfBoundsException ex1) {
				System.out.println("This operation would arise an exception.");
			}
			System.out.println("Test successfully completed");
		}
	}

	static void personalizedMode() {
		System.out.println("Personalized mode:");
		System.out.println("Type set followed by two integers and a character B/W to set a piece: set 2 3 W");
		System.out.println("Type gm followed by three characters t/f indicating whether the vertical, horizontal and diagonal capture is allowed: gm tft");
		System.out.println("Type load followed by a filename located in a subdirectory boards to load a board: load board1.txt");
		System.out.println("Type play followed by a char W/B to check whether a player can play or not: play W");
		System.out.println("Type print to print the board, white/black to see how many white/black pieces are on the board, adj to see all the valid possitions, restart to reset to a default possition and exit to stop testing.");
		GameMode gm=new GameMode();
		Board b=new Board(gm);
		Scanner s;
		while(true) {
			System.out.println("Type your instruction:");
			s=new Scanner(System.in);
			String str=s.next();
			System.out.println(str+":");
			if(str.equals("print")) b.print();
			else if(str.equals("set")) {
				int x=s.nextInt();
				int y=s.nextInt();
				char c=s.next().charAt(0);
				int res=b.set(x,y,c);
				System.out.println(res);
			} else if(str.equals("exit")) break;
			else if(str.equals("white")) System.out.println(b.pieces(0));
			else if(str.equals("black")) System.out.println(b.pieces(1));
			else if(str.equals("load")) b=new Board(s.next(),gm);
			else if(str.equals("adj")) {
				for(Point p:b.available_positions()) System.out.print("("+p.x+","+p.y+") ");
				System.out.println();
			} else if(str.equals("restart")) b=new Board(gm);
			else if(str.equals("gm")) {
				boolean v,h,d;
				String st=s.next();
				v=(st.charAt(0)=='t')? true : false;
				h=(st.charAt(1)=='t')? true : false;
				d=(st.charAt(2)=='t')? true : false;
			b.change_gm(v,h,d);
			} else if(str.equals("play")) {
				char c=s.next().charAt(0);
				System.out.println((b.can_play(c))?"yes":"no");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("Type 1 to test the constructors and some lateral functions, 2 to test the limits of the board, 3 to test different game modes, 4 for a mixed test, 5 to enter a personalized mode or 0 to exit.");
			switch(sc.nextInt()) {
				case 1:
				TCES();
				break;
				case 2:
				testLimits();
				break;
				case 3:
				test_gms();
				break;
				case 4:
				test_err();
				break;
				case 5:
				personalizedMode();
				break;
				case 0:
				return;
			}
		}
	}
}