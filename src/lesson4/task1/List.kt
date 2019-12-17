@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.*
import kotlin.text.StringBuilder


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumByDouble { it * it })


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = if (list.isEmpty()) list else {
    val mid = mean(list)
    for (i in 0 until list.size) list[i] -= mid
    list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in a.indices) result += a[i] * b[i]
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    for (i in p.indices) result += p[i] * x.toDouble().pow(i).toInt()
    return result
}


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = if (list.isEmpty()) list else {
    var sum = list.first()
    for (i in 1 until list.size) {
        sum += list[i]
        list[i] = sum
    }
    list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var varN = n
    val res = mutableListOf<Int>()
    var i = 2
    while (varN != 1) {
        if (i > ceil(sqrt(varN.toDouble()))) {
            res += varN
            break
        }
        while (varN % i != 0) i++
        res += i
        varN /= i
    }
    return res
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var varN = n
    val list = mutableListOf<Int>()
    if (n == 0) return listOf(0)
    while (varN > 0) {
        list += varN % base
        varN /= base
    }
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val abc = "0123456789abcdefghijklmnopqrstuvwxyz"
    val result = StringBuilder()
    val list = convert(n, base)
    for (element in list) result.append(abc[element])
    return result.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for (i in digits.indices) {
        result += digits[i] * base.toDouble().pow(digits.size - 1 - i).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    for (element in str)
        list += if (element <= '9') (element - '0')
        else element - 'a' + 10
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var varN = n
    val list = listOf<String>("I", "V", "X", "L", "C", "D", "M")
    var d = 100
    var j = 6
    val result = StringBuilder()
    for (i in 1..varN / 1000) result.append("M")
    varN %= 1000
    while (varN > 0) {
        when (varN / d) {
            1 -> result.append(list[j - 2])
            2 -> result.append(list[j - 2] + list[j - 2])
            3 -> result.append(list[j - 2] + list[j - 2] + list[j - 2])
            4 -> result.append(list[j - 2] + list[j - 1])
            5 -> result.append(list[j - 1])
            6 -> result.append(list[j - 1] + list[j - 2])
            7 -> result.append(list[j - 1] + list[j - 2] + list[j - 2])
            8 -> result.append(list[j - 1] + list[j - 2] + list[j - 2] + list[j - 2])
            9 -> result.append(list[j - 2] + list[j])
            else -> result.append("")
        }
        j -= 2
        varN %= d
        d /= 10
    }
    return result.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var varM = 1000
    var varN = n / 1000
    val result = StringBuilder()
    fun threeDigit(m: Int): String {
        val res = StringBuilder()
        var nVar = m / 100
        res.append(
            when (nVar) {
                1 -> "сто "
                2 -> "двести "
                3 -> "триста "
                4 -> "четыреста "
                5 -> "пятьсот "
                6 -> "шестьсот "
                7 -> "семьсот "
                8 -> "восемьсот "
                9 -> "девятьсот "
                else -> ""
            }
        )
        nVar = m % 100
        res.append(
            when (nVar / 10) {
                1 -> when (nVar) {
                    10 -> "десять "
                    11 -> "одиннадцать "
                    12 -> "двенадцать "
                    13 -> "тринадцать "
                    14 -> "четырнадцать "
                    15 -> "пятнадцать "
                    16 -> "шестнадцать "
                    17 -> "семнадцать "
                    18 -> "восемнадцать "
                    else -> "девятнадцать "
                }
                2 -> "двадцать "
                3 -> "тридцать "
                4 -> "сорок "
                5 -> "пятьдесят "
                6 -> "шестьдесят "
                7 -> "семьдесят "
                8 -> "восемьдесят "
                9 -> "девяносто "
                else -> ""
            }
        )
        if (nVar / 10 != 1) res.append(
            when (m % 10) {
                1 -> {
                    if (varM == 1000) "одна "
                    else "один "
                }
                2 -> {
                    if (varM == 1000) "две "
                    else "два "
                }
                3 -> "три "
                4 -> "четыре "
                5 -> "пять "
                6 -> "шесть "
                7 -> "семь "
                8 -> "восемь "
                9 -> "девять "
                else -> ""
            }
        )
        return res.toString()
    }
    if (n == 0) return "ноль"
    if (varN != 0)
    result.append(threeDigit(varN) + when (varN % 10) {
                    1 -> "тысяча "
                    2 -> "тысячи "
                    3 -> "тысячи "
                    4 -> "тысячи "
                    else -> "тысяч "
                }
            )
    varM = 1
    varN = n % 1000
    result.append(threeDigit(varN))
    return result.toString().trim()
}