package de.entwicklerheld.stateMachine;

import java.util.Set;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class State {

    public static final String INCOMPLETE = "INCOMPLETE";
    public static final String NEW = "NEW";
    public static final String IN_REVIEW = "IN_REVIEW";
    public static final String READY_FOR_TRANSMISSION = "READY_FOR_TRANSMISSION";
    public static final String TRANSMITTED = "TRANSMITTED";
    public static final String ERROR = "ERROR";
    public static final String DUPLICATE_FAKE = "DUPLICATE_FAKE";
    public static final String SUCCESSFUL = "SUCCESSFUL";
    public static final String INACTIVE = "INACTIVE";
    public static final String CANCELLED = "CANCELED";
    public static final String NOT_SUCCESSFUL = "NOT_SUCCESSFUL";

    private String name;
    private Set<String> transitions;

    public State(String name, Set<String> transitions) {
        this.name = name;
        this.transitions = transitions;
    }

    public String getName() {
        return name;
    }

    public Set<String> getTransitions() {
        return transitions;
    }
}

public class PolicyRequest extends AbstractPolicyRequest implements IPolicyRequest {

    private HashMap<String, State> states = new HashMap<String, State>()
    {
        {
            put(State.INCOMPLETE, new State (State.INCOMPLETE, new HashSet<>(Arrays.asList(State.DUPLICATE_FAKE, State.NEW, State.ERROR))));
            put(State.NEW, new State (State.NEW, new HashSet<>(Arrays.asList(State.DUPLICATE_FAKE, State.IN_REVIEW, State.ERROR))));
            put(State.IN_REVIEW, new State (State.IN_REVIEW, new HashSet<>(Arrays.asList(State.DUPLICATE_FAKE,State.READY_FOR_TRANSMISSION, State.ERROR))));
            put(State.READY_FOR_TRANSMISSION, new State (State.READY_FOR_TRANSMISSION, new HashSet<>(Arrays.asList(State.TRANSMITTED, State.ERROR))));
            put(State.TRANSMITTED, new State (State.TRANSMITTED, new HashSet<>(Arrays.asList(State.SUCCESSFUL, State.NOT_SUCCESSFUL, State.ERROR))));
            put(State.ERROR, new State (State.ERROR, new HashSet<>()));
            put(State.DUPLICATE_FAKE, new State (State.DUPLICATE_FAKE, new HashSet<>()));
            put(State.SUCCESSFUL, new State (State.SUCCESSFUL, new HashSet<>(Arrays.asList(State.INACTIVE, State.CANCELLED, State.ERROR))));
            put(State.INACTIVE, new State (State.INACTIVE, new HashSet<>()));
            put(State.CANCELLED, new State (State.CANCELLED, new HashSet<>()));
            put(State.NOT_SUCCESSFUL, new State (State.NOT_SUCCESSFUL, new HashSet<>()));
        }
    };

    private State currentState;

    public PolicyRequest(String state) {
        currentState = states.getOrDefault(state, states.get(State.ERROR));
    }

    @Override
    public String getLeadState() {
        return currentState.getName();
    }

    @Override
    public Set<String> getPossibleNextStates() {
        return currentState.getTransitions();
    }

    @Override
    public boolean transitionState(String to) {
        boolean ret = currentState.getTransitions().contains(to);

        if (ret == true
            && currentState.getName().equals(State.INCOMPLETE)
            && to.equals(State.NEW)) {
                ret = checkIban(this.getIBAN());
        }

        currentState = ret ? states.get(to) : currentState;

        return ret;
    }

    private boolean checkIban(String iban) {
        iban = iban.substring(4) + iban.substring(0, 4);
        iban = replaceLetterByNumber(iban);
        return new BigInteger(iban).mod(new BigInteger("97")).equals(new BigInteger("1"));
    }

    private String replaceLetterByNumber(String iban) {
        StringBuilder sb = new StringBuilder();
        for (char c : iban.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.getNumericValue(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}