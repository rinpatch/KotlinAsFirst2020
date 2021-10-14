@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt
import kotlin.math.min
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.abs
import kotlin.math.PI

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0) return 1
    var nWritable = n
    var numDigits = 0
    while (nWritable != 0) {
        nWritable = nWritable / 10
        numDigits += 1
    }
    return numDigits
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n in 1..2) return 1
    val buf = arrayOf(1, 1)
    var number = 0
    for (i in 3..n) {
       number = buf[0] + buf[1]
       buf[1] = buf[0]
       buf[0] = number
    }
    return number
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in (n-1) downTo 1) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var xW = x
    var steps = 0 
    while (xW != 1) {
        xW = when {
            xW % 2 == 0 -> xW / 2
            else -> 3 * xW + 1
        }
        steps = steps + 1
    }
    return steps
} 

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    if (max(m,n) % min(m,n) == 0) return max(m,n)
    var k = max(m, n) * 2
    while (k % m != 0 || k % n != 0) {
        k = k + max(m,n)
    }
    return k
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    // Алгоритм Евклида
    // https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%95%D0%B2%D0%BA%D0%BB%D0%B8%D0%B4%D0%B0#%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%95%D0%B2%D0%BA%D0%BB%D0%B8%D0%B4%D0%B0_%D0%B4%D0%BB%D1%8F_%D1%86%D0%B5%D0%BB%D1%8B%D1%85_%D1%87%D0%B8%D1%81%D0%B5%D0%BB
    val first = max(m,n)
    var previous = min(m, n)
    if ((first % previous == 0) && previous != 1) return false
    var remainder = first % previous
    while (remainder != 0) {
        if (previous % remainder == 0) {
            return remainder == 1
            } else {
            val lastRemainder = remainder
            remainder = previous % remainder
            previous = lastRemainder
        }
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    val digitCount = digitNumber(n)
    var result = 0
    for (i in (digitCount - 1) downTo 0) {
        val digit = n % (10.0).pow(digitCount - i).toInt() / (10.0).pow(digitCount - i - 1).toInt()
        result += digit * (10.0).pow(i).toInt()
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val digitCount = digitNumber(n)
    if (digitCount == 1) return true
    var leftSide = n / (10.0).pow(digitCount / 2 + digitCount % 2).toInt()
    // Zeroes at the start of right side disappear when the number is reverted,
    // so let's remove zeroes from the end of left side before comparing
    while (leftSide % 10 == 0) {
        leftSide /= 10
    }
    val rightSide = n % (10.0).pow(digitCount / 2).toInt()
    return leftSide == revert(rightSide)
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var lastDigit = - 1
    for (i in digitNumber(n) downTo 1) {
        val currentDigit = n % 10.0.pow(i).toInt() / 10.0.pow(i - 1).toInt()
        when(lastDigit) {
            -1, currentDigit -> lastDigit = currentDigit
            else -> return true
        }
    }
    return false
}

fun unbloatX(x: Double) : Double = x % (PI * 2)

fun countSinCosSeq(x: Double, eps: Double, numStart: Int) : Double {
    var result = 0.0
    var sub = true
    var num = numStart
    while (true) {
        val factorial = factorial(num)
        var seqMember = x.pow(num) / factorial
        result = if (sub) result - seqMember else result + seqMember
        sub = !sub
        num = num + 2
        if (abs(seqMember) < abs(eps)) break
    }
    return result

}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val shortX = unbloatX(x)
    return shortX + countSinCosSeq(shortX, eps, 3)
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val shortX = unbloatX(x)
    return 1 + countSinCosSeq(shortX, eps, 2)
}

fun sequenceDigit(n: Int, generator: (n: Int) -> Int) : Int {
    var digitsLeft = n
    for (i in 1..n) {
        val number = generator(i)
        val digitCount = digitNumber(number)
        if (digitsLeft <= digitCount) {
            val digitIndex = digitCount - digitsLeft + 1
            return number % (10.0).pow(digitIndex).toInt() / (10.0).pow(digitIndex - 1).toInt()
        }
        digitsLeft -= digitCount
    }
    return -1
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = sequenceDigit(n) {it * it}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int =
    // Calling fib over and over is not the most efficient,
    // but it's simpler and it's not like this is production code
    sequenceDigit(n) {fib(it)}
