package es.jllopezalvarez.dwes.ejemplo02solid.principio02ocp.correcto;

public class CalculadoraAreas {
    public double calcularArea(Figura figura) {
        return figura.calcularArea();
    }
}
