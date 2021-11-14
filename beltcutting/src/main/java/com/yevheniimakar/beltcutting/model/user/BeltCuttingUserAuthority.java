package com.yevheniimakar.beltcutting.model.user;

import com.yevheniimakar.beltcutting.model.KnownAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class BeltCuttingUserAuthority {

    @Id
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private KnownAuthority id;

    @ManyToMany(mappedBy = "authorities")
    @SuppressWarnings("FieldMayBeFinal")
    private Set<BeltCuttingUser> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeltCuttingUserAuthority that = (BeltCuttingUserAuthority) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public KnownAuthority getId() {
        return id;
    }

    public void setId(KnownAuthority id) {
        this.id = id;
    }

    public Set<BeltCuttingUser> getUsers() {
        return users;
    }
}
