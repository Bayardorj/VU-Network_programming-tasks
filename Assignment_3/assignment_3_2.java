// import necessary packages
import java.io.*;
import java.net.*;
import java.util.*;
public class assignment_3_2 {
    public static void main(String[] args) throws URISyntaxException {
        // The process of getting valid link
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter valid uri link: ");
        String uriString = scanner.nextLine();
        String output = "output.txt";

        try {
            // The process of converting URI to URl 
            URI uri = new URI(uriString);
            URL url = uri.toURL();
            BufferedReader  myReader = new BufferedReader(new InputStreamReader(url.openStream())); // Read data from given link
            BufferedWriter  myWriter = new BufferedWriter(new FileWriter(output));  // Save to target file

            String line;
            while ((line = myReader.readLine()) != null){
                myWriter.write(line);
                myWriter.newLine();
            }
            myReader.close();
            myWriter.close();

            System.out.println("The data saved to " + output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
