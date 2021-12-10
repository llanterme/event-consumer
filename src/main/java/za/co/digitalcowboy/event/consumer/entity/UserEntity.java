package za.co.digitalcowboy.event.consumer.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "date_registered")
    private String dateRegistered;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "name")
    private String name;

    @Column(name = "social_network")
    private String socialNetwork;


}


