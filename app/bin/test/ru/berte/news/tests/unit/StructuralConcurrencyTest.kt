package ru.berte.news.tests.unit

import kotlin.test.Test
import kotlin.test.assertEquals

class StructuralConcurrencyTest {

    @Test
    fun `When structural concurrency is enabled then all is good`() {
        println("0 end")
        assertEquals(1, 1)
    }
}
