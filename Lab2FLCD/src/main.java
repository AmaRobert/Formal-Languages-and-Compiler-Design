import com.sun.tools.javac.util.Pair;

public class main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(23);
        Pair<Integer, Integer> a = symbolTable.search("a");
        System.out.println(a.fst);
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
