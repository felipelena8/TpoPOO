package modelo.costos;

public class Almuerzo {
private static double valorMaximoAlmuerzo;
private boolean tiempoAlmuerzo;
private double valorAlmuerzo;

public static double getValorMaximoAlmuerzo() {
	return valorMaximoAlmuerzo;
}
public static void setValorMaximoAlmuerzo(double valorMaximoAlmuerzo) {
	Almuerzo.valorMaximoAlmuerzo = valorMaximoAlmuerzo;
}
public double getValorAlmuerzo() {
	return valorAlmuerzo;
}
public void setValorAlmuerzo(double valorAlmuerzo) {
	this.valorAlmuerzo = valorAlmuerzo;
}
public boolean isTiempoAlmuerzo() {
	return tiempoAlmuerzo;
}
public void setTiempoAlmuerzo(boolean tiempoAlmuerzo) {
	this.tiempoAlmuerzo = tiempoAlmuerzo;
}


}
