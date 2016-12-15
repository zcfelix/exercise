package com.thoughtworks.ioc.annotation;

import com.thoughtworks.ioc.container.ClassPathCandidateComponentScanner;
import org.junit.Test;

import java.util.Set;

public class ClassPathCandidateComponentScannerTest {

    @Test
    public void should_get_all_annotation_classes() throws Exception {
        ClassPathCandidateComponentScanner scanner = new ClassPathCandidateComponentScanner();
        String packageName = "com.thoughtworks.ioc.bean";
        Set<Class> set = scanner.findCandidateComponents(packageName);
        System.out.println(set.size());
        for(Class klass : set) {
            System.out.println(klass.getName());
        }
    }
}
