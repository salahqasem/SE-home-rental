package com.se.rental.service.impl;

import com.se.rental.repository.FavouriteListRepository;
import com.se.rental.repository.PropertyRepository;
import com.se.rental.repository.UserRepository;
import com.se.rental.entity.FavoriteList;
import com.se.rental.entity.Property;
import com.se.rental.entity.User;
import com.se.rental.entity.dto.request.FavoriteDto;
import com.se.rental.service.FavouriteListService;
import com.se.rental.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteListServiceImpl implements FavouriteListService {

    @Autowired
    FavouriteListRepository favouriteListRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PropertyRepository propertyRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createFavouriteList(FavoriteList favoriteList) {
        User user = userRepo.findByEmail(Util.getLoggedInUserName());
        favoriteList.setUser(user);
        favouriteListRepository.save(favoriteList);
    }




    @Override
    public List<FavoriteDto> findFavorites() {
        User user = userRepo.findByEmail(Util.getLoggedInUserName());
        //User user = userRepo.findByEmail(userDetails.getUsername());
        return user.getFavoriteLists().stream().map(f->modelMapper.map(f,FavoriteDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addFavorite(FavoriteDto fav) {
        User user = userRepo.findByEmail(Util.getLoggedInUserName());
        FavoriteList favWithSameName = favouriteListRepository.findByName(fav.getName());
        String username = user.getEmail();
        if (favWithSameName != null && favWithSameName.getUser().getEmail().equals(username)) {
            throw new RuntimeException("Sorry, you can not create favorite list with existing name");
        }

        FavoriteList favorite = modelMapper.map(fav, FavoriteList.class);
        favorite.setUser(user);
        favouriteListRepository.save(favorite);
    }

    @Override
    public void addPropertyToFavorite( long id, long propId) {
        User user = userRepo.findByEmail(Util.getLoggedInUserName());
        FavoriteList favorite = favouriteListRepository.findById(id);

        if (favorite.getFavoriteProperties().stream().anyMatch(p -> p.getId() == propId)) {
            throw new RuntimeException("This property is already added as favorite in this list");
        }
        Property property = propertyRepo.findById(propId);
        favorite.setUser(user);
        favorite.getFavoriteProperties().add(property);
        favouriteListRepository.save(favorite);
    }

    @Override
    public List<Integer> allUserFavouriteProps(){
        User user = userRepo.findByEmail(Util.getLoggedInUserName());
        return favouriteListRepository.allUserFavouriteProps(user.getId());
    }

    @Override
    public
    void removePropertyToFavorite(long propertyid){
        User user = userRepo.findByEmail(Util.getLoggedInUserName());
        favouriteListRepository.deletePropertyToFavorite(user.getId() , propertyid);
    }
}
