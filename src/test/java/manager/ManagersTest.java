//import org.testng.annotations.Test;
import main.java.ru.yandex.javacourse.manager.HistoryManager;
import main.java.ru.yandex.javacourse.manager.Managers;
import main.java.ru.yandex.javacourse.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ManagersTest {

    @Test
    void getDefaultReturnsTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager);
    }

    @Test
    void getDefaultHistoryReturnsHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }
}