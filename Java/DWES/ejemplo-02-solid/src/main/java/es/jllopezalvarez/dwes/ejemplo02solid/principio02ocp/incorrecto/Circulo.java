package es.jllopezalvarez.dwes.ejemplo02solid.principio02ocp.incorrecto;

public class Circulo {
    private final double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }
}