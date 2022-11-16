package costos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Costo {
private static double costoViaje;
private int soportes;
private int seguroAltura;
private double tiempoTrabajado;

public static void setCostoViaje(double costoViaje) {
	Costo.costoViaje = costoViaje;
}

public void calcularTiempoTrabajado(LocalDateTime fechaIni, LocalDateTime fechaFin) {
	tiempoTrabajado =ChronoUnit.MINUTES.between(fechaIni, fechaFin);
}
}
