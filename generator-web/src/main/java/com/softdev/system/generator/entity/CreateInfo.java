package com.softdev.system.generator.entity;

import lombok.Data;

/**
 * @author huangph 2018-10-26 14:48:04
 */
@Data
public class CreateInfo {
    private ClassInfo classInfo;
    private String authorName;
	private String packageName;
}