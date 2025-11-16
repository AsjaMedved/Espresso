package ru.kkuzmichev.simpleappforespresso.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.recyclerview.widget.RecyclerView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class TestUtils {

    private TestUtils() {}

    public static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup &&
                        parentMatcher.matches(parent) &&
                        view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
    public static Matcher<View> recyclerViewSizeMatcher(final int expectedSize) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("RecyclerView should have " + expectedSize + " items");
            }

            @Override
            protected boolean matchesSafely(View view) {
                if (!(view instanceof RecyclerView)) return false;
                RecyclerView recyclerView = (RecyclerView) view;
                return recyclerView.getAdapter() != null &&
                        recyclerView.getAdapter().getItemCount() == expectedSize;
            }
        };
    }
}


