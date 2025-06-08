package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ROLE_USER, ROLE_ADMIN
    @Column(nullable = false, unique = true)
    private String name;

    // 양방향 매핑(Optional)
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // 생성자
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    // getter / setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
