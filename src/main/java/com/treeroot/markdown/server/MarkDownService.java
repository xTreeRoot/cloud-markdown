package com.treeroot.markdown.server;

import com.treeroot.markdown.vo.SaveFileVo;
import com.treeroot.markdown.entity.FilePath;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author xugenyin
 */
public interface MarkDownService {
    /**
     * 导入文件夹
     *
     * @param file    文件夹
     * @param request 请求
     * @param path    路径
     */
    void importFiles(MultipartFile file,String path, HttpServletRequest request);

    /**
     * 递归指定目录下的所有文件夹以及文件给前端
     *
     * @param workSpace      工作目录
     * @param parentPathName 父级目录名称
     * @return 文件列表
     */
    List<FilePath> recursionFileList(File workSpace, String parentPathName);


    /**
     * 创建文件夹
     *
     * @param name 文件夹名称
     * @param path 路径
     */
    void createMkdir(String name,String path);

    /**
     * 保存文件(覆写文件)
     *
     * @param saveFileVo 文件路径&文件内容
     */
    void saveFile(SaveFileVo saveFileVo);

    /**
     * 在指定目录下新建文件
     * @param name 文件名
     * @param path 路径
     */
    void createFile(String name, String path);

    /**
     *  删除文件
     * @param path 文件路径
     */
    void deleteFile(String path);

    /**
     * 导出文件夹
     * @param path 文件夹路径
     * @param name 文件夹名称
     * @param response 响应
     */
    void exportFileZip(String path,String name,  HttpServletResponse response);

    /**
     * 上传图片
     * @param file 图片
     * @param request 请求
     * @return 图片路径
     */
    String importPng(MultipartFile file, HttpServletRequest request);
}
