package MyMath;
import edu.princeton.cs.algs4.StdIn;

public class LnComputer {
    /**
     * @return ln(n!)
     */
    public static double lnOfFactorial(int n){
        assert n >= 0;
        if (n == 0){return 0;}
        else{return Math.log(n)+lnOfFactorial(n-1);}
    }
    public static void main(String[] args) {
        System.out.println(lnOfFactorial(StdIn.readInt()));
    }
}
