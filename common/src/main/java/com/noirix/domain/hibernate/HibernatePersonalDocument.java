package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.noirix.domain.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "user"
})
@ToString(exclude = {
        "user"
})
@Entity
@Table(name = "personal_doc")
public class HibernatePersonalDocument {

    @Id
    private Long id;

    @Column(name = "personal_id")
    private String personalId;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType = DocumentType.PASSPORT;

    @Column(name = "doc_number")
    private String docNumber;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private HibernateUser user;
}
