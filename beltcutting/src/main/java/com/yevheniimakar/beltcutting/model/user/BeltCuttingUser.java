package com.yevheniimakar.beltcutting.model.user;

import com.yevheniimakar.beltcutting.model.KnownAuthority;
import com.yevheniimakar.beltcutting.model.UserStatus;
import com.yevheniimakar.beltcutting.model.task.Task;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
public class BeltCuttingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @NaturalId(mutable = true)
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKey(name = "id")
    private Map<KnownAuthority, BeltCuttingUserAuthority> authorities = new EnumMap<>(KnownAuthority.class);

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "beltCuttingUser")
    private List<Task> tasks;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<KnownAuthority, BeltCuttingUserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Map<KnownAuthority, BeltCuttingUserAuthority> authorities) {
        this.authorities = authorities;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeltCuttingUser beltCuttingUser = (BeltCuttingUser) o;
        return email.equals(beltCuttingUser.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

}
