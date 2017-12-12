package com.epam.trainings.testauto.model;

import lombok.*;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("WeakerAccess")
public class Food {
    @XmlAttribute(required = true)
    private int id;
    private String name;
    private String price;
    private String description;
    private int calories;
}
