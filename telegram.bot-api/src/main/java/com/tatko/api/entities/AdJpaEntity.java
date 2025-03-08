package com.tatko.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * User entity.
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ADS")
public class AdJpaEntity {

    /**
     * ID.
     */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ad_generator")
    @SequenceGenerator(name = "ad_generator", sequenceName = "ad_seq",
            initialValue = 1, allocationSize = 1)
    private Long id;

    /**
     * First Name.
     */
    @Column(name = "AD", nullable = false)
    private String ad;

    /**
     * Delivered Timestamp.
     */
    @Column(name = "DELIVERED_TIME")
    private LocalDateTime deliveredTime;

    /**
     * Creating timestamp.
     */
    @Column(name = "CREATING_TIME", nullable = false)
    private LocalDateTime creatingTime;


}
