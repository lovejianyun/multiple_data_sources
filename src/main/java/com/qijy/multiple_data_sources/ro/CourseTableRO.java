package com.qijy.multiple_data_sources.ro;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author qijy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/18 14:20
 */
@TableName("course_table")
public class CourseTableRO {
    private Integer id;
    private String coursename;
    private String coursetype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }
}
