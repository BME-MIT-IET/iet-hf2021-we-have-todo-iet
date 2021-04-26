package com.example.besttodo

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.besttodo.ui.todos.dialogfragments.AddTodoBottomSheetDialogFragment
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddTodoBottomSheetDialogFragmentTest {

    private lateinit var scenario: FragmentScenario<AddTodoBottomSheetDialogFragment>

    @Before
    fun initFragment() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_BestTodo) {
            AddTodoBottomSheetDialogFragment()
        }
    }


    @Test
    fun whenAddTodoButtonClickedWithEmptyEditText_thenDismissDoesNotHappen() {
        // When
        onView(allOf(withId(R.id.btnAdd), withText(R.string.btn_add))).perform(click())

        // Then
        onView(withId(R.id.scrollViewAddTodo)).check(matches(isDisplayed()))
    }

    @Test
    fun whenAddTodoButtonClickedWithEmptyEditText_thenEditTextHasErrorText() {
        // Given
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // When
        onView(allOf(withId(R.id.btnAdd), withText(R.string.btn_add))).perform(click())

        // Then
        onView(withId(R.id.etName))
                .check(matches(hasErrorText(appContext.getString(R.string.error_cant_be_empty))))
    }

    @Test
    fun whenAddTodoButtonClickedWithNotEmptyEditText_thenDismissHappens() {
        // When
        onView(withId(R.id.etName)).perform(typeText("Name"))
        onView(isRoot()).perform(closeSoftKeyboard())
        onView(allOf(withId(R.id.btnAdd), withText(R.string.btn_add))).perform(click())

        // Then
        onView(withId(R.id.scrollViewAddTodo)).check(doesNotExist())
    }


}