package com.treeroot.markdown.server.impl;

import com.treeroot.markdown.utils.FileZipUtil;
import com.treeroot.markdown.vo.SaveFileVo;
import com.treeroot.markdown.entity.FilePath;
import com.treeroot.markdown.server.MarkDownService;
import com.treeroot.markdown.utils.CheckTools;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xugenyin
 */

@Service
public class MarkDownServiceImpl implements MarkDownService {

    private int i = 0;

    @Value("${file.markdown}")
    public String markdownFileRootPath;

    @Value("${file.image}")
    public String imageFileRootPath;

    /**
     * 导入文件夹
     *
     * @param file    文件夹
     * @param path    保存路径
     * @param request 请求
     */
    @Override
    public void importFiles(MultipartFile file, String path, HttpServletRequest request) {
        String originalFilename = file.getOriginalFilename();
        String filePath;
        // 文件保存的位置
        if (CheckTools.isNotNullOrEmpty(path)) {
            filePath = path + "/" + originalFilename;
        } else {
            filePath = markdownFileRootPath + originalFilename;
        }
        Assert.isTrue(!file.isEmpty(), "文件为空");
        try {
            File file1 = new File(filePath);
            if (!file1.exists()) {
                // 是否创建新目录
                file1.mkdirs();
            }
            //写入磁盘
            file.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片
     *
     * @param file    图片
     * @param request 请求 用于获取路径
     * @return 路径
     */
    @Override
    public String importPng(MultipartFile file, HttpServletRequest request) {
        String filePath = "";
        String urlPath = "";
        Assert.isTrue(!file.isEmpty(), "文件为空");
        String originalFilename = file.getOriginalFilename();
        String newFileName = originalFilename;
        filePath = imageFileRootPath + originalFilename;
        try {
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            file.transferTo(file1);
            urlPath = request.getScheme()
                    + "://"
                    + request.getServerName()
                    + ":" + request.getServerPort()
                    + "/image/" + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(urlPath);
        return urlPath;
    }

    /**
     * 递归指定目录下的所有文件夹以及文件给前端
     *
     * @param workSpace  工作目录
     * @param parentPath 父级目录名称
     */
    @Override
    public List<FilePath> recursionFileList(File workSpace, String parentPath) {
        List<FilePath> filePathList = new ArrayList<>();
        File[] files = workSpace.getAbsoluteFile().listFiles();
        assert files != null;
        for (File item : files) {
            FilePath filePathChild = new FilePath();
            String url;
            if (CheckTools.isNotNullOrEmpty(parentPath)) {
                url = "/markdown/" + parentPath + "/" + item.getName();

            } else {
                url = "/markdown/" + item.getName();
            }
            filePathChild.setUrl(url);
            filePathChild.setPath(item.getAbsolutePath());
            filePathChild.setPathName(item.getName());
            filePathChild.setId(++i);
            if (item.isDirectory()) {
                String subDirectory = CheckTools.isNotNullOrEmpty(parentPath) ? parentPath + "/" + item.getName() : item.getName();
                filePathChild.setChildren(recursionFileList(item, subDirectory));
            }
            filePathList.add(filePathChild);
        }
        return filePathList;
    }


    /**
     * 创建文件夹
     *
     * @param name 文件夹名称
     * @param path 指定的路径
     */
    @Override
    public void createMkdir(String name, String path) {
        if (CheckTools.isNotNullOrEmpty(path)) {
            path = path + "/" + name;
        } else {
            path = markdownFileRootPath + "/" + name;
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param name 文件名
     * @param path 路径
     */
    @Override
    public void createFile(String name, String path) {
        if (CheckTools.isNotNullOrEmpty(path)) {
            path = path + "/" + name+ ".md";
        } else {
            path = markdownFileRootPath + "/" + name+ ".md";
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存文件
     *
     * @param saveFileVo 文件路径&文件内容
     */
    @Override
    public void saveFile(SaveFileVo saveFileVo) {
        try {
            // 第二个参数为 true 表示追加到文件末尾而不是覆盖
            FileWriter writer = new FileWriter(saveFileVo.getPath(), false);
            // 写入新内容
            writer.write(saveFileVo.getContent());
            // 关闭文件写入器
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件 或者 文件夹 递归删除 或空文件夹
     *
     * @param path 文件路径
     */
    @Override
    public void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                // 目录存在，需要删除整个目录及其中的所有内容
                deleteDirectory(file);
            } else {
                // 单个文件存在，直接删除文件
                deleteFile(file);
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param directory 文件夹
     */
    private void deleteDirectory(File directory) {
        if (Objects.requireNonNull(directory.list()).length == 0) {
            // 目录为空，直接删除
            directory.delete();
        } else {
            // 遍历目录中的文件和子目录，并递归删除
            File[] contents = directory.listFiles();
            if (contents != null) {
                for (File file : contents) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        deleteFile(file);
                    }
                }
            }
            // 删除完所有内容后，删除空目录本身
            directory.delete();
        }
    }

    /**
     * 删除文件
     *
     * @param file 文件
     */
    private void deleteFile(File file) {
        file.delete();
    }

    @Override
    public void exportFileZip(String path, String name, HttpServletResponse response) {
        if (CheckTools.isNullOrEmpty(path)) {
            path = markdownFileRootPath;
            name = "markdown工作目录.zip";
        }
        FileZipUtil.exportZip(response, path, name);
    }


}
