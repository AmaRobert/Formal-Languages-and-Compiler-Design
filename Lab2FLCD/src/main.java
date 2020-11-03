import com.sun.tools.javac.Main;
import com.sun.tools.javac.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static final String PATH_P1 = "source/p1.txt";
    private static final String PATH_P2 = "source/p2.txt";
    private static final String PATH_P3 = "source/p3.txt";
    private static final String PATH_ERROR = "source/error.txt";
    public static void main(String[] args) {
        //lab2();
        try{
            boolean aux = true;
            while (aux) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the number of the program:");
                String number = reader.readLine();
                String source = main.PATH_ERROR;
                switch (number) {
                    case "1":
                        source = main.PATH_P1;
                        break;
                    case "2":
                        source = main.PATH_P2;
                        break;
                    case "3":
                        source = main.PATH_P3;
                        break;
                    case "e":
                        source = main.PATH_ERROR;
                        break;
                    case "x":
                        aux = false;
                        break;
                    default:
                        System.out.println("Wrong number entered!");
                }
                Scanner scanner = new Scanner(source);
                scanner.scan();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void lab2(){
        SymbolTable symbolTable = new SymbolTable(23);
        Pair<Integer, Integer> a = symbolTable.search("a");
        System.out.println("HashValue: " + a.fst + " at position: " +  a.snd);
        a = symbolTable.add("a");
        System.out.println("HashValue: " + a.fst + " at position: " +  a.snd);

        Pair<Integer, Integer> b = symbolTable.add("b");
        System.out.println("HashValue: " + b.fst + " at position: " +  b.snd);

        Pair<Integer, Integer> a2 = symbolTable.add("a");
        System.out.println("HashValue: " + a2.fst + " at position: " +  a2.snd);

        Pair<Integer, Integer> a3 = symbolTable.add("a");
        System.out.println("HashValue: " + a3.fst + " at position: " +  a3.snd);
    }
}
