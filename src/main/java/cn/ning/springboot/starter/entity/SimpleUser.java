package cn.ning.springboot.starter.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class SimpleUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String passwordMd5;

    private String salt;
}