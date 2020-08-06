package co.com.codesa.socketcodesaoperadorexterno.utilidades;

public enum TeclasEnum {
ENTER(10),
TABHORIZO(9),
TABVERTICAL(11),
NUMERO0(96),
NUMERO1SUPERIOR(49),
NUMERO1(97),
NUMERO2(98),
NUMERO2SUPERIOR(50),
NUMERO3(99),
NUMERO6(102),
NUMERO9(105),
TECLAC(67),
TECLAF(70),
TECLAI(73),
TECLAK(75),
TECLAL(76),
TECLAM(77),
TECLAN(78),
TECLAO(79),
TECLAP(80),
TECLAR(82),
TECLAV(86),
FLECHADERECHA(39),
FLECHAIZQUIERDA(37),
FLECHAABAJO(40),
FLECHAARRIBA(38);

	private  int codigoTecla;

	 TeclasEnum (int pCodigoTecla){
		this.codigoTecla=pCodigoTecla;
	}

	public int getCodigoTecla() {
		return codigoTecla;
	}

	public void setCodigoTecla(int codigoTecla) {
		this.codigoTecla = codigoTecla;
	}

}
