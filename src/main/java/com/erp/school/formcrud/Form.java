package com.erp.school.formcrud;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@Data
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formId;

    private String formName;

    private String formDescription;

    private String formType; // e.g., "registration", "feedback", etc.

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> fields; // Dynamic fields stored as JSON
}