package com.se.rental.service;

import com.se.rental.entity.Picture;

public interface PictureService {

    Picture getPictureById(long id);

    void savePicture(Picture picture);

    void updatePicture(Picture picture);

    void deletePictureById(long id);
}
