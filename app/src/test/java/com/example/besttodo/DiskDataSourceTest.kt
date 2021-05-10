package com.example.besttodo

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.PresenterTest
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.example.besttodo.data.ResultFailure
import com.example.besttodo.data.ResultSuccess
import com.example.besttodo.data.disk.DiskDataSource
import com.example.besttodo.data.disk.DiskDataSource_Factory
import com.example.besttodo.data.disk.TodoDao
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
import javax.sql.DataSource

@RunWith(MockitoJUnitRunner::class)
class DiskDataSourceTest {

    companion object {
        private val TODOS = List(2) {
            DomainTodo(it.toLong(), "Name${it}")
        }
        private val TODO_UI = UiTodo(0, "Name0")
        private val TODO_DOMAIN = DomainTodo(0, "Name0")
        private val FAILURE_REASON = "Something went wrong"
    }

    private lateinit var diskDataSource: DiskDataSource

    @Test
    fun testGetTodosResultSuccess() = runBlockingTest {
        // Given
        val mockDiskDataSource = mock<DiskDataSource>()
        whenever(mockDiskDataSource.getTodos()) doReturn ResultSuccess(value = TODOS)


        diskDataSource = DiskDataSource()

        //When, Then
        diskDataSource.getTodos()
        verify(diskDataSource).getTodos()
    }


}