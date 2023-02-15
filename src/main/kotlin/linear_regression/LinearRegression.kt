package linear_regression

import utils.*
import kotlin.random.Random

/**
 * Linear regression model that can be trained on data.
 *
 * The model is trained using gradient descent to find the optimal intercept
 * and coefficients for the provided data.
 */
class LinearRegression {

    var intercept: Double? = null

    var coefficients: DoubleArray? = null

    companion object {
        const val DEFAULT_TRAINING_ITERATIONS = 3000
    }

    /**
     * Trains the linear regression model using the provided data.
     *
     * @param x The input features used to predict the output variable.
     * @param y The output variable that is being predicted.
     */
    fun fit(x: Array<DoubleArray>, y: DoubleArray) {
        // Generate initial parameters
        var coefficients = doubleArrayOf(
            Random.nextDouble(0.0, 10.0),
            Random.nextDouble(0.0, 10.0)
        )

        // Train the model using gradient descent
        repeat(DEFAULT_TRAINING_ITERATIONS) {
            coefficients = gradientDescentStep(coefficients, x, y)
        }

        // Save the trained intercept and coefficients
        intercept = coefficients[0]
        this.coefficients = coefficients.copyOfRange(1, coefficients.size)
    }

    /**
     * Calculates a single step of gradient descent to update the model parameters.
     *
     * @param coefficients The current model parameters.
     * @param x The input features used to predict the output variable.
     * @param y The output variable that is being predicted.
     * @param learningRate The learning rate used to adjust the size of the parameter update.
     * @return The updated model parameters after a single step of gradient descent.
     */
    private fun gradientDescentStep(
        coefficients: DoubleArray,
        x: Array<DoubleArray>,
        y: DoubleArray,
        learningRate: Double = 0.05
    ): DoubleArray {
        // Calculate the predicted y values for the current model parameters
        val yHat: DoubleArray = x.dot(coefficients)

        // Calculate the difference between the predicted and actual y values
        val error: DoubleArray = yHat.minus(y)

        // Calculate the gradient of the loss function with respect to the model parameters
        val gradient = x.transpose().matrixMultiply(error.expandDimension())

        // Update the model parameters using the gradient and learning rate
        return coefficients.minus(gradient.flatten().multiply(learningRate / x.size))
    }
}