package org.example.HotLoad;

import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.example.HotLoad.GetAllClassNames.getClassNames;

public class HotLoadServer{
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) {
        scheduledExecutorService.scheduleAtFixedRate(HotLoadServer::classLoaderTest, 1, 1, TimeUnit.SECONDS);
    }
    public static void classLoaderTest() {
        try {
            MyClassLoader myClassLoader = new MyClassLoader((System.getProperty("user.dir"))+"/target/classes/");
//            String packageName = "classes"; // 替换为你的包名
//            List<String> classNames = getClassNames(packageName);
//            for (String className : classNames) {
//               String newName = StringUtil.removeLastDot(className).substring(8);
//               Class<?> clazz = myClassLoader.findClass(newName);
//             }
            Class<?> clazz = myClassLoader.findClass("org.example.HotLoad.TestClassImpl");
            System.out.println("加载TestClassImpl热加载成功");
            TestClass testClass = (TestClass) clazz.getConstructor().newInstance();
            testClass.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
