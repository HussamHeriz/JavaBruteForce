import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Scanner;

public class BruteForce {
    
    private static int length;
    private static String[] characters;
    private static int possibilites;
    private static int loopIndex;

    private static PrintStream fileOut;
    
    public static void main(String[] args) {
        
        if(args[0].equals("help"))
        {
            System.out.println("java BruteForce {length} {characters seperated by ,} {output file} ");
        }
        else {
            length = Integer.parseInt(args[0]);
            characters = args[1].split(",");

            System.out.println("*******************************************");
            System.out.println("***                                   *****");
            System.out.println("***  BruteForce Characters Generator  *****");
            System.out.println("***                                   *****");
            System.out.println("***              Java Tool            *****");
            System.out.println("***                                   *****");
            System.out.println("***          By: Hussam Heriz         *****");
            System.out.println("***                                   *****");
            System.out.println("*******************************************");

            new Thread( () -> {
                try {
                    String fileName = args[2];
                    fileOut = new PrintStream(fileName);

                    possibilites = (int) Math.pow(characters.length, length);

                    long startTime = System.nanoTime();

                    for(loopIndex=0;loopIndex < possibilites; loopIndex++)
                    {

                        for(int j=0; j < length; j++)
                        {
                            int x = (int) Math.pow(characters.length, length-(j+1));
                            int index = (loopIndex/x)%characters.length;
                            fileOut.print(characters[index]);
                        }

                        fileOut.println();

                    }

                    long endTime = System.nanoTime();
                    double sndTime = (endTime - startTime) / Math.pow(10,9);
                    String elapsedTime = new DecimalFormat("##.##").format( sndTime );

                    System.out.println("\n\nFile "+fileName+" Generated Successfully");
                    System.out.println("Possibilites: "+ possibilites);

                    System.out.println("Execution Time Is: "+elapsedTime+" seconds");
                    System.exit(0);

                } catch (FileNotFoundException ex) {
                    System.out.println("Error: File Not Found");
                }
            }).start();

            while(true){
                Scanner input = new Scanner(System.in);
                System.out.print("Press (Enter) to show completion:");
                String inputStr = input.nextLine();
                if(inputStr.equalsIgnoreCase("exit")){
                    System.exit(0);
                }else
                    printCompletion();
            }
        
        }

        
    }
    
    public static void printCompletion()
    {
        String percentage = new DecimalFormat("##.##").format( (loopIndex/(double)possibilites) * 100);
        System.out.println(percentage+"% Completed");
    }
}
