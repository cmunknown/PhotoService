package com.example.photoservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Component
@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "directory", nullable=false)
    private String directory;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User userImages;

    @ManyToMany(mappedBy = "images", fetch = FetchType.LAZY)
    private List<Album> albums;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_image",
            joinColumns = {@JoinColumn(name = "id_image", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_category", referencedColumnName = "id")})
    private List<ImageCategory> categories;
}