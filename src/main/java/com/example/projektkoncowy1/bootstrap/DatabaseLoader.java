package com.example.projektkoncowy1.bootstrap;
import com.example.projektkoncowy1.model.Comment;
import com.example.projektkoncowy1.model.Link;
import com.example.projektkoncowy1.model.Role;
import com.example.projektkoncowy1.model.User;
import com.example.projektkoncowy1.repository.CommentRepository;
import com.example.projektkoncowy1.repository.LinkRepository;
import com.example.projektkoncowy1.repository.RoleRepository;
import com.example.projektkoncowy1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private LinkRepository linkRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private Map<String,User> users = new HashMap<>();

    public DatabaseLoader(LinkRepository linkRepository, CommentRepository commentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.linkRepository = linkRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        // add users and roles
        addUsersAndRoles();

        Map<String,String> links = new HashMap<>();
        links.put("1Link text","https://google.com");
        links.put("1Link text2","https://twitter.com");
        links.put("1Link text3","https://facebook.com");
        links.put("1Link text4","https://9gag.com");
        links.put("Link text5","https://interia.pl");
        links.put("Link text6 Link text6 Link text6 Link text6","https://onet.pl");
        links.put("Link text7 Link text7 Link text7","https://www.google.pl/maps");
        links.put("Link text8","https://play.google.com/store");

        links.forEach((k,v) -> {
            User u1 = users.get("user@gmail.com");
            User u2 = users.get("super@gmail.com");
            Link link = new Link(k,v);
            if(k.startsWith("Link")) {
                link.setUser(u1);
            } else {
                link.setUser(u2);
            }

            linkRepository.save(link);

            // we will do something with comments later
            Comment spring = new Comment("This is comment relating above link, subject1",link);
            Comment security = new Comment("This is comment relating above link, subject12",link);
            Comment pwa = new Comment("This is comment relating above link, subject1. Lore ipsum lore",link);
            Comment comments[] = {spring,security,pwa};
            for(Comment comment : comments) {
                commentRepository.save(comment);
                link.addComment(comment);
            }
        });

        long linkCount = linkRepository.count();
        System.out.println("Number of links in the database: " + linkCount );
    }

    private void addUsersAndRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User user = new User("user@gmail.com",secret,true,"Joe","User","joedirt");
        user.addRole(userRole);
        user.setConfirmPassword(secret);
        userRepository.save(user);
        users.put("user@gmail.com",user);

        User admin = new User("admin@gmail.com",secret,true,"John","Admin","masteradmin");
        admin.addRole(adminRole);
        admin.setConfirmPassword(secret);
        userRepository.save(admin);
        users.put("admin@gmail.com",admin);

        User master = new User("super@gmail.com",secret,true,"Super","User","superduper");
        master.addRoles(new HashSet<>(Arrays.asList(userRole,adminRole)));
        master.setConfirmPassword(secret);
        userRepository.save(master);
        users.put("super@gmail.com",master);
    }

}