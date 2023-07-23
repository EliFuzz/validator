package utility

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import utility.shared.EnumFixture
import utility.shared.EnumFixture.Success
import kotlin.test.Test

class StringValidatorTest {

    @Test
    fun `chained method on valid input should process successfully`() {
        assertNotNull(
            validate("name", Success.name)
                .exists()
                .exact(Success.name)
                .lessThan(4)
                .greaterThan(4)
                .tryParse(EnumFixture::class)
                .regex("^${Success.name}$")
                .contains('S')
        )
    }

    @Nested
    inner class Exits {

        @Test
        fun `exists on valid string input should process successfully`() {
            assertNotNull(validate("name", "John").exists())
        }

        @Test
        fun `exists on NULL input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", null as String?).exists() }
        }

        @Test
        fun `exists on empty string input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "").exists() }
        }
    }

    @Nested
    inner class Exact {

        @Test
        fun `exact on valid string input should process successfully`() {
            assertNotNull(validate("name", "John").exact("John"))
        }

        @Test
        fun `exact on NULL input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", null as String?).exact("") }
        }

        @Test
        fun `exact on empty string input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "").exact("") }
        }

        @Test
        fun `exact on invalid input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "John").exact("Jane") }
        }
    }

    @Nested
    inner class LessThan {

        @Test
        fun `lessThan on valid string input should process successfully`() {
            assertNotNull(validate("name", "John").lessThan(8))
        }

        @Test
        fun `lessThan on NULL input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", null as String?).lessThan(8) }
        }

        @Test
        fun `lessThan on empty string input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "").lessThan(8) }
        }

        @Test
        fun `lessThan on invalid input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "John").lessThan(3) }
        }
    }

    @Nested
    inner class GreaterThan {

        @Test
        fun `greaterThan on valid string input should process successfully`() {
            assertNotNull(validate("name", "John").greaterThan(4))
        }

        @Test
        fun `greaterThan on NULL input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", null as String?).greaterThan(8) }
        }

        @Test
        fun `greaterThan on empty string input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "").greaterThan(8) }
        }

        @Test
        fun `greaterThan on invalid input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "John").greaterThan(8) }
        }
    }

    @Nested
    inner class TryParse {

        @Test
        fun `tryParse on valid string input should process successfully`() {
            assertNotNull(validate("name", Success.name).tryParse(EnumFixture::class))
        }

        @Test
        fun `tryParse on invalid input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "John").tryParse(EnumFixture::class) }
        }
    }

    @Nested
    inner class Regex {

        @Test
        fun `regex on valid string input should process successfully`() {
            assertNotNull(validate("name", "John").regex("^John$"))
        }

        @Test
        fun `regex on NULL input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", null as String?).regex("^John$") }
        }

        @Test
        fun `regex on empty string input should throw exception`() {
            assertThrows<IllegalArgumentException> {
                validate("name", "").regex("^John$")
            }
        }

        @Test
        fun `regex on invalid input should throw exception`() {
            assertThrows<IllegalArgumentException> {
                validate("name", "John").regex("^Jane$")
            }
        }
    }

    @Nested
    inner class Contains {

        @Test
        fun `contains on valid string input should process successfully`() {
            assertNotNull(validate("name", "John").contains('J'))
        }

        @Test
        fun `contains on NULL input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", null as String?).contains('J') }
        }

        @Test
        fun `contains on empty string input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "").contains('J') }
        }

        @Test
        fun `contains on invalid input should throw exception`() {
            assertThrows<IllegalArgumentException> { validate("name", "John").contains("J") }
            assertThrows<IllegalArgumentException> { validate("name", "John").contains('A') }
        }
    }
}
