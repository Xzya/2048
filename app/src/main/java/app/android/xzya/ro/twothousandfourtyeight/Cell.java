package app.android.xzya.ro.twothousandfourtyeight;

/**
 * Created by Xzya on 1/3/2015.
 */
public class Cell {

    private int value;
    private boolean stackable;

    public Cell(){
        super();
        this.value = 0;
        this.stackable = true;
    }

    public Cell(int value){
        this.value = value;
        this.stackable = true;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public boolean isEmpty(){
        return this.value == 0 ? true:false;
    }
}

