package at.htl.control;

import at.htl.entity.Symptom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class SymptomRepository implements PanacheRepository<Symptom> {
}
