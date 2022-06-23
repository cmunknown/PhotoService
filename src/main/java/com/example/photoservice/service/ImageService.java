package com.example.photoservice.service;


import com.example.photoservice.api.dao.IAlbumDao;
import com.example.photoservice.api.dao.IImageCategoryDao;
import com.example.photoservice.api.dao.IImageDao;
import com.example.photoservice.api.dao.IUserDao;
import com.example.photoservice.api.service.IImageService;
import com.example.photoservice.dto.ImageDto;
import com.example.photoservice.dto.ImageDtoToAlbum;
import com.example.photoservice.exception.IdNotFountException;
import com.example.photoservice.exception.NotSupportedFormat;
import com.example.photoservice.model.Album;
import com.example.photoservice.model.Image;
import com.example.photoservice.model.ImageCategory;
import com.example.photoservice.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final IImageDao imageDao;
    private final ModelMapper modelMapper;
    private final IImageCategoryDao imageCategoryDao;
    private final IAlbumDao albumDao;
    private final IUserDao userDao;

    @SneakyThrows
    @Override
    public byte[] add(MultipartFile file, List<Long> categories, Long userId) {

        if (!file.getOriginalFilename().endsWith(".png")) {
            throw new NotSupportedFormat("Format not correct");
        }

        String fileDirectory = "/home/user/IdeaProjects/PhotoService/photos/";
        UUID uuid = UUID.randomUUID();
        String finalPath = fileDirectory + uuid + file.getOriginalFilename();

        Image image = new Image();
        User user = userDao.findById(userId).orElseThrow(() -> new IdNotFountException("ID not find"));
        image.setUserImages(user);

        List<ImageCategory> imageCategoryList = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            Long idForCheck = categories.get(i);
            ImageCategory imageCategoryResult = imageCategoryDao.findById(idForCheck).orElseThrow(() -> new IdNotFountException("ID not find"));
            imageCategoryList.add(imageCategoryResult);
        }

        image.setCategories(imageCategoryList);
        image.setAlbums(null);
        image.setDirectory(finalPath);
        imageDao.save(image);


        return sendPhotoAsArray(file, finalPath);
    }


    @SneakyThrows
    private byte[] sendPhotoAsArray(MultipartFile file, String path) {
        File myFile = new File(path);
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
        byte[] array = Files.readAllBytes(Paths.get(path));
        return array;
    }

    @Override
    public ImageDtoToAlbum addToAlbum(ImageDtoToAlbum imageDto) {
        Image image = imageDao.findById(imageDto.getIdImage()).orElseThrow(() -> new IdNotFountException("ID not find"));
        Album album = albumDao.findById(imageDto.getIdAlbum()).orElseThrow(() -> new IdNotFountException("ID not find"));

        List<Image> images = album.getImages();
        images.add(image);

        imageDao.save(image);
        albumDao.save(album);

        return imageDto;
    }

    @Override
    public void delete(Long id) {
        imageDao.deleteById(id);
    }

    @Override
    public ImageDto getById(Long id) {
        return modelMapper.map(
                imageDao.findById(id).orElseThrow(() -> new IdNotFountException("ID not find")),
                ImageDto.class);
    }

    @Override
    public List<ImageDto> getAll() {
        List<Image> images = (List<Image>) imageDao.findAll();
        List<ImageDto> result = images
                .stream()
                .map(profile -> modelMapper.map(profile, ImageDto.class))
                .collect(Collectors.toList());
        return result;
    }
}