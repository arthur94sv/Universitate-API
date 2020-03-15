package com.example.API.Universitate.entities.joinTable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class PromotionStudentEntityId implements Serializable {
    @Column(name = "id_promotie")
    private Integer promotionId;

    @Column(name = "id_student")
    private Integer studentId;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionStudentEntityId that = (PromotionStudentEntityId) o;
        return Objects.equals(promotionId, that.promotionId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotionId, studentId);
    }
}
