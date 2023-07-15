package com.treeroot.markdown.controller;

import com.treeroot.markdown.vo.SaveFileVo;
import com.treeroot.markdown.entity.FilePath;
import com.treeroot.markdown.server.MarkDownService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author xugenyin
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/markdown")
public class MarkdownController {

    @Value("${file.markdown}")
    public String markdownFileRootPath;
    @Resource
    private MarkDownService markDownService;

    /**
     * 导入文件夹到工作目录，可当创建文件到指定目录
     *
     * @param file 文件夹
     */
    @PostMapping("/import-markdown")
    public void importMarkdown(MultipartFile file,
                               @RequestParam(value = "path", required = false) String path,
                               HttpServletRequest request) {
        markDownService.importFiles(file, path, request);
    }


    /**
     * 导入文件夹到工作目录，可当创建文件到指定目录
     *
     * @param file 文件夹
     */
    @PostMapping("/import-image")
    public String importPng(MultipartFile file,
                            @RequestParam(value = "path", required = false) String path,
                            HttpServletRequest request) {
        return markDownService.importPng(file,  request);
    }

    /**
     * 递归指定目录下的所有文件夹以及文件给前端
     *
     * @return 文件列表
     */
    @GetMapping("/file-list")
    public List<FilePath> getFileList() {
        return markDownService.recursionFileList(new File(markdownFileRootPath), null);
    }

    /**
     * 创建文件夹
     *
     * @param name 文件夹名称
     * @param path 路径
     */
    @PostMapping("/create-mkdir")
    public void createMkdir(@RequestParam(value = "name") String name,
                            @RequestParam(value = "path", required = false) String path) {
        markDownService.createMkdir(name, path);
    }

    /**
     * 在指定目录下新建文件
     *
     * @param name 文件名
     * @param path 路径
     */
    @PostMapping("/create-file")
    public void createFile(@RequestParam(value = "name") String name,
                           @RequestParam(value = "path", required = false) String path) {
        markDownService.createFile(name, path);
    }

    /**
     * 保存文件(覆写文件)
     *
     * @param saveFileVo 文件路径&文件内容
     */
    @PostMapping("/save-file")
    public void saveFile(@RequestBody SaveFileVo saveFileVo) {
        markDownService.saveFile(saveFileVo);
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     */
    @DeleteMapping("/delete-file")
    public void deleteFile(@RequestParam(value = "path") String path) {
        markDownService.deleteFile(path);
    }

    /**
     * 导出文件夹压缩包
     *
     * @param name     文件夹名称
     * @param path     路径
     * @param response 响应
     */
    @GetMapping("/export")
    public void exportFileZip(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "path", required = false) String path,
                              HttpServletResponse response) {
        markDownService.exportFileZip(path, name, response);
    }



}
