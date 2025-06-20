package com.social.media.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "socialProfile")
    //@JoinColumn(name = "social_user")
    //This will be column name , earlier it was userId & now you have better control on the name
    //of FK and it will social_user_id
    private SocialUser user;

    //When I am adding mappedBy , then I am saying the above relationship is mapped by socialProfile
    //field of the socialUser

    //The non-owning class/enitity has to make use of mappedBy to tell that hey this relationship
    //is managed by this particular field and dont create a unneccesary column.
}
