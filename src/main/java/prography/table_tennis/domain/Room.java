package prography.table_tennis.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;
    private String title;

    @Enumerated(EnumType.STRING)
    private RoomType roomType; // SINGLE, DOUBLE
    @Enumerated(EnumType.STRING)
    private RoomStatus status; // WAIT, PROGRESS, FINISH

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host")
    private User host; // userId

    @OneToOne(mappedBy = "room")
    private UserRoom userRoom;

    public static Room of(String title, User host, RoomType roomType) {
        Room room = new Room();
        room.title = title;
        room.host = host;
        room.roomType = roomType;
        room.status = RoomStatus.WAIT;
        return room;
    }

}
