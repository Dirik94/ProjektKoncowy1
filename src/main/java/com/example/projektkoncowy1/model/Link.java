package com.example.projektkoncowy1.model;
import com.example.projektkoncowy1.service.BeanUtil;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class Link extends Auditable {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    @NotEmpty(message = "Please enter a title.")
    private String title;

    @NonNull
    @NotEmpty(message = "Please enter a url.")
    @URL(message = "Please enter a valid url.")
    private String url;

    @OneToMany(mappedBy = "link")
    private List<Comment> comments = new ArrayList<>();


    @ManyToOne
    private User user;

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(this.url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public String getPrettyTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        Date date = java.util.Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        return formatter.format(date);
    }
}