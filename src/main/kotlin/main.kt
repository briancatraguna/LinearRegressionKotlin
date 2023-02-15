import linear_regression.LinearRegression
import utils.generateLinearRegressionData

fun main() {
    /**
     * Generate data of Y = 1 + 7x with Standard Deviation of 2 as Noise
     */
    val data = generateLinearRegressionData(
        numberOfSamples = 100,
        coefficients = doubleArrayOf(1.0, 7.0),
        stdDev = 2.0,
        xLow = 0.0,
        xHigh = 10.0
    )

    val X = data.x
    val y = data.y

    val linearRegression = LinearRegression()
    linearRegression.fit(X, y)
    println("Intercept: ${linearRegression.intercept}")
    println("Coefficients: ${linearRegression.coefficients.contentToString()}")
}