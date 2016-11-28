package bg.tilchev.models;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */
@Entity
@Table(name = "users_albums")
@IdClass(UserAlbumId.class)
public class UserAlbum {

    @Id
    private long userId;
    
    @Id
    private long albumId;
    
    @Column(nullable = false)
    private boolean isOwner;

    @ManyToOne
    @JoinColumn(name = "userId", updatable = false, insertable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "albumId", updatable = false, insertable = false, referencedColumnName = "id")
    private Album album;

    public UserAlbum() {
        super();
    }

    public long getUserId() {
        return this.userId;
    }

    private void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    private void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public boolean isOwner() {
        return this.isOwner;
    }

    public void setRole(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
