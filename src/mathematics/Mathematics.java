package mathematics;

public class Mathematics {

    public static double getRandom(double lower, double upper){
        return Math.random() * (upper - lower) + lower;
    }

    /**
     * @return random number from [lower...upper), including lower, excluding upper
     */
    public static int getRandom(int lower, int upper){
        return (int)(Math.random() * (upper - lower) + lower);
    }
}
