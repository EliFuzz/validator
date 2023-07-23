package utility

import java.lang.Enum.valueOf
import kotlin.reflect.KClass

fun validate(fieldName: String, fieldValue: String?): Validator<String> = object: Validator<String> {
    override fun exists(): Validator<String> =
        require(!fieldValue.isNullOrBlank())
        { "${prepend()} $fieldName must not be empty" }
            .let { this }

    override fun exact(value: String): Validator<String> = exists().also {
        require(fieldValue == value)
        { "${prepend()} $fieldName must be equal to $value" }
    }.let { this }

    override fun lessThan(limit: Int): Validator<String> = exists().also {
        require(fieldValue!!.length <= limit) { "${prepend()} $fieldName must be longer than $limit" }
    }.let { this }

    override fun greaterThan(limit: Int): Validator<String> = exists().also {
        require(fieldValue!!.length >= limit) { "${prepend()} $fieldName must be shorter than $limit" }
    }.let { this }

    override fun <K: Enum<K>> tryParse(clazz: KClass<K>): Validator<String> = exists().also {
        try {
            valueOf(clazz.java, fieldValue!!).let { this }
        } catch (e: Exception) {
            throw IllegalArgumentException("${prepend()} $fieldName failed to parse: ${e.message}")
        }
    }

    override fun regex(regex: String): Validator<String> = exists().also {
        require(fieldValue!!.matches(regex.toRegex())) { "${prepend()} $fieldName must match $regex" }
    }.let { this }

    override fun <K> contains(value: K): Validator<String> = exists().also {
        require(value is Char && fieldValue!!.contains(value)) { "${prepend()} $fieldName must contain $value" }
    }.let { this }
}
