package org.processing.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private EventType eventType;
    private String value;
    private LocalDate eventDate;
    private String notes;

    @Override
    public String toString() {
        return "eventType=" + eventType +
                ", value='" + value + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}
