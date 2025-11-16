package ru.kkuzmichev.simpleappforespresso;


import androidx.test.espresso.idling.CountingIdlingResource;

public final class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL_APP_IDLING_RESOURCE";

    private static final CountingIdlingResource countingIdlingResource =
            new CountingIdlingResource(RESOURCE);

    private EspressoIdlingResource() {}

    public static CountingIdlingResource getIdlingResource() {
        return countingIdlingResource;
    }

    public static void increment() {
        countingIdlingResource.increment();
    }

    public static void decrement() {
        if (!countingIdlingResource.isIdleNow()) {
            countingIdlingResource.decrement();
        }
    }
}
