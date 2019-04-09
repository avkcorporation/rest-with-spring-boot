package ua.avk.shopdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.avk.shopdemo.domain.entity.Clothes;
import ua.avk.shopdemo.domain.enums.ClothesType;
import ua.avk.shopdemo.domain.enums.ColorType;
import ua.avk.shopdemo.domain.enums.OfficeType;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {

    /**
     * Get clothes by type of the clothes from database
     *
     * @param type enum
     * @return list
     */
    @Query("SELECT r FROM Clothes r where r.type = ?1")
    List<Clothes> findByType(ClothesType type);

    /**
     * Get clothes by color of the clothes from database
     *
     * @param type enum
     * @return list
     */
    @Query("SELECT r FROM Clothes r where r.color = ?1")
    List<Clothes> findByColor(ColorType type);

    /**
     * Get clothes by type of the office from database
     *
     * @param type enum
     * @return list
     */
    @Query("SELECT r FROM Clothes r where r.officeType = ?1")
    List<Clothes> findByOfficeType(OfficeType type);
}
