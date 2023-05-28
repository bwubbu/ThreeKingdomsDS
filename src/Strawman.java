
package kingdom;
import java.util.Random;
public class Strawman {
    private int StrawmanNum;
    private int Efficiency=100;
    private int percentage_decrease;
    private int usage1 = 0;
    private int usage2 = 0;
    private Random r = new Random();
    
    public Strawman(int strawman_num){
        this.StrawmanNum=strawman_num;
    }
    public void Decreased(){
        percentage_decrease = Efficiency/3 + r.nextInt(5);
        Efficiency = Efficiency - percentage_decrease;
        StrawmanNum = (int)((double)Efficiency/100*StrawmanNum);
    }
    public int GetStrawManLeft(){
        if(usage1 == 0){
            usage1++;
            return StrawmanNum;
        }
        else{
            usage1++;
            Decreased();
            return StrawmanNum;
        }
            
    }
    public int GetEfficiencyLeft(){
        return Efficiency;
    }
    public int ArrowCollected(int arrow){
        return (int) (((double)StrawmanNum/100)*arrow);
    }
}