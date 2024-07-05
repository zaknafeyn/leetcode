import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

  private class PointsComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
      return o1.compareTo(o2);
    }
  }

  private LineSegment[] segments;
  private int segmentsCount = 0;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    if (points == null) throw new IllegalArgumentException();
    this.segments = new LineSegment[0];

    var arrSize = points.length;
    // if (arrSize < 4) {
    //   return;
    // }

    Point[] line = new Point[4];

    for (var i = 0; i < arrSize; i++) {
      line[0] = points[i];

      if (points[i] == null) throw new IllegalArgumentException();

      for (var j = i + 1; j < arrSize; j++) {
        line[1] = points[j];
        if (line[0].compareTo(line[1]) == 0) throw new IllegalArgumentException();

        for (var k = j+1; k < arrSize; k++) {
          line[2] = points[k];

          if (line[0].slopeOrder().compare(line[1], line[2]) != 0) continue;

          for (var m = k + 1; m < arrSize; m++) {
            line[3] = points[m];
            if (line[0].slopeOrder().compare(line[1], line[3]) != 0) continue;

            // these 4 points are on the same line

            var lineCopy = Arrays.copyOf(line, 4);
            Arrays.sort(lineCopy, new PointsComparator());

            if (this.segments.length == this.segmentsCount) {
              var tempSegments = new LineSegment[this.segmentsCount * 2];
              for (var index = 0; index < this.segmentsCount; index++) {
                tempSegments[index] = this.segments[index];
              }

              this.segments = tempSegments;
            }
            if (this.segments.length == segmentsCount) {
              expandArray();
            }

            this.segments[segmentsCount++] = new LineSegment(lineCopy[0], lineCopy[3]);
          }
        }
      }
    }
  }

  private void expandArray() {
    var len = this.segments.length == 0 ? 1 : this.segments.length * 2;
    var tempSegments = new LineSegment[len];
    for (var i = 0; i < this.segments.length; i++) {
      tempSegments[i] = this.segments[i];
    }
    this.segments = tempSegments;
  }
    
  // the number of line segments
  public int numberOfSegments() {
    return this.segmentsCount;
  }

  // the line segments
  public LineSegment[] segments() {
    if (numberOfSegments() == 0) return this.segments;

    return Arrays.copyOf(this.segments, segmentsCount);
  }

  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
        StdOut.println(points[i]);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    var bruteCollinearPoints = new BruteCollinearPoints(points);
    for (var segment: bruteCollinearPoints.segments()) {
      StdOut.println(segment);
      segment.draw();
    }

    StdDraw.show();
  }
}
