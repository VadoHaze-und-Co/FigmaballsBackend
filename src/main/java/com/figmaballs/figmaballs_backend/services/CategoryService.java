package com.figmaballs.figmaballs_backend.services;

import com.figmaballs.figmaballs_backend.entities.CategoryEntity;
import com.figmaballs.figmaballs_backend.entities.TicketEntity;
import com.figmaballs.figmaballs_backend.repos.CategoryRepository;
import com.figmaballs.figmaballs_backend.repos.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
        loadCategories();
    }

    private void loadCategories() {
        String[] categories = new String[] {"Vertrieb", "Marketing", "Finanzen", "Personalwesen", "Kundendienst", "Forschung und Entwicklung", "Produktion", "Einkauf", "Qualitätskontrolle", "IT (Informationstechnologie)", "Recht", "Logistik", "Geschäftsentwicklung", "Öffentlichkeitsarbeit", "Projektmanagement", "Facility Management", "Compliance", "Risikomanagement", "Humanressourcen", "Beschaffung", "Buchhaltung", "Verwaltung", "Technischer Support", "Design und Kreativität", "Werbung", "Produktmanagement", "Lagerhaltung", "Datenschutz", "Umweltmanagement", "Schulung und Entwicklung", "Unternehmenskommunikation", "Interne Revision", "Geschäftsanalyse", "Gesundheit und Sicherheit", "Vertragsmanagement", "Informationssicherheit", "Softwareentwicklung", "Hardwareentwicklung", "Unternehmensstrategie", "Wissensmanagement", "Verkaufsförderung", "Kundenbeziehungsmanagement", "Produktionsplanung", "Lieferkettenmanagement", "Innovationsmanagement", "Kundenbindung", "Geschäftspartnerschaften", "F & E-Beratung", "Geschäftsprozessoptimierung", "Projektsteuerung und -überwachung"};
        for (var category : categories) {
            CategoryEntity entity = new CategoryEntity();
            entity.setName(category);
            this.repository.save(entity);
        }
    }

    public CategoryEntity create(CategoryEntity entity) {
        return this.repository.save(entity);
    }

    public CategoryEntity update(CategoryEntity newEntity) {
        CategoryEntity entityToUpdate = this.readById(newEntity.getId());
        entityToUpdate.setName(newEntity.getName());
        return this.repository.save(entityToUpdate);
    }

    public List<CategoryEntity> readAll() {
        var listOfCategories = this.repository.findAll();
        listOfCategories.sort(new Comparator<CategoryEntity>() {
            @Override
            public int compare(CategoryEntity o1, CategoryEntity o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        });
        return listOfCategories;
    }

    public CategoryEntity readById(long id) {
        return this.repository.getOne(id);
    }
}
