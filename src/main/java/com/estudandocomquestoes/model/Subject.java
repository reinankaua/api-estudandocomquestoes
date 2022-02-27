package com.estudandocomquestoes.model;

import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "questoes")
public class Subject {

    @Id
    private String id;

    @Nullable
    @Field
    private String subjectMatter;

    @Nullable
    @Field
    private String question;

    @Nullable
    @Field
    private String []alternative;

    @Nullable
    @Field
    private String correct;
}
