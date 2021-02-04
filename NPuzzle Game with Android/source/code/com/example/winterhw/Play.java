package com.example.winterhw;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatDrawableManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * This Class is for second screen of the game such that we play the game in this screen.
 * This class contains all code to play the game.
 * */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class Play extends AppCompatActivity {
    TextView moves; //number of move information screen
    int row; //row number from user
    int column; //column number from user
    int size; // whole size of puzzle
    int totalMove;
    char lastMove;
    boolean isEnd; //check game
    int extra; //extra size according to given size information
    Drawable d; // image object to use icons
    Blocks[][] board; // board array from block class
    Blocks[][] solution; // solution array is to check game
    GridLayout gLayout ; //grid layout is to fill the screen in a good shape
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        playRealTime(); //start game
        //new game button listener
        findViewById(R.id.newGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go main activity and show first screen
                Intent i = new Intent(Play.this,MainActivity.class);
                startActivity(i);
            }
        });
        //hint button listener
        findViewById(R.id.hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intelligentMove();//make an intelligent move
                incrementTotalMove();
                print();
                checkGame();
            }
        });
    }
    //getters and setters
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(){
        Intent intent = getIntent();
        row = Integer.parseInt(intent.getStringExtra("row"));
    }
    public void setColumn() {
        Intent intent = getIntent();
        column = Integer.parseInt(intent.getStringExtra("column"));
    }
    public int getSize() {
        return size;
    }
    public void setSize() {
        setRow();setColumn();
        size=getRow()*getColumn();
    }
    public int getTotalMove() {
        return totalMove;
    }
    public void setTotalMove(){
        totalMove=0;
        moves = findViewById(R.id.moves);
        moves.setText("Your Total Move: "+ totalMove);
    }
    public void incrementTotalMove() {
        this.totalMove++;
        moves.setText("Your Total Move: "+ totalMove);
    }
    public char getLastMove() {
        return lastMove;
    }
    public void setLastMove(char lastMove) {
        this.lastMove = lastMove;
    }
    public void setEnd(boolean end) {
        isEnd = end;
    }
    /**
     * This method is to fill board array and solution array with buttons that is created by programmatically.
     * Also in this class we set the properties of each button such as block number,image etc.
     * Lastly after initialization we call the shuffle method to shuffle board array according to difficulty mode.
     * */
    @SuppressLint("RestrictedApi")
    public void initializeBoard(){
        setSize();setTotalMove();setEnd(false);setLastMove('S'); //set some properties of the game
        board=new Blocks[getRow()][getColumn()]; //create the array according to given size information.
        solution=new Blocks[getRow()][getColumn()]; //create solution array
        extra = (9-getRow())*(9-getColumn())*3; //set extra by using suitable number
        if (extra==0) extra=1;
        int k=1;
        for (int i=0;i<getRow();i++){
            for (int j= 0; j<getColumn();j++){
                board[i][j] =new Blocks(this,extra); //create each button by using Blocks class.
                solution[i][j] =new Blocks(this,extra); //also solution array's elements
                if((i+1)*(j+1)==getSize()){ //if element is the last element of array
                    board[i][j].setValue(0); //set it zero to be empty block
                    solution[i][j].setValue(0); //set solution
                    //set the button image
                    d = AppCompatDrawableManager.get().getDrawable(this,getPictureID(0));
                    board[i][j].setBackground(d);
                    board[i][j].setVisibility(View.INVISIBLE); //set empty block to invisible
                }
                else{ //if block is not empty block
                    //set button properties
                    board[i][j].setValue(k);
                    solution[i][j].setValue(k);
                    d = AppCompatDrawableManager.get().getDrawable(this,getPictureID(k));
                    board[i][j].setBackground(d);
                    k++;
                }
                //set x and y coordinates
                board[i][j].setBlockRow(i);
                board[i][j].setBlockColumn(j);
                //action listener for every button
                //notice that this part works if and only if the button is clicked
                board[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Blocks temp = (Blocks)v;
                        //move button and set last move
                        setLastMove(move(temp));
                        print();
                        checkGame();
                    }
                });
            }
        }
        shuffle(); //after initialization shuffle board array

    }
    /**
     * Check the game over or not. if game over,then give a message to the gamer.
     * */
    public void checkGame(){

        if(is_solved()){
            AlertDialog.Builder builder = new AlertDialog.Builder(Play.this);
            builder.setTitle("Congratulations!");
            builder.setMessage("Puzzle Solved!");
            builder.setCancelable(true);
            builder.setIcon(R.drawable.winner2); //set image
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //go to the first screen and start game again.
                    Intent i = new Intent(Play.this,MainActivity.class);
                    startActivity(i);
                }
            });
            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    /**
     * Put buttons on grid layout properly.
     * */
    public void print(){
        gLayout = findViewById(R.id.grid);//find grid layout
        setSpace();
        gLayout.removeAllViews(); //remove
        gLayout.setRowCount(getRow()); //set row number
        gLayout.setColumnCount(getColumn()); //set column number
        //add buttons
        for (int i=0;i<getRow();i++){
            for (int j= 0; j<getColumn();j++){
                gLayout.addView(board[i][j]);
            }
        }
    }
    /**
     * Set margin of the buttons by using size and some calculations.
     * */
    public void setSpace(){
        if (getColumn()==9 && getRow() ==9){
            gLayout.setPadding(15,15,5,5);
        }
        else if (getColumn()==3 || getRow() ==3){
            gLayout.setPadding((225/((getColumn()-3)+1)),225/((getRow()-3)+1),1,1);
        }
        else{
            gLayout.setPadding((225/((getColumn()-3)+1))+(9-getColumn())*20,225/((getRow()-3)+1)+(9-getRow())*20,1,1);
        }

    }
    /**
     * Move the button which is given as parameter.
     * @param key button that will be moved.
     * */
    public char move(Blocks key){
        /*
         * ei1 = empty block x coordinate
         * ki1 = key    "    "     "
         * ei2 = empty  "    y     "
         * ki2 = key    "    "     "
         * */
        int ei1,ei2,ki1,ki2;
        ei1=getBlock(0).getBlockRow();//set ei1
        ei2=getBlock(0).getBlockColumn(); //set ei2
        ki1=getBlock(key.getValue()).getBlockRow(); //set ki1
        ki2=getBlock(key.getValue()).getBlockColumn(); //set ki2
        /*
         * First check directions that empty block can go
         * Then , if it can be moved then check the button which is in that direction.
         * if that button is the key button then change place between empty button and key button.
         * Do same thing for every direction.
         * */
        if (board[ei1][ei2].getBlockRow()-1>=0){ //up direction
            //check the button which is in that direction.
            if (board[board[ei1][ei2].getBlockRow()-1][board[ei1][ei2].getBlockColumn()].getValue()==key.getValue()){
                // if this button is the key button then change place between empty button and key button.
                final Blocks temp = board[ei1][ei2];
                board[ei1][ei2] = board[ki1][ki2];
                board[ei1][ei2].setBlockRow(ei1); //Don't change x coordinate
                board[ei1][ei2].setBlockColumn(ei2); //Don't change y coordinate
                board[ki1][ki2] = temp;
                board[ki1][ki2].setBlockRow(ki1); //Don't change x coordinate
                board[ki1][ki2].setBlockColumn(ki2); //Don't change y coordinate
                incrementTotalMove(); //increment move
                return 'U';
            }
        }
        if (board[ei1][ei2].getBlockColumn()-1>=0){//left
            if (board[board[ei1][ei2].getBlockRow()][board[ei1][ei2].getBlockColumn()-1].getValue()==key.getValue()){
                final Blocks temp = board[ei1][ei2];
                board[ei1][ei2] = board[ki1][ki2];
                board[ei1][ei2].setBlockRow(ei1);
                board[ei1][ei2].setBlockColumn(ei2);
                board[ki1][ki2] = temp;
                board[ki1][ki2].setBlockRow(ki1);
                board[ki1][ki2].setBlockColumn(ki2);
                incrementTotalMove();
                return 'L';
            }
        }
        if (board[ei1][ei2].getBlockColumn()+1<getColumn()){//right
            if (board[board[ei1][ei2].getBlockRow()][board[ei1][ei2].getBlockColumn()+1].getValue()==key.getValue()){
                final Blocks temp = board[ei1][ei2];
                board[ei1][ei2] = board[ki1][ki2];
                board[ei1][ei2].setBlockRow(ei1);
                board[ei1][ei2].setBlockColumn(ei2);
                board[ki1][ki2] = temp;
                board[ki1][ki2].setBlockRow(ki1);
                board[ki1][ki2].setBlockColumn(ki2);
                incrementTotalMove();
                return 'R';
            }
        }
        if (board[ei1][ei2].getBlockRow()+1<getRow()){//down
            if (board[board[ei1][ei2].getBlockRow()+1][board[ei1][ei2].getBlockColumn()].getValue()==key.getValue()){
                final Blocks temp = board[ei1][ei2];
                board[ei1][ei2] = board[ki1][ki2];
                board[ei1][ei2].setBlockRow(ei1);
                board[ei1][ei2].setBlockColumn(ei2);
                board[ki1][ki2] = temp;
                board[ki1][ki2].setBlockRow(ki1);
                board[ki1][ki2].setBlockColumn(ki2);
                incrementTotalMove();
                return 'D';
            }
        }
        return 'S';
    }
    /**
     * Shuffle board array according to difficulty mode.
     * */
    public void shuffle(){
        int howMany;
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");//get mode
        switch (mode){
            case "Easy":
                howMany =getColumn()+getRow()+2;
                break;
            case "Normal":
                howMany = (getColumn()+getRow())*3;
                break;
            case "Hard" :
                howMany =(getColumn()+getRow())*5;
                break;
            default: howMany = 15;
        }
        moveRandom(howMany);
        setLastMove('S');
    }
    /**
     * To shuffle board array move Randomly.
     * */
    public void moveRandom(int howMany){
        int i,number;
        char[] directions = new char[4];
        for ( i = 0; i < howMany; i++) {
            getPossibleMove(directions); //get possible directions
            do {
                number = (int) (Math.random() * 4); //choose a random number
            } while (directions[number] == 'S'); // if direction has not room to go choose again
            setLastMove(directions[number]); //set the last move to not make the same move again
            if (number==0)
                moveByDirection('L');
            else if (number==1)
                moveByDirection('R');
            else if (number==2)
                moveByDirection('U');
            else
                moveByDirection('D');
            if (i % 4 == 0) { //every 4 move make extra 1 up move to go up
                getPossibleMove(directions);
                for (int j = 0; j < 4; j++){
                    if(directions[j] == 'U')
                    {
                        moveByDirection('U');
                        setLastMove('U');
                    }
                }
            }

        }
    }
    /**
     * Get the possible directions where empty block can go.
     * @param direction directions where empty block can go.
     * */
    public void getPossibleMove(char[] direction){
        /*
         * ei1 = empty block x coordinate
         * ei2 = empty  "    y     "
         * */
        int ei1,ei2;
        ei1=getBlock(0).getBlockRow();
        ei2=getBlock(0).getBlockColumn();
        for (int i = 0; i < 4; i++) direction[i]='S';
        if (board[ei1][ei2].getBlockRow()-1>=0){ //up
            direction[2] ='U';
        }
        if (board[ei1][ei2].getBlockColumn()-1>=0){ //left
            direction[0] ='L';
        }
        if (board[ei1][ei2].getBlockColumn()+1<getColumn()){ //right
            direction[1] ='R';
        }
        if (board[ei1][ei2].getBlockRow()+1<getRow()){ //down
            direction[3] ='D';
        }
        //return the directions according to the last move to not make the same move again.
        switch (getLastMove())
        {
            case 'L':
                setLastMove('R');
                break;
            case 'R':
                setLastMove('L');
                break;
            case 'U':
                setLastMove('D');
                break;
            case 'D':
                setLastMove('U');
                break;
            default:
                break;
        }
        //check for last move
        for (int i = 0; i < 4; i++){

            if(direction[i] != 'S')
            {
                if (direction[i]==getLastMove()) //if our direction equal last direction
                {
                    direction[i]='S';
                }
            }
        }
    }
    /**
     * Return one block according to given key parameter.
     * @param keyID block number such as 1,3,5,61 etc.
     * */
    public Blocks getBlock(int keyID){
        //find block in array
        for (int i=0;i<getRow();i++){
            for (int j= 0; j<getColumn();j++){
                if(board[i][j].getValue() == keyID){
                    return board[i][j];
                }
            }
        }
        return board[0][0];//default return value
    }
    /**
     * Move the button according to given direction.
     * @param direction direction that empty block will be moved.
     * */
    public void moveByDirection(char direction){
        //same as other move method
        int ei1,ei2;
        ei1=getBlock(0).getBlockRow();
        ei2=getBlock(0).getBlockColumn();
        if (direction == 'L'){
            final Blocks temp = board[ei1][ei2];
            board[ei1][ei2] = board[ei1][ei2-1];
            board[ei1][ei2].setBlockRow(ei1);
            board[ei1][ei2].setBlockColumn(ei2);
            board[ei1][ei2-1] = temp;
            board[ei1][ei2-1].setBlockRow(ei1);
            board[ei1][ei2-1].setBlockColumn(ei2-1);
        }
        else if (direction == 'R'){
            final Blocks temp = board[ei1][ei2];
            board[ei1][ei2] = board[ei1][ei2+1];
            board[ei1][ei2].setBlockRow(ei1);
            board[ei1][ei2].setBlockColumn(ei2);
            board[ei1][ei2+1] = temp;
            board[ei1][ei2+1].setBlockRow(ei1);
            board[ei1][ei2+1].setBlockColumn(ei2+1);
        }
        else if (direction == 'U'){
            final Blocks temp = board[ei1][ei2];
            board[ei1][ei2] = board[ei1-1][ei2];
            board[ei1][ei2].setBlockRow(ei1);
            board[ei1][ei2].setBlockColumn(ei2);
            board[ei1-1][ei2] = temp;
            board[ei1-1][ei2].setBlockRow(ei1-1);
            board[ei1-1][ei2].setBlockColumn(ei2);
        }
        else if (direction == 'D'){
            final Blocks temp = board[ei1][ei2];
            board[ei1][ei2] = board[ei1+1][ei2];
            board[ei1][ei2].setBlockRow(ei1);
            board[ei1][ei2].setBlockColumn(ei2);
            board[ei1+1][ei2] = temp;
            board[ei1+1][ei2].setBlockRow(ei1+1);
            board[ei1+1][ei2].setBlockColumn(ei2);
        }

    }
    /**
     * Check whether game over.
     * To do this it uses solution array
     * */
    public boolean is_solved(){
        for (int i=0;i<getRow();i++){
            for (int j= 0; j<getColumn();j++){
                if (solution[i][j].getValue()!=board[i][j].getValue()){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Start the game and print board on the screen.
     * */
    public void playRealTime(){
        initializeBoard();
        print();
    }
    /**
     * Make an intelligent move to help gamer.
     * Hint button uses this method.
     * */
    public void intelligentMove(){
        /*
         * First get possible directions where empty block can go.
         * Then, find manhattan distance of each direction.
         * Manhattan distance is distance from current position to original position in solution.
         * Then select direction that has minimum manhattan distance( it means closest block to the it's original position)
         * */

        char[] directions = new char[4];
        char direction = 'L'; //default direction is left
        getPossibleMove(directions);
        Blocks zero = getBlock(0);
        int min = 100,manhat;
        for (int i = 0;i<4;i++){
            if (directions[i] == 'L'){
                manhat=calculateManhattanOfOneTile(board[zero.getBlockRow()][zero.getBlockColumn()-1]);
                if (manhat<min){
                    min=manhat;
                    direction ='L';
                }
            }
            if (directions[i] == 'R'){
                manhat =calculateManhattanOfOneTile(board[zero.getBlockRow()][zero.getBlockColumn()+1]);
                if (manhat<min){
                    min=manhat;
                    direction ='R';
                }
            }
            if (directions[i] == 'U'){
                manhat =calculateManhattanOfOneTile(board[zero.getBlockRow()-1][zero.getBlockColumn()]);
                if (manhat<min){
                    min=manhat;
                    direction ='U';
                }
            }
            if (directions[i] == 'D'){
                manhat =calculateManhattanOfOneTile(board[zero.getBlockRow()+1][zero.getBlockColumn()]);
                if (manhat<min){
                    min=manhat;
                    direction ='D';
                }
            }
        }
        moveByDirection(direction);
        setLastMove(direction);

    }
    /**
     * Find manhattan distance of given block as parameter.
     * Manhattan distance is distance from current position to original position in solution puzzle.
     * @param tile Block that will be calculated it's manhattan distance
     * */
    public int calculateManhattanOfOneTile(Blocks tile){
        /*
         * sR = x coordinate of parameter on solution board.
         * SC = y    "       "     "      "    "        "
         * tR = x    "       "  empty tile " current board.
         * tC = y    "       "     "        "   "      "
         * */
        int valueOfTile = tile.getValue();
        int sR=0,sC=0,tR,tC,distance;
        for (int i=0;i<getRow();i++){
            for (int j= 0; j<getColumn();j++){
                if(solution[i][j].getValue() == valueOfTile){
                    sR=i;sC=j;
                }
            }
        }
        //calculate distance
        tR=getBlock(0).getBlockRow();tC=getBlock(0).getBlockColumn();
        distance = absoluteValue(tR-sR) + absoluteValue(tC-sC);
        return  distance;
    }
    /**
     * Get the absolute value of the given number as parameter.
     * */
    public int absoluteValue(int value){
        if (value<0)
            return value*(-1);
        else
            return value;
    }
    /**
     * Get picture id of the given key as parameter.
     * This method will be used when determine picture of buttons.
     * */
    public int getPictureID(int key){
        switch (key){
            case 0:
                return R.drawable.b0;
            case 1:
                return R.drawable.b1;
            case 2:
                return R.drawable.b2;
            case 3:
                return R.drawable.b3;
            case 4:
                return R.drawable.b4;
            case 5:
                return R.drawable.b5;

            case 6:
                return R.drawable.b6;

            case 7:
                return R.drawable.b7;

            case 8:
                return R.drawable.b8;

            case 9:
                return R.drawable.b9;

            case 10:
                return R.drawable.b10;

            case 11:
                return R.drawable.b11;

            case 12:
                return R.drawable.b12;

            case 13:
                return R.drawable.b13;

            case 14:
                return R.drawable.b14;

            case 15:
                return R.drawable.b15;

            case 16:
                return R.drawable.b16;

            case 17:
                return R.drawable.b17;

            case 18:
                return R.drawable.b18;

            case 19:
                return R.drawable.b19;

            case 20:
                return R.drawable.b20;

            case 21:
                return R.drawable.b21;

            case 22:
                return R.drawable.b22;

            case 23:
                return R.drawable.b23;

            case 24:
                return R.drawable.b24;

            case 25:
                return R.drawable.b25;

            case 26:
                return R.drawable.b26;

            case 27:
                return R.drawable.b27;

            case 28:
                return R.drawable.b28;

            case 29:
                return R.drawable.b29;

            case 30:
                return R.drawable.b30;

            case 31:
                return R.drawable.b31;

            case 32:
                return R.drawable.b32;

            case 33:
                return R.drawable.b33;

            case 34:
                return R.drawable.b34;

            case 35:
                return R.drawable.b35;

            case 36:
                return R.drawable.b36;
            case 37:
                return R.drawable.b37;
            case 38:
                return R.drawable.b38;

            case 39:
                return R.drawable.b39;

            case 40:
                return R.drawable.b40;

            case 41:
                return R.drawable.b41;

            case 42:
                return R.drawable.b42;

            case 43:
                return R.drawable.b43;

            case 44:
                return R.drawable.b44;

            case 45:
                return R.drawable.b45;

            case 46:
                return R.drawable.b46;

            case 47:
                return R.drawable.b47;

            case 48:
                return R.drawable.b48;

            case 49:
                return R.drawable.b49;

            case 50:
                return R.drawable.b50;

            case 51:
                return R.drawable.b51;

            case 52:
                return R.drawable.b52;

            case 53:
                return R.drawable.b53;

            case 54:
                return R.drawable.b54;

            case 55:
                return R.drawable.b55;

            case 56:
                return R.drawable.b56;

            case 57:
                return R.drawable.b57;

            case 58:
                return R.drawable.b58;

            case 59:
                return R.drawable.b59;

            case 60:
                return R.drawable.b60;

            case 61:
                return R.drawable.b61;

            case 62:
                return R.drawable.b62;

            case 63:
                return R.drawable.b63;

            case 64:
                return R.drawable.b64;

            case 65:
                return R.drawable.b65;

            case 66:
                return R.drawable.b66;

            case 67:
                return R.drawable.b67;

            case 68:
                return R.drawable.b68;

            case 69:
                return R.drawable.b69;

            case 70:
                return R.drawable.b70;

            case 71:
                return R.drawable.b71;

            case 72:
                return R.drawable.b72;

            case 73:
                return R.drawable.b73;

            case 74:
                return R.drawable.b74;

            case 75:
                return R.drawable.b75;

            case 76:
                return R.drawable.b76;

            case 77:
                return R.drawable.b77;

            case 78:
                return R.drawable.b78;

            case 79:
                return R.drawable.b79;

            case 80:
                return R.drawable.b80;

            default:
                return R.drawable.b80;
        }

    }
}
