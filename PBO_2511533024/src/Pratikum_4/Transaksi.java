package Pratikum_2;
	
public class Transaksi {
	//mengubah semua atribut menjadi private 
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	public Transaksi(String id, String jenis,double nominal)
	{
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
		}
	
	//hanya menyediakan Getter (read only)
	public String getidTransaksi() { return idTransaksi; }
	public String getjenis() {return jenis; }
	public double getnominal() {return nominal; }
	
	
	public void cetakdetail() {
		System.out.println("ID : " + idTransaksi + " | jenis : " + jenis + " | Nominal : Rp" + nominal);
	}
}
