package com.se.rental.service.impl;

import com.se.rental.entity.Picture;
import com.se.rental.repository.PictureRepository;
import com.se.rental.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Override
    public Picture getPictureById(long id) {
        return pictureRepository.findById(id).get();
    }

    @Override
    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void updatePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void deletePictureById(long id) {
        pictureRepository.deleteById(id);
    }
}
