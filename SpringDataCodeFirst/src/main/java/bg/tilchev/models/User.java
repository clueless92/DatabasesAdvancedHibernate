package bg.tilchev.models;


import bg.tilchev.annotations.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Todor Ilchev on 2016-11-03.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%+-]+\\.[A-Za-z]{2,6}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#*$%^&+=])(?=\\S+$).{8,}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    @Length(min = 4, max = 30)
    private String username;

    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    @Column(length = 50, nullable = false)
    @Password(minLength = 4,
            maxLength = 30,
            containsDigit = true,
            containsLowercase = true,
            containsUppercase = true,
            containsSpecialSymbols = true,
            message = "Invalid password!")
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] profilePicture;

    @Column
    private Date registeredOn;

    @Column
    private Date lastTimeLoggedIn;

    @Column
    private int age;

    @Column
    private Boolean deleted;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_town_id", referencedColumnName = "id")
    private Town homeTown;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_town_id", referencedColumnName = "id")
    private Town currentTown;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    private Set<User> friends;

    @OneToMany(mappedBy = "album", targetEntity = UserAlbum.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Album> albums;

    public User() {
        super();
        this.setFriends(new HashSet<>());
        this.setAlbums(new HashSet<>());
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
//        int len = username.length();
//        if (len < 4 || len > 30) {
//            throw new IllegalArgumentException("Invalid username");
//        }
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
//        if(!this.validateString(PASSWORD_PATTERN, password)) {
//            throw new IllegalArgumentException("Invalid password");
//        }
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
//        if(!this.validateString(EMAIL_PATTERN, email)) {
//            throw new IllegalArgumentException("Invalid email");
//        }
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        if (profilePicture.length > 1024 * 1024) {
            throw new IllegalArgumentException("Invalid picture");
        }
        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return this.lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Invalid age");
        }
        this.age = age;
    }

    public Boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Town getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Town getCurrentTown() {
        return this.currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Album> getAlbums() {
        if (this.albums == null) {
            this.albums = new HashSet<>();
        }
        return this.albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        this.getAlbums().add(album);
    }

    public Set<User> getFriends() {
        if (this.friends == null) {
            this.friends = new HashSet<>();
        }
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user) {
        this.getFriends().add(user);
    }

    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    private boolean validateString(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
