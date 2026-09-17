package Pratikum_2;
	
public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	
	public Transaksi(String id, String jenis,double nominal)
	{
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
		}
	public void cetakdetail() {
		System.out.println("ID : " + idTransaksi + " | jenis : " + jenis + " | Nominal : Rp" + nominal);
	}
}
