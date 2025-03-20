package ul.ed5042_lab2

import org.junit.Test

import org.junit.Assert.*
import ul.ed5042_lab2.model.Module
import ul.ed5042_lab2.ui.calculateQca

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class QcaScreenUnitTest {
    @Test

    fun qca_zero_for_invalid_grade() {
        assertEquals("0.00", calculateQca(listOf(
            Module(
            grade = "a1",
            weight = 6,
            name = "",
            code = ""),
            Module(
                grade = "b1",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_zero_for_invalid_weight() {
        assertEquals("0.00", calculateQca(listOf(
            Module(
                grade = "A1",
                weight = 0,
                name = "",
                code = ""),
            Module(
                grade = "B1",
                weight = 0,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_all_A1() {
        assertEquals("4.00", calculateQca(listOf(
            Module(
                grade = "A1",
                weight = 6,
                name = "",
                code = ""),
            Module(
                grade = "A1",
                weight = 6,
                name = "",
                code = ""),
            Module(
                grade = "A1",
                weight = 6,
                name = "",
                code = ""),
            Module(
                grade = "A1",
                weight = 6,
                name = "",
                code = ""),
            Module(
                grade = "A1",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_A1() {
        assertEquals("4.00", calculateQca(listOf(
            Module(
            grade = "A1",
            weight = 6,
            name = "",
            code = ""))))
    }

    @Test
    fun qca_valid_one_A2() {
        assertEquals("3.60", calculateQca(listOf(
            Module(
            grade = "A2",
            weight = 6,
            name = "",
            code = ""))))
    }

    @Test
    fun qca_valid_one_B1() {
        assertEquals("3.20", calculateQca(listOf(
            Module(
            grade = "B1",
            weight = 6,
            name = "",
            code = ""))))
    }

    @Test
    fun qca_valid_one_B2() {
        assertEquals("3.00", calculateQca(listOf(
            Module(
                grade = "B2",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_B3() {
        assertEquals("2.80", calculateQca(listOf(
            Module(
                grade = "B3",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_C1() {
        assertEquals("2.60", calculateQca(listOf(
            Module(
                grade = "C1",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_C2() {
        assertEquals("2.40", calculateQca(listOf(
            Module(
                grade = "C2",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_C3() {
        assertEquals("2.00", calculateQca(listOf(
            Module(
                grade = "C3",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_D1() {
        assertEquals("1.60", calculateQca(listOf(
            Module(
                grade = "D1",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_D2() {
        assertEquals("1.20", calculateQca(listOf(
            Module(
                grade = "D2",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_valid_one_F() {
        assertEquals("0.00", calculateQca(listOf(
            Module(
                grade = "F",
                weight = 6,
                name = "",
                code = ""))))
    }

    @Test
    fun qca_weight1_2_correct() {
        assertEquals("2.47", calculateQca(listOf(
            Module(
                grade = "A1",
                weight = 9,
                name = "",
                code = ""),
            Module(
                grade = "A2",
                weight = 3,
                name = "",
                code = ""),
            Module(
                grade = "B1",
                weight = 5,
                name = "",
                code = ""),
            Module(
                grade = "B2",
                weight = 3,
                name = "",
                code = ""),
            Module(
                grade = "C1",
                weight = 4,
                name = "",
                code = ""),
            Module(
                grade = "F",
                weight = 3,
                name = "",
                code = ""),
            Module(
                grade = "C2",
                weight = 9,
                name = "",
                code = ""),
            Module(
                grade = "F",
                weight = 6,
                name = "",
                code = "")
            )))
    }


}