package com.se.rental.service;

import com.se.rental.entity.dto.request.FavoriteDto;
import com.se.rental.entity.FavoriteList;

import java.util.List;

public interface FavouriteListService {

    void createFavouriteList(FavoriteList favoriteList);

    List<FavoriteDto> findFavorites();

    void addFavorite( FavoriteDto fav);

    void addPropertyToFavorite( long id, long propId);

    List<Integer> allUserFavouriteProps();

    void removePropertyToFavorite(long propertyid);
}
