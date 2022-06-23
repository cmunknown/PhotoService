package com.example.photoservice.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable=false)
    private String username;

    @Column(name = "password", nullable=false)
    private String password;

    @OneToMany(mappedBy = "userAlbum", fetch = FetchType.LAZY)
    private List<Album> albums;

    @OneToMany(mappedBy = "userImages", fetch = FetchType.LAZY)
    private List<Image> images;
}