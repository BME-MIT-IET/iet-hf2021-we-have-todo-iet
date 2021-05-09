package com.example.besttodo

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.PresenterTest
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.example.besttodo.data.ResultFailure
import com.example.besttodo.data.ResultSuccess
import com.example.besttodo.domain.TodosInteractor
import com.example.besttodo.domain.models.DomainTodo
import com.example.besttodo.ui.todos.*
import com.example.besttodo.ui.todos.models.UiTodo
import com.example.besttodo.utils.ResourcesHelper
import kotlinx.coroutines.test.runBlockingTest
import net.bytebuddy.dynamic.DynamicType
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.mockito.verification.VerificationMode

@RunWith(MockitoJUnitRunner::class)
class TodosPresenterTest : PresenterTest() {

    companion object {
        private val TODOS = List(2) {
            DomainTodo(it.toLong(), "Name${it}")
        }
        private val TODO_UI = UiTodo(0, "Name0")
        private val TODO_DOMAIN = DomainTodo(0, "Name0")
        private val FAILURE_REASON = "Something went wrong"
    }

    private lateinit var presenter: TodosPresenter

    @Test
    fun testGetTodosResultSuccess() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.getTodos()) doReturn ResultSuccess(value = TODOS)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.getTodos()
        verify(mockTodosInteractor).getTodos()
    }

    @Test
    fun testGetTodosResultFailed() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.getTodos()) doReturn ResultFailure(reason = FAILURE_REASON)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.getTodos()
        verify(mockTodosInteractor).getTodos()
    }

    @Test
    fun testAddTodoResultSuccess() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.addTodo(TODO_DOMAIN)) doReturn ResultSuccess(Unit)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.addTodo(TODO_UI)
        verify(mockTodosInteractor).addTodo(TODO_DOMAIN)
    }

    @Test
    fun testAddTodoResultFailed() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.addTodo(TODO_DOMAIN)) doReturn ResultFailure(reason = FAILURE_REASON)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.addTodo(TODO_UI)
        verify(mockTodosInteractor).addTodo(TODO_DOMAIN)
    }

    @Test
    fun testUpdateTodoResultSuccess() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.updateTodo(TODO_DOMAIN)) doReturn ResultSuccess(Unit)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.updateTodo(TODO_UI)
        verify(mockTodosInteractor).updateTodo(TODO_DOMAIN)
    }

    @Test
    fun testUpdateTodoResultFailed() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.updateTodo(TODO_DOMAIN)) doReturn ResultFailure(reason = FAILURE_REASON)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.updateTodo(TODO_UI)
        verify(mockTodosInteractor).updateTodo(TODO_DOMAIN)
    }

    @Test
    fun testDeleteTodoResultSuccess() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.deleteTodo(TODO_DOMAIN)) doReturn ResultSuccess(Unit)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.deleteTodo(TODO_UI)
        verify(mockTodosInteractor).deleteTodo(TODO_DOMAIN)
    }

    @Test
    fun testDeleteTodoResultFailed() = runBlockingTest {
        // Given
        val mockTodosInteractor = mock<TodosInteractor>()
        whenever(mockTodosInteractor.deleteTodo(TODO_DOMAIN)) doReturn ResultFailure(reason = FAILURE_REASON)

        presenter = TodosPresenter(mockTodosInteractor)

        //When, Then
        presenter.deleteTodo(TODO_UI)
        verify(mockTodosInteractor).deleteTodo(TODO_DOMAIN)
    }



}