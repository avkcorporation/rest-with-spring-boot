package ua.avk.shopdemo.repository.model;

import ua.avk.shopdemo.domain.entity.Clothes;
import ua.avk.shopdemo.domain.enums.OfficeType;

import java.util.List;

/**
 * The interface for database.
 */
public interface IDataBaseRepository {

    /**
     * Get DataBase object
     *
     * @return obj
     */
    //DataBase getDataBase();

    /**
     * Save new clothes
     *
     * @param clothes obj
     */
    void save(Clothes clothes);

    /**
     * Delete clothes
     *
     * @param clothes obj
     */
    void delete(Clothes clothes);

    /**
     * Update clothes
     *
     * @param oldClothes obj
     * @param newClothes ojb
     */
    void update(Clothes oldClothes, Clothes newClothes);

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
    List<Clothes> getByOffice(OfficeType officeType);

    /**
     * Get clothes by ID
     *
     * @param id int
     * @return clothes obj
     */
    Clothes getById(int id);

}
