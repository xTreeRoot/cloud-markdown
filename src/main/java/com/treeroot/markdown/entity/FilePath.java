package com.treeroot.markdown.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xugenyin
 */
@Data

public class FilePath implements Serializable {
    private Integer id;
    private String path;
    private String pathName;
    private String url;
    List<FilePath> children;

}
