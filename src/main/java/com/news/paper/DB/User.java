package com.news.paper.DB;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(of = { "id" })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IdName.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String username;

    private String password;

    @Transient
    private String password2;

    private boolean active;

   @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(EnumType.STRING)
    private Set<Role> roles;


   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name = "user_subscribers",
           joinColumns = @JoinColumn(name = "subscriber_id"),
           inverseJoinColumns = @JoinColumn(name = "channel_id")
   )
   @JsonIdentityReference
   @JsonIdentityInfo(
           generator = ObjectIdGenerators.PropertyGenerator.class,
           property = "id")
   @JsonView(Views.FullProfile.class)
   private Set<User> subscriptions = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscribers",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    @JsonIdentityReference
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonView(Views.FullProfile.class)
    private Set<User> subscribers = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
