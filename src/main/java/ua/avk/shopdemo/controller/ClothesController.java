package ua.avk.shopdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.avk.shopdemo.domain.entity.Clothes;
import ua.avk.shopdemo.domain.enums.ClothesType;
import ua.avk.shopdemo.domain.enums.ColorType;
import ua.avk.shopdemo.domain.enums.OfficeType;
import ua.avk.shopdemo.services.IClothesService;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Controller by the clothes
 */
@Slf4j
@RestController
@RequestMapping("/clothes")
public class ClothesController {
    private IClothesService clothesService;

    @Autowired
    public ClothesController(IClothesService clothesService) {
        this.clothesService = clothesService;
    }

    /**
     * Get all clothes
     *
     * @return list
     */
    @GetMapping(value = "/all")
    public List<Clothes> getAll() {
        return clothesService.getAll();
    }

    /**
     * Get all types of the clothes
     *
     * @return list
     */
    @GetMapping("/types")
    public List<ClothesType> getClothesTypes() {
        return new ArrayList<>(EnumSet.allOf(ClothesType.class));
    }

    /**
     * Get all colors of the clothes
     *
     * @return list
     */
    @GetMapping("/colors")
    public List<ColorType> getColors() {
        return new ArrayList<>(EnumSet.allOf(ColorType.class));
    }

    /**
     * Create clothes
     *
     * @param clothes obj
     */
    @PostMapping
    public void add(@RequestBody @Validated Clothes clothes) {
        log.info("CLOTHES: " + clothes.toString());
        try {
            clothesService.saveOrUpdate(clothes);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Delete clothes by id
     *
     * @param id int
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        try {
            Clothes clothes = clothesService.getById(id);
            log.info("CLOTHES: " + clothes.toString());
            clothesService.delete(clothes);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Get all clothes by storage
     *
     * @return list
     */
    @GetMapping(value = "/all/storage")
    public List<Clothes> getAllByStorage() {
        try {
            return clothesService.getByOffice(OfficeType.STORAGE);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Get all clothes by shop
     *
     * @return list
     */
    @GetMapping(value = "/all/shop")
    public List<Clothes> getAllByShop() {
        try {
            return clothesService.getByOffice(OfficeType.SHOP);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * The clothes move in to the shop
     *
     * @return list
     */
    @GetMapping(value = "/move/{id}/toShop")
    public List<Clothes> toShop(@PathVariable int id) {
        try {
            Clothes clothes = clothesService.getById(id);
            clothes.setOfficeType(OfficeType.SHOP);
            //clothesService.saveOrUpdate(clothes);
            return clothesService.getByOffice(OfficeType.SHOP);

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * The clothes move in to the storage
     *
     * @return list
     */
    @GetMapping(value = "/move/{id}/toStorage")
    public List<Clothes> toStorage(@PathVariable int id) {
        try {
            Clothes clothes = clothesService.getById(id);
            clothes.setOfficeType(OfficeType.STORAGE);
            //clothesService.saveOrUpdate(clothes);
            return clothesService.getByOffice(OfficeType.STORAGE);

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }

    /**
     * Get all the types of offices
     *
     * @return list
     */
    @GetMapping("/office/types")
    public List<OfficeType> getOfficeType() {
        return new ArrayList<>(EnumSet.allOf(OfficeType.class));
    }
}
