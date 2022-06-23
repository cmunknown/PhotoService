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
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable=false)
    private String title;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User userAlbum;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "album_image",
            joinColumns = {@JoinColumn(name = "id_album", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_image", referencedColumnName = "id")})
    private List<Image> images;
}
