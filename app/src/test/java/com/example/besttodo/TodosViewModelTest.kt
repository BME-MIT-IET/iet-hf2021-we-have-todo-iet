package com.example.besttodo

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.example.besttodo.data.ResultFailure
import com.example.besttodo.data.ResultSuccess
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
class TodosViewModelTest : ViewModelTest() {

    companion object {
        private val TODOS = List(2) {
            UiTodo(it.toLong(), "Name${it}")
        }
        private val TODO = UiTodo(0, "Name0")
        private val FAILURE_REASON = "Something went wrong"
        private const val MOCK_RESOURCES_HELPER_STRING = "MockResourcesHelperString"
    }

    private lateinit var viewModel: TodosViewModel

    private val mockResourcesHelper = mock<ResourcesHelper>()

    @Before
    fun initResourcesHelperMock() {
        whenever(mockResourcesHelper.getString(anyInt())) doReturn MOCK_RESOURCES_HELPER_STRING
    }

    @Test
    fun testLoadTodosResultSuccess() = runBlockingTest {
        // Given
        val mockTodosPresenter = mock<TodosPresenter>()
        whenever(mockTodosPresenter.getTodos()) doReturn ResultSuccess(value = TODOS)

        viewModel = TodosViewModel(mockTodosPresenter, mockResourcesHelper)

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, eventsObserver ->
            viewModel.load()
            stateObserver.assertObserved(Initial, Loading, TodosLoaded(TODOS))
        }
        verify(mockTodosPresenter).getTodos()
    }

    @Test
    fun testLoadTodosResultFailed() = runBlockingTest {
        // Given
        val mockTodosPresenter = mock<TodosPresenter>()
        whenever(mockTodosPresenter.getTodos()) doReturn ResultFailure(reason = FAILURE_REASON)

        viewModel = TodosViewModel(mockTodosPresenter, mockResourcesHelper)

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, eventsObserver ->
            viewModel.load()
            stateObserver.assertObserved(Initial, Loading, Errored)
            eventsObserver.assertObserved(Failed(FAILURE_REASON))
        }
        verify(mockTodosPresenter).getTodos()
    }

    @Test
    fun testAddTodoResultSuccess() = runBlockingTest {
        // Given
        val mockTodosPresenter = mock<TodosPresenter>()
        whenever(mockTodosPresenter.getTodos()) doReturn ResultSuccess(value = TODOS)
        whenever(mockTodosPresenter.addTodo(TODO)) doReturn ResultSuccess(Unit)

        viewModel = TodosViewModel(mockTodosPresenter, mockResourcesHelper)
        viewModel.load()

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, eventsObserver ->
            viewModel.addTodo(TODO)
            stateObserver.assertObserved(TodosLoaded(TODOS), Uploading, Loading, TodosLoaded(TODOS))
            eventsObserver.assertObserved(ActionSuccess(MOCK_RESOURCES_HELPER_STRING))
        }
        verify(mockTodosPresenter).addTodo(TODO)
    }

    @Test
    fun testAddTodoResultFailed() = runBlockingTest {
        // Given
        val mockTodosPresenter = mock<TodosPresenter>()
        whenever(mockTodosPresenter.getTodos()) doReturn ResultSuccess(value = TODOS)
        whenever(mockTodosPresenter.addTodo(TODO)) doReturn ResultFailure(reason = FAILURE_REASON)

        viewModel = TodosViewModel(mockTodosPresenter, mockResourcesHelper)
        viewModel.load()

        //When, Then
        viewModel.observeStateAndEvents { stateObserver, eventsObserver ->
            viewModel.addTodo(TODO)
            stateObserver.assertObserved(TodosLoaded(TODOS), Uploading, Loading, TodosLoaded(TODOS))
            eventsObserver.assertObserved(Failed(FAILURE_REASON))
        }
        verify(mockTodosPresenter).addTodo(TODO)
    }

}