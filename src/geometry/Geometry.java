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
}
