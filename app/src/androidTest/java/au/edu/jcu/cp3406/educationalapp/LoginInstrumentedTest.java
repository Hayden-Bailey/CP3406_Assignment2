package au.edu.jcu.cp3406.educationalapp;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {
    private LoginDatabaseHelper loginDatabaseHelper;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        loginDatabaseHelper = new LoginDatabaseHelper(context);
    }

    @After
    public void closeDB() {
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("au.edu.jcu.cp3406.educationalapp", appContext.getPackageName());
    }

    @Test
    public void loginEmailExists() {

        loginDatabaseHelper.addLoginDetails("example@example.com", "example");

        assertTrue(loginDatabaseHelper.checkEmails("example@example.com"));
        assertFalse(loginDatabaseHelper.checkEmails("test@example.com"));

    }
}