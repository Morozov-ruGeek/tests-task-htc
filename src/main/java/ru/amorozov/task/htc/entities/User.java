package ru.amorozov.task.htc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(firstName).append(" ").append(lastName);
        return builder.toString();
    }
}
