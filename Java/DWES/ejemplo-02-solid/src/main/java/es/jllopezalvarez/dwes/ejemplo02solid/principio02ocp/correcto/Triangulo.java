package es.jllopezalvarez.dwes.ejemplo02solid.principio02ocp.correcto;

public class Triangulo implements Figura {
    private final double base;
    private final double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }
}