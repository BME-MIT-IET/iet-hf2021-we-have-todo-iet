package com.example.besttodo

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.besttodo.core.di.TestDataModule
import com.example.besttodo.ui.todos.TodosFragment
import com.example.besttodo.ui.todos.models.UiTodo
import com.example.besttodo.ui.todos.recyclerview.TodosRecyclerViewAdapter
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TodosFragmentTest {

    private val listItemNumberInTest = 2

    private val todoInTest = UiTodo(2, "Name2")

    private lateinit var navController: TestNavHostController

    private lateinit var scenario: FragmentScenario<TodosFragment>

    // This warning is suppressed, because if lambda is used instead of anonymous object,
    // then that causes compilation errors
    @Suppress("ObjectLiteralToLambda")
    @Before
    fun initFragment() {
        TestDataModule.todosList = MutableList(listItemNumberInTest + 2) {
            UiTodo(it.toLong(), "Name${it}")
        }.apply {
            this[listItemNumberInTest] = todoInTest
        }

        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_BestTodo
        ) {
            TodosFragment()
        }

        scenario.onFragment(object: FragmentScenario.FragmentAction<TodosFragment>{
            override fun perform(fragment: TodosFragment) {
                navController.setGraph(R.navigation.mobile_navigation)
                Navigation.setViewNavController(fragment.requireView(), navController)
                navController.setCurrentDestination(R.id.navigation_home)
            }

        })
    }

    @Test
    fun verifyUI() {
        // Then
        onView(withId(R.id.clTodos)).check(matches(isDisplayed()))
        onView(withId(R.id.fabCreateTodo)).check(matches(isClickable()))
    }

    @Test
    fun whenCreateTodoButtonClicked_thenAddTodoBottomSheetDialogFragmentVisible() {
        // When
        onView(withId(R.id.fabCreateTodo)).perform(click())

        // Then
        onView(withId(R.id.scrollViewAddTodo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenTodoClicked_thenDialogFragmentWithTheNameOfTheTodoVisible() {
        // When
        onView(withId(R.id.rvTodos))
            .perform(actionOnItemAtPosition<TodosRecyclerViewAdapter.ViewHolder>(
                listItemNumberInTest, click()
            ))

        // Then
        onView(withText(todoInTest.name)).check(matches(isDisplayed()))
    }

    @Test
    fun whenTodoLongClicked_thenAlertDialogVisible() {
        // When
        onView(withId(R.id.rvTodos))
            .perform(actionOnItemAtPosition<TodosRecyclerViewAdapter.ViewHolder>(
                listItemNumberInTest, longClick()
            ))

        // Then
        onView(withText(R.string.title_warning))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenTodoDeleted_thenNotDisplayed() {
        // When
        onView(withId(R.id.rvTodos))
            .perform(actionOnItemAtPosition<TodosRecyclerViewAdapter.ViewHolder>(
                listItemNumberInTest, longClick()
            ))
        onView(withText(R.string.btn_yes)).perform(click())

        // Then
        onView(withId(R.id.rvTodos))
            .check(matches(
                not(hasDescendant(allOf(withId(R.id.tvWorkoutName), withText(todoInTest.name))))))
    }

}