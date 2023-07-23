package utility

import java.lang.StackWalker.getInstance
import kotlin.reflect.KClass

interface Validator<T> {
    fun exists(): Validator<T>
    fun exact(value: T): Validator<T>
    fun lessThan(limit: Int): Validator<T>
    fun greaterThan(limit: Int): Validator<T>
    fun <K: Enum<K>> tryParse(clazz: KClass<K>): Validator<T>
    fun <K> contains(value: K): Validator<T>
    fun regex(regex: String): Validator<T>
    fun prepend(): String = getInstance().walk { it.skip(1).findFirst().get() }
        .let { "[${it.className}.${it.methodName}:${it.lineNumber}]" }
}
