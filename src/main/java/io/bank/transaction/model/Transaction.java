package io.bank.transaction.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class Transaction {
    private long id;
    private String fullName;
    private LocalDateTime date;
    private String strDate;
    private String type;
    private double amount;
}
