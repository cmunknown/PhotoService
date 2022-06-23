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
@Table(name = "categories")
public class ImageCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable=false)
    private String title;


    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Image> imageList;


}
