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
    private Integer host; // userId
    private String roomType; // SINGLE, DOUBLE
    private String status; // WAIT, PROGRESS, FINISH
}
