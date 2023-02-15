package utils

import kotlin.random.Random
import kotlin.random.asJavaRandom

data class Data(
    val x: Array<DoubleArray>,
    val y: DoubleArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (!x.contentDeepEquals(other.x)) return false
        if (!y.contentEquals(other.y)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.contentDeepHashCode()
        result = 31 * result + y.contentHashCode()
        return result
    }
}

/**

Generates linear regression data given the number of samples, coefficients, standard deviation, and range of x values.
@param numberOfSamples the number of data samples to generate
@param coefficients the coefficients to use for the linear regression
@param stdDev the standard deviation to use when adding noise to the data
@param xLow the lower bound of the range of x values to generate
@param xHigh the upper bound of the range of x values to generate
@return a Data object containing the generated x and y values
 */
fun generateLinearRegressionData(
    numberOfSamples: Int,
    coefficients: DoubleArray,
    stdDev: Double = 0.0,
    xLow: Double = 0.0,
    xHigh: Double = 10.0,
): Data {
    val x: Array<DoubleArray> = Array(numberOfSamples) { DoubleArray(coefficients.size) }
    for (i in 0 until numberOfSamples) {
        x[i][0] = 1.0
        for (j in 1 until coefficients.size) {
            x[i][j] = Random.nextDouble(xLow, xHigh) + Random.asJavaRandom().nextGaussian() * stdDev
        }
    }
    val y: DoubleArray = x.dot(coefficients)
    return Data(x, y)
}