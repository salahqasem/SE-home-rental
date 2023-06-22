package com.se.rental.repository;

import com.se.rental.entity.FavoriteList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavouriteListRepository extends CrudRepository<FavoriteList, Long> {

    FavoriteList findByName(String name);
    FavoriteList findById(long id);


    @Query("select fp.id from User u join u.favoriteLists fl join fl.favoriteProperties fp where u.id =:userid")
    List<Integer> allUserFavouriteProps(long userid);

    @Transactional
    @Modifying
    @Query( nativeQuery = true ,value = "delete from favorite_property  \n" +
            "using  favorite_list  where favorite_list.id  = favorite_property.favorite_list_id \n" +
            "and   favorite_property.property_id  = :propertyid and favorite_list.user_id  = :userid")
    void deletePropertyToFavorite(long userid, long propertyid);
}

