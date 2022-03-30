import java.util.Random;

public class Main {
    public static void mainCorrect(int[] args, String fileName) {
        try {
            Maze maze = Maze.readMaze(fileName);
            IMazeSolverWithPower solver = new CorrectMazeSolverWithPower();
            solver.initialize(maze);
            try {
                System.out.println(solver.pathSearch(args[0], args[1], args[2], args[3], args[4]));
                ImprovedMazePrinter.printMaze(maze);
            } catch (Exception e) {System.out.println(e.getMessage());}

            for (int i = 0; i <= 9; ++i) {
                System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void mainOriginal(int[] args, String fileName) {
        try {
            Maze maze = Maze.readMaze(fileName);
            IMazeSolverWithPower solver = new MazeSolverWithPower();
            solver.initialize(maze);

            try {
                System.out.println(solver.pathSearch(args[0], args[1], args[2], args[3], args[4]));
                ImprovedMazePrinter.printMaze(maze);
            } catch (Exception e) {System.out.println(e.getMessage());}

            for (int i = 0; i <= 9; ++i) {
                System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void mainRandCorrect(int seed, int size) {
        String[] mazeStr = new String[] {"maze-dense.txt", "maze-empty.txt", "maze-horizontal.txt", "maze-sample.txt", "maze-vertical.txt"};
        Random rand = new Random(seed);
        for (String str : mazeStr) {
            System.out.println("Now doing: " + str + " ------------------------------------------");
            try {
                Maze maze =Maze.readMaze(str);
                IMazeSolverWithPower solver = new CorrectMazeSolverWithPower();
                printer(size, rand, maze, solver);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mainRandOriginal(int seed, int size) {
        String[] mazeStr = new String[] {"maze-dense.txt", "maze-empty.txt", "maze-horizontal.txt", "maze-sample.txt", "maze-vertical.txt"};
        Random rand = new Random(seed);
        for (String str : mazeStr) {
            System.out.println("Now doing: " + str + " ------------------------------------------");
            try {
                Maze maze = Maze.readMaze(str);
                IMazeSolverWithPower solver = new MazeSolverWithPower();
                printer(size, rand, maze, solver);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printer(int size, Random rand, Maze maze, IMazeSolverWithPower solver) throws Exception {
        solver.initialize(maze);

        for(int n = 1; n <= size; n++) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            int endX = rand.nextInt(10);
            int endY = rand.nextInt(10);
            int power = rand.nextInt(10);
            try {
                System.out.println(solver.pathSearch(x, y, endX, endY, power));
                ImprovedMazePrinter.printMaze(maze);
            } catch (Exception e) {
                //System.out.println(e.getMessage()+ " inner");
            }
            finally {
                //System.out.println("x: " + x + " y: " + y + " endX: " + endX + " endY: " + endY + " power: " + power);
            }

            for (int i = 0; i < maze.getRows() * maze.getColumns(); ++i) {
                //System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
                System.out.print(solver.numReachable(i) + " ");
            }
            System.out.println();
        }
    }
}