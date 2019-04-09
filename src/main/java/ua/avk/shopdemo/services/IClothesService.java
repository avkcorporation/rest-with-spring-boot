package ua.avk.shopdemo.services;

import ua.avk.shopdemo.domain.entity.Clothes;
import ua.avk.shopdemo.domain.enums.OfficeType;

import java.util.List;

public interface IClothesService {

    /**
     * Save or update clothes
     *
     * @param clothes obj
     */
    void saveOrUpdate(Clothes clothes) throws Exception;

    /**
     * Delete clothes
     *
     * @param clothes obj
     */
    void delete(Clothes clothes) throws Exception;

    /**
     * Get all clothes
     *
     * @return list
     */
    List<Clothes> getAll();

    /**
     * Get all clothes by type of the office
     *
     * @param officeType obj
     * @return list
     */
    List<Clothes> getByOffice(OfficeType officeType) throws Exception;

    /**
     * Get clothes by ID
     *
     * @param id int
     * @return clothes obj
     */
    Clothes getById(int id) throws Exception;
}
