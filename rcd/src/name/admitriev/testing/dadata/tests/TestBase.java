package name.admitriev.testing.dadata.tests;

import name.admitriev.testing.dadata.framework.AppManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class TestBase {

    public AppManager app;

    @BeforeClass
    public void setUp() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("dadata.properties"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        app = new AppManager(properties);
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }

    public static Iterator<Object[]> wrapListForDataProvider(List<?> list) {
        List<Object[]> result = new ArrayList<Object[]>();
        for (Object login: list) {
            result.add(new Object[]{login});
        }
        return result.iterator();
    }


}
