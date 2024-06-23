package com.bookstore.jpa.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;



public record BookRecordDTO (String title,
                            UUID publisherId,
                            Set<UUID> authorIds,
                            String reviewComment){



}
