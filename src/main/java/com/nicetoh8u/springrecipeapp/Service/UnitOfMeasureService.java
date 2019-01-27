package com.nicetoh8u.springrecipeapp.Service;

import com.nicetoh8u.springrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
