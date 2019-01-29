package geometry;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import mathematics.Mathematics;

public class Geometry {

    public static Point2D getRandomPoint(Bounds bounds){
        double x = Mathematics.getRandom(bounds.getMinX(), bounds.getMaxX());
        double y = Mathematics.getRandom(bounds.getMinY(), bounds.getMaxY());
        return new Point2D(x,y);
    }

    /**
     * This method checks just x and y coordinates, without z
     */
    public static boolean containsXY(Bounds outer, Bounds inner){

        if (inner.getMinX() < outer.getMinX())
            return false;
        if (inner.getMinY() < outer.getMinY())
            return false;
        if (inner.getMaxX() > outer.getMaxX())
            return false;
        if (inner.getMaxY() > outer.getMaxY())
            return false;

        return true;
    }
}
