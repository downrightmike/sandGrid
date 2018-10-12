/* A. StudentName
* Downrightmike
* 
*
*TODO: 
    Particles need to wrap
    sandGrid needs to use the max canvas
    Need to complete particles
        Destroyer
        Water 
        Oil
        Air
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
    public static final int CREATOR = 4;
    //Below particles have no functions yet 
    public static final int DESTROYER = 5;
    public static final int WATER = 6;
    public static final int OIL = 7;
    public static final int AIR = 8;

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
        String[] names = new String[9]; //Update for the names array size
        
        names[EMPTY] = "Empty";
        names[METAL] = "Metal";
        names[SAND] = "Sand";
        names[SAVEFILE] = "SaveFile"; 
        names[CREATOR] = "Creator";
        //Below particles have no functions yet 
        names[DESTROYER] = "Destroyer"; 
        names[WATER] = "Water"; 
        names[OIL] = "Oil"; 
        names[AIR] = "Air"; 
        
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
        if(sandGrid[i][j] == 4){display.setColor(i,j,Color.pink);}
        //Below particles have no functions yet
        if(sandGrid[i][j] == 5){display.setColor(i,j,Color.green);}
        if(sandGrid[i][j] == 6){display.setColor(i,j,Color.blue);}
        if(sandGrid[i][j] == 7){display.setColor(i,j,Color.white);}
        if(sandGrid[i][j] == 8){display.setColor(i,j,Color.red);}
            } // End of Columns For-Loop.
        } // End of Rows For-Loop.
    }
    
    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step(){
        
        //insert code here
        int c = getRandomNumber(1, MAX_COLS); //Getting range from 1 to max cols, low chance for 0 and max
        int r = getRandomNumber(1, MAX_ROWS); //Getting range from 1 to max rows,  low chance for 0 and max
        //ToDo
        //This bit of code corrects for the out of bounds exceptions and allows us to draw. MB
        //What this does is force any out of bounds pixel to 2,2
        //if(r > 219 || c > 179 ){r = 2;  c = 2; }

        
        int rm1 = r - 1;
        int rp1 = r + 1;
        int cm1 = c - 1;
        int cp1 = c + 1; 
        // wrap from bottom to top
        if(rp1 >= MAX_ROWS-1 || rp1 > MAX_ROWS-1){rp1 = 0;}
        // wrap from top to bottom
        if(rm1 <= 0){rm1 = (MAX_ROWS -1);}
        // Wrap from right to left
        if(cp1 >= MAX_COLS-1 || cp1 > MAX_COLS-1){cp1 = 0;}
        // Wrap from left to right
        if(cm1 <= 0){cm1 = (MAX_COLS -1);}

        //Print Block

        
    modifySand(r, c);
    modifyCreator(r, c);
    //Below methods have not been written yet
    //modifyDestroyer(r, c);
    modifyWater(r, c);
    //modifyOil(r, c);
    modifyAir(r, c);
    }//end of step

                    public void checkRowAndColBounds(int r, int c, int elementType) {
                        // This is the route that prof wants us to go:
                        int rm1 = r - 1;
                        int rp1 = r + 1;
                        int cm1 = c - 1;
                        int cp1 = c + 1; 
                        if(r == MAX_ROWS) {
                            sandGrid[r][c] = EMPTY;
                            sandGrid[0][c] =  elementType;
                          } // End of 2nd If-Statement.

                        if(c == MAX_COLS) {
                            sandGrid[r][c] = EMPTY;
                            sandGrid[r][0] =  elementType;
                          } // End of 2nd If-Statement.  

                        if(r > MAX_ROWS){r = 1;}  
                        // wrap from bottom to top
                        if(rp1 >= MAX_ROWS-1){rp1 = 1;}
                        // wrap from top to bottom
                        if(rm1 <= 1){rm1 = (MAX_ROWS -1);}

                        if(c > MAX_COLS){c = 1;}  
                        // Wrap from right to left
                        if(cp1 >= MAX_COLS-1){cp1 = 1;}
                        // Wrap from left to right
                        if(cm1 <= 1){cm1 = (MAX_COLS -1);}
                        
                    
                        /*
                        if(sandGrid[r][0] == elementType && sandGrid[r][180] != METAL) {
                          sandGrid[r][0] = EMPTY;
                          sandGrid[r][180] = elementType;
                        } else if(sandGrid[r][0] == elementType && sandGrid[r][179] != METAL) {
                            sandGrid[r][0] = EMPTY;
                            sandGrid[r][179] = elementType;
                        } else {
                            sandGrid[r][0] = EMPTY;
                            sandGrid[r][180] = EMPTY;
                        }
                        
                        if(sandGrid[220][c] == elementType && sandGrid[0][c] != METAL) {
                          sandGrid[220][c] = EMPTY;
                          sandGrid[0][c] = elementType; 
                        } else {
                          sandGrid[220][c] = EMPTY;
                          sandGrid[0][c] = EMPTY; 
                        }      */            
                      }// end of check
                      
                      // This module will modify the sand element.
  public void modifySand(int r, int c) {
    //let's simplify the code checks by doing the row and col checks in shorthand.
    //IMPORTANT TO SIMPLIFY
    int rm1 = r - 1;
    int rp1 = r + 1;
    int cm1 = c - 1;
    int cp1 = c + 1;
    int EVEN = 0;
    int ODD = 0;
    // This will get a random number from 0-10
    int randomNumber = getRandomNumber(0, 100);
    
    // This will get the modulus, the modulus of 0 is an even number, the modulus of 1 is an odd number.
    int modulus = randomNumber % 2;
    if(modulus == 0){EVEN = 0;} else {ODD = 1;}
    
    // Checking Row & Column Boundaries.
    checkRowAndColBounds(r, c, SAND);

    // Make sand drop below water
    if (sandGrid[r][c] == SAND && sandGrid[rp1][c] == WATER) {
        sandGrid[r][c] = WATER;
        sandGrid[rp1][c] = SAND;
      }
    // Swap positions between sand and empty to move sand around
    if(sandGrid[r][c] == SAND && sandGrid[rp1][c] == EMPTY) { // This can almost randomly control the speed of the sand falling which is cool : && randomNumber >= 8
            sandGrid[rp1][c]  =  SAND;
            sandGrid[r][c]      = EMPTY;

      // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
      // min or max row has been reached.
      if(r == MAX_ROWS-1) {
        sandGrid[r][c] = EMPTY;
        sandGrid[0][c] =  SAND;
      } // End of 2nd If-Statement.

    } // End of 1st If-Statement.

    // Get sand to fall if its at the top
    if (sandGrid[0][c] == SAND) {
      sandGrid[0][c]  =  EMPTY;
      sandGrid[1][c]  = SAND;
    }
    
    // This will adjust the sand to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    
    // This is to the left. 
    // Make this happen less often by triggeting on random number
    if(randomNumber >= 8){ // > 8 makes it stack again because there isn't a chance to move

      if(sandGrid[r][c] == SAND 
         && sandGrid[r][cm1] != SAND 
         && sandGrid[r][cm1] != METAL
         && sandGrid[r][cm1] != WATER
         && sandGrid[r][cm1] != CREATOR
         && sandGrid[r][cm1] != DESTROYER
         && modulus == EVEN) {
      
      sandGrid[r][cm1] =  SAND;
      sandGrid[r][c]     = EMPTY;
         }
      //This is to the right. 
    if(sandGrid[r][c] == SAND
               && sandGrid[r][cp1] != SAND 
               && sandGrid[r][cp1] != METAL
               && sandGrid[r][cp1] != WATER
               && sandGrid[r][cp1] != CREATOR
               && sandGrid[r][cp1] != DESTROYER
               && modulus == EVEN) { 
      
     sandGrid[r][cp1] = SAND;
     sandGrid[r][c] = EMPTY;
         } // End of If-Statement.
        } // End of If-Statement. Random one
  } // End of modify sand module.

  //Modify creator
  public void modifyCreator(int r, int c) {
    //let's simplify the code checks by doing the row and col checks in shorthand.
    //IMPORTANT TO SIMPLIFY
    int rm1 = r - 1;
    int rp1 = r + 1;
    int cm1 = c - 1;
    int cp1 = c + 1;

    // Checking Row & Column Boundaries.
    checkRowAndColBounds(r, c, CREATOR);

    // for sand
    if(sandGrid[rm1][c] == SAND && sandGrid[r][c] == CREATOR && sandGrid[rp1][c] == EMPTY) { 
        sandGrid[rp1][c]  =  sandGrid[rm1][c];
        sandGrid[r][c]      = CREATOR;
      } //end of sand if
      
      // for water
      if(sandGrid[rm1][c] == WATER && sandGrid[r][c] == CREATOR && sandGrid[rp1][c] == EMPTY) { 
        sandGrid[rp1][c]  =  sandGrid[rm1][c];
        sandGrid[r][c]      = CREATOR;
      } //end of water if
      
      // for oil
      if(sandGrid[rm1][c] == OIL && sandGrid[r][c] == CREATOR && sandGrid[rp1][c] == EMPTY) { 
        sandGrid[rp1][c]  =  sandGrid[rm1][c];
        sandGrid[r][c]      = CREATOR;
      }
    // for air
      if(sandGrid[rp1][c] == AIR && sandGrid[r][c] == CREATOR && sandGrid[rm1][c] == EMPTY) { 
        sandGrid[rm1][c]  =  sandGrid[rp1][c];
        sandGrid[r][c]      = CREATOR;
      }
}// end of modifyCreator

  //Modify destroyer
  public void modifyDestroyer(int r, int c) {
    //let's simplify the code checks by doing the row and col checks in shorthand.
    //IMPORTANT TO SIMPLIFY
    int rm1 = r - 1;
    int rp1 = r + 1;
    int cm1 = c - 1;
    int cp1 = c + 1;
    
    // Checking Row & Column Boundaries.
    checkRowAndColBounds(r, c, DESTROYER);
    
    if(sandGrid[r][c] == DESTROYER && sandGrid[rm1][c] != EMPTY && sandGrid[rm1][c] != METAL) { 
      sandGrid[rm1][c]  =  EMPTY;
      sandGrid[r][c]      = DESTROYER;
      
    } //end of if
    else if(sandGrid[r][c] == DESTROYER && sandGrid[rp1][c] != EMPTY && sandGrid[rp1][c] != METAL) { 
        sandGrid[rp1][c]  =  EMPTY;
        sandGrid[r][c]      = DESTROYER;
        
      } //end of if
  }// end of modifyDestroyer

