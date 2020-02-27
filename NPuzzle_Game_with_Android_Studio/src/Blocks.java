package com.example.winterhw;
import android.content.Context;
import android.os.Build;
import android.widget.GridLayout;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
/**
 * This Class is for every block on the puzzle.
 * Since every block is special we keep block's information by using this class.
 * Also this class extends from AppCompatButton thus we can treat every object as a Button.
 * We are going to use this class to make buttons programmatically.
 * */
public class Blocks extends AppCompatButton implements Cloneable {
    private int value;//block number such as 1,3,5,61 etc.
    private int blockRow; //x coordinate of block
    private int blockColumn; //y coordinate of block
    /**
     * Constructor from AppCompatButton class
     * @param context is like a handle to the environment that your application is currently running in.
     * @param extra is extra size for button's shape according to size of puzzle.
     * */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Blocks(Context context,int extra) {
        super(context);
        //create a layout param to adjust button's size according to given size information
        //by doing this we can fit buttons on screen.
        //we used grid layout to put buttons on the screen in a good shape.
        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.height = 115+extra;
        param.width=115+extra;
        setLayoutParams(param);
    }
    //setter and getters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getBlockRow() {
        return blockRow;
    }

    public void setBlockRow(int blockRow) {
        this.blockRow = blockRow;
    }

    public int getBlockColumn() {
        return blockColumn;
    }

    public void setBlockColumn(int blockColumn) {
        this.blockColumn = blockColumn;
    }
}
