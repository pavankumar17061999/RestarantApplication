package com.pavan.userInfo.enitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "users") // optional custom table name
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId; // wrapper type for null safety

    @Column(nullable = false, unique = true)
    private String userName;

    @JsonIgnore // prevent leaking in API responses
    @Column(nullable = false)
    private String userPassword;

    private String address;

    private String city;
}
