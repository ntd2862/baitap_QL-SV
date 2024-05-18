import java.util.Scanner;

class SinhVien {
    private static int nextId = 10000;
    private int maSV;
    private String hoTen;
    private String diaChi;
    private String soDT;
    private String lop;

    public SinhVien(String hoTen, String diaChi, String soDT, String lop) {
        this.maSV = nextId++;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.lop = lop;
    }

    public int getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void printInfo() {
        System.out.println("Ma SV: " + maSV + ", Ho ten: " + hoTen + ", Dia chi: " + diaChi + ", So DT: " + soDT + ", Lop: " + lop);
    }
}

class MonHoc {
    private static int nextId = 100;
    private int maMH;
    private String tenMH;
    private int soDVHT;
    private String loaiMH;

    public MonHoc(String tenMH, int soDVHT, String loaiMH) {
        this.maMH = nextId++;
        this.tenMH = tenMH;
        this.soDVHT = soDVHT;
        this.loaiMH = loaiMH;
    }

    public int getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public int getSoDVHT() {
        return soDVHT;
    }

    public void printInfo() {
        System.out.println("Ma MH: " + maMH + ", Ten MH: " + tenMH + ", So DVHT: " + soDVHT + ", Loai MH: " + loaiMH);
    }
}

class BangDiem {
    private SinhVien sinhVien;
    private MonHoc monHoc;
    private float diem;

    public BangDiem(SinhVien sinhVien, MonHoc monHoc, float diem) {
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.diem = diem;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public float getDiem() {
        return diem;
    }

    public void printInfo() {
        System.out.println("Ma SV: " + sinhVien.getMaSV() + ", Ho ten: " + sinhVien.getHoTen() + ", Ma MH: " + monHoc.getMaMH() + ", Ten MH: " + monHoc.getTenMH() + ", Diem: " + diem);
    }
}

public class QuanLySinhVien {
    private static SinhVien[] dsSinhVien = new SinhVien[100];
    private static MonHoc[] dsMonHoc = new MonHoc[100];
    private static BangDiem[] dsBangDiem = new BangDiem[100];
    private static int svCount = 0;
    private static int mhCount = 0;
    private static int bdCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Nhap danh sach sinh vien moi.");
            System.out.println("2. Nhap danh sach mon hoc moi.");
            System.out.println("3. Nhap diem cho moi sinh vien va in danh sach.");
            System.out.println("4. Sap xep danh sach bang diem.");
            System.out.println("5. Tinh diem tong ket chung cho moi sinh vien.");
            System.out.println("0. Thoat.");
            System.out.print("Lua chon cua ban: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    nhapDanhSachSinhVien(scanner);
                    inDanhSachSinhVien();
                    break;
                case 2:
                    nhapDanhSachMonHoc(scanner);
                    inDanhSachMonHoc();
                    break;
                case 3:
                    nhapDiem(scanner);
                    inDanhSachBangDiem();
                    break;
                case 4:
                    sapXepBangDiem(scanner);
                    break;
                case 5:
                    tinhDiemTongKet();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private static void nhapDanhSachSinhVien(Scanner scanner) {
        System.out.print("Nhap so luong sinh vien: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Nhap ho ten: ");
            String hoTen = scanner.nextLine();
            System.out.print("Nhap dia chi: ");
            String diaChi = scanner.nextLine();
            System.out.print("Nhap so DT: ");
            String soDT = scanner.nextLine();
            System.out.print("Nhap lop: ");
            String lop = scanner.nextLine();
            dsSinhVien[svCount++] = new SinhVien(hoTen, diaChi, soDT, lop);
        }
    }

    private static void inDanhSachSinhVien() {
        System.out.println("Danh sach sinh vien:");
        for (int i = 0; i < svCount; i++) {
            dsSinhVien[i].printInfo();
        }
    }

    private static void nhapDanhSachMonHoc(Scanner scanner) {
        System.out.print("Nhap so luong mon hoc: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Nhap ten mon hoc: ");
            String tenMH = scanner.nextLine();
            System.out.print("Nhap so DVHT: ");
            int soDVHT = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            System.out.print("Nhap loai mon hoc: ");
            String loaiMH = scanner.nextLine();
            dsMonHoc[mhCount++] = new MonHoc(tenMH, soDVHT, loaiMH);
        }
    }

