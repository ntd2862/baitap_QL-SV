import java.io.Serializable;

public class PhanCong implements Serializable {
    private LaiXe laiXe;
    private Tuyen tuyen;
    private int soLuot;

    public PhanCong(LaiXe laiXe, Tuyen tuyen, int soLuot) {
        this.laiXe = laiXe;
        this.tuyen = tuyen;
        this.soLuot = soLuot;
    }

    public LaiXe getLaiXe() {
        return laiXe;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public int getSoLuot() {
        return soLuot;
    }

    @Override
    public String toString() {
        return "PhanCong{" +
                "laiXe=" + laiXe +
                ", tuyen=" + tuyen +
                ", soLuot=" + soLuot +
                '}';
    }
}
