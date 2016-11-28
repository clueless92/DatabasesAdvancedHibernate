package bg.tilchev.services;

import bg.tilchev.models.Medicament;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
public interface MedicamentService {

    void persist(Medicament medicament);

    long count();
}
