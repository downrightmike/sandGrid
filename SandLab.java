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
                        sandGrid = new int[numRows + 1][numCols + 1];
                        
                        
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
                        int randomRow = getRandomNumber(1, MAX_COLS); //Getting the range from 1 to max, should avoid 0 and max
                        int randomCol = getRandomNumber(1, MAX_ROWS); //Getting the range from 1 to max, should avoid 0 and max
                        //ToDo
                        //This bit of code corrects for the out of bounds exceptions and allows us to draw. MB
                        if(randomRow > 219 || randomCol > 179 ){
                            randomRow = 2;
                            randomCol = 2;
                        }
                        /*
                        if (sandGrid[0][randomCol] == SAND && sandGrid[1][randomCol] != METAL) {
                            sandGrid[1][randomCol] = SAND;
                            sandGrid[0][randomCol] = EMPTY;
                          } //This should keep the sand from bunching at the top if there is metal in row 1
                     // This will modify the sand elements behavior. THOMAS
                    */ //
                    modifySand(randomRow, randomCol);
                        
                    }//end of step

                    
                    public void checkRowAndColBounds(int rndRowLocation, int rndColLocation, int elementType) {
                        // If at location 0 on the columns is 
                       
                        if(sandGrid[rndRowLocation][0] == elementType && sandGrid[rndRowLocation][180] != METAL) {
                          sandGrid[rndRowLocation][0] = EMPTY;
                          sandGrid[rndRowLocation][180] = elementType;
                        } else if(sandGrid[rndRowLocation][0] == elementType && sandGrid[rndRowLocation][179] != METAL) {
                            sandGrid[rndRowLocation][0] = EMPTY;
                            sandGrid[rndRowLocation][179] = elementType;
                        
                        } else {
                            sandGrid[rndRowLocation][0] = EMPTY;
                            sandGrid[rndRowLocation][180] = EMPTY;

                        }
                        
                        if(sandGrid[220][rndColLocation] == elementType && sandGrid[0][rndColLocation] != METAL) {
                          sandGrid[220][rndColLocation] = EMPTY;
                          sandGrid[0][rndColLocation] = elementType; 
                          
                        } else {
                          sandGrid[220][rndColLocation] = EMPTY;
                          sandGrid[0][rndColLocation] = EMPTY; 
                        }
                        
                    
                        
                      }// end of check
                      
                      // This module will modify the sand element.
  public void modifySand(int rndRowLocation, int rndColLocation) {

    // This will get a random number from 0-100.
    //int randomNumber = getRandomNumber(0, 10);
    
    // This will get the modulus, the modulus of 0 is an even number, the modulus of 1 is an odd number.
    //int modulus = randomNumber % 2;
    
    // Checking Row & Column Boundaries.
    checkRowAndColBounds(rndRowLocation, rndColLocation, SAND);
    
    // If the element selected is sand and the element under it is EMPTY (blank), then it'll add sand to the
    // row below the selected location for the sand. This is done with a loop somewhere else of course for it to act fluidy.
    if(sandGrid[rndRowLocation][rndColLocation] == SAND 
         && sandGrid[rndRowLocation + 1][rndColLocation] == EMPTY) { // This can almost randomly control the speed of the sand falling which is cool : && randomNumber >= 80
      sandGrid[rndRowLocation + 1][rndColLocation]  =  SAND;
      sandGrid[rndRowLocation][rndColLocation]      = EMPTY;
      // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
      // min or max row has been reached.
      //if(sandGrid[rndRowLocation + 1][rndColLocation] == sandGrid[MAX_ROWS - 1][rndColLocation]) {
     //   sandGrid[rndRowLocation + 1][rndColLocation]              = EMPTY;
      //  sandGrid[rndRowLocation - (MAX_ROWS - 2)][rndColLocation] =  SAND;
      //} // End of 2nd If-Statement.
    } // End of 1st If-Statement.
    
    // This will adjust the sand to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side it'll end up being on the left or right side of the original position.
    // This is to the left. ADDITION BY : ZACH
    /*if(sandGrid[rndRowLocation][rndColLocation] == SAND 
         && sandGrid[rndRowLocation][rndColLocation - 1] != SAND 
         && sandGrid[rndRowLocation][rndColLocation - 1] != METAL
         && modulus == EVEN) {
      
      sandGrid[rndRowLocation][rndColLocation - 1] =  SAND;
      sandGrid[rndRowLocation][rndColLocation]     = EMPTY;
         }*/
      // This is to the right. 
     //else if(sandGrid[rndRowLocation][rndColLocation] == SAND
      //          && sandGrid[rndRowLocation][rndColLocation + 1] != SAND 
        //        && sandGrid[rndRowLocation][rndColLocation + 1] != METAL
          //      && modulus == ODD) { 
      
    //  sandGrid[rndRowLocation][rndColLocation + 1] = SAND;
    //  sandGrid[rndRowLocation][rndColLocation] = EMPTY;
      
   // } // End of If-Statement.
  } // End of modify sand module.
                    
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
  