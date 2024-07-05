import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Board {

  private int[][] tiles;
  private int blankRow;
  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    var n = tiles.length;
    this.tiles = new int[n][n];
    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        this.tiles[i][j] = tiles[i][j];
        if (tiles[i][j] == 0) {
          blankRow = i;
        }
      }
    }
  }
                                         
  // string representation of this board
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(this.tiles.length);
    for (var line : this.tiles) {
      result.append("\n");
      for (var i = 0; i < line.length; i++) {
        result.append(line[i]);
        if (i != line.length - 1) {
          result.append(" ");
        }
      }
    }

    return result.toString();
  }

  // board dimension n
  public int dimension() {
    return this.tiles.length;
  }

  // number of tiles out of place
  public int hamming() {
    var counter = 0;
    var n = this.dimension();

    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        var current = this.tiles[i][j];
        if (current != 0 && current != i * n + j + 1) {
          counter++;
        }
      }
    }

    return counter;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    var counter = 0;
    var n = this.dimension();

    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        var current = this.tiles[i][j];
        if (current != 0 && current != i * n + j + 1) {
          // calc steps to right place
          var ti = (current - 1) / n;
          var tj = (current - 1) % n;
          var di = Math.abs(ti - i);
          var dj = Math.abs(tj - j);
          counter += di + dj;
        }
      }
    }

    return counter;
  }

  // is this board the goal board?
  public boolean isGoal() {
    var n = this.dimension();
    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        var current = this.tiles[i][j];
        if (current != 0 && current != i * n + j + 1) {
          return false;
        }
      }
    }

    return true;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == null) return false;
    if (y.getClass() != this.getClass()) return false;
    
    var other = (Board) y;
    var n = this.dimension();
    
    if (other.dimension() != n) return false;

    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        if (this.tiles[i][j] != other.tiles[i][j]) return false;
      }
    }

    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    var n = this.dimension();
    var copyTiles = new int[][][] {
      new int[n][n],
      new int[n][n],
      new int[n][n],
      new int[n][n],
    };
    var zi = -1;
    var zj = -1;
    for (var i = 0; i < n; i++) {
      for (var j = 0; j < n; j++) {
        for (var k = 0; k < 4; k++) {
          copyTiles[k][i][j] = this.tiles[i][j];
        }

        if (this.tiles[i][j] == 0) {
          zi = i;
          zj = j;
        }
      }
    }

    var list = new ArrayList<Board>();
    for (var k = 0; k < 4; k++) {
      // 0 - left
      // 1 - top
      // 2 - right
      // 3 - bottom
      var copyTile = copyTiles[k];
      switch (k) {
        case 0:
          if (zj == 0) continue;
          copyTile[zi][zj] = copyTile[zi][zj - 1];
          copyTile[zi][zj - 1] = 0;
          break;
        case 1:
          if (zi == 0) continue;
          copyTile[zi][zj] = copyTile[zi - 1][zj];
          copyTile[zi - 1][zj] = 0;
          break;
        case 2:
          if (zj == n - 1) continue;
          copyTile[zi][zj] = copyTile[zi][zj + 1];
          copyTile[zi][zj + 1] = 0;
          break;
        case 3:
          if (zi == n - 1) continue;
          copyTile[zi][zj] = copyTile[zi + 1][zj];
          copyTile[zi + 1][zj] = 0;
          break;
      }

      list.add(new Board(copyTile));
    }

    return list;
  }

  // a board that is obtained by exchanging any pair of tiles
  // public Board twin() {
  //   var n = this.dimension();
  //   var copyTiles = new int [n][n];

  //   var zi = -1;
  //   var zj = -1;
  //   for (var i = 0; i < n; i++) {
  //     for (var j = 0; j < n; j++) {
  //       if (this.tiles[i][j] == 0) {
  //         zi = i;
  //         zj = j;
  //       }

  //       copyTiles[i][j] = this.tiles[i][j];
  //     }
  //   }

  //   var i1 = StdRandom.uniformInt(n);
  //   var j1 = StdRandom.uniformInt(n);
  //   var i2 = StdRandom.uniformInt(n);
  //   var j2 = StdRandom.uniformInt(n);
    
  //   while ((i1 == zi && j1 == zj) || (i2 == zi && j2 == zj) || (i1 == i2 && j1 == j2)) {
  //     i1 = StdRandom.uniformInt(n);
  //     j1 = StdRandom.uniformInt(n);
  //     i2 = StdRandom.uniformInt(n);
  //     j2 = StdRandom.uniformInt(n);
  //   }

  //   var tmp = copyTiles[i1][j1];
  //   copyTiles[i1][j1] = copyTiles[i2][j2];
  //   copyTiles[i2][j2] = tmp;

  //   return new Board(copyTiles);
  // }

  public Board twin() {
    var cloned = copyOf(this.tiles);
    if (blankRow != 0) {
        swap(cloned, 0, 0, 0, 1);
    } else {
        swap(cloned, 1, 0, 1, 1);
    }
    return new Board(cloned);
}

  private int[][] copyOf(int[][] matrix) {
    int[][] clone = new int[matrix.length][];
    for (int row = 0; row < matrix.length; row++) {
        clone[row] = matrix[row].clone();
    }
    return clone;
  }

  private void swap(int[][] v, int rowA, int colA, int rowB, int colB) {
    int swap = v[rowA][colA];
    v[rowA][colA] = v[rowB][colB];
    v[rowB][colB] = swap;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    var tiles = new int[n][n];
    for (int i = 0; i < n; i++) {
      tiles[i] = new int[n];
      for (int j = 0; j < n; j++)
      {
        int x = in.readInt();
        tiles[i][j] = x;
      }
    }

    var board = new Board(tiles);
    StdOut.println(board);
    StdOut.println(board.manhattan());

    StdOut.println("---Boards---");
    var boards = board.neighbors();
    var i = 0;
    for (var b : boards) {
      StdOut.println("Board number " + i++);
      StdOut.println(b.toString());
    }
  }
}
