package app.deritarigan.com.a3dcga;

import android.content.res.Resources;
import android.graphics.Point;

public class CountUtil {


public static MatrixModel CalculationOnMatrixVt(MatrixModel value,double p,double q,double r,int theta,int alpa, int beta,double dx,double dy){

    double degree1,degree2,degree3;
    double x2, y2, z2, p2;
    double rz = 0;
    double pz = 0;
    double qz = 0;
    if (r!=0){rz = 1.0/r;}
    if (p!=0){pz = 1.0/p;}
    if (q!=0){qz = 1.0/q;}

    degree1 = theta * Math.PI / 180;
    degree2 = alpa * Math.PI/180;
    degree3 = beta * Math.PI / 180;

    MatrixModel model = new MatrixModel();
    double value1 = (Math.cos(degree1)*Math.cos(degree3))+(Math.sin(degree1)*Math.sin(degree2)*Math.sin(degree3));
    double value2 = Math.sin(degree1)*Math.cos(degree2);
    double value3 = (-Math.sin(degree3)*Math.cos(degree1))+(Math.cos(degree3)*Math.sin(degree1)*Math.sin(degree2));
    double value4 = (-Math.sin(degree1)*Math.cos(degree3))+(Math.cos(degree1)*Math.sin(degree2)*Math.sin(degree3));
    double value5 = (Math.cos(degree1)*Math.cos(degree2));
    double value6 = (-Math.sin(degree1)*-Math.sin(degree3))+(Math.cos(degree1)*Math.sin(degree2)*Math.cos(degree3));
    double value7 = (Math.cos(degree2)*Math.sin(degree3));
    double value8 = (-Math.sin(degree2));
    double value9 = (Math.cos(degree3)*Math.cos(degree2));

    MatrixModel row1 = new MatrixModel(value1, value2,value3,value1*pz+value2*qz+value3*rz);
    MatrixModel row2 = new MatrixModel(value4,value5,value6,value4*pz + value5*qz +value6*rz);
    MatrixModel row3 = new MatrixModel(value7,value8,value9,value7*pz+value8*qz+value9*rz);
    MatrixModel row4 = new MatrixModel(dx,dy,0,1);

    x2 = (value.getX() * row1.getX()) + (value.getY() * row2.getX()) + (value.getZ() * row3.getX()) + (value.getP() * row4.getX());
    y2 = ( value.getX()* row1.getY()) + (value.getY() * row2.getY()) + (value.getZ() * row3.getY()) + (value.getP() * row4.getY());
    z2 = (value.getX() * row1.getZ()) + (value.getY() * row2.getZ()) + (value.getZ() * row3.getZ()) + (value.getP() * row4.getZ());
    p2 = (value.getX() * row1.getP()) + (value.getY() * row2.getP()) + (value.getZ() * row3.getP()) + (value.getP() * row4.getP());
    x2 = x2 / p2;
    y2 = y2 / p2;
    z2 = z2 / p2;
    p2 = p2 / p2;
    model = new MatrixModel(x2,y2,z2,p2);
    return model;
}



    public static MatrixModel MoveUp(MatrixModel value,double dx) {

        double x2, y2, z2, p2;
        MatrixModel model = new MatrixModel();
        MatrixModel row1 = new MatrixModel(1, 0, 0, 0);
        MatrixModel row2 = new MatrixModel(0, 1, 0, 0);
        MatrixModel row3 = new MatrixModel(0, 0, 1, 0);
        MatrixModel row4 = new MatrixModel(dx, 0, 0, 1);
        x2 = (value.getX() * row1.getX()) + (value.getY() * row2.getX()) + (value.getZ() * row3.getX()) + (value.getP() * row4.getX());
        y2 = ( value.getX()* row1.getY()) + (value.getY() * row2.getY()) + (value.getZ() * row3.getY()) + (value.getP() * row4.getY());
        z2 = (value.getX() * row1.getZ()) + (value.getY() * row2.getZ()) + (value.getZ() * row3.getZ()) + (value.getP() * row4.getZ());
        p2 = (value.getX() * row1.getP()) + (value.getY() * row2.getP()) + (value.getZ() * row3.getP()) + (value.getP() * row4.getP());

        model = new MatrixModel(x2,y2,z2,p2);

        return model;
    }

    public static Point getCenter(){
        Point C = new Point();
        C.x = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        C.y = Resources.getSystem().getDisplayMetrics().heightPixels / 2;

        return C;
    }
}
