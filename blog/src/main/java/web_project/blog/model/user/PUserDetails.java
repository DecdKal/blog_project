package web_project.blog.model.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;


public class PUserDetails extends User {

    private final UUID uuid;
    private final String nickName;

    public PUserDetails(
            UUID uuid,
            String nickName,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.uuid = uuid;
        this.nickName = nickName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNickName() {
        return nickName;
    }
}
