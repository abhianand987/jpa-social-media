package com.social.media.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_profile_id_in_userClass")
    private SocialProfile socialProfile;

    //Here user entity is the owner of the relationship

    //One user can have many Posts so represent it in a form of List
    @OneToMany(mappedBy = "socialUser")
    private List<SocialPost> socialPosts = new ArrayList<>();

    //Here 3rd table is automatically getting created i.e, SOCIAL_USER_POSTS having
    // SOCIAL_USER_ID & POSTS_ID as column

    @ManyToMany
    @JoinTable(
            name = "user_group" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
     )
    //@JoinColumn(name = "group_id_in_user_defined")
    private Set<SocialGroup> socialGroups = new HashSet<>();

    //We should make use of a set because we want unique values here and it does not make sense
    //for a user to join a same group twice
    //So user can be a part of group only once.

    /*
===============================
JPA Relationship Annotation Notes
===============================

1. ONE-TO-ONE RELATIONSHIP
---------------------------
- Annotation: @OneToOne + @JoinColumn
- Foreign Key: Stored in the owning entity's table
- Example:
    @OneToOne
    @JoinColumn(name = "profile_id") // FK in User table pointing to Profile
    private Profile profile;

2. ONE-TO-MANY RELATIONSHIP
---------------------------
- Annotation: @ManyToOne + @JoinColumn (on child entity)
- Foreign Key: Stored in the "many" side (child) table
- Example:
    @ManyToOne
    @JoinColumn(name = "user_id") // FK in Post table pointing to User
    private User user;

    // In User class (optional, inverse side)
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

3. MANY-TO-MANY RELATIONSHIP
----------------------------
- Annotation: @ManyToMany + @JoinTable
- Foreign Key: Stored in a separate join table
- @JoinTable defines:
    - name: Name of the join table
    - joinColumns: FK to the owning entity
    - inverseJoinColumns: FK to the other entity
- Example:
    @ManyToMany
    @JoinTable(
        name = "user_group", // Join table name
        joinColumns = @JoinColumn(name = "user_id"), // FK to User
        inverseJoinColumns = @JoinColumn(name = "group_id") // FK to Group
    )
    private Set<Group> groups;

    // In Group class (inverse side)
    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

===============================
Summary
===============================
| Relationship   | FK Location           | Annotation Used         |
|----------------|------------------------|--------------------------|
| One-to-One     | Owning entity's table  | @JoinColumn              |
| One-to-Many    | Child ("many") table   | @JoinColumn (on child)   |
| Many-to-Many   | Separate join table    | @JoinTable + @JoinColumn |

*/

}
