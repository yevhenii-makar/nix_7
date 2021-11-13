package com.yevheniimakar.beltcutting.model.auth;

import com.yevheniimakar.beltcutting.model.user.BeltCuttingUser;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID value;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private BeltCuttingUser user;

    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "expire_at", nullable = false)
    private OffsetDateTime expireAt;

    @SuppressWarnings("FieldMayBeFinal")
    @OneToMany(mappedBy = "next", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshToken> previous = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next")
    @Access(AccessType.PROPERTY)
    private RefreshToken next;

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }

    public BeltCuttingUser getUser() {
        return user;
    }

    public void setUser(BeltCuttingUser user) {
        this.user = user;
    }

    public OffsetDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(OffsetDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public OffsetDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(OffsetDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public RefreshToken getNext() {
        return next;
    }

    public List<RefreshToken> getPrevious() {
        return previous;
    }

    public void setNext(RefreshToken next) {
        this.next = next;
    }

}
