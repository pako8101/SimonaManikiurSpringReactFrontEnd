package kamenov.simonamanikiur.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kamenov.simonamanikiur.entity.enums.UserRoleEnum;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRoleEnum role) {
        this.role = role;
    }

    public @NotNull UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(@NotNull UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
