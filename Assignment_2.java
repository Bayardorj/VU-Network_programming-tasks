import java.util.*;

// User-defined exception class
class VectorException extends Exception {
    public VectorException() {
        super();
    }
    
    public VectorException(String message) {
        super(message);
    }
    
    public VectorException(String message, Throwable cause) {
        super(message, cause);
    }
}

class Vector {
    private static final int max_length = 15;
    private double[] elements;
    private int length;

    // Contructor with length parameter
    public Vector(int length) throws VectorException {
        if(length <= 0) {
            throw new VectorException("Vector length must be positive");
        }
        if (length > max_length) {
            throw new VectorException("Vector length cannot exceed " + max_length);
        }
        this.length = length;
        this.elements = new double[length];
    }

    // Copy elements from array
    public void copyElements(double[] arr) throws VectorException {
        if(arr.length != this.length) {
            throw new VectorException("Array's length should meet vector's length");
        }
        System.arraycopy(arr, 0, this.elements, 0, this.length);
    }

    // Here is setElement method
    public void setElement(int i, double value) throws VectorException {
        if(i < 0 || i >= length) {
            throw new VectorException("Index is out of range" + i);
        }
        elements[i] = value;
    }

    // Here is getElement method
    public double getElement(int i) throws VectorException {
        if(i < 0 || i >= length) {
            throw new VectorException("Index is out of range " + i);
        }
        return elements[i];
    }

    // Here is set_l method
    public void set_l(int newLength) throws VectorException {
        if(newLength <= 0 || newLength > max_length) {
            throw new VectorException("Length should be between 1 and " + max_length);
        }
        double[] newElements = new double[newLength];
        int minLength = Math.min(this.length, newLength);
         System.arraycopy(this.elements, 0, newElements, 0, minLength);
        
        this.elements = newElements;
        this.length = newLength;
    }

    // Here is get_l method
    public int get_l() {
        return length;
    }

    // Method of calculating scalar product
    public double scalarProduct(Vector second) throws VectorException {
         if (second == null) {
            throw new VectorException("Second vector cannot be null");
        }
        if (this.length != second.length) {
            throw new VectorException("Vectors must have the same length for scalar product");
        }
        
        double result = 0.0;
        for (int i = 0; i < length; i++) {
            result += this.elements[i] * second.elements[i];
        }
        return result;
    }

    // Here is method of calculating Vector product
    public Vector vectorProduct(Vector second) throws VectorException {
        if(second == null) {
            throw new VectorException("Second vector cannot be null");
        }
        if(this.length != 3 || second.length != 3) {
            throw new VectorException("Vector product is defined for 3d vectors");
        }

        try {
            Vector result = new Vector(3);
            result.setElement(0, this.elements[1] * second.elements[2] - this.elements[2] * second.elements[1]);
            result.setElement(1, this.elements[2] * second.elements[0] - this.elements[0] * second.elements[2]);
            result.setElement(2, this.elements[0] * second.elements[1] - this.elements[1] * second.elements[0]);
            return result;
        } catch (VectorException e) {
            throw new VectorException("Error calculating vector product", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < length; i++) {
            sb.append(elements[i]);
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    
}

// Here is TestVector class
 class TestVector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("If you want to calculate vector product, please give 3 as its length");
        System.out.println("-------");
        System.out.print("Please enter the vector length: ");
        int length = scanner.nextInt();

        try{
        Vector v1 = new Vector(length);
        Vector v2 = new Vector(length);

        for(int i = 0; i < length; i++) {
            System.out.print("Please enter element " + (i+1) + " of 1st vector: ");
            double value = scanner.nextDouble();
            v1.setElement(i, value);
        }
        for(int i = 0; i < length; i++) {
            System.out.print("Please enter element " + (i+1) + " of 2nd vector: ");
            double value = scanner.nextDouble();
            v2.setElement(i, value);
        }

        System.out.println("1st Vector's elements: " + v1);
        System.out.println("2nd Vector's elements: " + v2);
        System.out.println("---------------");

        if( length != 3) {
            double scalarResult = v1.scalarProduct(v2);
            System.out.println("Scalar product: " + scalarResult);
        } else {
            double scalarResult = v1.scalarProduct(v2);
            Vector vectorResult = v1.vectorProduct(v2);
            System.out.println("Scalar product: " + scalarResult);
            System.out.println("Vector product: " + vectorResult);
        }
        // Showing exception handling
        System.out.println("----------------------------");
        System.out.println("Here is some exception handling: ");

        //
        // Test invalid index
        System.out.println("[Here is 1st example]: if you want to give value on invalid index, you will see the following error: ");
            try {
                v1.setElement(10, 5.0);
            } catch (VectorException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }
            System.out.println("----------------------------");
            // Test different lengths for scalar product
            System.out.println("[Here is 2nd example]: if you want to make scalar calculation with vectors that has different size, you will see the following error: ");
            try {
                Vector v3 = new Vector(2);
                v1.scalarProduct(v3);
            } catch (VectorException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }
            System.out.println("----------------------------");
            // Test vector product with non-3D vectors
            System.out.println("[Here is 3rd example]: if you want to make vector calculation with non-3D vectors, you will see the following error: ");
            try {
                Vector v4 = new Vector(2);
                Vector v5 = new Vector(2);
                v4.vectorProduct(v5);
            } catch (VectorException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }
            System.out.println("----------------------------");

           } catch (VectorException e){
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}

public class Assignment_2 { 
    public static void main(String[] args) {
        TestVector.main(args);
    }
}

