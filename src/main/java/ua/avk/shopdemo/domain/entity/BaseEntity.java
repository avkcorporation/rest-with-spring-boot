package ua.avk.shopdemo.domain.entity;


import java.io.Serializable;

/**
 * Base class for entities
 */
public abstract class BaseEntity implements Serializable {

    abstract public int getId();

    abstract public void setId(int id);
}
