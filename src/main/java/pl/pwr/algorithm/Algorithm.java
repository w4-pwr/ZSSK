package pl.pwr.algorithm;

import pl.pwr.model.Matrix;

/**
 * Created by Rafal on 2016-03-26.
 */
public abstract class Algorithm {

    Matrix matrix;

    private int[] minRoute;

    abstract void invoke(Matrix matrix);
}
