package org.isaaq;
import java.util.LinkedList;
import java.util.Queue;
public class MovingAverageFilter {
    private Queue<Double> buffer;
    private int windowSize;
    private double sum;

    public MovingAverageFilter(int windowSize) {
        this.buffer = new LinkedList<>();
        this.windowSize = windowSize;
        this.sum = 0.0;
    }
    public double filter(double newValue) {
        buffer.add(newValue);
        sum += newValue;

        if (buffer.size() > windowSize) {
            sum -= buffer.poll();
        }

        return sum / buffer.size();
    }
    public void clear() {
        buffer.clear();
        sum = 0.0;
    }

}
