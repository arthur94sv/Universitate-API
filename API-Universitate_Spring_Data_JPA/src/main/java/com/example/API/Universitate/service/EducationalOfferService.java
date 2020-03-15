package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.educationalOffer.DisplayEducationalOfferDTO;
import com.example.API.Universitate.dto.educationalOffer.CreateEducationalOfferDTO;
import com.example.API.Universitate.dto.educationalOffer.UpdateEducationalOfferDTO;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.mapper.EducationalOfferMapper;
import com.example.API.Universitate.mapper.EducationalOfferMapperImpl;
import com.example.API.Universitate.repository.CollegeRepository;
import com.example.API.Universitate.repository.EducationalOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EducationalOfferService {
    private CollegeRepository collegeRepository;
    private EducationalOfferRepository educationalOfferRepository;

    private EducationalOfferMapper educationalOfferMapper;

    @Autowired
    public EducationalOfferService(CollegeRepository collegeRepository,
                                   EducationalOfferRepository educationalOfferRepository,
                                   EducationalOfferMapperImpl educationalOfferMapper) {
        this.collegeRepository = collegeRepository;
        this.educationalOfferRepository = educationalOfferRepository;

        this.educationalOfferMapper = educationalOfferMapper;
    }

    public List<DisplayEducationalOfferDTO> getEducationalOffersForCollege(int idCollege) {
        if (collegeRepository.existsById(idCollege)) {
            List<EducationalOfferEntity> educationalOffers = educationalOfferRepository.getEducationalOffersForCollege(idCollege);
            return educationalOffers.stream()
                    .map(educationalOffer -> educationalOfferMapper.toDisplayEducationalOfferDTO(educationalOffer))
                    .collect(Collectors.toList());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Ofertele educationale nu au putut fi afisate doarece nu exista o facultatea cu id-ul:" + idCollege);
    }

    public void addEducationalOffer(int idCollege, CreateEducationalOfferDTO createEducationalOfferDTO) {
        if (collegeRepository.existsById(idCollege)) {
            CollegeEntity collegeEntity = collegeRepository.getOne(idCollege);

            EducationalOfferEntity educationalOfferEntity = educationalOfferMapper.toEntityFromCreateEducationalOfferDTO(createEducationalOfferDTO);
            educationalOfferEntity.setCollegeEntity(collegeEntity);

            educationalOfferRepository.save(educationalOfferEntity);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Oferta educationala nu a putut fi adaugata deoarece nu exista o facultatea cu id-ul:" + idCollege);
    }

    public void updateEducationalOffer(int idEducOffer, UpdateEducationalOfferDTO updateEducationalOfferDTO) {
        if (educationalOfferRepository.existsById(idEducOffer)) {
            EducationalOfferEntity educationalOfferToUpdate = educationalOfferRepository.getOne(idEducOffer);

            educationalOfferToUpdate.setName(updateEducationalOfferDTO.name);

            educationalOfferRepository.save(educationalOfferToUpdate);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista oferta educationala cu id-ul: " + idEducOffer);
    }

}
