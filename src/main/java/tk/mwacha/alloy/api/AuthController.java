package tk.mwacha.alloy.api;

import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mwacha.configuration.CustomJdbcTokenStore;

@Controller
@RequestMapping(value="auth")
public class AuthController  {

	@Resource(name="tokenStore")
	private CustomJdbcTokenStore tokenStore;

	@Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

	@Autowired
    private JdbcTemplate jdbcTemplate;

	//@PreAuthorize("hasAuthority('Token_C')")
	/*@PostMapping(value = "/tokens")
	@ResponseBody
	public PaginationDTO getTokens(@RequestBody UsuarioTokenDTO dto) {
		return tokenStore.findTokensByClientId(dto.getClientId(), dto.getItens(), dto.getPage());
	}
*/
	//@PreAuthorize("hasAuthority('Token_REVO')")
    @PostMapping(value = "/revokeToken")
    public void revokeToken(@RequestBody String login) {

    	Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByUserName(login);

    	if(tokens != null) {
    		for(OAuth2AccessToken token : tokens) {
    			tokenServices.revokeToken(token.getValue());
    		}
    	}
    	//broadcastService.enviarMensagemBroadcast("TOKEN", login);
    }

    @DeleteMapping(value="/logout")
    @ResponseBody
    public void logout(HttpServletRequest request) {
    	String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(tokenId);
            tokenServices.revokeToken(tokenId);
          /*  BroadcastMessage message = new BroadcastMessage();
    		message.getMapaAtributos().put("userInfo", oAuth2Authentication.getName());
            //broadcastService.sendMessageTopic("logout", message);*/
        }
    }

}