package prography.table_tennis.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_room_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Team team; // RED, BLUE

    public static UserRoom of(User user, Room room, Team team) {
        UserRoom userRoom = new UserRoom();
        userRoom.user = user;
        userRoom.room = room;
        userRoom.team = team;
        return userRoom;
    }
}
