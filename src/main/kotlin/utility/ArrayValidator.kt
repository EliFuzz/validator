package utility

import kotlin.reflect.KClass

inline fun <reified T> T.validate(fieldName: String, fieldValue: Iterable<*>?): Validator<T> = object: Validator<T> {
    override fun exists(): Validator<T> =
        require(!fieldValue?.toList().isNullOrEmpty()) { "${prepend()} $fieldName must not be empty" }.let { this }

    override fun exact(value: T): Validator<T> = TODO("Not yet implemented")

    override fun lessThan(limit: Int): Validator<T> = TODO("Not yet implemented")

    override fun greaterThan(limit: Int): Validator<T> = TODO("Not yet implemented")

    override fun <K: Enum<K>> tryParse(clazz: KClass<K>): Validator<T> = TODO("Not yet implemented")

    override fun regex(regex: String): Validator<T> = TODO("Not yet implemented")

    override fun <K> contains(value: K): Validator<T> = TODO("Not yet implemented")
}
