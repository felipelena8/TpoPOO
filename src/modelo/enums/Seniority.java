package modelo.enums;

public enum Seniority {

	JUNIOR(4, 100), SEMISENIOR(3, 70), SENIOR(2, 50);

	private double tiempoParaInstalacion;
	private double precioHora;

	private Seniority(double tiempoParaInstalacion, double precioHora) {
		this.tiempoParaInstalacion = tiempoParaInstalacion;
		this.precioHora = precioHora;
	}

	public double getTiempoParaInstalacion() {
		return tiempoParaInstalacion;
	}

	public void setTiempoParaInstalacion(double tiempoParaInstalacion) {
		this.tiempoParaInstalacion = tiempoParaInstalacion;
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}

}
