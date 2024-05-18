import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Tuyen implements Serializable {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int maTuyen;
    private double khoangCach;
    private int soDiemDung;

    public Tuyen(double khoangCach, int soDiemDung) {
        this.maTuyen = count.incrementAndGet();
        this.khoangCach = khoangCach;
        this.soDiemDung = soDiemDung;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public double getKhoangCach() {
        return khoangCach;
    }

    public int getSoDiemDung() {
        return soDiemDung;
    }

    @Override
    public String toString() {
        return "Tuyen{" +
                "maTuyen=" + maTuyen +
                ", khoangCach=" + khoangCach +
                ", soDiemDung=" + soDiemDung +
                '}';
    }
}
