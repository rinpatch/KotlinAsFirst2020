@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.lang.IllegalArgumentException
import kotlin.time.toDuration

val complexRegex = "^(\\d*)(?:([\\+-])(\\d*)i)?\$".toRegex()

 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) {
        val matchResult = complexRegex.find(s)
        return if (matchResult != null) {
            val match = matchResult.destructured
            val real = match.component1().toIntOrNull()
                ?: throw IllegalArgumentException("Invalid format")
            val imaginary = if (match.component2() != "" && match.component3() != "") {
                val multiplier = when (match.component2()) {
                    "+" -> 1
                    else -> -1
                }
                val rawImaginary = match.component3().toIntOrNull() ?: throw IllegalArgumentException("Invalid format")
                multiplier * rawImaginary
            } else 0.0
            this(real.toDouble(), imaginary.toDouble())
        } else throw IllegalArgumentException("Invalid format")
    }

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(this.re + other.re, this.im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-1 * this.re, -1 * this.im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(this.re - other.re, this.im - other.im)

    /**
     * Умножение
     * Формула из https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BC%D0%BF%D0%BB%D0%B5%D0%BA%D1%81%D0%BD%D0%BE%D0%B5_%D1%87%D0%B8%D1%81%D0%BB%D0%BE
     */
    operator fun times(other: Complex): Complex =
        Complex(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re)

    /**
     * Деление
     * Формула из https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BC%D0%BF%D0%BB%D0%B5%D0%BA%D1%81%D0%BD%D0%BE%D0%B5_%D1%87%D0%B8%D1%81%D0%BB%D0%BE
     */
    operator fun div(other: Complex): Complex = Complex(
        (this.re * other.re + this.im * other.im) / (other.re * other.re + other.im * other.im),
        (this.im * other.re - this.re * other.im) / (other.re * other.re + other.im * other.im)
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        return if (other is Complex) {
            this.re == other.re && this.im == other.im
        } else {
            false
        }
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = "${this.re}+${this.im}i"
}
