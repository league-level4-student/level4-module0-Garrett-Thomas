package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		selectNextPath(maze.c[randGen.nextInt(width)][randGen.nextInt(height)]);

		// 5. call selectNextPath method with the randomly selected cell

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for (int i = 0; i < getUnvisitedNeighbors(currentCell).size(); i++) {
			temp.add(getUnvisitedNeighbors(currentCell).get(i));
		}
		// C. if has unvisited neighbors,
		if (temp.size() >= 1) {
			Cell temp1 = temp.get(randGen.nextInt(temp.size()));
			uncheckedCells.push(temp1);
			removeWalls(temp1, currentCell);
			// currentCell = temp1;

			currentCell.setBeenVisited(true);
		}
		if (uncheckedCells.size() != 0) {
			currentCell = uncheckedCells.pop();
		}

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as
		// visited if all the
		// neighbors are
		// visited if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c2.getX() + 1 + c2.getY() == c1.getX() + c1.getY()) {
			c2.setEastWall(false);
		}
		if (c2.getX() - 1 + c2.getY() == c1.getX() + c1.getY()) {
			c2.setSouthWall(false);
		}
		if (c2.getY() + 1 + c2.getX() == c1.getY() + c1.getX()) {
			c2.setNorthWall(false);
		}
		if (c2.getY() - 1 + c2.getX() == c1.getX() + c1.getY()) {
			c2.setSouthWall(false);
		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell cub) {
		ArrayList<Cell> temp = new ArrayList<Cell>();
		if (cub.getX() >= 1 && cub.getX() <= (maze.getWidth() - 1) && cub.getY() >= 1
				&& cub.getY() <= (maze.getHeight() - 1)) {
			if (!maze.c[cub.getX()][cub.getY() + 1].hasBeenVisited()) {
				temp.add(maze.c[cub.getX()][cub.getY() + 1]);
			}
			if (!maze.c[cub.getX()][cub.getY() - 1].hasBeenVisited()) {
				temp.add(maze.c[cub.getX()][cub.getY() - 1]);
			}
			if (!maze.c[cub.getX() + 1][cub.getY() + 1].hasBeenVisited()) {
				temp.add(maze.c[cub.getX() + 1][cub.getY()]);
			}
			if (!maze.c[cub.getX() - 1][cub.getY() + 1].hasBeenVisited()) {
				temp.add(maze.c[cub.getX() - 1][cub.getY()]);
			}
			return temp;
		}
		return temp;
	}
}