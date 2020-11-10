package Automata;

import com.sun.tools.javac.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FiniteAutomata {
    //REVIEW:might as well make the entire class static then since you can only use one input file per execution
    //make this a parameter,pretty likely to need more than 1 FA (you need 2 for integer and identifier check for example)
    private static final String INPUT_PATH = "source/inputLab4";
    private List<String> alphabet;
    private List<String> states;
    private String initialState;
    private List<String> finalStates;
    private List<Transition> transitions;

    public FiniteAutomata() {
        this.alphabet = new ArrayList<>();
        this.states = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.transitions = new ArrayList<>();
    }

    public void read(){
        try{
            BufferedReader reader = new BufferedReader( new FileReader(INPUT_PATH));

            states = Arrays.asList(reader.readLine().split(","));

            alphabet = Arrays.asList(reader.readLine().split(","));

            initialState = reader.readLine();

            finalStates = Arrays.asList(reader.readLine().split(","));

            String line = reader.readLine();
            while(line != null){

                List<String> trans = Arrays.asList(line.split(",|\\="));
                Transition transition = new Transition(trans.get(0), trans.get(2), trans.get(1));
                transitions.add(transition);

                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //REVIEW: if an error happens you don't close the reader (i probably don't either in my code :c )
            e.printStackTrace();
        }
    }

    public Boolean isDFA(){
        HashMap<Pair<String, String>, Boolean> mapChecker = new HashMap<>();
        //REVIEW:you can turn this into one foreach : for transition -> check if it exists in map ->yes:return false no:add it
        //      if foreach finishes return true
        //this also makes the boolean useless so use Set<Pair> or Map<String startstate,String value>
        for(Transition transition: transitions)
            mapChecker.put(new Pair<>(transition.getStartState(), transition.getValue()), false);
        for(Transition transition: transitions) {
            if (mapChecker.get(new Pair<>(transition.getStartState(), transition.getValue())))
                return false;
            mapChecker.put(new Pair<>(transition.getStartState(), transition.getValue()), true);
        }
        return true;
    }

    private List<Transition> getItsTransition(String startState){
        List<Transition> transitionList = new ArrayList<>();
        for(Transition transition: transitions)
            if(transition.getStartState().equals(startState))
                transitionList.add(transition);
        return transitionList;
    }

    private String nextState(String startState, String value){
        for(Transition transition: getItsTransition(startState))
            if(transition.getValue().equals(value))
                return transition.getEndState();
        return "Invalid state!";
    }


    public boolean testSequence(String sequence){
        String currentState = initialState;
        Integer index = 0;
        while (index < sequence.length()) {
            String chValue = sequence.charAt(index) + "";
            String nextState = nextState(currentState, chValue);
            if(nextState.equals("Invalid state!"))
                return false;
            currentState = nextState;
            index++;
        }
        return finalStates.toString().contains(currentState);
    }

    public void showTransitions(){
        for (Transition transition : transitions){
            System.out.println("Start state: " + transition.getStartState() +" --> " + "End state: " + transition.getEndState() + " with the Value: " + transition.getValue());
        }
        System.out.println("\n");
    }

    public void showStates(){
        System.out.println(states + "\n");
    }

    public void showAlphabet(){
        System.out.println(alphabet + "\n");
    }
    public void showInitialState(){
        System.out.println("The initial state is: " + initialState + "\n");
    }
    public void showFinalState(){
        System.out.println("The final state is: " + finalStates + "\n");
    }

}