    private static void inDanhSachMonHoc() {
        System.out.println("Danh sach mon hoc:");
        for (int i = 0; i < mhCount; i++) {
            dsMonHoc[i].printInfo();
        }
    }

    private static void nhapDiem(Scanner scanner) {
        System.out.print("Nhap ma sinh vien: ");
        int maSV = scanner.nextInt();
        System.out.print("Nhap ma mon hoc: ");
        int maMH = scanner.nextInt();
        System.out.print("Nhap diem: ");
        float diem = scanner.nextFloat();

        SinhVien sv = timSinhVien(maSV);
        MonHoc mh = timMonHoc(maMH);

        if (sv != null && mh != null) {
            dsBangDiem[bdCount++] = new BangDiem(sv, mh, diem);
        } else {
            System.out.println("Sinh vien hoac mon hoc khong ton tai.");
        }
    }

    private static SinhVien timSinhVien(int maSV) {
        for (int i = 0; i < svCount; i++) {
            if (dsSinhVien[i].getMaSV() == maSV) {
                return dsSinhVien[i];
            }
        }
        return null;
    }

    private static MonHoc timMonHoc(int maMH) {
        for (int i = 0; i < mhCount; i++) {
            if (dsMonHoc[i].getMaMH() == maMH) {
                return dsMonHoc[i];
            }
        }
        return null;
    }

    private static void inDanhSachBangDiem() {
        System.out.println("Danh sach bang diem:");
        for (int i = 0; i < bdCount; i++) {
            dsBangDiem[i].printInfo();
        }
    }

    private static void sapXepBangDiem(Scanner scanner) {
        System.out.println("Sap xep theo:");
        System.out.println("1. Ho ten sinh vien");
        System.out.println("2. Ten mon hoc");
        System.out.print("Lua chon cua ban: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                sapXepTheoHoTenSinhVien();
                break;
            case 2:
                sapXepTheoTenMonHoc();
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }

        inDanhSachBangDiem();
    }

    private static void sapXepTheoHoTenSinhVien() {
        for (int i = 0; i < bdCount - 1; i++) {
            for (int j = i + 1; j < bdCount; j++) {
                if (dsBangDiem[i].getSinhVien().getHoTen().compareTo(dsBangDiem[j].getSinhVien().getHoTen()) > 0) {
                    BangDiem temp = dsBangDiem[i];
                    dsBangDiem[i] = dsBangDiem[j];
                    dsBangDiem[j] = temp;
                }
            }
        }
    }

    private static void sapXepTheoTenMonHoc() {
        for (int i = 0; i < bdCount - 1; i++) {
            for (int j = i + 1; j < bdCount; j++) {
                if (dsBangDiem[i].getMonHoc().getTenMH().compareTo(dsBangDiem[j].getMonHoc().getTenMH()) > 0) {
                    BangDiem temp = dsBangDiem[i];
                    dsBangDiem[i] = dsBangDiem[j];
                    dsBangDiem[j] = temp;
                }
            }
        }
    }

    private static void tinhDiemTongKet() {
        for (int i = 0; i < svCount; i++) {
            SinhVien sv = dsSinhVien[i];
            float tongDiem = 0;
            int tongDVHT = 0;

            for (int j = 0; j < bdCount; j++) {
                if (dsBangDiem[j].getSinhVien().getMaSV() == sv.getMaSV()) {
                    tongDiem += dsBangDiem[j].getDiem() * dsBangDiem[j].getMonHoc().getSoDVHT();
                    tongDVHT += dsBangDiem[j].getMonHoc().getSoDVHT();
                }
            }

            float diemTongKet = (tongDVHT == 0) ? 0 : tongDiem / tongDVHT;
            System.out.println("Ma SV: " + sv.getMaSV() + ", Ho ten: " + sv.getHoTen() + ", Diem tong ket: " + diemTongKet);
        }
    }
}
