package tk.mwacha.alloy.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String userName;
	private String email;
	private ProfileDTO profile;
	private String newPassword;
	private LocalDate expiration;
}
