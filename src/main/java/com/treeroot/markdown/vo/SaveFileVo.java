package com.treeroot.markdown.vo;


import lombok.Data;

/**
 * @author xugenyin
 */
@Data
public class SaveFileVo {
    /**
     * 位置
     */
    String path;
    /**
     * 新的内容
     */
    String content;
}
