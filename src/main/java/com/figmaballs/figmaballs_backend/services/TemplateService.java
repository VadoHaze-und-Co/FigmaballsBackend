package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.TicketCommentEntity;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.repos.TicketCommentRepository;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import com.figmaballs.figmaballs_backend.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TemplateService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketCommentRepository commentRepository;

    public TemplateService(TicketRepository ticketRepository, UserRepository userRepository, TicketCommentRepository commentRepository) throws InterruptedException {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;

        loadTickets();
        loadUsers();
        loadComments();
    }

    public void loadTickets() {
        String[] titles = new String[] {"Gerätstörung", "Request service for update server", "Patch-Update für Laptop #123456", "Terminänderung", "Beratung für Software-Update"};
        String desc = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
        int[] status = new int[] {0,1,2,3,-1};
        int[] priority = new int[] {-2,-1,0,1,2};
        long[] creationDate = new long[] {1713439536580L, 1713435536580L,1713439586140L, 1713434536580L,1713432452456L};
        Random random = new Random();
        for (int i = 0; i < titles.length; i++) {
            TicketEntity entity = new TicketEntity();
            entity.setTitle(titles[i]);
            entity.setDescription(desc);
            entity.setStatus(status[i]);
            entity.setPriority(priority[i]);
            entity.setCreationDate(creationDate[i]);
            //entity.setAssignments(new ArrayList<>());
            if (i == 1 || i == 3) {
                entity.setAppendIds("1");
            } else {
                entity.setAppendIds("");
            }
            entity.setCategoryIds(random.nextInt(20) + " " + random.nextInt(20));
            entity.setComments(new ArrayList<>());

            this.ticketRepository.save(entity);
        }
    }

    public void loadUsers() {
        String[] usernames = {"maxmustermann","mariamusterfrau","abcdefgh","reinert-zufall"};
        String[] firstName = {"Max","Maria","Admin","Reinert"};
        String[] lastNames = {"Mustermann","Musterfrau","ABCDEFGH","Zufall"};
        String[] emails = {"max.mustermann@email.com","maria.musterfrau@email.com","admin@email.com","reinert-zufall@email.com"};
        String[] address = {"Irgendwo Str. 1","Irgendwo Str. 1","Firmaweg 99","Zufallsstraße 123"};
        String[] postcodes = {"12345","12345","01254","98765"};
        String[] cities = {"Musterstadt","Musterstadt","Firmenstadt","Zufallsdorf"};
        String[] groupIds = {"2","2","5","1"};
        boolean[] isAdmins = {false,false,true,false};
        for (int i = 0; i < usernames.length; i++) {
            UserEntity user = new UserEntity();
            user.setUserName(usernames[i]);
            user.setFirstName(firstName[i]);
            user.setLastName(lastNames[i]);
            user.setEmailAddress(emails[i]);
            user.setAddress(address[i]);
            user.setPostcode(postcodes[i]);
            user.setCity(cities[i]);
            user.setUserGroupIds(groupIds[i]);
            user.setAdmin(isAdmins[i]);
            user.setCommentedTo(new ArrayList<>());
            /*if (i == 0 || i == 3) {
                if (i == 0) {
                    List<TicketCommentEntity> comments = new ArrayList<>();
                    comments.add(this.commentRepository.getOne(2L));
                    comments.add(this.commentRepository.getOne(4L));
                    user.setCommentedTo(comments);
                }
                else {
                    List<TicketCommentEntity> comments = new ArrayList<>();
                    comments.add(this.commentRepository.getOne(1L));
                    comments.add(this.commentRepository.getOne(3L));
                    user.setCommentedTo(comments);
                }
            }*/
            this.userRepository.save(user);
        }
    }

    public void loadComments() {
        String[] comments = {
                "Hallo, ich habe ein Problem mit mein PC, können Sie mir helfen?",
                "Hi, nö haha ciao",
                "Hallo, bitte fix mein PC und ich gebe dir ein Döner",
                "Hallönchen, alles klar alles fürs Döner :p"
        };
        long ticketId = 1;
        long[] userIds = {4L,1L,4L,1L};
        long[] commentDates = {1714637240274L,1714638380274L,1714639420274L,1714641620274L};
        for (int i = 0; i < comments.length; i++) {
            TicketCommentEntity commentEntity = new TicketCommentEntity();
            commentEntity.setId((long)i+1);
            commentEntity.setTicket(this.ticketRepository.getOne(ticketId));
            commentEntity.setUser(this.userRepository.getOne(userIds[i]));
            commentEntity.setComment(comments[i]);
            commentEntity.setCommentDate(commentDates[i]);
            commentEntity.setEdited(false);
            this.commentRepository.save(commentEntity);
        }
    }
}
