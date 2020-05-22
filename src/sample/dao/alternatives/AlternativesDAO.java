package sample.dao.alternatives;

import sample.Organization.AlternativesForMarks;
import sample.Organization.AlternativesForMarksProperty;

public interface AlternativesDAO
{
    void update(AlternativesForMarksProperty alternativeProperty);

    void insert(AlternativesForMarksProperty alternativeProperty);

    void delete(AlternativesForMarksProperty alternativeProperty);

    void getList();
}
