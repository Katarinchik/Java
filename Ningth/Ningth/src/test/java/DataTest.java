import junit.framework.TestCase;

/**
 * class for test class DataBase
 */
public class DataTest extends TestCase {
    private Data dataBase = new Data();

    public void testLoadFile() {
        assertEquals(10,dataBase.LoadFile(new Window().data));
    }

    public void testSaveFile() {
        assertEquals(true,dataBase.SaveFile(new Window().data));
    }
}