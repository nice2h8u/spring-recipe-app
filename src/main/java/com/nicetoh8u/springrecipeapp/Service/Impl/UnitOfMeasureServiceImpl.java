package com.nicetoh8u.springrecipeapp.Service.Impl;

import com.nicetoh8u.springrecipeapp.Repositories.UnitOfMeasureRepository;
import com.nicetoh8u.springrecipeapp.Service.UnitOfMeasureService;
import com.nicetoh8u.springrecipeapp.commands.IngredientCommand;
import com.nicetoh8u.springrecipeapp.commands.UnitOfMeasureCommand;
import com.nicetoh8u.springrecipeapp.convertes.UnitOfMeasureToUnitOfMeasureCommand;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    public Set<UnitOfMeasureCommand> listAllUoms() {

        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();
         unitOfMeasureRepository.findAll().iterator()
                 .forEachRemaining(unitOfMeasure
                         -> unitOfMeasureCommands.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure)));

        return unitOfMeasureCommands;

//Or it can be found such like this
//        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(),false)
//                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
//                .collect(toSet());

    }




}
