package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Entity
@Table(name = "albums")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long backGroundColour;

    @Column
    private boolean isPublic;

    @ManyToMany(mappedBy = "albums", targetEntity = Picture.class,
                fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Picture> pictures;

    @ManyToMany(mappedBy = "albums", targetEntity = Tag.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tag> tags;

    @OneToMany(mappedBy = "user", targetEntity = UserAlbum.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    public Album() {
        super();
        this.setPictures(new HashSet<>());
        this.setTags(new HashSet<>());
        this.setUsers(new HashSet<>());
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBackGroundColour() {
        return this.backGroundColour;
    }

    public void setBackGroundColour(Long backGroundColour) {
        this.backGroundColour = backGroundColour;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean aPublic) {
        this.isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        if (this.pictures == null) {
            this.pictures = new HashSet<>();
        }
        return this.pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public void addPicture(Picture picture) {
        this.getPictures().add(picture);
    }

    public Set<User> getUsers() {
        if (this.users == null) {
            this.users = new HashSet<>();
        }
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.getUsers().add(user);
    }

    public Set<Tag> getTags() {
        if(this.tags == null) {
            this.tags = new HashSet<>();
        }
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.getTags().add(tag);
    }
}
