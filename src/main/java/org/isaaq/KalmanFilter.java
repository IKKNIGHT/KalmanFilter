package org.isaaq;

public class KalmanFilter {
    double x = 0; // your initial state
    double Q = 0.1; // your model covariance
    double R = 0.4; // your sensor covariance
    double p = 1; // your initial covariance guess
    double K = 1; // your initial Kalman gain guess

    double x_previous = x;
    double p_previous = p;
    double u = 0;
    double z = 0;

    // kalman filter that is made for sensor outputs 1d

    /**
     *
     * @param x
     * @param p
     * @param u
     * @param z
     * @param Q
     * @param R
     * @param K
     */
    public KalmanFilter(double x, double p, double u, double z, double Q, double R, double K) {
        this.x = x;
        this.p = p;
        this.u = u;
        this.z = z;
        this.Q = Q;
        this.R = R;
        this.K = K;
        this.x_previous = x;
        this.p_previous = p;
    }

    // calculate the kalman gain

    /**
     *
     * @param p
     * @param R
     * @return p/(p+r)
     *
     */
    public static double calculateK(double p, double R) {

        return p / (p + R);
    }

    /**
     *
     * @param measurement
     * @return x
     */
    public double update(double measurement) {
        // prediction
        setZ(measurement);
        x = calculateX_prediction(x_previous, u);
        p = calculateP_prediction(p_previous, Q);
        // update
        K = calculateK(p, R);
        x = calculateX(x, K, measurement);
        p = calculateP(K, p);
        // save previous values
        x_previous = x;
        p_previous = p;
        return x;
    }

    /**
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    //setters

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @return p
     */
    public double getP() {
        return p;
    }

    /**
     *
     * @param p
     */
    public void setP(double p) {
        this.p = p;
    }

    /**
     *
     * @return u
     */
    public double getU() {
        return u;
    }

    /**
     *
     * @param u
     */
    public void setU(double u) {
        this.u = u;
    }

    /**
     *
     * @return z
     */
    public double getZ() {
        return z;
    }

    /**
     *
     * @param z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     *
     * @return q
     */
    public double getQ() {
        return Q;
    }

    /**
     *
     * @param Q
     */
    public void setQ(double Q) {
        this.Q = Q;
    }

    /**
     *
     * @return r
     */
    public double getR() {
        return R;
    }

    /**
     *
     * @param R
     */
    public void setR(double R) {
        this.R = R;
    }

    /**
     *
     * @return k
     */
    public double getK() {
        return K;
    }

    /**
     *
     * @param K
     */
    public void setK(double K) {
        this.K = K;
    }

    /**
     *
     * @return x_previous
     */
    public double getX_previous() {
        return x_previous;
    }

    /**
     *
     * @param x_previous
     */
    public void setX_previous(double x_previous) {
        this.x_previous = x_previous;
    }

    /**
     *
     * @return p_previous
     */
    public double getP_previous() {
        return p_previous;
    }

    /**
     *
     * @param p_previous
     */
    public void setP_previous(double p_previous) {
        this.p_previous = p_previous;
    }

    // calculate the new state

    /**
     *
     * @param x
     * @param K
     * @param measurement
     * @return x+K*(measurement-x)
     */
    public double calculateX(double x, double K, double measurement) {
        return x + K * (measurement - x);
    }

    // calculate the new covariance

    /**
     *
     * @param K
     * @param p
     * @return (1-k)*p
     */
    public double calculateP(double K, double p) {
        return (1 - K) * p;
    }

    // calculate state prediction

    /**
     *
     * @param x
     * @param u
     * @return x+u
     */
    public double calculateX_prediction(double x, double u) {
        return x + u;
    }

    /**
     *
     * @param p
     * @param Q
     * @return p+Q
     */
    // calculate covariance prediction
    public double calculateP_prediction(double p, double Q) {
        return p + Q;
    }
}
