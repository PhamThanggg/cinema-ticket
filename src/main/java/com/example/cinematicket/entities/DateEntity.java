package com.example.cinematicket.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class DateEntity {
    @Column(name = "created_date")
    private LocalDate createdDate;
    @PrePersist
    protected void onCreate(){
        createdDate = LocalDate.now();
    }
}
