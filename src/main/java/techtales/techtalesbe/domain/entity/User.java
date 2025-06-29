package techtales.techtalesbe.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kakaoId;

    private String userName;

    @Column(unique = true)
    private String nickName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String password;

    private String profile_image;

    private String birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_name") // FK 컬럼 이름
    private Role role;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public User(String userName, String nickName, String email, String phoneNumber, String password, String birth, Role role) {
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.birth = birth;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
