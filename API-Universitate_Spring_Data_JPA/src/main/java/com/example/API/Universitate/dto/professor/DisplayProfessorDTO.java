package com.example.API.Universitate.dto.professor;

import java.util.Objects;

public class DisplayProfessorDTO {
    public Integer id;
    public String nume;
    public String prenume;
    public String email;
    public String grad;
    public String department;
    public String college;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisplayProfessorDTO professor = (DisplayProfessorDTO) o;
        return Objects.equals(id, professor.id) &&
                Objects.equals(nume, professor.nume) &&
                Objects.equals(prenume, professor.prenume) &&
                Objects.equals(email, professor.email) &&
                Objects.equals(grad, professor.grad) &&
                Objects.equals(department, professor.department) &&
                Objects.equals(college, professor.college);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, email, grad, department, college);
    }
}
