package pl.pwr.algorithm;

import pl.pwr.model.Matrix;
import pl.pwr.output.AlgorithmProduct;

/**
 * Created by Rafal on 2016-03-26.
 */
public abstract class Algorithm {

    private int[] minRoute;

    public abstract AlgorithmProduct invoke(Matrix matrix);
}
