package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.trivial.TrivialCardEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;


    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<TrivialCardEntity> cards;

    
}
