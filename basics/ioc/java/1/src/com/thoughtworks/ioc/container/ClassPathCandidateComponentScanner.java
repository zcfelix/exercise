package com.thoughtworks.ioc.container;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.logging.Logger;

public class ClassPathCandidateComponentScanner {
    private static final Logger logger = Logger.getLogger("ClassPathCandidateComponentScanner");
    private Set<Class> classes = new LinkedHashSet<>();

    public Set<Class> findCandidateComponents(String packageName) throws IOException, ClassNotFoundException {
        String path = packageName.replace(".", "/");

        Enumeration<URL> dirs;
        dirs = Thread.currentThread().getContextClassLoader().getResources(path);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                logger.info("扫描file类型的class文件...");
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                scanPackageClassesByFile(filePath, packageName);
            }
        }
        return classes;
    }

    private void scanPackageClassesByFile(String filePath, String packageName) throws ClassNotFoundException {
        File dir = new File(filePath);
        File[] dirFiles = dir.listFiles();
        for (File file : dirFiles) {
            String className = file.getName().substring(0, file.getName().length() - 6);
            classes.add(Class.forName(packageName + "." + className));
        }
    }
}
