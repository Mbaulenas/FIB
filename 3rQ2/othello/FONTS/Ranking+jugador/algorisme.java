import java.awt.Point;

public class algorisme{
    public Point minimax (int depth, boolean max, board t){
        if (max){
            System.out.println("Paso per la funcio del minimax i jugo amb blanques");
        }
        else {
            System.out.println("Paso per la funcio del minimax i jugo amb negres");
        }
        Point a = new Point();
        a.x = 0;
        a.y = 0;
        return a;

    }
}