package net.harupiza.hikouki.aircraft;

public class SeparatedPart<T> extends PartMeta {
    public T part;

    public SeparatedPart(double x, double y, double z, T part) {
        super(x, y, z);
        this.part = part;
    }
}
