package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.entities.UserEntity;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
        loadTickets();
    }

    private void loadTickets() {
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
            this.repository.save(entity);
        }
    }

    public TicketEntity create(TicketEntity entity) {
        return this.repository.save(entity);
    }

    public TicketEntity update(TicketEntity newEntity) {
        TicketEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setDescription(newEntity.getDescription());
        entityToUpdate.setStatus(newEntity.getStatus());
        entityToUpdate.setPriority(newEntity.getPriority());
        entityToUpdate.setFinishDate(newEntity.getFinishDate());
        entityToUpdate.setAppendIds(newEntity.getAppendIds());
        entityToUpdate.setCategoryIds(newEntity.getCategoryIds());
        return this.repository.save(entityToUpdate);
    }

    public List<TicketEntity> readAll() {
        return this.repository.findAll();
    }

    public TicketEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
