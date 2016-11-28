package bg.tilchev.models;

import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */


public class UserAlbumId implements Serializable {

    private Long userId;
    private Long albumId;

    public UserAlbumId() {
        super();
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public int hashCode() {
        return (int)(this.userId + this.albumId);
    }

    public boolean equals(Object object) {
        if (object instanceof UserAlbumId) {
            UserAlbumId otherId = (UserAlbumId) object;
            return Long.compare(otherId.albumId, this.albumId) == 0 &&
                   Long.compare(otherId.userId, this.userId) == 0;
        }
        return false;
    }
}
