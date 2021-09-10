package tk.mwacha.alloy.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.mwacha.entities.Profile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private UUID id;
    private String profileName;
}
