package org.example.HotLoad;

public class LoadInfo {
    //自定义的类加载器
    private MyClassLoader myClassLoader;

    //记录要加载的类的时间戳 --加载的时间
    private long loadTime;

    private BaseManager baseManager;

    public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }

    public void setBaseManager(BaseManager baseManager) {
        this.baseManager = baseManager;
    }

    public MyClassLoader getMyClassLoader() {
        return myClassLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public BaseManager getBaseManager() {
        return baseManager;
    }

}
