package project.blog.events.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    UserDetails extractUserInformation(String jwtToken);
}
