package ua.avk.shopdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.avk.shopdemo.domain.entity.Clothes;
import ua.avk.shopdemo.domain.enums.OfficeType;
import ua.avk.shopdemo.repository.model.IDataBaseRepository;

import java.util.List;

/**
 * The services class by clothes. Class implements the IClothesService
 */
@Service
class ClothesService implements IClothesService {
    @Autowired
    IDataBaseRepository dataBaseRepository;


    /**
     * For unit tests
     *
     * @param dataBaseRepository obj
     */
    public void setDataBaseRepository(IDataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    /**
     * Save or update clothes
     *
     * @param clothes obj
     */
    public void saveOrUpdate(Clothes clothes) throws Exception {
        Clothes inRepo = dataBaseRepository.getById(clothes.getId());
        if (inRepo != null) {
            dataBaseRepository.update(inRepo, clothes);
            //throw new IllegalArgumentException("This record is present");
        } else {
            dataBaseRepository.save(clothes);
        }
    }

    /**
     * Delete clothes
     *
     * @param clothes obj
     */
    public void delete(Clothes clothes) throws Exception {
        dataBaseRepository.delete(clothes);
    }

    /**
     * Get all clothes
     *
     * @return list
     */
    public List<Clothes> getAll() {
        return dataBaseRepository.getAll();
    }

    /**
     * Get all clothes by type of the office
     *
     * @param officeType obj
     * @return list
     */
    public List<Clothes> getByOffice(OfficeType officeType) throws Exception {
        return dataBaseRepository.getByOffice(officeType);
    }

    /**
     * Get clothes by ID
     *
     * @param id int
     * @return clothes obj
     */
    public Clothes getById(int id) throws Exception {
        return dataBaseRepository.getById(id);
    }
}
