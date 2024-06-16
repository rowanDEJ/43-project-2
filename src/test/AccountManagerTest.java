import com.assistant.aiassistant.AccountManager;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountManagerTest {// MC/DC for login method

    public AccountManager accountManager = new AccountManager();

    @Test
    public void testLoginMCDC() {
        // T, T -> should return true
        assertTrue(accountManager.login("t@t.tt", "test"));

        // T, F -> should return false
        accountManager.logout();
        assertFalse(accountManager.login("t@t.tt", "wrongtest"));

        // F, X -> should return false
        accountManager.logout();
        assertFalse(accountManager.login("wrongt@t.tt", "test"));

        // F, F -> should return false
        accountManager.logout();
        assertFalse(accountManager.login("wrongt@t.tt", "wrongtest"));
    }

    // MC/DC for checkIfUserWithUsernameExists method
    @Test
    public void testCheckIfUserWithUsernameExistsMCDC() {

        // T -> should return true
        assertTrue(accountManager.checkIfUserWithUsernameExists("MeneerMart"));

        // F -> should return false
        assertFalse(accountManager.checkIfUserWithUsernameExists("NietMeneerMart"));
    }

    // MC/DC for checkIfUserWithEmailExists method
    @Test
    public void testCheckIfUserWithEmailExistsMCDC() {
        // T -> should return true
        assertTrue(accountManager.checkIfUserWithEmailExists("t@t.tt"));

        // F -> should return false
        assertFalse(accountManager.checkIfUserWithEmailExists("wrongt@t.tt"));
    }
}
