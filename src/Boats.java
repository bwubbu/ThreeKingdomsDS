
public class Boats {
    private Strawman Front,Left,Right,Back;
    private String Direction;
    public Boats(){
    
}
    public Boats(int front,int left,int Right,int Back){
    this.Front = new Strawman(front);
    this.Left = new Strawman(left);
    this.Right = new Strawman(Right);
    this.Back = new Strawman(Back);
    }
    public void setFront(int front){
        this.Front = new Strawman(front);
    }
    public void setLeft(int Left){
        this.Left = new Strawman(Left);
    }
    public void setRight(int Right){
        this.Right = new Strawman(Right);
    }
    public void setBack (int Back){
        this.Back = new Strawman(Back);
    }
    public int Attack(String Direction,int arrow){
        int x,y;
        if(Direction.equals("Front")){
        System.out.print("\nAttack from "+Direction);
        y= Front.GetStrawManLeft();
        System.out.printf("\n%d Strawman at %d efficiency",y,Front.GetEfficiencyLeft());
        x = Front.ArrowCollected(arrow);
        System.out.printf("\n%d Straw men get %d arrow",y,x);
        return x;
        }
        else if(Direction.equals("Left")){
        System.out.print("\nAttack from "+Direction);
        y= Left.GetStrawManLeft();
        System.out.printf("\n%d Strawman at %d efficiency",y,Left.GetEfficiencyLeft());
        x = Left.ArrowCollected(arrow);
        System.out.printf("\n%d Straw men can get %d arrow",y,x);
        return x;
        }
        else if(Direction.equals("Right")){
        System.out.print("\nAttack from "+Direction);
        y= Right.GetStrawManLeft();
        System.out.printf("\n%d Strawman at %d efficiency",y,Right.GetEfficiencyLeft());
        x = Right.ArrowCollected(arrow);
        System.out.printf("\n%d Straw men can get %d arrow",y,x);
        return x;
        }
        else if(Direction.equals("Back")){
        System.out.print("\nAttack from "+Direction);
        y= Back.GetStrawManLeft();
        System.out.printf("\n%d Strawman at %d efficiency",y,Back.GetEfficiencyLeft());
        x = Back.ArrowCollected(arrow);
        System.out.printf("\n%d Straw men can get %d arrow",y,x);
        return x;
        }
       else
            return 0; 
    }
    }
