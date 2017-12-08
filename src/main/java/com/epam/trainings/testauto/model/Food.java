package com.epam.trainings.testauto.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Food", propOrder = {"name", "price", "description", "calories"})
public class Food {
    @XmlAttribute(required = true)
    private int id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String price;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private int calories;

    //  set and get methods
}
