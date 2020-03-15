package com.example.API.Universitate.dto.student;

import java.util.List;
import java.util.Objects;

public class DisplayStudentDTO {
    public Integer id;
    public String nume;
    public String prenume;
    public String email;
    public List<DisplayPromotionsForStudentDTO> listOfPromotions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisplayStudentDTO student = (DisplayStudentDTO) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(prenume, student.prenume) &&
                Objects.equals(email, student.email) &&
                Objects.equals(listOfPromotions, student.listOfPromotions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, email, listOfPromotions);
    }
}
