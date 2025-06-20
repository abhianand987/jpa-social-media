package com.social.media.models;

import jakarta.persistence.*;

@Entity
public class SocialPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SocialUser socialUser;

    //If we want to make Post entity/class as the owner of this relationship
    //then we need to explicitly mention mappedBy in the user class




}
