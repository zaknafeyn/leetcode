import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {

  private class SearchNode implements Comparable<SearchNode> {

    private final SearchNode prev;
    private final Board board;
    private final int moves;

    SearchNode(Board board, int moves, SearchNode prev) {
        this.board = board;
        this.moves = moves;
        this.prev = prev;
    }

    @Override
    public int compareTo(SearchNode that) {
        return this.priority() - that.priority();
    }

    public int priority() {
        return board.manhattan() + moves;
    }

    public Board getBoard() {
        return board;
    }

    public int getMoves() {
        return moves;
    }

    public SearchNode prev() {
        return prev;
    }
  }

  private boolean isSolvable = false;
  private SearchNode solutionNode = null;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null)
    throw new IllegalArgumentException("Value could not be null");

    var minPQ = new MinPQ<SearchNode>();

    minPQ.insert(new SearchNode(initial, 0, null));

    while (true) {
      var currentNode = minPQ.delMin();
      var currentBoard = currentNode.getBoard();

      if (currentBoard.isGoal()) {
        this.isSolvable = true;
        this.solutionNode = currentNode;
        break;
      }

      if (currentBoard.manhattan() == 2 && currentBoard.twin().isGoal()) {
        isSolvable = false;
        break;
      }

      var prevBoard = currentNode.getMoves() == 0 ? null : currentNode.prev().getBoard();

      for (var board: currentBoard.neighbors()) {
        if (prevBoard != null && prevBoard.equals(board)) {
          continue;
        }

        minPQ.insert(new SearchNode(board, currentNode.getMoves() + 1, currentNode));
      }
    }
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return this.isSolvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!this.isSolvable()) return -1;
    return solutionNode.getMoves();
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!this.isSolvable()) return null;

    ArrayList<Board> result = new ArrayList<>();
    var node = solutionNode;
    while (node != null) {
      result.add(node.getBoard());
      node = node.prev();
    }

    return result;
  }

  // test client (see below) 
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
  }
}
