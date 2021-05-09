package com.example.besttodo

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.PresenterTest
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.example.besttodo.data.ResultFailure
import com.example.besttodo.data.ResultSuccess
import com.example.besttodo.data.disk.DiskDataSource
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
class TodosInteractorTest {

    companion object {
        private val TODOS = List(2) {
            DomainTodo(it.toLong(), "Name${it}")
        }
        private val TODO_UI = UiTodo(0, "Name0")
        private val TODO_DOMAIN = DomainTodo(0, "Name0")
        private val FAILURE_REASON = "Something went wrong"
    }

    private lateinit var interactor: TodosInteractor

    @Test
    fun testGetTodosResultSuccess() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.getTodos()) doReturn ResultSuccess(value = TODOS)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.getTodos()
        verify(mockDiskDataSource).getTodos()
    }

    @Test
    fun testGetTodosResultFailed() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.getTodos()) doReturn ResultFailure(reason = FAILURE_REASON)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.getTodos()
        verify(mockDiskDataSource).getTodos()
    }

    @Test
    fun testAddTodoResultSuccess() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.addTodo(TODO_DOMAIN)) doReturn ResultSuccess(Unit)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.addTodo(TODO_DOMAIN)
        verify(mockDiskDataSource).addTodo(TODO_DOMAIN)
    }

    @Test
    fun testAddTodoResultFailed() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.addTodo(TODO_DOMAIN)) doReturn ResultFailure(reason = FAILURE_REASON)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.addTodo(TODO_DOMAIN)
        verify(mockDiskDataSource).addTodo(TODO_DOMAIN)
    }

    @Test
    fun testUpdateTodoResultSuccess() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.updateTodo(TODO_DOMAIN)) doReturn ResultSuccess(Unit)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.updateTodo(TODO_DOMAIN)
        verify(mockDiskDataSource).updateTodo(TODO_DOMAIN)
    }

    @Test
    fun testUpdateTodoResultFailed() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.updateTodo(TODO_DOMAIN)) doReturn ResultFailure(reason = FAILURE_REASON)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.updateTodo(TODO_DOMAIN)
        verify(mockDiskDataSource).updateTodo(TODO_DOMAIN)
    }

    @Test
    fun testDeleteTodoResultSuccess() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.deleteTodo(TODO_DOMAIN)) doReturn ResultSuccess(Unit)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.deleteTodo(TODO_DOMAIN)
        verify(mockDiskDataSource).deleteTodo(TODO_DOMAIN)
    }

    @Test
    fun testDeleteTodoResultFailed() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.deleteTodo(TODO_DOMAIN)) doReturn ResultFailure(reason = FAILURE_REASON)

        interactor = TodosInteractor(mockDiskDataSource)

        //When, Then
        interactor.deleteTodo(TODO_DOMAIN)
        verify(mockDiskDataSource).deleteTodo(TODO_DOMAIN)
    }



}