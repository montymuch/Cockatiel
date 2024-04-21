package org.example.HotLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllClassNames {
    public static void main(String[] args) {
        String packageName = "classes"; // 替换为你的包名
        List<String> classNames = getClassNames(packageName);
        for (String className : classNames) {
            System.out.println(className);
        }
    }

    public static List<String> getClassNames(String packageName) {
        List<String> classNames = new ArrayList<>();
        String path = System.getProperty("user.dir") + File.separator + "target" + File.separator + packageName.replace('.', File.separatorChar);
        File directory = new File(path);
        if (directory.exists()) {
            getClassNames(directory, packageName, classNames);
        }
        return classNames;
    }

    private static void getClassNames(File directory, String packageName, List<String> classNames) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getClassNames(file, packageName + "." + file.getName(), classNames);
                } else if (file.getName().endsWith(".class")) {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 5);
                    classNames.add(className);
                }
            }
        }
    }
}
