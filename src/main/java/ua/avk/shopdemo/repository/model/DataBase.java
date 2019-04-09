package ua.avk.shopdemo.repository.model;

import lombok.Getter;
import lombok.Setter;
import ua.avk.shopdemo.domain.entity.Clothes;

import java.util.ArrayList;
import java.util.List;

/**
 * This class description the database of clothes
 */
@Getter
@Setter
class DataBase {
    /**
     * It's id for Clothes obj
     */
    private int id = 0;

    /**
     * List of clothes
     */
    private List<Clothes> clothes = new ArrayList();
}
