package com.example.ecommerce.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users" , schema = "ecommerce" ,
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "password"})
}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)

public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id ;

    private String name;

    private String email;

    private String password;

    //    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdAt;

    //    @UpdateTimestamp
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

}
