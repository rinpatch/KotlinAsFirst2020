@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    init {
      if (width == 0 || height == 0) throw IllegalArgumentException("Invalid width/height")
    }
    private val list = MutableList (height) { MutableList(width) { e } }

    override fun get(row: Int, column: Int): E = list[row][column]

    override fun get(cell: Cell): E = list[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
      list[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
     list[cell.row][cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
      return if(other is MatrixImpl<*> && other.width == this.width && other.height == this.height) {
        var result = true
        for (i in 0 until height) {
            for(j in 0 until width) {
                if (result) result = (this[i, j] == other[i, j])
            }
        }
        result
      } else false
    }

    override fun toString(): String = list.toString()
}

