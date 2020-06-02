package usersservice.model;


import usersservice.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="users")
public class UserEntity implements Serializable {
    private final long serialVersionUID = -7657361516407008422L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false, length=50)
    private String userName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=120, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String userId;

    @Column(nullable=false, unique=true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable=false)
    private Boolean deleted;

    public UserEntity() {
    }

    public UserEntity(long id, String firstName, String userName, String lastName, String email, String userId, String password, Gender gender, Boolean deleted) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.gender = gender;
        this.deleted = deleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public Boolean getDeleted() {
        return deleted;
    }

}
