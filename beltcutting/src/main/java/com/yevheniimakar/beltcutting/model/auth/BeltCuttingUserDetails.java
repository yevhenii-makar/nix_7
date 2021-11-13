package com.yevheniimakar.beltcutting.model.auth;




import com.yevheniimakar.beltcutting.model.UserStatus;
import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;
import org.springframework.security.core.userdetails.User;

import java.util.EnumSet;

public class BeltCuttingUserDetails extends User {

    private final BeltCuttingUser source;

    public BeltCuttingUserDetails(BeltCuttingUser source) {
        super(source.getEmail(),
                source.getPassword(),
                source.getStatus() == UserStatus.ACTIVE,
                true,
                true,
                true,
                EnumSet.copyOf(source.getAuthorities().keySet())
        );
        this.source = source;
    }

    public BeltCuttingUser getSource() {
        return source;
    }
}
