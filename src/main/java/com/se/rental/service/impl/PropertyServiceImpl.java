package com.se.rental.service.impl;


import com.se.rental.entity.*;
import com.se.rental.entity.dto.PropertiesByLocationDto;
import com.se.rental.entity.enums.UserRole;
import com.se.rental.repository.OfferRepository;
import com.se.rental.repository.PropertyRepository;
import com.se.rental.repository.UserRepository;
import com.se.rental.service.PropertyService;
import com.se.rental.service.S3Service;
import com.se.rental.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ResourceLoader resourceLoader;
    private CriteriaBuilder criteriaBuilder;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private S3Service s3Service;

    @Override
    public Property getPropertyById(long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getLastTenProperties() {
        return offerRepository.lastTenRentedProperties();
    }

    @Override
    public void saveProperty(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public void updateProperty(Property property) {
        Property original = propertyRepository.findById(property.getId());

        original.setPrice(property.getPrice());
        original.setArea(property.getArea());
        original.setRooms(property.getRooms());
        original.setStatus(property.getStatus());
        original.setPropertyType(property.getPropertyType());

        propertyRepository.save(original);

    }

    @Override
    public void deletePropertyById(long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<String> saveFiles(MultipartFile[] files) throws IOException {
        Resource resource = new ClassPathResource("/static/");
        List<String> paths = new ArrayList<>();

        for (MultipartFile file : files) {
            paths.add(s3Service.uploadFile(file));
        }

        return paths;
    }

    @Override
    public List<PropertiesByLocationDto> getAllOwnerPropertiesViewByLocation() {
        User u = userRepo.findByEmail(Util.getLoggedInUserName());
        System.out.println(u.getId());
        return propertyRepository.getAllOwnerPropertiesViewByLocation(u.getId());
    }


    @Override
    public List<Property> findAllWithFilters(Map<String, String> searchCriteria) {
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        System.out.println("In criteria");

        CriteriaQuery<Property> query = criteriaBuilder.createQuery(Property.class);

        Root<Property> root = query.from(Property.class);
        Predicate predicate = getPredicate(searchCriteria, root);

        query.where(predicate);
        TypedQuery<Property> typedQuery = entityManager.createQuery(query);
        List<Property> results = typedQuery.getResultList();
        return results;
    }


    private Predicate getPredicate(Map<String, String> searchCriteria, Root<Property> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.get("title") != null && !searchCriteria.get("title").isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchCriteria.get("title") + "%"));
        }

        if (searchCriteria.get("propertyType") != null && !searchCriteria.get("propertyType").isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("propertyType"), PropertyType.valueOf(searchCriteria.get("propertyType"))));
        }

        if (searchCriteria.get("propertyStatus") != null && !searchCriteria.get("propertyStatus").isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("propertyStatus"), PropertyStatus.valueOf(searchCriteria.get("propertyStatus"))));
        }

        if (searchCriteria.get("maxPrice") != null && !searchCriteria.get("maxPrice").isEmpty()) {
            predicates.add(criteriaBuilder.le(root.get("price"), Integer.valueOf(searchCriteria.get("maxPrice"))));
        }

        if (searchCriteria.get("minPrice") != null && !searchCriteria.get("minPrice").isEmpty()) {
            predicates.add(criteriaBuilder.ge(root.get("price"), Integer.valueOf(searchCriteria.get("minPrice"))));
        }

        if (searchCriteria.get("area") != null && !searchCriteria.get("area").isEmpty()) {
            predicates.add(criteriaBuilder.le(root.get("area"), Integer.valueOf(searchCriteria.get("area"))));
        }

        if (searchCriteria.get("noOfRooms") != null && !searchCriteria.get("noOfRooms").isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("rooms"), Integer.valueOf(searchCriteria.get("noOfRooms"))));
        }

        if (searchCriteria.get("location") != null && !searchCriteria.get("location").isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("location")), "%" + searchCriteria.get("location").toUpperCase() + "%"));
        }
        if (searchCriteria.get("location") != null && !searchCriteria.get("location").isEmpty()) {
            predicates.add(criteriaBuilder.le(root.get("location"), Integer.valueOf(searchCriteria.get("location"))));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    @Override
    public List<Property> findPropertiesForLoggedInUser() {
        if (!Util.loggedInUserHasRole(UserRole.ADMIN.name())) {
            User u = userRepo.findByEmail(Util.getLoggedInUserName());
            return u.getProperties();
        }
        return findAll();
    }


    @Override
    public List<Property> findAll() {
        List<Property> res = new ArrayList();
        Util.addAll(res, propertyRepository.findAll().iterator());
        return res;
    }


    @Override
    public Property findById(long id) {
        return modelMapper.map(propertyRepository.findById(id), Property.class);
    }


    @Override
    public void updateStatus(long id, PropertyStatus status) {
        Property property = propertyRepository.findById(id);
        property.setStatus(status);
        propertyRepository.save(property);
    }


    public List<Offer> getOffers(long productId) {
        Property property = propertyRepository.findById(productId);
        return property.getOffers();
    }


    @Override
    public void cancelContingency(long id) {
        Property property = propertyRepository.findById(id);
        if (property.getStatus().equals(PropertyStatus.CONTINGENT)) {
            property.setStatus(PropertyStatus.PENDING);
        }
        propertyRepository.save(property);
    }

    @Override
    public boolean updateClicks(long id) {
        try {
            Property property = propertyRepository.findById(id);
            property.setClickCount(property.getClickCount() + 1);
            propertyRepository.save(property);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
