package com.wxs.ckeditor.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>FileInfo</p>
 *
 * @author Joe Yi
 */
@Getter
@Setter
public class FileInfo {
    private int width = 0;
    private int height = 0;
    private String mime = "";
    private String fileName = "";
    private long fileSize = 0;
    private String oldName = "";
    private String fullPath = "";
}
