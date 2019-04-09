package ua.avk.shopdemo.repository.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.avk.shopdemo.domain.entity.Clothes;
import ua.avk.shopdemo.domain.enums.OfficeType;

import java.util.ArrayList;
import java.util.List;

/**
 * The services class by database in memory. Class implements the IDataBaseRepository
 */
@Configuration
class DataBaseRepository implements IDataBaseRepository {

    /**
     * Create bean for database in memory
     *
     * @return obj
     */
    @Bean
    @Scope(value = "singleton")
    public DataBase getDataBase() {
        return new DataBase();
    }


    /**
     * Save or update clothes
     *
     * @param clothes obj
     */
    public void save(Clothes clothes) {
        int id = this.getDataBase().getId() + 1;
        clothes.setId(id);
        this.getDataBase().getClothes().add(clothes);
        this.getDataBase().setId(id);
    }

    /**
     * Delete clothes
     *
     * @param clothes obj
     */
    public void delete(Clothes clothes) {
        this.getDataBase().getClothes().remove(clothes);
    }

    /**
     * Update clothes
     *
     * @param oldClothes obj
     * @param newClothes ojb
     */
    public void update(Clothes oldClothes, Clothes newClothes) {
        oldClothes.setColor(newClothes.getColor());
        oldClothes.setDescription(newClothes.getDescription());
        oldClothes.setOfficeType(newClothes.getOfficeType());
        oldClothes.setPrice(newClothes.getPrice());
        oldClothes.setSize(newClothes.getSize());
        oldClothes.setType(newClothes.getType());
    }

    /**
     * Get all clothes
     *
     * @return list
     */
    public List<Clothes> getAll() {
        return this.getDataBase().getClothes();
    }

    /**
     * Get all clothes by type of the office
     *
     * @param officeType obj
     * @return list
     */
    public List<Clothes> getByOffice(OfficeType officeType) {
        List<Clothes> list = new ArrayList<>();
        for (Clothes clothes : this.getDataBase().getClothes()) {
            if (clothes.getOfficeType() == officeType) {
                list.add(clothes);
            }
        }
        return list;
    }

    /**
     * Get clothes by ID
     *
     * @param id int
     * @return clothes obj
     */
    public Clothes getById(int id) {
        for (Clothes clothes : this.getDataBase().getClothes()) {
            if (clothes.getId() == id) {
                return clothes;
            }
        }
        return null;
    }

}
