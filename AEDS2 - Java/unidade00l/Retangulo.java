public class Retangulo {
    private double base;
    private double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public Retangulo() {
        this(1.0, 1.0); // Construtor padrão
    }

    public double getArea() {
        return base * altura;
    }

    public double getPerimetro() {
        return 2 * (base + altura);
    }

    public double getDiagonal() {
        return Math.sqrt(base * base + altura * altura);
    }
}
