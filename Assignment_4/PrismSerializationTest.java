import java.io.*;
// Abstract Shape class
abstract class Shape {
    public abstract double getPerimeter();
    public abstract double getArea();
    public abstract void printNameShape();
    public abstract void print();
}

// Triangle class
class Triangle extends Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        setSides(a, b, c);
    }

    public Triangle() {
        this(1.0, 1.0, 1.0);
    }

    public double getA() { return a; }
    public void setA(double a) { this.a = a; }
    public double getB() { return b; }
    public void setB(double b) { this.b = b; }
    public double getC() { return c; }
    public void setC(double c) { this.c = c; }

    public void setSides(double a, double b, double c) {
        if (a > 0 && b > 0 && c > 0 && isValidTriangle(a, b, c)) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public boolean isValidTriangle(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public void printNameShape() {
        System.out.println("Triangle");
    }

    @Override
    public void print() {
        System.out.printf("Triangle with sides %.2f, %.2f, and %.2f, has a perimeter L=%.2f and an area P=%.2f.%n",
                a, b, c, getPerimeter(), getArea());
    }
}

// TriangularPrism class (Serializable)
class TriangularPrism extends Triangle implements Serializable {
    private static final long serialVersionUID = 1L;

    private double height;

    public TriangularPrism() {
        super();
        this.height = 1.0;
    }

    public TriangularPrism(double a, double b, double c, double height) {
        super(a, b, c);
        setHeight(height);
    }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    @Override
    public double getArea() {
        double baseArea = super.getArea();
        double lateralArea = getPerimeter() * height;
        return 2 * baseArea + lateralArea;
    }

    public double getVolume() {
        return super.getArea() * height;
    }

    @Override
    public void printNameShape() {
        System.out.println("Triangular prism");
    }

    @Override
    public void print() {
        System.out.printf(
                "Triangular prism with a base with sides a=%.2f, b=%.2f and c=%.2f, and a height %.2f, "
                        + "has an area P=%.2f and a volume V=%.2f.%n",
                getA(), getB(), getC(), height, getArea(), getVolume());
    }
}

// Test class for serialization
public class PrismSerializationTest {
    public static void main(String[] args) {

        // Create 7 triangular prisms
        TriangularPrism[] prisms = new TriangularPrism[7];
        prisms[0] = new TriangularPrism(3, 4, 5, 3);
        prisms[1] = new TriangularPrism(4, 4, 4, 5);
        prisms[2] = new TriangularPrism(5, 5, 5, 2);
        prisms[3] = new TriangularPrism(6, 8, 10, 7);
        prisms[4] = new TriangularPrism(2, 2, 3, 6);
        prisms[5] = new TriangularPrism(7, 7, 10, 4);
        prisms[6] = new TriangularPrism(9, 12, 15, 8);

        String filename = "prisms3.ser";

        // Serialize to file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (TriangularPrism prism : prisms) {
                out.writeObject(prism);
            }
            System.out.println("7 triangular prisms serialized into " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize from file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("\n Deserialized triangular prisms:");
            for (int i = 0; i < prisms.length; i++) {
                TriangularPrism p = (TriangularPrism) in.readObject();
                System.out.print("Prism " + (i + 1) + ": ");
                p.print();
                System.out.println("-------------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
