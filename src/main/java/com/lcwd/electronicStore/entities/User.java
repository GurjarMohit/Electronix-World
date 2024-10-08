package com.lcwd.electronicStore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User {
    @Id
    private String userId;
    @Column(name = "username")
    private String name;
    @Column(name = "user_email",unique = true)
    private String email;
    @Column(name = "user_password",length = 15)
    private String password;
    private  String gender;
    @Column(length = 1000)
    private  String about;
    @Column(name  ="user_image_name")
    private  String imageName;
}
