package myData;

import java.io.Serializable;
import java.util.ArrayList;

public class MyData implements Serializable {

    private ArrayList<ArrayList<Integer>> data;
    private MyInstructions instructions;


    public MyData(MyInstructions instructions) {
        this.data = new ArrayList<>();
        this.instructions = instructions;
    }

    public MyData(ArrayList<ArrayList<Integer>> data, MyInstructions instructions) {
        this.data = data;
        this.instructions = instructions;
    }

    public void printData() {
        for ( ArrayList<Integer> line : data ) {
            System.out.println( line );
        }
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
