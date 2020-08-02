package myData;

import java.util.ArrayList;

public class MyData {

    private ArrayList<ArrayList<Integer>> data;
    private MyInstructions instructions;

    public MyData(ArrayList<ArrayList<Integer>> data, MyInstructions instructions) {
        this.data = data;
        this.instructions = instructions;
    }

    public ArrayList<ArrayList<Integer>> getData() {
        return data;
    }
    public void setData(ArrayList<ArrayList<Integer>> data) {
        this.data = data;
    }
    public MyInstructions getInstructions() {
        return instructions;
    }
    public void setInstructions(MyInstructions instructions) {
        this.instructions = instructions;
    }
}
