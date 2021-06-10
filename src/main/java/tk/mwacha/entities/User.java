package tk.mwacha.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import tk.mwacha.alloy.dto.UserDTO;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Where(clause = "deleted_at is null")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pg-uuid")
    @GenericGenerator(name = "pg-uuid", strategy = "uuid2")
    private UUID id;
    private String userName;
    private String email;
    private String pass;


    @OneToOne(fetch = FetchType.LAZY)
    private Profile profile;

    public static User of(UserDTO dto) {
        return User.builder()
            .id(dto.getId())
            .userName(dto.getUserName())
            .email(dto.getEmail())
            .profile(Profile.of(dto.getProfile()))
            .build();
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
            .id(id)
            .userName(userName)
            .email(email)
            .profile(profile.toDTO())
            .build();
    }
}
