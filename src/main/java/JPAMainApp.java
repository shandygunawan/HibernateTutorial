public class JPAMainApp {
    public static void main(String[] args) {
        BarangDao barangDao = new BarangDao();
//        Barang barang = barangDao.selectById("111");
//        System.out.println(barang.getId_barang());
//        System.out.println(barang.getNama_barang());
//        System.out.println(barang.getHarga());
//        System.out.println(barang.getJumlah());

//        barangDao.insert(new Barang("137", "test hibernate 1", 111, 111));
//        barangDao.update(new Barang("137", "test hibernate 1", 111, 112));
//        barangDao.delete(new Barang("137", "test hibernate 1", 111, 112));
        barangDao.callStoredProcedure();
    }
}
