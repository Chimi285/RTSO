import java.util.LinkedList;

public class Building {
    private int X, Y, numberLut, enterX, enterY;
    private LinkedList<item> Lut = new LinkedList<>();
    private String texture;
    public Building(int X, int Y, int enterX, int enterY, int numberLut, String texture, LinkedList<item> Lut){
        this.X = X;
        this.Y = Y;
        this.enterX = enterX;
        this.enterY = enterY;
        this.numberLut = numberLut;
        this.texture = texture;
        this.Lut = Lut;
    }
}
