package ru.innovat.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verificationtoken")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

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
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, VerificationToken.EXPIRATION);
        cal.getTime();
        expiryDate = cal.getTime();
    }

}