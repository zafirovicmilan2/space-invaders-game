package mathematics;

public class Mathematics {
    public static double getRandom(double lower, double upper){
        return Math.random() * (upper - lower) + lower;
    }

    public static int getRandom(int lower, int upper){
        return (int)(Math.random() * (upper - lower) + lower);
    }
}
