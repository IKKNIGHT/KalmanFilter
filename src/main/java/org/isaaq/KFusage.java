package org.isaaq;

public class KFusage {
    public static void main(String[] args){
        double initvalue = 0;
        double filteredvalue = 0;
        KalmanFilter kalmanFilter = new KalmanFilter(5, 100, 0, 0, 1, 1, KalmanFilter.calculateK(1, 1));
        while(true) {
            var precision = 1; // 2 decimals
            initvalue = Math.floor(Math.random() * (10 * precision - precision) + precision) / (precision); // dsensor values
            filteredvalue = kalmanFilter.update(initvalue);
            System.out.println("x: " + kalmanFilter.getX()); // use telemetry.addData
            System.out.println("p: " + kalmanFilter.getP());
            System.out.println("u: " + kalmanFilter.getU());
            System.out.println("z: " + kalmanFilter.getZ());
            System.out.println("Q: " + kalmanFilter.getQ());
            System.out.println("R: " + kalmanFilter.getR());
            System.out.println("K: " + kalmanFilter.getK());
            System.out.println("x_previous: " + kalmanFilter.getX_previous());
            System.out.println("p_previous: " + kalmanFilter.getP_previous());
            System.out.println("initvalue: " + initvalue);
            System.out.println("filteredvalue: " + filteredvalue);
        }
    }
}
