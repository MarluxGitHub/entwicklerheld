package de.entwicklerheld.walkingRobot;


public class WalkingRobot {

    public static int uniquePaths(Grid board) {
        //implement this in scenario 1 and 2
        board.setValue(0, 0, 1);
        board = pathMatrix(board);

        return board.getValue(board.getHeight()-1, board.getWidth()-1);
    }

    public static Grid pathMatrix(Grid board) {
        if(board.getValue(0, 0) == null) {
            board.setValue(0, 0, 0);
        }

        for(int i = 0; i < board.getHeight(); i++){
            for(int j = 0; j < board.getWidth(); j++){
                if(i == 0 && j == 0){
                    continue;
                }
                if(board.getValue(i, j) == 0){
                   continue;
                }else if(i == 0){
                    board.setValue(i, j, board.getValue(i, j-1));
                }else if(j == 0){
                    board.setValue(i, j, board.getValue(i-1, j));
                }else{
                    board.setValue(i, j, board.getValue(i-1, j) + board.getValue(i, j-1));
                }
            }
        }

        return board;
    }


}