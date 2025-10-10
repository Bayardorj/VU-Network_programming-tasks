import java.util.*;

// Here is abstract class
abstract class Shape {
    // Here is abstract methods
    public abstract double getPerimeter();
    public abstract double getArea();
    public abstract void printNameShape();
    public abstract void print();
}
// Here is Triangle class which inherited from Shape class
class Triangle extends Shape {
  private double a;
  private double b;
  private double c;

  // Here is constructor with argument
  public Triangle( double a, double b, double c) {
   setSides(a,b,c);
  }

  // Here is constructor woth no argument
  public Triangle() {
    this.a = 1.0;
    this.b = 1.0;
    this.c = 1.0;
  }

  // Here is get and set method for the attributes
  public double getA() {
    return a;
  }
  public void setA(double a) {
    this.a = a;
  }
  public double getB() {
    return b;
  }
  public void setB(double b) {
    this.b = b;
  }
  public double getC() {
    return c;
  }
  public void setC(double c) {
    this.c = c;
  }
  
  // Here is i have created the method which checks whether a,b and c can be triangle or not
  // Because, if each two sides' sum is more than rest side, it can build triangle
  public void setSides(double a, double b, double c) {
    if(a > 0 && b > 0 &&  c > 0 && isValidTriangle(a,b,c)) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
  }

  public boolean isValidTriangle(double a, double b, double c) {
    return(a + b > c) && (a + c > b) && (b + c > a);
  }

  // Here is to implement with all method from Shape abstract class, i need to use override. 
  @Override
  public double getPerimeter() {
    return a + b + c;
  }

  @Override
  public double getArea() {
    double s = getPerimeter() / 2;
    return Math.sqrt(s * (s-a) * (s-b) * (s-c));
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
class TriangularPrism extends Triangle {
 
    private double height;

    public TriangularPrism() {
        super();
        this.height = 1.0;
    }

    public TriangularPrism(double a, double b, double c, double height) {
        super(a,b,c);
        setHeight(height);

    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
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
        System.out.printf("Triangular prism with a base with sides a=%.2f, b=%.2f and c=%.2f, and a height %.2f," + "has an area P=%.2f and a volume V=%.2f.%n",  getA(), getB(), getC(), height, getArea(), getVolume() );

    }
    @Override
public String toString() {
    return String.format(
        "Triangular prism with a base with sides a=%.2f, b=%.2f and c=%.2f, and a height %.2f, has an area P=%.2f and a volume V=%.2f.",
        getA(), getB(), getC(), getHeight(), getArea(), getVolume()
    );
}
}

// Here is the class for test

class Test {
    public static void main(String[] args) {
        System.out.println("Here is testing");
        Triangle testTriangle = new Triangle();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter A side: ");
        double a = scanner.nextDouble();

        System.out.print("Please enter B side: ");
        double b = scanner.nextDouble();

        System.out.print("Please enter C side: ");
        double c = scanner.nextDouble();

        testTriangle.setA(a);
        testTriangle.setB(b);
        testTriangle.setC(c);
        testTriangle.print();
        System.out.println("--------------------");

        System.out.println("Here is start for testing Triangular prism");
        System.out.println("--------------------");
        
        TriangularPrism testPrism = new TriangularPrism();
        System.out.print("Please enter height: ");
        double h = scanner.nextDouble();
        testPrism.setA(a);
        testPrism.setB(b);
        testPrism.setC(c);
        testPrism.setHeight(h);
        testPrism.print();
        scanner.close();

        

    }
}

// Here is Test2 class

class Test2 {
    public static void main(String[] args) {
        TriangularPrism[] testPrism2 = new TriangularPrism[10];
        
        // Here is list of triangle prism's sides and height. i thought i don't need to give input from keyboard.
        // Because, its task is to print the triangular prism with smallest volume
        testPrism2[0] = new TriangularPrism(3,4,5,3);
        testPrism2[1] = new TriangularPrism(3,4,5,6);
        testPrism2[2] = new TriangularPrism(5,5,5,2);
        testPrism2[3] = new TriangularPrism(7,7,10,4);
        testPrism2[4] = new TriangularPrism(2,2,3,6);
        testPrism2[5] = new TriangularPrism(7,7,10,6);
        testPrism2[6] = new TriangularPrism(4,4,4,8);
        testPrism2[7] = new TriangularPrism(9,12,15,8);
        testPrism2[8] = new TriangularPrism(4,4,4,9);
        testPrism2[9] = new TriangularPrism(5,4,5,5);

        TriangularPrism prism_with_smallest_volume = testPrism2[0];
        double minVolume = testPrism2[0].getVolume();

        for(int i = 1; i < testPrism2.length;i++) {
            double currentVolume = testPrism2[i].getVolume();
            if(currentVolume < minVolume) {
                minVolume = currentVolume;
                prism_with_smallest_volume = testPrism2[i];
            }
        }

        System.out.println("Here 10 triangular prism which are listed:");
        System.out.println("----------------");

        for(int i = 0; i < testPrism2.length;i++) {
            System.out.print("Prism " + (i + 1) + ": " );
            testPrism2[i].print();
             System.out.println("---");
        }
        System.out.println("--------------------");
        System.out.println("The Triangular prism with smallest volume: ");
        prism_with_smallest_volume.print();
        System.out.println("");

    }
}

public class third_task {
    public static void main(String[] args) {
        Test.main(args);
        Test2.main(args);
    }
}
