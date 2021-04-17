package ru.innovat.models.authorization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

import static ru.innovat.models.utils.CalculateExpiryDate.calculateDate;

@Entity
@Table(name = "verificationtoken")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_token;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_user")
    private AppUser user;

    @Column(name = "expirydate")
    private Date expiryDate;

    public VerificationToken(String token, AppUser user) {
        this.token = token;
        this.user = user;
        calculateExpiryDate();
    }

    private void calculateExpiryDate() {
        expiryDate = calculateDate();
    }
}