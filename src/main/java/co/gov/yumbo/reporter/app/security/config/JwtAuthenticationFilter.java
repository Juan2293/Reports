package co.gov.yumbo.reporter.app.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static co.gov.yumbo.reporter.app.constants.Constants.HEADER_STRING;
import static co.gov.yumbo.reporter.app.constants.Constants.TOKEN_PREFIX;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // esto se llena con lo que está en la constante dela clase Constants valor = Authorization
    	String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        //si header tiene algo y comienza con "Bearer"
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
        	
        //se cambia el Bearer por "" para dejar el token solo
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
            	//recupera el username del token
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        //loguearse
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        	// trae la información del usuario y los roles
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
//                System.out.println("**********************************************************");
//                System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//                System.out.println("**********************************************************");

            }
        }

        chain.doFilter(req, res);
    }
}