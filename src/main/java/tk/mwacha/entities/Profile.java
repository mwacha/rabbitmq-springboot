package tk.mwacha.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import tk.mwacha.alloy.dto.ProfileDTO;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "profiles")
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted_at is null")
public class Profile implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "pg-uuid")
  @GenericGenerator(name = "pg-uuid", strategy = "uuid2")
  private UUID id;
  private String profileName;

  public ProfileDTO toDTO() {
    return ProfileDTO.builder()
        .id(id)
        .profileName(profileName)
        .build();
  }

  public static Profile of(ProfileDTO dto) {
    return Profile.builder()
        .id(dto.getId())
        .profileName(dto.getProfileName())
        .build();
  }
}
