package fr.myhome.server.model.mother;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class MotherPersistent {
    
    @Id
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
}
