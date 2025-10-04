import java.util.*;
public class amicable_numbers {
    // sumDivisors function
    public static int sumDivisors (int number) {
        if(number <= 1) {
            return 0;
        }
        int sum = 1;
        for ( int i = 2; i < number; i++) {
           if(number % i == 0) {
            sum += i;
           }
        }
        return sum;
    }
    // Fair_amicable function
    public static List<int[]> Fair_amicable(int n) {
       List<int[]> pairs = new ArrayList<>();
       
       for(int i = 2; i < n; i++) {

       int sum1 = sumDivisors(i);
        if(sum1 > i && sum1 < n) {
            int sum2 = sumDivisors(sum1);
        if(sum2 == i) {
            pairs.add(new int[] {i, sum1});
        }        
      }
    }
    return pairs;
}

// Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter number: ");
            int n = scanner.nextInt();
            if (n <= 284){
                System.out.println("Please enter bigger number");
                return;
            }
            List<int[]> amicable_pairs = Fair_amicable(n);

            System.out.println("The amicable pairs: ");
            for(int i= 0; i < amicable_pairs.size(); i++) {
                int[] pair = amicable_pairs.get(i);
                System.out.printf( pair[0] +  " " + pair[1]);
                System.out.println();
            }
            scanner.close();
        }

    }
    



