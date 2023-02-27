package at.htl.control;

import at.htl.entity.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class PatientRepository implements PanacheRepository<Patient>{
    public Boolean checkSsn(String ssn) throws Exception {
        if (ssn.length() != 10)
            throw new Exception("The SSN "+ssn+" id does not match the required length");

        int checkNumber = ssn.charAt(3)-'0';
        int[] checkArray = new int[] { 3,7,9, 0, 5, 8, 4, 2, 1, 6 };
        int checkSum = 0;

        for(int i =0; i < checkArray.length; i++)
        {
            int actNumber = ssn.charAt(i) - '0';
            checkSum += actNumber * checkArray[i];
        }

        return checkSum % 11 ==  checkNumber;
    }

    public List<Patient> getAllByStation(Long stationId) {
        return this.getEntityManager().createQuery("SELECT p FROM Patient p WHERE p.station.id = :stationId", Patient.class)
                .setParameter("stationId", stationId)
                .getResultList();
    }
}
