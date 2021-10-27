import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MST_Barang")
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id_barang;
    private String nama_barang;
    private Integer jumlah;
    private Integer harga;

    public Barang() {

    }

    public Barang(String id_barang, String nama_barang, Integer jumlah, Integer harga) {
        super();
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    @Id
    @Column(name = "id_barang", unique = true, nullable = false)
    public String getId_barang() {
        return this.id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    @Column(name = "nama_barang")
    public String getNama_barang() {
        return this.nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    @Column(name = "jumlah")
    public Integer getJumlah() {
        return this.jumlah;
    }

    public void setJumlah(Integer jumlah) {
        if (jumlah < 0) {
            this.jumlah = 0;
        } else {
            this.jumlah = jumlah;
        }
    }

    @Column(name = "harga")
    public Integer getHarga() {
        return this.harga;
    }

    public void setHarga(Integer harga) {
        if (harga < 0) {
            this.harga = 0;
        } else {
            this.harga = harga;
        }
    }
}
