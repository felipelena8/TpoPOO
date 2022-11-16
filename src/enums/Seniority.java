package enums;

public enum Seniority {
JUNIOR(0),
SEMI_SENIOR(0),
SENIOR(0);
private double sueldo;

private Seniority(double sueldo) {
	this.sueldo = sueldo;
}
public double getSueldo() {
	return sueldo;
}

}
