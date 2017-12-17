/**
 * Created by Виталий on 15.12.2017.
 */
public class Hero {
    private String name;
    private int x, y;
    public Hero(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String getName(){
        return name;
    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
