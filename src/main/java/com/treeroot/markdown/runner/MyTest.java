package com.treeroot.markdown.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xugenyin
 */
@Component
public class MyTest implements ApplicationRunner {

    @Value("${file.markdown}")
    public String markdownFileRootPath;

    @Value("${file.image}")
    public String imageFileRootPath;


    @Override
    public void run(ApplicationArguments args) {
        System.out.println("markdown工作空间：" + markdownFileRootPath);
        System.out.println("image工作空间：" + imageFileRootPath);

    }
}
