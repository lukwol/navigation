package io.github.lukwol.viewmodel

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    private val viewModel = object : ViewModel() {}

    @Test
    fun cancelingViewModelScope() = runTest {
        val result1 = viewModel.viewModelScope.async {
            delay(10L)
            42
        }
        val result2 = viewModel.viewModelScope.async {
            delay(10L)
            "foo"
        }

        viewModel.viewModelScope.cancel()

        assertFailsWith<CancellationException> {
            result1.await()
            result2.await()
        }
    }

    @Test
    fun failingOneOfTheChildCoroutines() = runTest {
        val result1 = viewModel.viewModelScope.async {
            delay(10L)
            42
        }
        val result2 = viewModel.viewModelScope.async {
            throw RuntimeException()
        }

        assertEquals(result1.await(), 42)
        assertFailsWith<RuntimeException> {
            result2.await()
        }
    }

    @Test
    fun completingAllCoroutines() = runTest {
        val result1 = viewModel.viewModelScope.async {
            delay(10L)
            42
        }
        val result2 = viewModel.viewModelScope.async {
            delay(10L)
            "foo"
        }
        assertEquals(result1.await(), 42)
        assertEquals(result2.await(), "foo")
    }
}
