package tk.mwacha.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import tk.mwacha.alloy.dto.PaginationDTO;
import tk.mwacha.alloy.dto.UserDTO;


public class CustomJdbcTokenStore extends JdbcTokenStore{

	private static final String DEFAULT_ACCESS_TOKEN_UPDATE_STATEMENT = "update oauth_access_token set expiration = ? where token_id = ?";
	private static final String DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT = "select user_name, expiration from oauth_access_token where client_id = ? limit ? offset ?";
	private static final String COUNT_DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT = "select count(*) from oauth_access_token where client_id = ? ";

	private final JdbcTemplate customJdbcTemplate;

	public CustomJdbcTokenStore(DataSource dataSource) {
		super(dataSource);
		this.customJdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		super.storeAccessToken(token, authentication);

		Date expiration = token.getExpiration();

		//update na tabela de token para atualizar a data
		customJdbcTemplate.update(
				DEFAULT_ACCESS_TOKEN_UPDATE_STATEMENT,
				new Object[] { expiration,  extractTokenKey(token.getValue())},
				new int[] { Types.TIMESTAMP, Types.VARCHAR});


	}

	public PaginationDTO findTokensByClientId(String clientId, Integer itemsPorPagina, Integer pagina) {

		Long totalRecord = customJdbcTemplate.queryForObject(COUNT_DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT, new Object[]{clientId}, Long.class);
		List<UserDTO> users = customJdbcTemplate.query(DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT, new UserRowMapper(), clientId, itemsPorPagina, ((pagina - 1) * itemsPorPagina) );

		return new PaginationDTO(totalRecord, users);
	}

	private final class UserRowMapper implements RowMapper<UserDTO> {
		public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return UserDTO.builder()
					.email(rs.getString("user_name"))
					.expiration(rs.getObject("expiration", LocalDate.class))
					.build();
		}
	}

}
