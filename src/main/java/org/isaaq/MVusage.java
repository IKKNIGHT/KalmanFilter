package org.isaaq;

public class MVusage {
    public static void main(String[] args){
        double initvalue = 0;
        double filteredvalue = 0;
        MovingAverageFilter movingAverageFilter = new MovingAverageFilter(5);
        while(true) {
            var precision = 1; // 2 decimals
            initvalue = Math.floor(Math.random() * (10 * precision - precision) + precision) / (precision); // dsensor values
            filteredvalue = movingAverageFilter.filter(initvalue);
            System.out.println("initvalue: " + initvalue);
            System.out.println("filteredvalue: " + filteredvalue);
        }
    }
}
