package net.harupiza.hikouki.aircraft;

import org.joml.Vector3d;

public class PartMeta {
    // 相対座標
    public double x, y, z;

    public PartMeta(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d asVector3d() {
        return new Vector3d(x, y, z);
    }

    public void setAsVector3d(Vector3d vector) {
        x = vector.x();
        y = vector.y();
        z = vector.z();
    }
}
