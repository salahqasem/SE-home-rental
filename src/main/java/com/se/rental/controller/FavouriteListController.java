package com.se.rental.controller;

import com.se.rental.entity.dto.request.FavoriteDto;
import com.se.rental.service.FavouriteListService;
import com.se.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("/api/v1/favourite")
public class FavouriteListController {

    @Autowired
    FavouriteListService favouriteListService;

    @Autowired
    UserService userService;

//    @PostMapping({"/" , ""})
//    public ResponseEntity<String> createFavouriteList(@RequestBody FavoriteDto favoriteList) {
//        favouriteListService.addFavorite(favoriteList);
//        return ResponseEntity.ok("favourite list created successfully.");
//    }




    @GetMapping("")
    public List<FavoriteDto> getFavorites() {
        return favouriteListService.findFavorites();
    }

    @GetMapping("/alluserfavouriteprops")
    public List<Integer> allUserFavouriteProps() {
        return favouriteListService.allUserFavouriteProps();
    }

    @PostMapping("")
    public void addNewFavoriteList(@RequestBody FavoriteDto fav) {
        favouriteListService.addFavorite(fav);
    }

    @PostMapping("/addpropertytofavorite")
    public void addPropertyToFavorite(
                                      @RequestParam("listid") long id,
                                      @RequestParam("propertyid") long propertyid) {
        favouriteListService.addPropertyToFavorite( id, propertyid);
    }

    @DeleteMapping("/removepropertytofavorite")
    public void removePropertyToFavorite(
            @RequestParam("propertyid") long propertyid) {
        favouriteListService.removePropertyToFavorite(propertyid);
    }
}
