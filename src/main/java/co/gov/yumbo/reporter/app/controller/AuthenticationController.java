package co.gov.yumbo.reporter.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import co.gov.yumbo.reporter.app.model.AuthToken;
import co.gov.yumbo.reporter.app.model.LoginUser;
import co.gov.yumbo.reporter.app.security.config.TokenProvider;


//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = {"http://192.168.0.12:4200","http://localhost:4200", "http://localhost:8080"})
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

    	try {
    		 final Authentication authentication = authenticationManager.authenticate(
    	                new UsernamePasswordAuthenticationToken(
    	                        loginUser.getUsername(),
    	                        loginUser.getPassword()
    	                )
    	        );
    	        SecurityContextHolder.getContext().setAuthentication(authentication);
    	        final String token = jwtTokenUtil.generateToken(authentication);
    	        return ResponseEntity.ok(new AuthToken(token));
		} catch (Exception e) {
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
       
    }
    
    @GetMapping(value="/refresh-token")
    public ResponseEntity<?> refreshToken() throws AuthenticationException {

 
        // yo creo que para el refresh solo se hace esa parte de ahi

        final String token = jwtTokenUtil.generateToken(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(new AuthToken(token));
    }
    

}
