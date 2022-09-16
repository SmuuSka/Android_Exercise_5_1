package fi.samu.aikio.exercise_5_1;

public class Counter {

    private int min;
    private int max;
    private int steps;
    private int startValue;
    private int currentValue;

    public Counter(int minimumValue, int maximumValue, int start, int steps){
        this.min = minimumValue;
        this.max = maximumValue;
        this.startValue = start;
        this.steps = steps;
        currentValue = startValue;
    }
    public Counter(){
        this.min = -100;
        this.max = 100;
        this.startValue = 0;
        this.steps = 1;
    }

    public int setStartValue(){
        return startValue;
    }

    public int incrementValueByStep() {
        if (currentValue + steps <= max){
            currentValue += steps;
            return currentValue;
        }
        return currentValue;
    }

    public int decreaseValueByStep(){
        if (currentValue - steps >= min){
            currentValue -= steps;
            return currentValue;
        }
        return currentValue;
    }

    public int resetValue(){
        currentValue = startValue;
        return startValue;
    }

    public double valueToDecimal(){
        Double d = new Double(currentValue);
        return d;
    }

    public int getValue(){
        return currentValue;
    }
}

