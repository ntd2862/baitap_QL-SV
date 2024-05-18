import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class QuanLyPhanCong {
    private List<LaiXe> dsLaiXe = new ArrayList<>();
    private List<Tuyen> dsTuyen = new ArrayList<>();
    private List<PhanCong> dsPhanCong = new ArrayList<>();

    public static void main(String[] args) {
        QuanLyPhanCong qlpc = new QuanLyPhanCong();
        qlpc.menu();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Nhập danh sách Lái xe mới");
            System.out.println("2. Nhập danh sách Tuyến mới");
            System.out.println("3. Nhập danh sách phân công");
            System.out.println("4. Sắp xếp danh sách phân công");
            System.out.println("5. Bảng kê tổng khoảng cách chạy xe trong ngày");
            System.out.println("6. Thoát");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    nhapLaiXe(scanner);
                    break;
                case 2:
                    nhapTuyen(scanner);
                    break;
                case 3:
                    nhapPhanCong(scanner);
                    break;
                case 4:
                    sapXepPhanCong(scanner);
                    break;
                case 5:
                    bangKeTongKhoangCach();
                    break;
                case 6:
                    luuDuLieu();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void nhapLaiXe(Scanner scanner) {
        System.out.println("Nhập số lượng lái xe:");
        int soLuong = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin lái xe " + (i + 1) + ":");
            System.out.print("Họ tên: ");
            String hoTen = scanner.nextLine();
            System.out.print("Địa chỉ: ");
            String diaChi = scanner.nextLine();
            System.out.print("Số điện thoại: ");
            String soDT = scanner.nextLine();
            System.out.print("Trình độ (A-F): ");
            String trinhDo = scanner.nextLine().toUpperCase();

            dsLaiXe.add(new LaiXe(hoTen, diaChi, soDT, trinhDo));
        }

        System.out.println("Danh sách lái xe:");
        dsLaiXe.forEach(System.out::println);
    }

    private void nhapTuyen(Scanner scanner) {
        System.out.println("Nhập số lượng tuyến:");
        int soLuong = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin tuyến " + (i + 1) + ":");
            System.out.print("Khoảng cách: ");
            double khoangCach = scanner.nextDouble();
            System.out.print("Số điểm dừng: ");
            int soDiemDung = scanner.nextInt();
            scanner.nextLine();

            dsTuyen.add(new Tuyen(khoangCach, soDiemDung));
        }

        System.out.println("Danh sách các tuyến:");
        dsTuyen.forEach(System.out::println);
    }

    private void nhapPhanCong(Scanner scanner) {
        System.out.println("Nhập danh sách phân công:");
        for (LaiXe lx : dsLaiXe) {
            System.out.println("Phân công cho lái xe " + lx.getHoTen() + ":");
            int tongLuot = 0;
            while (true) {
                if (tongLuot >= 15) {
                    System.out.println("Tổng số lượt không được vượt quá 15.");
                    break;
                }
                System.out.print("Nhập mã tuyến (0 để dừng): ");
                int maTuyen = scanner.nextInt();
                if (maTuyen == 0) break;
                Tuyen tuyen = dsTuyen.stream().filter(t -> t.getMaTuyen() == maTuyen).findFirst().orElse(null);
                if (tuyen == null) {
                    System.out.println("Mã tuyến không tồn tại!");
                    continue;
                }
                System.out.print("Nhập số lượt: ");
                int soLuot = scanner.nextInt();
                scanner.nextLine();

                if (soLuot + tongLuot > 15) {
                    System.out.println("Số lượt vượt quá giới hạn. Vui lòng nhập lại.");
                    continue;
                }

                PhanCong phanCong = new PhanCong(lx, tuyen, soLuot);
                if (dsPhanCong.contains(phanCong)) {
                    System.out.println("Lái xe đã được phân công vào tuyến này.");
                    continue;
                }
                dsPhanCong.add(phanCong);
                tongLuot += soLuot;
            }
        }

        System.out.println("Danh sách phân công:");
        dsPhanCong.forEach(System.out::println);
    }

    private void sapXepPhanCong(Scanner scanner) {
        System.out.println("Sắp xếp danh sách phân công:");
        System.out.println("a. Theo Họ tên lái xe");
        System.out.println("b. Theo Số lượng tuyến đảm nhận trong ngày (giảm dần)");
        char choice = scanner.next().charAt(0);

        switch (choice) {
            case 'a':
                dsPhanCong.sort(Comparator.comparing(pc -> pc.getLaiXe().getHoTen()));
                break;
            case 'b':
                dsPhanCong.sort((pc1, pc2) -> {
                    long count1 = dsPhanCong.stream().filter(pc -> pc.getLaiXe().equals(pc1.getLaiXe())).count();
                    long count2 = dsPhanCong.stream().filter(pc -> pc.getLaiXe().equals(pc2.getLaiXe())).count();
                    return Long.compare(count2, count1);
                });
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        System.out.println("Danh sách phân công sau khi sắp xếp:");
        dsPhanCong.forEach(System.out::println);
    }

    private void bangKeTongKhoangCach() {
        System.out.println("Bảng kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe:");
        Map<LaiXe, Double> tongKhoangCach = new HashMap<>();

        dsPhanCong.forEach(pc -> {
            tongKhoangCach.put(pc.getLaiXe(),
                    tongKhoangCach.getOrDefault(pc.getLaiXe(), 0.0) + pc.getSoLuot() * pc.getTuyen().getKhoangCach());
        });

        tongKhoangCach.forEach((lx, kc) -> System.out.println(lx.getHoTen() + " - " + kc + " km"));
    }

    private void luuDuLieu() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(dsLaiXe);
            oos.writeObject(dsTuyen);
            oos.writeObject(dsPhanCong);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
