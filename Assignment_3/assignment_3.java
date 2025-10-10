import java.io.*;


public class assignment_3 {
    public static void main(String[] args) {

        // Here is the process of using the method from task3 in assignment 1
        TriangularPrism[]  newPrisms = { 
         new TriangularPrism(3, 4, 5, 3),
         new TriangularPrism(3, 4, 5, 6),
         new TriangularPrism(5, 4, 5, 3),
         new TriangularPrism(5, 5, 5, 3),
         new TriangularPrism(4, 4, 4, 9),
         new TriangularPrism(3, 4, 5, 3)
        };
        

        try(PrintWriter myWriter = new PrintWriter(new FileWriter("TriangularPrisms6.txt",true));) {
            for(TriangularPrism newPrism : newPrisms ) {
                myWriter.println(newPrism.toString());
            }
            myWriter.close();
            System.out.println("Prisms' data successfully copied into the file ");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try(BufferedReader myReader = new BufferedReader(new FileReader("TriangularPrisms6.txt"))) {
            String line;
            while ((line = myReader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
        }
        
    }
}