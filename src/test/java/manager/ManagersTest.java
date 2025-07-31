import org.testng.annotations.Test;
import ru.yandex.javacourse.manager.HistoryManager;
import ru.yandex.javacourse.manager.Managers;
import ru.yandex.javacourse.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

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