/*
* Downrightmike
* JohnnyPoblano
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
          }
         /* if (tool == SAVEFILE) {
            //   SandLabFiles.setRowsAndCols(MAX_ROWS, MAX_COLS);
            //   SandLabFiles.writeFile(sandGrid, NEW_FILE_NAME);
           //  } else {
               sandGrid[row][col] = tool;
             } 
             */
    }
    
    //copies each element of grid into the display
    public void updateDisplay(){
        
        //insert code here
        for(int i = 0; i < MAX_ROWS; i++) {     // Used to loop through rows.
            for(int j =0; j < MAX_COLS - 1; j++) { // Used to loop through columns.
        if(sandGrid[i][j] == EMPTY){display.setColor(i,j,Color.black);}
        if(sandGrid[i][j] == METAL){display.setColor(i,j,Color.gray);}
        // #2 is being used by the save file
        if(sandGrid[i][j] == SAND){display.setColor(i,j,Color.yellow);}
        if(sandGrid[i][j] == CREATOR){display.setColor(i,j,Color.pink);}
        //Below particles have no functions yet
        if(sandGrid[i][j] == DESTROYER){display.setColor(i,j,Color.green);}
        if(sandGrid[i][j] == WATER){display.setColor(i,j,Color.blue);}
        if(sandGrid[i][j] == OIL){display.setColor(i,j,Color.white);}
        if(sandGrid[i][j] == AIR){display.setColor(i,j,Color.lightGray);}
            } // End of Columns For-Loop.
        } // End of Rows For-Loop.
    }
    
    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step(){
        
        //insert code here
        int c = getRandomNumber(1, MAX_COLS); //Getting range from 1 to max cols, low chance for 0 and max
        int r = getRandomNumber(1, MAX_ROWS); //Getting range from 1 to max rows,  low chance for 0 and max        
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
    modifyDestroyer(r, c);
    modifyWater(r, c);
    modifyOil(r, c);
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

    // Make sand drop below oil
    if (sandGrid[r][c] == SAND && sandGrid[rp1][c] == OIL) {
      sandGrid[r][c] = OIL;
      sandGrid[rp1][c] = SAND;
    }

    // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
    // min or max row has been reached.
    if (sandGrid[r][c] != SAND) {}
    else if ((sandGrid[r][c] == SAND) && r == MAX_ROWS-2 || r == MAX_ROWS-1 || r == MAX_ROWS || r == MAX_ROWS+1) {
      sandGrid[r][c] = EMPTY;
      sandGrid[1][c] =  SAND;
    }
    //warpping for right to left
    if (sandGrid[r][c] != SAND) {}
    else if ((sandGrid[r][c] == SAND) && (c == MAX_COLS-2 || c == MAX_COLS-1 || c == MAX_COLS || c == MAX_COLS+1) && sandGrid[r][2] != METAL){
      sandGrid[r][c] = EMPTY;
      sandGrid[r][2] =  SAND;//had to use 2 to allow sand enough room to move
    }
    else if ((sandGrid[r][c] == SAND) && (c == 0 || c == -1 || c == 0 || c == 1) && sandGrid[r][MAX_COLS-3] != METAL ){
      sandGrid[r][c] = EMPTY;
      sandGrid[r][MAX_COLS-3] =  SAND;//-3 allows sand's falling behavior to work and not get stuck on right MAX_COLS
    }
    // Swap positions between sand and empty to move sand around
    if(sandGrid[r][c] == SAND && sandGrid[rp1][c] == EMPTY) { // This can almost randomly control the speed of the sand falling which is cool : && randomNumber >= 8
            sandGrid[rp1][c]  =  SAND;
            sandGrid[r][c]      = EMPTY;
    } // End of 1st If-Statement.
    
    if(sandGrid[r][c] == SAND && sandGrid[rp1][c] == OIL) { // This can almost randomly control the speed of the sand falling which is cool : && randomNumber >= 8
      sandGrid[rp1][c]  =  SAND;
      sandGrid[r][c]      = OIL;
} // End of 1st If-Statement.

    // This will adjust the sand to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    
    if (sandGrid[r][c] == SAND && sandGrid[rp1][c] == SAND && sandGrid[rp1][cm1] == SAND && sandGrid[rp1][cp1] == SAND) {
      // Do nothing. Stack as a pyramid
    }

    // Improve sand code so it doesn't move around endlessly until it piles up on itself
    else if (sandGrid[r][c] == SAND && sandGrid[rp1][c] == METAL) {
      // Do nothing.
    }
    
    else {
      // This is to the left. 
      // Make this happen less often by triggeting on random number
      if(randomNumber < 50){ // > 8 makes it stack again because there isn't a chance to move

        if(sandGrid[r][c] == SAND 
          && sandGrid[r][cm1] != SAND 
          && sandGrid[r][cm1] != METAL
          && sandGrid[r][cm1] != OIL
          && sandGrid[r][cm1] != WATER
          && sandGrid[r][cm1] != CREATOR
          && sandGrid[r][cm1] != DESTROYER
          && modulus == EVEN) {
        
        sandGrid[r][cm1] =  SAND;
        sandGrid[r][c]     = EMPTY;
        }

        // Else if for water movement left
        else if(sandGrid[r][c] == SAND && sandGrid[r][cm1] == WATER) {
          sandGrid[r][cm1] =  SAND;
          sandGrid[r][c]     = WATER;
        }
        //Sand movement in oil left
        else if(sandGrid[r][c] == SAND && sandGrid[r][cm1] == OIL) {
          sandGrid[r][cm1] =  SAND;
          sandGrid[r][c]     = OIL;
        }

      }
        //This is to the right.
      if(randomNumber > 50){
        if(sandGrid[r][c] == SAND
                  && sandGrid[r][cp1] != SAND 
                  && sandGrid[r][cp1] != METAL
                  && sandGrid[r][cp1] != OIL
                  && sandGrid[r][cp1] != WATER
                  && sandGrid[r][cp1] != CREATOR
                  && sandGrid[r][cp1] != DESTROYER
                  && modulus == EVEN) { 
          
        sandGrid[r][cp1] = SAND;
        sandGrid[r][c] = EMPTY;
        } // End of If-Statement.

        // Else if for water movement right
        if(sandGrid[r][c] == SAND && sandGrid[r][cp1] == WATER) {
          sandGrid[r][cp1] =  SAND;
          sandGrid[r][c]     = WATER;
        }
        // If for Sand in Oil movement right
        if(sandGrid[r][c] == SAND && sandGrid[r][cp1] == OIL) {
          sandGrid[r][cp1] =  SAND;
          sandGrid[r][c]     = OIL;
        }

      } // End of If-Statement. Random one
    }

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
    
//rmx  Add a function to swap air with the next empty space above the current block of particles
//loop through rows until empty then swap

    // Destroy above element
    if(sandGrid[r][c] == DESTROYER 
    && sandGrid[rm1][c] != EMPTY 
    && sandGrid[rm1][c] != METAL
    && sandGrid[rm1][c] != DESTROYER) 
    { // this is to calculate out where to put the air vapor after particle is destroyed
      int rowCount = 5; 
      for(int i = 0; i < MAX_ROWS -r; i++){
        if(sandGrid[r - rowCount][c] != EMPTY) 
        rowCount++;
        else break;
      }
      sandGrid[r - rowCount][c]  =  AIR;
      sandGrid[rm1][c]  =  EMPTY;
      sandGrid[r][c]      = DESTROYER;
    }

    // Destroy below element
    else if(sandGrid[r][c] == DESTROYER 
    && sandGrid[rp1][c] != EMPTY 
    && sandGrid[rp1][c] != METAL
    && sandGrid[rp1][c] != DESTROYER) 
    { 
        sandGrid[rp1][c]  =  EMPTY;
        sandGrid[r][c]      = DESTROYER;
    }

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
        && sandGrid[rp1][c] != OIL
    ) { // This can almost randomly control the speed of the WATER falling which is cool : && randomNumber >= 8
            sandGrid[rp1][c]  =  WATER; 
            sandGrid[r][c]      = EMPTY;
      // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
      // min or max row has been reached.
      if(sandGrid[rp1][c] == sandGrid[MAX_ROWS - 1][c]
        && sandGrid[rp1][c] != AIR
        && sandGrid[rp1][c] != OIL
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
        // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
    // min or max row has been reached.
    if (sandGrid[r][c] != WATER) {}
    else if ((sandGrid[r][c] == WATER) && r == MAX_ROWS-2 || r == MAX_ROWS-1 || r == MAX_ROWS || r == MAX_ROWS+1) {
      sandGrid[r][c] = EMPTY;
      sandGrid[1][c] =  WATER;
    }
    //warpping for right to left
    if (sandGrid[r][c] != WATER) {}
    else if ((sandGrid[r][c] == WATER) && (c == MAX_COLS-2 || c == MAX_COLS-1 || c == MAX_COLS || c == MAX_COLS+1) && sandGrid[r][2] != METAL ) {
      sandGrid[r][c] = EMPTY;
      sandGrid[r][2] =  WATER;//had to use 2 to allow sand enough room to move
    }
    else if ((sandGrid[r][c] == WATER) && (c == 0 || c == -1 || c == 0 || c == 1) && sandGrid[r][MAX_COLS-3] !=METAL ){
      sandGrid[r][c] = EMPTY;
      sandGrid[r][MAX_COLS-3] =  WATER;//-3 allows sand's falling behavior to work and not get stuck on right MAX_COLS
    }
    // This will adjust the WATER to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    // This is to the left. 
    // Make this happen less often by triggeting on random number
    if(randomNumber > 50){ // > 8 makes it stack again because there isn't a chance to move

      // Oil/Water movement - LEFT
      if (sandGrid[r][c] == WATER && sandGrid[r][cm1] == OIL) {
        sandGrid[r][cm1] = WATER; 
        sandGrid[r][c] = OIL;
      }

      if(sandGrid[r][c] == WATER 
          && sandGrid[r][cm1] != WATER 
          && sandGrid[r][cm1] != SAND
          && sandGrid[r][cm1] != OIL 
          && sandGrid[r][cm1] != AIR 
          && sandGrid[r][cm1] != METAL
          && sandGrid[r][cm1] != CREATOR
          && sandGrid[r][cm1] != DESTROYER
          && modulus == EVEN) {
        
        sandGrid[r][cm1] =  WATER; 
        sandGrid[r][c]     = EMPTY;
      }
    } // End of If-Statement. Random one
      //This is to the right.
    if(randomNumber < 50) { 

      // Oil/Water movement - RIGHT
      if (sandGrid[r][c] == WATER && sandGrid[r][cp1] == OIL) {
        sandGrid[r][cp1] = WATER; 
        sandGrid[r][c] = OIL;
      }
      
     if(sandGrid[r][c] == WATER
                && sandGrid[r][cp1] != WATER 
                && sandGrid[r][cp1] != OIL 
                && sandGrid[r][cp1] != AIR 
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
    if(sandGrid[r][c] == AIR && sandGrid[rm1][c] == EMPTY
        && sandGrid[rm1][c] != AIR
        && sandGrid[rm1][c] != WATER
        && sandGrid[rm1][c] != METAL
        && sandGrid[rm1][c] != CREATOR
        && sandGrid[rm1][c] != DESTROYER
        && sandGrid[rm1][c] != OIL
    ) { // This can almost randomly control the speed of the AIR falling which is cool : && randomNumber >= 8
            sandGrid[rm1][c]  =  AIR; 
            sandGrid[r][c]      = EMPTY;
      // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
      // min or max row has been reached.
      if(sandGrid[rm1][c] == sandGrid[MAX_ROWS - 1][c]
        && sandGrid[rm1][c] != AIR
        && sandGrid[rm1][c] != WATER
        && sandGrid[rm1][c] != METAL
        && sandGrid[rm1][c] != CREATOR
        && sandGrid[rm1][c] != DESTROYER
        //&& sandGrid[rp1][c] != OIL
        ) {
       sandGrid[rm1][c]              = EMPTY;
       sandGrid[r - (MAX_ROWS - 2)][c] =  AIR; 
      } // End of 2nd If-Statement.
    } // End of 1st If-Statement.
           // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
    // min or max row has been reached.
    if (sandGrid[r][c] != AIR) {}
    else if ((sandGrid[r][c] == AIR) && r == 0 || r == -1 || r == 1 || r == 2) {
      sandGrid[r][c] = EMPTY;
      sandGrid[1][c] = EMPTY;
      sandGrid[MAX_ROWS-2][c] =  AIR;
    }
    //warpping for right to left
    if (sandGrid[r][c] != AIR) {}
    else if ((sandGrid[r][c] == AIR) && (c == MAX_COLS-2 || c == MAX_COLS-1 || c == MAX_COLS || c == MAX_COLS+1) && sandGrid[r][2] != METAL ) {
      sandGrid[r][c] = EMPTY;
      sandGrid[r][2] =  AIR;//had to use 2 to allow sand enough room to move
    }
    else if ((sandGrid[r][c] == AIR) && (c == 0 || c == -1 || c == 0 || c == 1) && sandGrid[r][MAX_COLS-3] != METAL ){
      sandGrid[r][c] = EMPTY;
      sandGrid[r][MAX_COLS-3] =  AIR;//-3 allows sand's falling behavior to work and not get stuck on right MAX_COLS
    }
    // This will adjust the AIR to pile instead of stack. It will check the right and the left locations. 
    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    // This is to the left. 
    // Make this happen less often by triggeting on random number
    if(randomNumber > 50){ // > 8 makes it stack again because there isn't a chance to move

      // Oil/AIR movement - LEFT
      if (sandGrid[r][c] == AIR && sandGrid[r][cp1] == OIL) {
        sandGrid[r][cp1] = AIR; 
        sandGrid[r][c] = OIL;
      }

      if(sandGrid[r][c] == AIR 
          && sandGrid[r][cp1] != AIR 
          && sandGrid[r][cp1] != SAND
          && sandGrid[r][cp1] != OIL 
          && sandGrid[r][cp1] != METAL
          && sandGrid[r][cp1] != CREATOR
          && sandGrid[r][cp1] != DESTROYER
          && modulus == EVEN) {
        
        sandGrid[r][cp1] =  AIR; 
        sandGrid[r][c]     = EMPTY;
      }
    } // End of If-Statement. Random one
      //This is to the right.
    if(randomNumber < 50) { 

      // Oil/AIR movement - RIGHT
      if (sandGrid[r][c] == AIR && sandGrid[r][cm1] == OIL) {
        sandGrid[r][cm1] = AIR; 
        sandGrid[r][c] = OIL;
      }
      
     if(sandGrid[r][c] == AIR
                && sandGrid[r][cm1] != AIR 
                && sandGrid[r][cm1] != OIL 
                && sandGrid[r][cm1] != SAND 
                && sandGrid[r][cm1] != METAL
                && sandGrid[r][cm1] != CREATOR
                && sandGrid[r][cm1] != DESTROYER
                && modulus == EVEN) { 
      
     sandGrid[r][cm1] = AIR; 
     sandGrid[r][c] = EMPTY;
         } // End of If-Statement.
        } // End of If-Statement. Random one
  } // End of modify AIR module.

  public void modifyOil(int r, int c) {
    //let's simplify the code checks by doing the row and col checks in shorthand.
    //IMPORTANT TO SIMPLIFY
    int rm1 = r - 1;
    int rp1 = r + 1;
    int cm1 = c - 1;
    int cp1 = c + 1;

    // Checking Row & Column Boundaries.
    checkRowAndColBounds(r, c, OIL);

    // This will get a random number from 0-100
    int randomNumber = getRandomNumber(0, 100);
    
    // Swap up and down for empty space
    if(sandGrid[r][c] == OIL && sandGrid[rp1][c] == EMPTY) {
      sandGrid[rp1][c] = OIL;
      sandGrid[r][c] = EMPTY;
    }

    // Swap up and down for water
    if(sandGrid[r][c] == OIL && sandGrid[rm1][c] == WATER) {
      sandGrid[rm1][c] = OIL;
      sandGrid[r][c] = WATER;
    }

    // If there is an open spot on either side itll end up being on the left or right side of the original position.
    // This is to the left. 
    // Make this happen less often by triggeting on random number
    if(randomNumber > 50){ // > 8 makes it stack again because there isn't a chance to move
      if(sandGrid[r][c] == OIL 
          && sandGrid[r][cm1] != WATER 
          && sandGrid[r][cm1] != SAND
          && sandGrid[r][cm1] != OIL 
          && sandGrid[r][cm1] != METAL
          && sandGrid[r][cm1] != CREATOR
          && sandGrid[r][cm1] != DESTROYER) {
        
        sandGrid[r][cm1] =  OIL; 
        sandGrid[r][c]     = EMPTY;
      }
    } // End of If-Statement. Random one
      //This is to the right.
    if(randomNumber < 50) { 
     if(sandGrid[r][c] == OIL
                && sandGrid[r][cp1] != WATER  
                && sandGrid[r][cp1] != SAND
                && sandGrid[r][cp1] != OIL 
                && sandGrid[r][cp1] != METAL
                && sandGrid[r][cp1] != CREATOR
                && sandGrid[r][cp1] != DESTROYER) { 
      
     sandGrid[r][cp1] = OIL; 
     sandGrid[r][c] = EMPTY;
         } // End of If-Statement.
        } // End of If-Statement. Random one
             // This will address the situation of wrapping up and down. It swaps the location to produce the element when the
    // min or max row has been reached.
    if (sandGrid[r][c] != OIL) {}
    else if ((sandGrid[r][c] == OIL) && (r == MAX_ROWS-2 || r == MAX_ROWS-1 || r == MAX_ROWS || r == MAX_ROWS+1)) {
      sandGrid[r][c] = EMPTY;
      sandGrid[1][c] =  OIL;
    }
    //warpping for right to left
    if (sandGrid[r][c] != OIL) {}
    else if ((sandGrid[r][c] == OIL) && (c == MAX_COLS-2 || c == MAX_COLS-1 || c == MAX_COLS || c == MAX_COLS+1) && sandGrid[r][2] != METAL) {
      sandGrid[r][c] = EMPTY;
      sandGrid[r][2] =  OIL;//had to use 2 to allow sand enough room to move
    }
    else if ((sandGrid[r][c] == OIL) && (c == 0 || c == -1 || c == 0 || c == 1) && sandGrid[r][MAX_COLS-3] != METAL ){
      sandGrid[r][c] = EMPTY;
      sandGrid[r][MAX_COLS-3] =  OIL;//-3 allows sand's falling behavior to work and not get stuck on right MAX_COLS
  }
}



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
  