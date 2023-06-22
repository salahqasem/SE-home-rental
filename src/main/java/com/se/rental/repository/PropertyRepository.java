package com.se.rental.repository;

import com.se.rental.entity.Property;
import com.se.rental.entity.dto.PropertiesByLocationDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {


    @Query("SELECT p from Property p order by p.id desc Limit 10 ")
    List<Property> getLastTenProperties ();

    @Query("SELECT p from Property p where p.user.id = :userId")
    List<Property> getAlluserProperties (Long userId);

    @Query("SELECT  new com.se.rental.entity.dto.PropertiesByLocationDto(sum(p.clickCount)  , p.location ) from Property p where p.user.id = :userId group by p.location ")
    List<PropertiesByLocationDto> getAllOwnerPropertiesViewByLocation(Long userId);
    Property findById(long id);


    @Query(value = "select o.id from Property o where o.user_id=:id",nativeQuery = true)
    List<Long> findAllByUserId(long id);
}
