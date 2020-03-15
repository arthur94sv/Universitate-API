package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.college.CreateCollegeDTO;
import com.example.API.Universitate.dto.college.DisplayCollegeDTO;
import com.example.API.Universitate.dto.college.UpdateCollegeDTO;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.mapper.CollegeMapperImpl;
import com.example.API.Universitate.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CollegeService {
    private CollegeRepository collegeRepository;
    private CollegeMapperImpl collegeMapper;

    @Autowired
    public CollegeService(CollegeRepository collegeRepository,
                          CollegeMapperImpl collegeMapper) {
        this.collegeRepository = collegeRepository;
        this.collegeMapper = collegeMapper;
    }

    public List<DisplayCollegeDTO> getAllColleges() {
        List<CollegeEntity> colleges = collegeRepository.findAll(Sort.by("nume"));
        return colleges.stream()
                .map(collegeEntity -> collegeMapper.toDisplayCollegeDTO(collegeEntity))
                .collect(Collectors.toList());
    }

    public void addCollege(CreateCollegeDTO createCollegeDTO) {
        CollegeEntity collegeEntity = collegeMapper.toEntityFromCreateCollegeDTO(createCollegeDTO);
        collegeRepository.save(collegeEntity);
    }

    public void updateCollege(int idCollege, UpdateCollegeDTO updateCollegeDTO) {
        if (collegeRepository.existsById(idCollege)) {
            CollegeEntity collegeToUpdate = collegeRepository.getOne(idCollege);

            collegeToUpdate.setNume(updateCollegeDTO.nume);
            collegeToUpdate.setStrada(updateCollegeDTO.strada);
            collegeToUpdate.setNr(updateCollegeDTO.nr);
            collegeToUpdate.setTelefon(updateCollegeDTO.telefon);

            collegeRepository.save(collegeToUpdate);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu a fost gasita o facultate cu id-ul: " + idCollege);
    }

    public void deleteCollege(int idCollege) {
        if (collegeRepository.existsById(idCollege))
            collegeRepository.deleteById(idCollege);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu a fost gasita o facultate cu id-ul: " + idCollege);
    }

}

