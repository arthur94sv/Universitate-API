package com.example.API.Universitate.dto.promotion;

import java.util.Objects;

public class GetPromotion {
    public Integer id;
    public String educationalOffer;
    public String educationalLevel;
    public int startYear;
    public int endYear;
    public int nrLocuriBuget;
    public int nrLocuriTaxa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPromotion that = (GetPromotion) o;
        return startYear == that.startYear &&
                endYear == that.endYear &&
                nrLocuriBuget == that.nrLocuriBuget &&
                nrLocuriTaxa == that.nrLocuriTaxa &&
                Objects.equals(id, that.id) &&
                Objects.equals(educationalOffer, that.educationalOffer) &&
                Objects.equals(educationalLevel, that.educationalLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, educationalOffer, educationalLevel, startYear, endYear, nrLocuriBuget, nrLocuriTaxa);
    }
}
