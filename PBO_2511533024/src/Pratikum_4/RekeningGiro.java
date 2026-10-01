package Pratikum_4;
import Pratikum_1.Rekening;

public class RekeningGiro extends Rekening{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		//memanggil inisialisasi dasar dari superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
		
	}
	
	//getter kuhsus giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

}
