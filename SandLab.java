                /* A. StudentName
                * 
                * 
                */
                import java.awt.*;
                import java.util.*;

                public class SandLab{
                    static final int MAX_ROWS = 220;  
                    static final int MAX_COLS = 180;  
                    
                    static final String FILE_NAME     = "SandLabFile.txt";  //This is the name of the input file.
                    static final String NEW_FILE_NAME = "SandLabFile.txt";  //This is the name of the file you are saving.

                    //add constants for particle types here
                    public static final int EMPTY = 0;
                    public static final int METAL = 1;
                    public static final int SAVEFILE  = 2;  
                    public static final int SAND = 3;

                    //do not add any more fields
                    private int[][] sandGrid;
                    private LabDisplay display;

                    //---------------------------------------------------------------------------------------------------------
                    
                    public static void main(String[] args){
                        System.out.println("================= Starting Program =================");
                        
                        SandLab lab = new SandLab(MAX_ROWS, MAX_COLS);
                        lab.run();
                    }
                    
                    //SandLab constructor 
                    public SandLab(int numRows, int numCols){
                        String[] names = new String[4]; //Update for the names array size
                        
                        names[EMPTY] = "Empty";
                        names[METAL] = "Metal";
                        names[SAND] = "Sand";
                        names[SAVEFILE] = "SaveFile"; 
                        
                        display = new LabDisplay("SandLab", numRows, numCols, names);
                        //grid =
                        sandGrid = new int[numRows][numCols];
                        
                        
                        if (FILE_NAME != "") {
                            System.out.println("loading " + FILE_NAME);
                            //sandGrid = SandLabFiles.readFile(FILE_NAME);   //uncomment this later to save your file...
                        } 
                    }
                    
                    //called when the user clicks on a location using the given tool
                    private void locationClicked(int row, int col, int tool){
                        
                        //insert code here 
                        sandGrid[row][col] = tool;

                      if (tool == SAVEFILE) {
                           //SandLabFiles.writeFile(grid, NEW_FILE_NAME);  //uncomment this later to save your file...
                           // Lines below are from an older version that may not work out of the box
                           // SandLabFiles.setRowsAndCols(MAX_ROWS, MAX_COLS);
                           // SandLabFiles.writeFile(sandGrid, NEW_FILE_NAME); 
                          }
                    }
                    
                    //copies each element of grid into the display
                    public void updateDisplay(){
                        
                        //insert code here
                        for(int i = 0; i < MAX_ROWS; i++) {     // Used to loop through rows.
                            for(int j =0; j < MAX_COLS - 1; j++) { // Used to loop through columns.
                        if(sandGrid[i][j] == 0){display.setColor(i,j,Color.black);}
                        if(sandGrid[i][j] == 1){display.setColor(i,j,Color.gray);}
                        // #2 is being used by the save file
                        if(sandGrid[i][j] == 3){display.setColor(i,j,Color.yellow);}
                    } // End of Columns For-Loop.
                } // End of Rows For-Loop.
                    }
                    
                    //called repeatedly.
                    //causes one random particle to maybe do something.
                    public void step(){
                        
                        //insert code here
                        int randomRow = getRandomNumber(0, MAX_COLS);
                        int randomCol = getRandomNumber(0, MAX_ROWS);

                        //if(randomRow > 0  && randomCol > 0){
                           // if(sandGrid[randomRow][randomCol] == SAND & sandGrid[randomRow][randomCol + 1] == EMPTY){
                            //sandGrid[randomRow][randomCol + 1] = SAND;
                            //sandGrid[randomRow][randomCol] = EMPTY;
                        //} //end 2nd if
                       // } //else {sandGrid[randomRow][randomCol] = sandGrid[randomRow][randomCol];}
                        //} //end of 1st if
                        
                    }//end of step
                    
                    //DO NOT modify anything below here!!! /////////////////////////////////////////////////////////////////
                    public void run(){
                        while (true){
                            for (int i = 0; i < display.getSpeed(); i++)
                                step();
                            updateDisplay();
                            display.repaint();
                            display.pause(1);  //wait for redrawing and for mouse   
                            int[] mouseLoc = display.getMouseLocation();
                            if (mouseLoc != null)  //test if mouse clicked
                                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
                        }
                    }
                    
                    public int getRandomNumber (int low, int high){
                        return (int)(Math.random() * (high - low)) + low;
                    }
                }
  