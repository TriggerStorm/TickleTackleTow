package TTT.bll.move;

/**
 *
 * @author cille
 */
public class Move implements IMove{
    private int x;
    private int y;
    
    public Move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int getX()
    {
        return x;
    }
    
    @Override
    public int getY()
    {
        return y;
    }
    public void setY(int y){
        this.y=y;
    }

    public void setX(int x){
        this.x=x;
        
    }
    public String toString() {
        return "("+x+","+y+")";
    }
}
