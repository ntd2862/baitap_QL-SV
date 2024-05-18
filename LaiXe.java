import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class LaiXe implements Serializable {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int maLX;
    private String hoTen;
    private String diaChi;
    private String soDT;
    private String trinhDo;

    public LaiXe(String hoTen, String diaChi, String soDT, String trinhDo) {
        this.maLX = count.incrementAndGet();
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.trinhDo = trinhDo;
    }

    public int getMaLX() {
        return maLX;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    @Override
    public String toString() {
        return "LaiXe{" +
                "maLX=" + maLX +
                ", hoTen='" + hoTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDT='" + soDT + '\'' +
                ", trinhDo='" + trinhDo + '\'' +
                '}';
    }
}
