package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Time : 2022/8/9 15:29
 * @Author : 赵浩栋
 * @File : User.java
 * @Software: IntelliJ IDEA
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String name;
    private String password;
    private String email;


}