public void modifyWater(int r, int c) {
    //let's simplify the code checks by doing the row and col checks in shorthand.
    //IMPORTANT TO SIMPLIFY
    int rm1 = r - 1;
    int rp1 = r + 1;
    int cm1 = c - 1;
    int cp1 = c + 1;
    int EVEN = 0;
    int ODD = 0;
    // This will get a random number from 0-10
    int randomNumber = getRandomNumber(0, 100);
    
    // This will get the modulus, the modulus of 0 is an even number, the modulus of 1 is an odd number.
    int modulus = randomNumber % 2;
    if(modulus == 0){EVEN = 0;} else {ODD = 1;}
    
    // Checking Row & Column Boundaries.
    checkRowAndColBounds(r, c, WATER);

    // Swap positions between WATER and empty to move WATER around
    if(sandGrid[r][c] == WATER && sandGrid[rp1][c] == EMPTY
        && sandGrid[rp1][c] != AIR
        && sandGrid[rp1][c] != WATER
        && sandGrid[rp1][c] != METAL
        && sandGrid[rp1][c] != CREATOR
        && sandGrid[rp1][c] != DESTROYER
        //&& sandGrid[rp1][c] != OIL
    ) { // This can almost randomly control the speed of the WATER falling which is cool : && randomNumber >= 8
            sandGrid[rp1][c]  =  WATER; 
            sandGrid[r][c]      = EMPTY;
      // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
      // min or max row has been reached.
      if(sandGrid[rp1][c] == sandGrid[MAX_ROWS - 1][c]
        && sandGrid[rp1][c] != AIR
        && sandGrid[rp1][c] != WATER
        && sandGrid[rp1][c] != METAL
        && sandGrid[rp1][c] != CREATOR
        && sandGrid[rp1][c] != DESTROYER
        //&& sandGrid[rp1][c] != OIL
        ) {
       sandGrid[rp1][c]              = EMPTY;
       sandGrid[r - (MAX_ROWS - 2)][c] =  WATER; 
      } // End of 2nd If-Statement.
    } // End of 1st If-Statement.
    
    // This will adjust the WATER to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    // This is to the left. 
    // Make this happen less often by triggeting on random number
    if(randomNumber >= 8){ // > 8 makes it stack again because there isn't a chance to move
    if(sandGrid[r][c] == WATER 
         && sandGrid[r][cm1] != WATER 
         && sandGrid[r][cm1] != SAND 
         && sandGrid[r][cm1] != METAL
         && sandGrid[r][cm1] != CREATOR
         && sandGrid[r][cm1] != DESTROYER
         && modulus == EVEN) {
      
      sandGrid[r][cm1] =  WATER; 
      sandGrid[r][c]     = EMPTY;
         }
      //This is to the right. 
     if(sandGrid[r][c] == WATER
                && sandGrid[r][cp1] != WATER 
                && sandGrid[r][cp1] != WATER 
                && sandGrid[r][cp1] != SAND 
                && sandGrid[r][cp1] != METAL
                && sandGrid[r][cp1] != CREATOR
                && sandGrid[r][cp1] != DESTROYER
                && modulus == EVEN) { 
      
     sandGrid[r][cp1] = WATER; 
     sandGrid[r][c] = EMPTY;
         } // End of If-Statement.
        } // End of If-Statement. Random one
  } // End of modify WATER module.


  public void modifyAir(int r, int c) {
    //let's simplify the code checks by doing the row and col checks in shorthand.
    //IMPORTANT TO SIMPLIFY
    int rm1 = r - 1;
    int rp1 = r + 1;
    int cm1 = c - 1;
    int cp1 = c + 1;
    int EVEN = 0;
    int ODD = 0;
    // This will get a random number from 0-10
    int randomNumber = getRandomNumber(0, 100);
    
    // This will get the modulus, the modulus of 0 is an even number, the modulus of 1 is an odd number.
    int modulus = randomNumber % 2;
    if(modulus == 0){EVEN = 0;} else {ODD = 1;}
    
    // Checking Row & Column Boundaries.
    checkRowAndColBounds(r, c, AIR);

    // Swap positions between AIR and empty to move AIR around
    if(sandGrid[r][c] == AIR 
         && sandGrid[rm1][c] == EMPTY
         && sandGrid[rm1][c] != SAND
         && sandGrid[rm1][c] != WATER
         && sandGrid[rm1][c] != METAL
         && sandGrid[rm1][c] != CREATOR
         && sandGrid[rm1][c] != DESTROYER
        //&& sandGrid[rm1][c] != OIL
         
         ) { // This can almost randomly control the speed of the AIR falling which is cool : && randomNumber >= 8
            sandGrid[rm1][c]  =  AIR ;
            sandGrid[r][c]      = EMPTY;
      // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
      // min or max row has been reached.
      if(sandGrid[rm1][c] == sandGrid[MAX_ROWS - 1][c]
        && sandGrid[rm1][c] != SAND
        && sandGrid[rm1][c] != WATER
        && sandGrid[rm1][c] != METAL
        && sandGrid[rm1][c] != CREATOR
        && sandGrid[rm1][c] != DESTROYER
        //&& sandGrid[rm1][c] != OIL
      ) {
       sandGrid[rm1][c]              = EMPTY;
       sandGrid[r - (MAX_ROWS - 2)][c] =  AIR; 
      } // End of 2nd If-Statement.
    } // End of 1st If-Statement.
    
    // This will adjust the AIR to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    // This is to the left. 
    // Make this happen less often by triggeting on random number
    if(randomNumber >= 8){ // > 8 makes it stack again because there isn't a chance to move
    if(sandGrid[r][c] == AIR 
         && sandGrid[r][cm1] != AIR 
         && sandGrid[r][cm1] != METAL
         && sandGrid[r][cm1] != SAND 
         && sandGrid[r][cm1] != METAL
         && sandGrid[r][cm1] != CREATOR
         && sandGrid[r][cm1] != DESTROYER
         && modulus == EVEN) {
      
      sandGrid[r][cp1] =  AIR ;
      sandGrid[r][c]     = EMPTY;
         }
      //This is to the right. 
     else if(sandGrid[r][c] == AIR
               && sandGrid[r][cp1] != AIR 
               && sandGrid[r][cp1] != METAL
               && sandGrid[r][cp1] != SAND 
               && sandGrid[r][cp1] != METAL
               && sandGrid[r][cp1] != CREATOR
               && sandGrid[r][cp1] != DESTROYER
               && modulus == EVEN) { 
      
     sandGrid[r][cp1] = AIR ;
     sandGrid[r][c] = EMPTY;
         } // End of If-Statement.
        } // End of If-Statement. Random one
  } // End of modify AIR module.



//DO NOT modify anything below here!!! //////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  