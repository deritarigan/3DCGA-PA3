package app.deritarigan.com.a3dcga;

public class MatrixModel {
    private double x,y,z,p;

    public MatrixModel(){

    }

    public MatrixModel(double x, double y, double z, double p) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.p = p;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

}
