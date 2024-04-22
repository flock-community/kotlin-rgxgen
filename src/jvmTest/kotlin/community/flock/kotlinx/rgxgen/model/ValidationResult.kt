package community.flock.kotlinx.rgxgen.model

import org.junit.jupiter.api.Assertions

class ValidationResult {
    var countMatched: Int = 0
    var countNotMatched: Int = 0

    fun addMatched(): ValidationResult {
        ++countMatched
        return this
    }

    fun addNotMatched(): ValidationResult {
        ++countNotMatched
        return this
    }

    fun assertPassed() {
        if (countNotMatched > 0) {
            Assertions.fail<Any>("Not matched $countNotMatched vs matched $countMatched")
        }
    }
}
