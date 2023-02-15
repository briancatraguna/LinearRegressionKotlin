package utils

/**
 * Performs dot product of two matrices.
 *
 * @param matrix a DoubleArray that represents the second matrix
 * @return a DoubleArray that represents the dot product of the two matrices
 * @throws IllegalArgumentException if the matrices are not compatible for multiplication
 */
fun Array<DoubleArray>.dot(matrix: DoubleArray): DoubleArray {
    if (this[0].size != matrix.size) {
        throw IllegalArgumentException(
            "Column size ${this[0].size} is not compatible with matrix size ${matrix.size}"
        )
    }
    val result = DoubleArray(this.size)
    for (row in 0 until this.size) {
        var sum = 0.0
        for (column in matrix.indices) {
            sum += (this[row][column] * matrix[column])
        }
        result[row] = sum
    }
    return result
}

/**
 * Subtracts two DoubleArrays element-wise.
 *
 * @param arr a DoubleArray to be subtracted from this array
 * @return a DoubleArray that represents the result of the element-wise subtraction
 * @throws IllegalArgumentException if the two arrays are not the same size
 */
fun DoubleArray.minus(arr: DoubleArray): DoubleArray {
    if (this.size != arr.size) {
        throw IllegalArgumentException(
            "Array size is not the same! size ${this.size} is not compatible with size ${arr.size}"
        )
    }
    val result = DoubleArray(this.size)
    for (index in this.indices) {
        result[index] = this[index] - arr[index]
    }
    return result
}

/**
 * Transposes a 2D array.
 *
 * @return a new 2D array that represents the transposed version of this array
 */
fun Array<DoubleArray>.transpose(): Array<DoubleArray> {
    val result = Array(this[0].size) { DoubleArray(this.size) }
    for (row in 0 until this.size) {
        for (col in 0 until this[0].size) {
            result[col][row] = this[row][col]
        }
    }
    return result
}

/**
 * Multiplies two matrices element-wise.
 *
 * @param matrix a 2D array that represents the second matrix
 * @return a new 2D array that represents the result of the matrix multiplication
 * @throws IllegalArgumentException if the matrices are not compatible for multiplication
 */
fun Array<DoubleArray>.matrixMultiply(matrix: Array<DoubleArray>): Array<DoubleArray> {
    if (this[0].size != matrix.size) {
        throw IllegalArgumentException("Matrix row needs to be same as other matrix col!")
    }
    val result = Array(this.size) { DoubleArray(matrix[0].size) }
    for (row in 0 until this.size) {
        for (col in 0 until matrix[0].size) {
            var dotProduct = 0.0
            for (index in 0 until this[0].size) {
                dotProduct += (this[row][index] * matrix[index][col])
            }
            result[row][col] = dotProduct
        }
    }
    return result
}

/**
 * Expands a 1D array to a 2D array with a single column.
 *
 * @return a 2D array with one column that represents the expanded version of this array
 */
fun DoubleArray.expandDimension(): Array<DoubleArray> {
    return Array(this.size) { i -> doubleArrayOf(this[i]) }
}

/**
 * Flattens the 2D array into a 1D array by concatenating all the rows.
 *
 * @return A 1D array containing all the elements of the 2D array in row-major order.
 */
fun Array<DoubleArray>.flatten(): DoubleArray {
    val result = DoubleArray(this.size * this[0].size)
    var index = 0
    for (row in 0 until this.size) {
        for (col in 0 until this[0].size) {
            result[index++] = this[row][col]
        }
    }
    return result
}

/**
 * Multiplies each element of the array by a scalar value.
 *
 * @param x The scalar value to multiply the array elements by.
 * @return A new array containing the result of the element-wise multiplication.
 */
fun DoubleArray.multiply(x: Double): DoubleArray {
    val result: DoubleArray = this.clone()
    for (i in 0 until result.size) {
        result[i] = result[i] * x
    }
    return result
}