package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.promotion.CreatePromotionDTO;
import com.example.API.Universitate.dto.promotion.DisplayPromotionDTO;
import com.example.API.Universitate.dto.promotion.UpdatePromotionDTO;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.mapper.PromotionMapperImpl;
import com.example.API.Universitate.repository.CollegeRepository;
import com.example.API.Universitate.repository.EducationalOfferRepository;
import com.example.API.Universitate.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromotionService {
    private PromotionRepository promotionRepository;
    private EducationalOfferRepository educationalOfferRepository;
    private CollegeRepository collegeRepository;

    private PromotionMapperImpl promotionMapper;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, EducationalOfferRepository educationalOfferRepository, CollegeRepository collegeRepository, PromotionMapperImpl promotionMapper) {
        this.promotionRepository = promotionRepository;
        this.educationalOfferRepository = educationalOfferRepository;
        this.collegeRepository = collegeRepository;
        this.promotionMapper = promotionMapper;
    }

    public List<DisplayPromotionDTO> getPromotionsForEducationalOffer(int idEducOffer) {
        if (educationalOfferRepository.existsById(idEducOffer)) {
            List<PromotionEntity> promotions = promotionRepository.getAllPromotionsForEducationalOffer(idEducOffer);
            return promotions.stream()
                    .map(promotionEntity -> promotionMapper.toDisplayPromotionDTO(promotionEntity))
                    .collect(Collectors.toList());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista oferta educationala cu id-ul: " + idEducOffer);
    }

    public List<DisplayPromotionDTO> getPromotionsForCollege(int idCollege, Integer idEducLevel, Integer endYear) {
        if (collegeRepository.existsById(idCollege) && idEducLevel == null && endYear == null)
            return getPromotionsByCollege(idCollege);
        else if (collegeRepository.existsById(idCollege) && idEducLevel != null && endYear == null)
            return getPromotionsByCollegeAndEducationalLevel(idCollege, idEducLevel);
        else if (collegeRepository.existsById(idCollege) && idEducLevel != null && endYear != null)
            return getPromotionsByCollege_EducationalLevel_And_EndYear(idCollege, idEducLevel, endYear);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista facultate cu id-ul :" + idCollege);
    }

            private List<DisplayPromotionDTO> getPromotionsByCollege(int idCollege) {
                List<PromotionEntity> promotions = promotionRepository.getPromotionsByCollege(idCollege);
                return promotions.stream()
                        .map(promotionEntity -> promotionMapper.toDisplayPromotionDTO(promotionEntity))
                        .collect(Collectors.toList());
            }

            private List<DisplayPromotionDTO> getPromotionsByCollegeAndEducationalLevel(int idCollege, int idEducLevel) {
                List<PromotionEntity> promotions = promotionRepository.getPromotionsByCollegeAndEducationLevel(idCollege, idEducLevel);
                return promotions.stream()
                        .map(promotionEntity -> promotionMapper.toDisplayPromotionDTO(promotionEntity))
                        .collect(Collectors.toList());
            }

            private List<DisplayPromotionDTO> getPromotionsByCollege_EducationalLevel_And_EndYear(int idCollege, int idEducLevel, int endYear) {
                List<PromotionEntity> promotions = promotionRepository.getPromotionsByCollege_EducationalLevel_And_EndYear(idCollege, idEducLevel, endYear);
                return promotions.stream()
                        .map(promotionEntity -> promotionMapper.toDisplayPromotionDTO(promotionEntity))
                        .collect(Collectors.toList());
            }

    public void addPromotionToEducOffer(int idEducOffer, CreatePromotionDTO createPromotionDTO) {
        if (educationalOfferRepository.existsById(idEducOffer)) {
            EducationalOfferEntity educationalOfferEntity = educationalOfferRepository.getOne(idEducOffer);

            PromotionEntity promotionEntity = promotionMapper.toEntity(createPromotionDTO);
            promotionEntity.setEducationalOfferEntity(educationalOfferEntity);

            promotionRepository.save(promotionEntity);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista o oferta educationala cu id-ul: " + idEducOffer);
    }

    public void updatePromotion(int idPromotion, UpdatePromotionDTO updatePromotionDTO) {
        if (promotionRepository.existsById(idPromotion)) {
            PromotionEntity promotionEntity = promotionRepository.getOne(idPromotion);

            promotionEntity.setNrLocuriTaxa(updatePromotionDTO.nrLocuriTaxa);
            promotionEntity.setNrLocuriBuget(updatePromotionDTO.nrLocuriBuget);

            promotionRepository.save(promotionEntity);

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista promotia cu id-ul: " + idPromotion);
    }

}
